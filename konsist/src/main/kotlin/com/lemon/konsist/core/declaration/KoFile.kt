package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.cache.KoDeclarationCache
import com.lemon.konsist.core.const.KoModifier
import com.lemon.konsist.core.declaration.provider.KoClassProvider
import com.lemon.konsist.core.declaration.provider.KoCompanionObjectProvider
import com.lemon.konsist.core.declaration.provider.KoDeclarationProvider
import com.lemon.konsist.core.declaration.provider.KoDeclarationProviderUtil
import com.lemon.konsist.core.declaration.provider.KoFunctionProvider
import com.lemon.konsist.core.declaration.provider.KoInterfaceProvider
import com.lemon.konsist.core.declaration.provider.KoObjectProvider
import com.lemon.konsist.core.declaration.provider.KoPropertyProvider
import com.lemon.konsist.util.PackageHelper
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList
import org.jetbrains.kotlin.psi.KtTypeAlias
import java.io.File

class KoFile private constructor(private val ktFile: KtFile) :
    KoNamedDeclaration(ktFile),
    KoDeclarationProvider,
    KoClassProvider,
    KoInterfaceProvider,
    KoObjectProvider,
    KoCompanionObjectProvider,
    KoPropertyProvider,
    KoFunctionProvider {

    override val name = ktFile.name.split("/").last()

    val imports by lazy {
        val ktImportDirectives = ktFile
            .children
            .filterIsInstance<KtImportList>()
            .first()
            .children
            .filterIsInstance<KtImportDirective>()

        ktImportDirectives.map { KoImport.getInstance(it) }
    }

    val path by lazy {
        ktFile
            .virtualFilePath
            .replace("//", "/")
    }

    val projectPath by lazy {
        val mainPath = File("")
            .absoluteFile
            .path
            .substringBeforeLast('/')

        path.removePrefix(mainPath)
    }

    val packageDirective by lazy {
        if (ktFile.packageDirective?.qualifiedName == "") {
            null
        } else {
            ktFile.packageDirective?.let { KoPackage.getInstance(it) }
        }
    }

    val typeAliases by lazy {
        ktFile
            .children
            .filterIsInstance<KtTypeAlias>()
            .map { KoTypeAlias.getInstance(it) }
    }

    val annotations by lazy {
        ktFile
            .annotationEntries
            .map { KoAnnotation.getInstance(it) }
    }

    fun hasAnnotation(name: String) = annotations
        .any { it.fullyQualifiedName?.substringAfterLast(".") == name }

    inline fun <reified T> hasAnnotation(): Boolean {
        val qualifiedName = T::class.qualifiedName ?: return false

        return annotations.any { it.fullyQualifiedName?.contains(qualifiedName) ?: false }
    }

    override fun declarations(modifiers: List<KoModifier>, includeNested: Boolean, includeLocal: Boolean): List<KoDeclaration> =
        KoDeclarationProviderUtil.getKoDeclarations(ktFile, modifiers, includeNested, includeLocal)

    fun hasImport(name: String) = imports.any { PackageHelper.resideInPackage(name, it.name) }

    override fun equals(other: Any?): Boolean = other is KoFile && path == other.path

    override fun hashCode(): Int = 31 * 7 + path.hashCode()

    companion object {
        private val cache = KoDeclarationCache<KoFile>()

        fun getInstance(ktFile: KtFile) = cache.getOrCreateInstance(ktFile) { KoFile(ktFile) }
    }
}
