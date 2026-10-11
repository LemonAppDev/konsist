package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.declaration.KoFileDeclarationCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.diagnostics.DiagnosticUtils
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtImportList
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtPackageDirective
import org.jetbrains.kotlin.psi.KtUserType
import org.jetbrains.kotlin.psi.psiUtil.collectDescendantsOfType

/**
 * Provides dependencies of files: imports and fully qualified references written in code without an import.
 *
 * Konsist does not resolve types, so a qualified name (e.g. `com.app.data.Repository()`) is treated as a reference
 * only when its qualifier is a package declared by one of the [files]. This way property chains like `data.size`
 * are not mistaken for references to a `data` package.
 */
internal class FileDependencies(
    files: List<KoFileDeclaration>,
) {
    private val packageNames = files.mapNotNullTo(mutableSetOf()) { it.packagee?.name }

    private val dependenciesByFile = mutableMapOf<KoFileDeclaration, List<FileDependency>>()

    operator fun get(file: KoFileDeclaration): List<FileDependency> =
        dependenciesByFile.getOrPut(file) {
            val imports = file.imports.map { FileDependency(FileDependencyType.IMPORT, it.name, it.location) }
            imports + getReferences(file)
        }

    private fun getReferences(file: KoFileDeclaration): List<FileDependency> {
        val ktFile = (file as? KoFileDeclarationCore)?.ktFile ?: return emptyList()

        return ktFile
            .children
            .filterNot { it is KtPackageDirective || it is KtImportList }
            .flatMap { child ->
                child.collectDescendantsOfType<PsiElement> { it is KtUserType || it is KtDotQualifiedExpression }
            }.mapNotNull { element ->
                getQualifiedNameSegments(element)
                    ?.let { getReferenceName(it) }
                    ?.let { name -> element to name }
            }.sortedBy { (element, _) -> element.textOffset }
            .map { (element, name) ->
                val lineAndColumn = DiagnosticUtils.getLineAndColumnInPsiFile(ktFile, element.textRange)
                FileDependency(FileDependencyType.REFERENCE, name, "${file.path}:${lineAndColumn.line}:${lineAndColumn.column}")
            }
    }

    /**
     * Returns name of the declaration referenced by qualified name segments, e.g. "com.app.data.Repository"
     * for `com.app.data.Repository.Companion.create`. The longest qualifier being a known package is used.
     */
    private fun getReferenceName(segments: List<String>): String? =
        (segments.size - 1 downTo 1)
            .firstOrNull { packageNames.contains(segments.take(it).joinToString(".")) }
            ?.let { packageSegmentCount -> segments.take(packageSegmentCount + 1).joinToString(".") }

    private fun getQualifiedNameSegments(element: PsiElement): List<String>? =
        when (element) {
            // Only the outermost type, e.g. `com.app.Repository` and not its qualifier `com.app`
            is KtUserType -> if (element.parent is KtUserType) null else element.getSegments()

            is KtDotQualifiedExpression -> element.getSegments()

            else -> null
        }?.takeIf { it.size > 1 }

    private fun KtUserType.getSegments(): List<String>? {
        val name = referencedName ?: return null
        val qualifier = qualifier ?: return listOf(name)
        return qualifier.getSegments()?.plus(name)
    }

    /**
     * Returns segments of the chain of names starting at this expression, e.g. `com`, `app`, `Repository`
     * for `com.app.Repository().load()`. Only the expression holding the first name of the chain returns segments,
     * so each chain is processed once.
     */
    private fun KtDotQualifiedExpression.getSegments(): List<String>? {
        val firstName = (receiverExpression as? KtNameReferenceExpression)?.getReferencedName() ?: return null

        val selectorNames =
            generateSequence(this) { expression ->
                // Call ends the chain of names, e.g. `com.app.Repository().load()`
                if (expression.selectorExpression is KtCallExpression) {
                    null
                } else {
                    (expression.parent as? KtDotQualifiedExpression)?.takeIf { it.receiverExpression == expression }
                }
            }.map { it.selectorExpression.getSelectorName() }
                .takeWhile { it != null }
                .filterNotNull()

        return listOf(firstName) + selectorNames
    }

    private fun KtExpression?.getSelectorName(): String? =
        when (this) {
            is KtNameReferenceExpression -> getReferencedName()
            is KtCallExpression -> (calleeExpression as? KtNameReferenceExpression)?.getReferencedName()
            else -> null
        }
}
