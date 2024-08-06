package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDefaultValueProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNonNullableTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoRepresentsTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoCrossInlineModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoNoInlineModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoValModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVarArgModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVarModifierProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtTypeReference

internal class KoParameterDeclarationCore private constructor(
    override val ktParameter: KtParameter,
    override val containingDeclaration: KoBaseDeclaration,
) :
    KoParameterDeclaration,
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
        KoRepresentsTypeProviderCore,
        KoResideInPackageProviderCore,
        KoTextProviderCore,
        KoNonNullableTypeProviderCore,
        KoVarModifierProviderCore,
        KoValModifierProviderCore,
        KoVarArgModifierProviderCore,
        KoNoInlineModifierProviderCore,
        KoCrossInlineModifierProviderCore {
        override val ktAnnotated: KtAnnotated by lazy { ktParameter }

        override val ktModifierListOwner: KtModifierListOwner by lazy { ktParameter }

        override val psiElement: PsiElement by lazy { ktParameter }

        override val ktElement: KtElement by lazy { ktParameter }

        override val type: KoTypeDeclaration by lazy {
            val type =
                ktParameter
                    .children
                    .filterIsInstance<KtTypeReference>()
                    .firstOrNull()

            type?.let { KoTypeDeclarationCore.getInstance(it, this) }
                ?: throw KoInternalException("Class type cannot be null")
        }

        override fun representsType(name: String?): Boolean = type.name == name // todo: add this?: || type.fullyQualifiedName == name

        override val hasValModifier: Boolean by lazy { ktParameter.valOrVarKeyword?.text == "val" }

        override val hasVarModifier: Boolean by lazy { ktParameter.valOrVarKeyword?.text == "var" }

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
