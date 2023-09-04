package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoEnumConstantDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoEnumNameProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalClassProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.KtValueArgument

internal class KoArgumentDeclarationCore private constructor(
    private val ktValueArgument: KtValueArgument,
    override val containingDeclaration: KoContainingDeclarationProvider,
) : KoArgumentDeclaration,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
//    KoDeclarationFullyQualifiedNameProviderCore,
    KoPackageDeclarationProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoTextProviderCore {
    override val psiElement: PsiElement by lazy { ktValueArgument }

    override val ktElement: KtElement by lazy { ktValueArgument }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoArgumentDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktValueArgument: KtValueArgument,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoArgumentDeclaration =
            cache.getOrCreateInstance(ktValueArgument, containingDeclaration) {
                KoArgumentDeclarationCore(ktValueArgument, containingDeclaration)
            }
    }
}
