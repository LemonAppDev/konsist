package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationCoreProviderUtil
import com.lemonappdev.konsist.core.util.LocationHelper
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList
import org.jetbrains.kotlin.psi.KtTypeAlias
import kotlin.reflect.KClass

internal class KoFileDeclarationImpl private constructor(private val ktFile: KtFile) :
    KoNamedDeclarationImpl(ktFile),
    KoFileDeclaration {

    override val name: String by lazy {
        ktFile.name.split("/").last()
    }

    override val imports: List<KoImportDeclaration> by lazy {
        val ktImportDirectives = ktFile
            .children
            .filterIsInstance<KtImportList>()
            .first()
            .children
            .filterIsInstance<KtImportDirective>()

        ktImportDirectives.map { KoImportDeclarationImpl.getInstance(it, this) }
    }

    override val annotations: List<KoAnnotationDeclaration> by lazy {
        ktFile
            .annotationEntries
            .map { KoAnnotationDeclarationImpl.getInstance(it, this) }
    }

    override val packagee: KoPackageDeclaration? by lazy {
        if (ktFile.packageDirective?.qualifiedName == "") {
            null
        } else {
            ktFile.packageDirective?.let { KoPackageDeclarationImpl.getInstance(it, this) }
        }
    }

    override val typeAliases: List<KoTypeAliasDeclaration> by lazy {
        ktFile
            .children
            .filterIsInstance<KtTypeAlias>()
            .map { KoTypeAliasDeclarationImpl.getInstance(it, this) }
    }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoNamedDeclaration> =
        KoDeclarationCoreProviderUtil.getKoDeclarations(ktFile, includeNested, includeLocal, this)

    override fun hasAnnotations(vararg names: String): Boolean = when {
        names.isEmpty() -> annotations.isNotEmpty()
        else -> names.all { hasAnnotationNameOrAnnotationFullyQualifyName(it) }
    }

    private fun hasAnnotationNameOrAnnotationFullyQualifyName(name: String): Boolean = annotations.any {
        it.fullyQualifiedName.substringAfterLast(".") == name || it.fullyQualifiedName == name
    }

    override fun hasAnnotationsOf(vararg names: KClass<*>): Boolean = names.all {
        annotations.any { annotation -> annotation.fullyQualifiedName == it.qualifiedName }
    }

    override fun hasPackage(name: String): Boolean = packagee
        ?.qualifiedName
        ?.let { LocationHelper.resideInLocation(name, it) } ?: false

    override fun hasImports(vararg names: String): Boolean = when {
        names.isEmpty() -> imports.isNotEmpty()
        else -> names.all {
            imports.any { import -> LocationHelper.resideInLocation(it, import.name) }
        }
    }

    override fun hasTypeAliases(vararg names: String): Boolean = when {
        names.isEmpty() -> typeAliases.isNotEmpty()
        else -> names.all {
            typeAliases.any { typeAlias -> typeAlias.name == it }
        }
    }

    override fun equals(other: Any?): Boolean = other is KoFileDeclaration && filePath == other.filePath

    override fun hashCode(): Int = 31 * 7 + filePath.hashCode()

    internal companion object {
        private val cache: KoDeclarationCache<KoFileDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktFile: KtFile, parent: KoBaseDeclaration?): KoFileDeclaration =
            cache.getOrCreateInstance(ktFile, parent) { KoFileDeclarationImpl(ktFile) }
    }
}
