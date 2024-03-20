package com.lemonappdev.konsist.core.declaration.type

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceAndAliasTypeProviderCore
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtUserType

internal class KoKotlinTypeDeclarationCore private constructor(
    private val ktUserType: KtUserType,
) :
    KoKotlinTypeDeclaration,
        KoBaseTypeDeclarationCore,
        KoBaseProviderCore,
        KoFullyQualifiedNameProviderCore,
        KoSourceAndAliasTypeProviderCore {
        override val psiElement: PsiElement by lazy { ktUserType }

        override val ktElement: KtElement by lazy { ktUserType }

        override val packagee: KoPackageDeclaration? by lazy {
            KoPackageDeclarationCore(
                fullyQualifiedName,
                ktUserType,
            )
        }

        override val name: String by lazy { ktUserType.text }

        override val fullyQualifiedName: String by lazy {
            when {
                TypeUtil.isKotlinBasicType(name) -> "kotlin.$bareSourceType"
                TypeUtil.isKotlinCollectionTypes(name) -> "kotlin.collections.$bareSourceType"
                else -> throw IllegalArgumentException("Kotlin type has incorrect fullyQualified name")
            }
        }

        override fun toString(): String = ktUserType.text

        internal companion object {
            private val cache: KoDeclarationCache<KoKotlinTypeDeclaration> = KoDeclarationCache()

            internal fun getInstance(
                ktUserType: KtUserType,
                containingDeclaration: KoBaseDeclaration,
            ): KoKotlinTypeDeclaration =
                cache.getOrCreateInstance(ktUserType, containingDeclaration) {
                    KoKotlinTypeDeclarationCore(ktUserType)
                }
        }
    }
