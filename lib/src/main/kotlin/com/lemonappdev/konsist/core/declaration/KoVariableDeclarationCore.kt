package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoVariableDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDelegateProviderCore
import com.lemonappdev.konsist.core.provider.KoIsValProviderCore
import com.lemonappdev.konsist.core.provider.KoIsVarProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNullableTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTacitTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoValueProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoValModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVarModifierProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtPropertyAccessor

internal class KoVariableDeclarationCore private constructor(
    override val ktProperty: KtProperty,
    override val containingDeclaration: KoBaseDeclaration,
) : KoVariableDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoContainingFileProviderCore,
    KoDelegateProviderCore,
    KoNullableTypeProviderCore,
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
    KoVarModifierProviderCore,
    KoTacitTypeProviderCore,
    KoIsValProviderCore,
    KoIsVarProviderCore {
    override val ktAnnotationEntries: List<KtAnnotationEntry>? by lazy { ktProperty.annotationEntries }

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

    @Deprecated("Will be removed in version 0.18.0", replaceWith = ReplaceWith("isVal"))
    override val hasValModifier: Boolean by lazy { !ktProperty.isVar }

    @Deprecated("Will be removed in version 0.18.0")
    override val hasVarModifier: Boolean by lazy { ktProperty.isVar }

    override val isVal: Boolean by lazy { !ktProperty.isVar }

    override val isVar: Boolean by lazy { ktProperty.isVar }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoVariableDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktProperty: KtProperty,
            containingDeclaration: KoBaseDeclaration,
        ): KoVariableDeclaration =
            cache.getOrCreateInstance(ktProperty, containingDeclaration) {
                KoVariableDeclarationCore(ktProperty, containingDeclaration)
            }
    }
}
