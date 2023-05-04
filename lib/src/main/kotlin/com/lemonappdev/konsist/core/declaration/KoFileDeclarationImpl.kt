package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationProviderUtil
import com.lemonappdev.konsist.util.PackageHelper
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList
import org.jetbrains.kotlin.psi.KtTypeAlias
import kotlin.reflect.KClass

internal class KoFileDeclarationImpl private constructor(private val ktFile: KtFile) :
    KoNamedDeclarationImpl(ktFile),
    KoFileDeclaration {

    override val name = ktFile.name.split("/").last()

    override val imports by lazy {
        val ktImportDirectives = ktFile
            .children
            .filterIsInstance<KtImportList>()
            .first()
            .children
            .filterIsInstance<KtImportDirective>()

        ktImportDirectives.map { KoImportDeclarationImpl.getInstance(it) }
    }

    override val annotations by lazy {
        ktFile
            .annotationEntries
            .map { KoAnnotationDeclarationImpl.getInstance(it) }
    }

    override val packagee by lazy {
        if (ktFile.packageDirective?.qualifiedName == "") {
            null
        } else {
            ktFile.packageDirective?.let { KoPackageDeclarationImpl.getInstance(it) }
        }
    }

    override val typeAliases by lazy {
        ktFile
            .children
            .filterIsInstance<KtTypeAlias>()
            .map { KoTypeAliasDeclarationImpl.getInstance(it) }
    }

    override fun declarations(
        modifiers: List<KoModifier>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoDeclarationImpl> =
        KoDeclarationProviderUtil.getKoDeclarations(ktFile, modifiers, includeNested, includeLocal)

    override fun hasAnnotations(vararg names: String) = when {
        names.isEmpty() -> annotations.isNotEmpty()
        else -> names.all { hasAnnotationNameOrAnnotationFullyQualifyName(it) }
    }

    private fun hasAnnotationNameOrAnnotationFullyQualifyName(name: String) = annotations.any {
        it.fullyQualifiedName.substringAfterLast(".") == name || it.fullyQualifiedName == name || it.text == name
    }

    override fun hasAnnotationsOf(vararg names: KClass<*>) = names.all {
        annotations.any { annotation -> annotation.fullyQualifiedName == it.qualifiedName }
    }

    override fun hasPackage(name: String) = packagee
        ?.qualifiedName
        ?.let { PackageHelper.resideInPackage(name, it) } ?: false

    override fun hasImports(vararg names: String) = when {
        names.isEmpty() -> imports.isNotEmpty()
        else -> names.all {
            imports.any { import -> PackageHelper.resideInPackage(it, import.name) }
        }
    }

    override fun hasTypeAliases(vararg names: String) = when {
        names.isEmpty() -> typeAliases.isNotEmpty()
        else -> names.all {
            typeAliases.any { typeAlias -> typeAlias.name == it }
        }
    }

    override fun equals(other: Any?): Boolean = other is KoFileDeclarationImpl && filePath == other.filePath

    override fun hashCode(): Int = 31 * 7 + filePath.hashCode()

    internal companion object {
        private val cache = KoDeclarationCache<KoFileDeclarationImpl>()

        internal fun getInstance(ktFile: KtFile) = cache.getOrCreateInstance(ktFile) { KoFileDeclarationImpl(ktFile) }
    }
}
