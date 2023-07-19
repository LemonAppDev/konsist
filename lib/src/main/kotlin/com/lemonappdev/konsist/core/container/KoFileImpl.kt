package com.lemonappdev.konsist.core.container

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoImportDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclarationImpl
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationCoreProviderUtil
import com.lemonappdev.konsist.core.ext.sep
import com.lemonappdev.konsist.core.ext.toOsSeparator
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.provider.KoAnnotationDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoFileExtensionProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.util.LocationUtil
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList
import org.jetbrains.kotlin.psi.KtTypeAlias
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import kotlin.reflect.KClass

internal class KoFileImpl(override val ktFile: KtFile) :
    KoFile,
    KoNameProviderCore,
    KoPathProviderCore,
    KoTextProviderCore,
    KoAnnotationDeclarationProviderCore,
    KoFileExtensionProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoPackageDeclarationProviderCore {

    override val ktElement: KtElement
        get() = ktFile

    override val psiElement: PsiElement
        get() = ktFile

    override val ktAnnotated: KtAnnotated
        get() = ktFile

    override val parentDeclaration: KoParentProvider?
        get() = null

    override val name by lazy { nameWithExtension.substringBeforeLast('.') }

    override val path: String by lazy {
        ktFile
            .name
            .toOsSeparator()
    }

    override val imports: List<KoImportDeclaration> by lazy {
        val ktImportDirectives = ktFile
            .children
            .filterIsInstance<KtImportList>()
            .first()
            .children
            .filterIsInstance<KtImportDirective>()

        ktImportDirectives.map { KoImportDeclarationImpl.getInstance(it, null) }
    }

    override val typeAliases: List<KoTypeAliasDeclaration> by lazy {
        ktFile
            .children
            .filterIsInstance<KtTypeAlias>()
            .map { KoTypeAliasDeclarationImpl.getInstance(it, null) }
    }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoBaseDeclaration> =
        KoDeclarationCoreProviderUtil.getKoDeclarations(ktFile, includeNested, includeLocal, null)

    override fun hasPackage(name: String): Boolean = packagee
        ?.fullyQualifiedName
        ?.let { LocationUtil.resideInLocation(name, it) } ?: false

    override fun hasImports(vararg names: String): Boolean = when {
        names.isEmpty() -> imports.isNotEmpty()
        else -> names.all {
            imports.any { import -> LocationUtil.resideInLocation(it, import.name) }
        }
    }

    override fun hasTypeAliases(vararg names: String): Boolean = when {
        names.isEmpty() -> typeAliases.isNotEmpty()
        else -> names.all {
            typeAliases.any { typeAlias -> typeAlias.name == it }
        }
    }

    override fun equals(other: Any?): Boolean = other is KoFile && path == other.path

    override fun hashCode(): Int = 31 * 7 + path.hashCode()
}
