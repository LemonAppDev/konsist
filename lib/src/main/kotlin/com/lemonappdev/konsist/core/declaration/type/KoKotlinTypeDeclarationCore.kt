package com.lemonappdev.konsist.core.declaration.type

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtUserType
import java.awt.SystemColor.text

internal class KoKotlinTypeDeclarationCore private constructor(
    private val ktUserType: KtUserType,
) :
    KoKotlinTypeDeclaration,
    KoBaseTypeDeclarationCore,
    KoBaseProviderCore,
    KoFullyQualifiedNameProviderCore {
    override val psiElement: PsiElement by lazy { ktUserType }

    override val ktElement: KtElement by lazy { ktUserType }

    override val packagee: KoPackageDeclaration? by lazy {
        KoPackageDeclarationCore(
            fullyQualifiedName,
            ktUserType,
        )
    }

    override val name: String by lazy { ktUserType.text }

//    override val fullyQualifiedName: String by lazy {
//        when {
//            isKotlinBasicType -> "kotlin.$name"
//            isKotlinCollectionType -> "kotlin.collection.$name"
//            else -> throw IllegalArgumentException("Kotlin type has incorrect fullyQualified name")
//        }
//    }

//    override val textUsedToFqn: String by lazy { name }

    override fun toString(): String = ktUserType.text // Todo: change to name

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
