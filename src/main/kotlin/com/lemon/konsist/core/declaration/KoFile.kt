package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.util.KoDeclarationUtil
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList

class KoFile(private val ktFile: KtFile) : KoNamedDeclaration(ktFile), KoDeclarationProvider {
    val imports by lazy {
        val ktImportDirectives = ktFile
            .children
            .filterIsInstance<KtImportList>()
            .first()
            .children
            .filterIsInstance<KtImportDirective>()

        ktImportDirectives.map { KoImport(it) }
    }

    val packageDirective by lazy {
        if (ktFile.packageDirective?.qualifiedName == "") {
            null
        } else {
            ktFile.packageDirective?.let { KoPackage(it) }
        }
    }

    override fun declarations(includeNested: Boolean, includeLocal: Boolean): List<KoDeclaration> =
        KoDeclarationUtil.getKoDeclarations(ktFile, includeNested, includeLocal)

    fun hasImport(name: String) = imports.any { it.name == name }
}
