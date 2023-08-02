package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoDefaultValueProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoRepresentsTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoCrossInlineModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoNoInlineModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoValModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVarArgModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVarModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVisibilityModifierProviderCore
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstance

internal class KoParameterDeclarationImpl private constructor(
    override val ktParameter: KtParameter,
    override val containingDeclaration: KoContainingDeclarationProvider?,
) :
    KoParameterDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoContainingFileProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoDefaultValueProviderCore,
    KoLocationProviderCore,
    KoModifierProviderCore,
    KoNameProviderCore,
    KoPackageProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoRepresentsTypeProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoTextProviderCore,
    KoTypeProviderCore,
    KoVisibilityModifierProviderCore,
    KoVarModifierProviderCore,
    KoValModifierProviderCore,
    KoVarArgModifierProviderCore,
    KoNoInlineModifierProviderCore,
    KoCrossInlineModifierProviderCore {
    override val ktAnnotated: KtAnnotated by lazy { ktParameter }

    override val ktFile: KtFile? by lazy { null }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktParameter }

    override val koFiles: List<KoFile>? by lazy { null }

    override val psiElement: PsiElement by lazy { ktParameter }

    override val ktElement: KtElement by lazy { ktParameter }

    override val type: KoTypeDeclaration by lazy {
        val type = ktParameter
            .children
            .firstIsInstance<KtTypeReference>()

        KoTypeDeclarationImpl.getInstance(type, this)
    }

    override fun representsType(name: String): Boolean = type.name == name || type.fullyQualifiedName == name

    override val hasValModifier: Boolean by lazy { ktParameter.valOrVarKeyword?.text == "val" }

    override val hasVarModifier: Boolean by lazy { ktParameter.valOrVarKeyword?.text == "var" }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoParameterDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktParameter: KtParameter, parent: KoContainingDeclarationProvider?): KoParameterDeclaration =
            cache.getOrCreateInstance(ktParameter, parent) { KoParameterDeclarationImpl(ktParameter, parent) }
    }
}
