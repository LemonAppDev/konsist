package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoParametersProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVisibilityModifierProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtConstructor
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal interface KoConstructorDeclarationCore :
    KoConstructorDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoContainingFileProviderCore,
    KoLocationProviderCore,
    KoModifierProviderCore,
    KoPackageDeclarationProviderCore,
    KoParametersProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoResideInPackageProviderCore,
    KoTextProviderCore,
    KoVisibilityModifierProviderCore {
    val ktConstructor: KtConstructor<*>

    override val ktAnnotated: KtAnnotated
        get() = ktConstructor

    override val ktTypeParameterListOwner: KtTypeParameterListOwner
        get() = ktConstructor

    override val ktCallableDeclaration: KtCallableDeclaration
        get() = ktConstructor

    override val psiElement: PsiElement
        get() = ktConstructor

    override val ktElement: KtElement
        get() = ktConstructor

    /*
    1.0.0 CleanUp - Now declaration implements two providers - KoResideInPackageProvider and KoResideInOrOutsidePackageProvider
    (the second one is deprecated) - with the same methods, so we must override this and choose which implementation
    this method should have. After removing deprecated provider in v1.0.0 it will be unnecessary.
     */
    override fun resideInPackage(name: String): Boolean {
        return super<KoResideInPackageProviderCore>.resideInPackage(name)
    }

    /*
    1.0.0 CleanUp - Now declaration implements two providers - KoResideInPackageProvider and KoResideInOrOutsidePackageProvider
    (the second one is deprecated) - with the same methods, so we must override this and choose which implementation
    this method should have. After removing deprecated provider in v1.0.0 it will be unnecessary.
     */
    override fun resideOutsidePackage(name: String): Boolean {
        return super<KoResideInPackageProviderCore>.resideOutsidePackage(name)
    }
}
