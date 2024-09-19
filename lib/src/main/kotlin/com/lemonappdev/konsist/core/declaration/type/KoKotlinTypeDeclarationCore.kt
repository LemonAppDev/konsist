package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceAndAliasTypeProviderCore
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtUserType

internal class KoKotlinTypeDeclarationCore private constructor(
    private val ktUserType: KtUserType?,
    private val ktNameReferenceExpression: KtNameReferenceExpression?,
) : KoKotlinTypeDeclaration,
    KoBaseTypeDeclarationCore,
    KoBaseProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoSourceAndAliasTypeProviderCore {
    // Ensure that at least one of the parameters is non-null
    init {
        require(ktUserType != null || ktNameReferenceExpression != null) {
            "Either KtUserType or KtNameReferenceExpression must be provided"
        }
    }

    override val psiElement: PsiElement by lazy {
        ktUserType ?: ktNameReferenceExpression ?: error("Both KtUserType and KtNameReferenceExpression are null")
    }

    override val ktElement: KtElement by lazy {
        ktUserType ?: ktNameReferenceExpression ?: error("Both KtUserType and KtNameReferenceExpression are null")
    }

    override val packagee: KoPackageDeclaration? by lazy {
        KoPackageDeclarationCore(
            fullyQualifiedName,
            ktElement,
        )
    }

    override val name: String by lazy {
        ktUserType?.text ?: ktNameReferenceExpression?.text ?: error("Both KtUserType and KtNameReferenceExpression are null")
    }

    override val fullyQualifiedName: String by lazy {
        when {
            TypeUtil.isKotlinBasicType(name) -> "kotlin.$bareSourceType"
            TypeUtil.isKotlinCollectionTypes(name) -> "kotlin.collections.$bareSourceType"
            else -> throw IllegalArgumentException("Kotlin type has incorrect fully qualified name")
        }
    }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoKotlinTypeDeclaration> = KoDeclarationCache()

        // Factory method for KtUserType
        internal fun getInstance(
            ktUserType: KtUserType,
            containingDeclaration: KoBaseDeclaration,
        ): KoKotlinTypeDeclaration =
            cache.getOrCreateInstance(ktUserType, containingDeclaration) {
                KoKotlinTypeDeclarationCore(ktUserType, null)
            }

        // Factory method for KtNameReferenceExpression
        internal fun getInstance(
            ktNameReferenceExpression: KtNameReferenceExpression,
            containingDeclaration: KoBaseDeclaration,
        ): KoKotlinTypeDeclaration =
            cache.getOrCreateInstance(ktNameReferenceExpression, containingDeclaration) {
                KoKotlinTypeDeclarationCore(null, ktNameReferenceExpression)
            }
    }
}
