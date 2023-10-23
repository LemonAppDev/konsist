package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoVariableDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDelegateProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoPropertyTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoValueProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoValModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVarModifierProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.util.EndOfLine
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtPropertyAccessor
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoVariableDeclarationCore private constructor(
    private val ktProperty: KtProperty,
    override val containingDeclaration: KoContainingDeclarationProvider,
) :
    KoVariableDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoContainingFileProviderCore,
    KoDelegateProviderCore,
    KoPropertyTypeProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoKDocProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoPackageDeclarationProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoResideInPackageProviderCore,
    KoTextProviderCore,
    KoValueProviderCore,
    KoValModifierProviderCore,
    KoVarModifierProviderCore {
    override val ktAnnotated: KtAnnotated by lazy { ktProperty }

    override val ktCallableDeclaration: KtCallableDeclaration by lazy { ktProperty }

    override val psiElement: PsiElement by lazy { ktProperty }

    override val ktElement: KtElement by lazy { ktProperty }

    override val ktExpression: KtExpression? by lazy {
        ktProperty
            .children
            .filterNot { it is KtPropertyAccessor }
            .filterIsInstance<KtExpression>()
            .firstOrNull()
    }

    override val delegateName: String? by lazy {
        ktProperty
            .delegateExpression
            ?.text
            ?.replace(EndOfLine.UNIX.value, " ")
            ?.substringAfter("by ")
            ?.substringBefore("{")
            ?.removeSuffix(" ")
    }

    override val hasValModifier: Boolean by lazy { !ktProperty.isVar }

    override val hasVarModifier: Boolean by lazy { ktProperty.isVar }

    override val fullyQualifiedName: String by lazy { name }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoVariableDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktProperty: KtProperty,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoVariableDeclaration =
            cache.getOrCreateInstance(ktProperty, containingDeclaration) {
                KoVariableDeclarationCore(ktProperty, containingDeclaration)
            }
    }
}
