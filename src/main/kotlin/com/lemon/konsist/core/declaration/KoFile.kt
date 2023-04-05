package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.const.KoModifier
import com.lemon.konsist.core.declaration.provider.KoClassProvider
import com.lemon.konsist.core.declaration.provider.KoCompanionObjectProvider
import com.lemon.konsist.core.declaration.provider.KoDeclarationProvider
import com.lemon.konsist.core.declaration.provider.KoDeclarationProviderUtil
import com.lemon.konsist.core.declaration.provider.KoFunctionProvider
import com.lemon.konsist.core.declaration.provider.KoInterfaceProvider
import com.lemon.konsist.core.declaration.provider.KoObjectProvider
import com.lemon.konsist.core.declaration.provider.KoPropertyProvider
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList

class KoFile private constructor(private val ktFile: KtFile) :
    KoNamedDeclaration(ktFile),
    KoDeclarationProvider,
    KoClassProvider,
    KoInterfaceProvider,
    KoObjectProvider,
    KoCompanionObjectProvider,
    KoPropertyProvider,
    KoFunctionProvider {
    val imports by lazy {
        val ktImportDirectives = ktFile
            .children
            .filterIsInstance<KtImportList>()
            .first()
            .children
            .filterIsInstance<KtImportDirective>()

        ktImportDirectives.map { KoImport.getInstance(it) }
    }

    val path by lazy { ktFile.virtualFilePath }

    val packageDirective by lazy {
        if (ktFile.packageDirective?.qualifiedName == "") {
            null
        } else {
            ktFile.packageDirective?.let { KoPackage.getInstance(it) }
        }
    }

    override fun declarations(modifiers: List<KoModifier>, includeNested: Boolean, includeLocal: Boolean): List<KoDeclaration> =
        KoDeclarationProviderUtil.getKoDeclarations(ktFile, modifiers, includeNested, includeLocal)

    fun hasImport(name: String) = imports.any { it.name == name }

    companion object {
        private val cache = KoDeclarationCache<KoFile>()
        fun getInstance(ktFile: KtFile) = cache.getOrCreateInstance(ktFile) { KoFile(ktFile) }
    }
}
