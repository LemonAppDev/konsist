package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoParametersProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVisibilityModifierProviderCore
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtConstructor
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal interface KoConstructorDeclarationCore :
    KoConstructorDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoContainingFileProviderCore,
    KoLocationProviderCore,
    KoModifierProviderCore,
    KoPackageProviderCore,
    KoParametersProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoTextProviderCore,
    KoVisibilityModifierProviderCore {
    val ktConstructor: KtConstructor<*>

    override val ktAnnotated: KtAnnotated
        get() = ktConstructor

    override val ktFile: KtFile?
        get() = null

    override val ktTypeParameterListOwner: KtTypeParameterListOwner
        get() = ktConstructor

    override val ktCallableDeclaration: KtCallableDeclaration
        get() = ktConstructor

    override val koFiles: List<KoFileDeclaration>?
        get() = null

    override val psiElement: PsiElement
        get() = ktConstructor

    override val ktElement: KtElement
        get() = ktConstructor
}
