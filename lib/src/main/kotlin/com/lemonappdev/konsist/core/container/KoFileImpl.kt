package com.lemonappdev.konsist.core.container

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.ext.toOsSeparator
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoClassProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoFileExtensionProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoHasPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoImportProviderCore
import com.lemonappdev.konsist.core.provider.KoInterfaceProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoObjectProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoPropertyProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeAliasProviderCore
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile

internal class KoFileImpl(override val ktFile: KtFile) :
    KoFile,
    KoAnnotationProviderCore,
    KoClassProviderCore,
    KoDeclarationProviderCore,
    KoFileExtensionProviderCore,
    KoFunctionProviderCore,
    KoHasPackageProviderCore,
    KoImportProviderCore,
    KoInterfaceProviderCore,
    KoModuleProviderCore,
    KoNameProviderCore,
    KoObjectProviderCore,
    KoPackageProviderCore,
    KoPathProviderCore,
    KoPropertyProviderCore,
    KoSourceSetProviderCore,
    KoTextProviderCore,
    KoTypeAliasProviderCore {

    override val ktElement: KtElement by lazy { ktFile }

    override val psiElement: PsiElement by lazy { ktFile }

    override val ktAnnotated: KtAnnotated by lazy { ktFile }

    override val parent: KoParentProvider? by lazy { null }

    override val koFiles: Sequence<KoFile>? by lazy { null }

    override val name by lazy { nameWithExtension.substringBeforeLast('.') }

    override val path: String by lazy {
        ktFile
            .name
            .toOsSeparator()
    }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoBaseProvider> =
        KoDeclarationProviderCoreUtil.getKoDeclarations(ktFile, includeNested, includeLocal, null)

    override fun toString(): String = path

    override fun equals(other: Any?): Boolean = other is KoFile && path == other.path

    override fun hashCode(): Int = 31 * 7 + path.hashCode()
}
