package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalTypeDeclaration
import com.lemonappdev.konsist.api.declaration.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoGenericTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoKotlinTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNullableProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceAndAliasTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtUserType
import java.lang.IllegalArgumentException

internal class KoKotlinTypeDeclarationCore private constructor(
    override val ktUserType: KtUserType,
) :
    KoKotlinTypeDeclaration,
    KoTypeDeclarationCore,
    KoBaseProviderCore,
    KoGenericTypeProviderCore,
    KoKotlinTypeProviderCore,
    KoNullableProviderCore,
    KoSourceAndAliasTypeProviderCore {
    override val psiElement: PsiElement by lazy { ktUserType }

    override val ktElement: KtElement by lazy { ktUserType }

    override val name: String by lazy {
        when {
            isAlias -> aliasType + if (isNullable) "?" else ""
            else -> ktUserType.text
        }
    }

    override val fullyQualifiedName: String by lazy {
        when {
            isKotlinBasicType -> "kotlin.$name"
            isKotlinCollectionType -> "kotlin.collection.$bareSourceType"
            else -> bareSourceType
        }
    }

    override val textUsedToFqn: String by lazy { bareSourceType }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoKotlinTypeDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktUserType: KtUserType,
            containingDeclaration: KoBaseDeclaration,
        ): KoKotlinTypeDeclaration =
            cache.getOrCreateInstance(ktUserType, containingDeclaration) {
                KoKotlinTypeDeclarationCore(
                    ktUserType,
                )
            }
    }
}
