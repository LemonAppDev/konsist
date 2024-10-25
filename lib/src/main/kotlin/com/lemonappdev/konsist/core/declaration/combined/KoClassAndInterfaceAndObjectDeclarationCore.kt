package com.lemonappdev.konsist.core.declaration.combined

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.core.declaration.KoChildDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoBaseTypeDeclarationCore
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoClassAndInterfaceAndObjectProviderCore
import com.lemonappdev.konsist.core.provider.KoClassAndInterfaceProviderCore
import com.lemonappdev.konsist.core.provider.KoClassAndObjectProviderCore
import com.lemonappdev.konsist.core.provider.KoClassProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoExternalParentProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoInterfaceAndObjectProviderCore
import com.lemonappdev.konsist.core.provider.KoInterfaceProviderCore
import com.lemonappdev.konsist.core.provider.KoIsTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoObjectProviderCore
import com.lemonappdev.konsist.core.provider.KoParentInterfaceProviderCore
import com.lemonappdev.konsist.core.provider.KoParentProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoPropertyProviderCore
import com.lemonappdev.konsist.core.provider.KoRepresentsTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVisibilityModifierProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal interface KoClassAndInterfaceAndObjectDeclarationCore :
    KoClassAndInterfaceAndObjectDeclaration,
    KoBaseProviderCore,
    KoChildDeclarationCore,
    KoBaseTypeDeclarationCore,
    KoAnnotationProviderCore,
    KoClassAndInterfaceAndObjectProviderCore,
    KoClassAndInterfaceProviderCore,
    KoClassAndObjectProviderCore,
    KoInterfaceAndObjectProviderCore,
    KoClassProviderCore,
    KoContainingDeclarationProviderCore,
    KoContainingFileProviderCore,
    KoDeclarationProviderCore,
    KoExternalParentProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoFunctionProviderCore,
    KoInterfaceProviderCore,
    KoKDocProviderCore,
    KoLocationProviderCore,
    KoModifierProviderCore,
    KoModuleProviderCore,
    KoNameProviderCore,
    KoObjectProviderCore,
    KoPackageProviderCore,
    KoParentInterfaceProviderCore,
    KoParentProviderCore,
    KoPathProviderCore,
    KoPropertyProviderCore,
    KoRepresentsTypeProviderCore,
    KoResideInPackageProviderCore,
    KoSourceSetProviderCore,
    KoTextProviderCore,
    KoTopLevelProviderCore,
    KoIsTopLevelProviderCore,
    KoVisibilityModifierProviderCore {
    override val ktClassOrObject: KtClassOrObject

    override val psiElement: PsiElement
        get() = ktClassOrObject

    override val ktModifierListOwner: KtModifierListOwner
        get() = ktClassOrObject

    override val ktTypeParameterListOwner: KtTypeParameterListOwner
        get() = ktClassOrObject

    override val ktAnnotationEntries: List<KtAnnotationEntry>?
        get() = ktClassOrObject.annotationEntries

    override val ktElement: KtElement
        get() = ktClassOrObject

    @Deprecated("Will be removed in version 0.18.0", ReplaceWith(""))
    override val isTopLevel: Boolean
        get() = super<KoIsTopLevelProviderCore>.isTopLevel

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoBaseDeclaration> = KoDeclarationProviderCoreUtil.getKoDeclarations(ktClassOrObject, includeNested, includeLocal, this)
}
