package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.cache.KoPropertyCache
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDefaultValueProviderCore
import com.lemonappdev.konsist.core.provider.KoIsValProviderCore
import com.lemonappdev.konsist.core.provider.KoIsVarProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNonNullableTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoCrossInlineModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoNoInlineModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVarArgModifierProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtTypeReference

internal class KoParameterDeclarationCore private constructor(
    override val ktParameter: KtParameter,
    override val containingDeclaration: KoBaseDeclaration,
) : KoParameterDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoContainingFileProviderCore,
    KoDefaultValueProviderCore,
    KoLocationProviderCore,
    KoModifierProviderCore,
    KoNameProviderCore,
    KoPackageDeclarationProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoResideInPackageProviderCore,
    KoTextProviderCore,
    KoNonNullableTypeProviderCore,
    KoVarArgModifierProviderCore,
    KoNoInlineModifierProviderCore,
    KoCrossInlineModifierProviderCore,
    KoIsValProviderCore,
    KoIsVarProviderCore {
    override val ktAnnotationEntries: List<KtAnnotationEntry>?
        get() = ktParameter.annotationEntries

    override val ktModifierListOwner: KtModifierListOwner
        get() = ktParameter

    override val psiElement: PsiElement
        get() = ktParameter

    override val ktElement: KtElement
        get() = ktParameter

    @Volatile
    private var cachedType: Any? = KoPropertyCache.NOT_COMPUTED

    override val type: KoTypeDeclaration
        get() =
            KoPropertyCache.getOrCompute(cachedType, { cachedType = it }) {
                val type =
                    ktParameter
                        .children
                        .filterIsInstance<KtTypeReference>()
                        .firstOrNull()

                type?.let { KoTypeDeclarationCore.getInstance(it, this) }
                    ?: throw KoInternalException("Class type cannot be null")
            }

    override fun representsType(
        name: String?,
        ignoreCase: Boolean,
    ): Boolean = name?.let { type.hasName(it, ignoreCase) } == true

    override val isVal: Boolean
        get() = ktParameter.valOrVarKeyword?.text == "val"

    override val isVar: Boolean
        get() = ktParameter.valOrVarKeyword?.text == "var"

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoParameterDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktParameter: KtParameter,
            containingDeclaration: KoBaseDeclaration,
        ): KoParameterDeclaration =
            cache.getOrCreateInstance(ktParameter, containingDeclaration) {
                KoParameterDeclarationCore(
                    ktParameter,
                    containingDeclaration,
                )
            }
    }
}
