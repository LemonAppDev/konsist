package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.api.KoModifier
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
import kotlin.reflect.KClass

class KoFileDeclarationImpl private constructor(private val ktFile: KtFile) :
    KoNamedDeclarationImpl(ktFile),
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

        ktImportDirectives.map { KoImportDeclarationImpl.getInstance(it) }
    }

    val annotations by lazy {
        ktFile
            .annotationEntries
            .map { KoAnnotationDeclarationImpl.getInstance(it) }
    }

    val packagee by lazy {
        if (ktFile.packageDirective?.qualifiedName == "") {
            null
        } else {
            ktFile.packageDirective?.let { KoPackageDeclarationImpl.getInstance(it) }
        }
    }

    val typeAliases by lazy {
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

    fun hasAnnotations(vararg names: String) = when {
        names.isEmpty() -> annotations.isNotEmpty()
        else -> names.all { hasAnnotationNameOrAnnotationFullyQualifyName(it) }
    }

    private fun hasAnnotationNameOrAnnotationFullyQualifyName(name: String) = annotations.any {
        it.fullyQualifiedName.substringAfterLast(".") == name || it.fullyQualifiedName == name
    }

    fun hasAnnotationsOf(vararg names: KClass<*>) = names.all {
        annotations.any { annotation -> annotation.fullyQualifiedName == it.qualifiedName }
    }

    inline fun <reified T> hasAnnotationOf(): Boolean {
        val qualifiedName = T::class.qualifiedName ?: return false

        return annotations.any { it.fullyQualifiedName.contains(qualifiedName) }
    }

    fun hasPackage(name: String) = packagee
        ?.qualifiedName
        ?.let { PackageHelper.resideInPackage(name, it) } ?: false

    fun hasImports(vararg names: String) = when {
        names.isEmpty() -> imports.isNotEmpty()
        else -> names.all {
            imports.any { import -> PackageHelper.resideInPackage(it, import.name) }
        }
    }

    fun hasTypeAliases(vararg names: String) = when {
        names.isEmpty() -> typeAliases.isNotEmpty()
        else -> names.all {
            typeAliases.any { typeAlias -> typeAlias.name == it }
        }
    }

    override fun equals(other: Any?): Boolean = other is KoFileDeclarationImpl && filePath == other.filePath

    override fun hashCode(): Int = 31 * 7 + filePath.hashCode()

    companion object {
        private val cache = KoDeclarationCache<KoFileDeclarationImpl>()

        fun getInstance(ktFile: KtFile) = cache.getOrCreateInstance(ktFile) { KoFileDeclarationImpl(ktFile) }
    }
}
