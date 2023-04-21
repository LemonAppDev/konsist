package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.const.KoModifier
import com.lemonappdev.konsist.core.declaration.provider.KoClassProvider
import com.lemonappdev.konsist.core.declaration.provider.KoCompanionObjectProvider
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationProvider
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationProviderUtil
import com.lemonappdev.konsist.core.declaration.provider.KoFunctionProvider
import com.lemonappdev.konsist.core.declaration.provider.KoInterfaceProvider
import com.lemonappdev.konsist.core.declaration.provider.KoObjectProvider
import com.lemonappdev.konsist.core.declaration.provider.KoPropertyProvider
import com.lemonappdev.konsist.util.PackageHelper
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

    val annotations by lazy {
        ktFile
            .annotationEntries
            .map { KoAnnotation.getInstance(it) }
    }

    val packagee by lazy {
        if (ktFile.packageDirective?.qualifiedName == "") {
            null
        } else {
            ktFile.packageDirective?.let { KoPackage.getInstance(it) }
        }
    }

    val imports by lazy {
        val ktImportDirectives = ktFile
            .children
            .filterIsInstance<KtImportList>()
            .first()
            .children
            .filterIsInstance<KtImportDirective>()

        ktImportDirectives.map { KoImport.getInstance(it) }
    }

    val typeAliases by lazy {
        ktFile
            .children
            .filterIsInstance<KtTypeAlias>()
            .map { KoTypeAlias.getInstance(it) }
    }

    override fun declarations(
        modifiers: List<KoModifier>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoDeclaration> =
        KoDeclarationProviderUtil.getKoDeclarations(ktFile, modifiers, includeNested, includeLocal)

    fun resideInPath(name: String) = PackageHelper.resideInPackage(name, path, '/')

    fun hasAnnotation(name: String) = annotations
        .any { it.fullyQualifiedName.substringAfterLast(".") == name || it.fullyQualifiedName == name }

    inline fun <reified T> hasAnnotation(): Boolean {
        val qualifiedName = T::class.qualifiedName ?: return false

        return annotations.any { it.fullyQualifiedName.contains(qualifiedName) }
    }

    fun hasPackage(name: String) = packagee
        ?.qualifiedName
        ?.let { PackageHelper.resideInPackage(name, it) } ?: false

    fun hasImport(name: String? = null) = when (name) {
        null -> imports.isNotEmpty()
        else -> imports.any { PackageHelper.resideInPackage(name, it.name) }
    }

    fun hasTypeAlias(name: String? = null) = when (name) {
        null -> typeAliases.isNotEmpty()
        else -> typeAliases.any { it.name == name }
    }

    override fun equals(other: Any?): Boolean = other is KoFile && path == other.path

    override fun hashCode(): Int = 31 * 7 + path.hashCode()

    companion object {
        private val cache = KoDeclarationCache<KoFile>()

        fun getInstance(ktFile: KtFile) = cache.getOrCreateInstance(ktFile) { KoFile(ktFile) }
    }
}
