package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.core.ext.toOsSeparator
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoClassAndInterfaceAndObjectProviderCore
import com.lemonappdev.konsist.core.provider.KoClassAndInterfaceProviderCore
import com.lemonappdev.konsist.core.provider.KoClassAndObjectProviderCore
import com.lemonappdev.konsist.core.provider.KoClassProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoFileExtensionProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoHasPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoImportAliasProviderCore
import com.lemonappdev.konsist.core.provider.KoImportProviderCore
import com.lemonappdev.konsist.core.provider.KoInterfaceAndObjectProviderCore
import com.lemonappdev.konsist.core.provider.KoInterfaceProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoObjectProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoPropertyProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeAliasProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile

internal class KoFileDeclarationCore(
    override val ktFile: KtFile,
) : KoFileDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoClassAndInterfaceAndObjectProviderCore,
    KoClassAndInterfaceProviderCore,
    KoClassAndObjectProviderCore,
    KoInterfaceAndObjectProviderCore,
    KoClassProviderCore,
    KoDeclarationProviderCore,
    KoFileExtensionProviderCore,
    KoFunctionProviderCore,
    KoHasPackageProviderCore,
    KoImportProviderCore,
    KoImportAliasProviderCore,
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

    override val name: String by lazy { nameWithExtension.substringBeforeLast('.') }

    override val path: String by lazy {
        ktFile
            .name
            .toOsSeparator()
    }

    override val packagee: KoPackageDeclaration? by lazy {
        if (ktFile.packageDirective?.qualifiedName == "") {
            null
        } else {
            ktFile.packageDirective?.let { KoPackageDeclarationCore.getInstance(it, this) }
        }
    }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoBaseDeclaration> = KoDeclarationProviderCoreUtil.getKoDeclarations(ktFile, includeNested, includeLocal, this)

    override fun toString(): String = path

    override fun equals(other: Any?): Boolean = other is KoFileDeclaration && path == other.path

    override fun hashCode(): Int = 31 * 7 + path.hashCode()
}
