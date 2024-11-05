package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceTypeProviderCore
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement

internal class KoKotlinTypeDeclarationCore private constructor(
    override val ktElement: KtElement,
) : KoKotlinTypeDeclaration,
    KoBaseTypeDeclarationCore,
    KoBaseProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoSourceTypeProviderCore {
    override val psiElement: PsiElement by lazy { ktElement }

    override val packagee: KoPackageDeclaration? by lazy {
        KoPackageDeclarationCore(
            fullyQualifiedName,
            ktElement,
        )
    }

    override val name: String by lazy { ktElement.text }

    override val fullyQualifiedName: String by lazy {
        when {
            TypeUtil.isKotlinBasicType(name) -> "kotlin.$bareSourceType"
            TypeUtil.isKotlinCollectionTypes(name) -> "kotlin.collections.$bareSourceType"
            else -> throw IllegalArgumentException("Kotlin type has incorrect fullyQualified name")
        }
    }

    override fun toString(): String = ktElement.text

    internal companion object {
        private val cache: KoDeclarationCache<KoKotlinTypeDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktElement: KtElement,
            containingDeclaration: KoBaseDeclaration,
        ): KoKotlinTypeDeclaration =
            cache.getOrCreateInstance(ktElement, containingDeclaration) {
                KoKotlinTypeDeclarationCore(ktElement)
            }
    }
}
