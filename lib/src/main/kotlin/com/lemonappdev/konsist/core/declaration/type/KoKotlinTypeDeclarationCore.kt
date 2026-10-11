package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.cache.KoPropertyCache
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
    override val psiElement: PsiElement
        get() = ktElement

    @Volatile
    private var cachedPackagee: Any? = KoPropertyCache.NOT_COMPUTED

    override val packagee: KoPackageDeclaration?
        get() =
            KoPropertyCache.getOrCompute(cachedPackagee, { cachedPackagee = it }) {
                KoPackageDeclarationCore(
                    fullyQualifiedName,
                    ktElement,
                )
            }

    @Volatile
    private var cachedName: Any? = KoPropertyCache.NOT_COMPUTED

    override val name: String
        get() = KoPropertyCache.getOrCompute(cachedName, { cachedName = it }) { TypeUtil.getBareType(ktElement.text) }

    @Volatile
    private var cachedFullyQualifiedName: Any? = KoPropertyCache.NOT_COMPUTED

    override val fullyQualifiedName: String
        get() =
            KoPropertyCache.getOrCompute(cachedFullyQualifiedName, { cachedFullyQualifiedName = it }) {
                TypeUtil
                    .kotlinTypes
                    .firstOrNull { it.endsWith(".$stringUsedAsFullyQualifiedName") }
                    ?: throw IllegalArgumentException("Kotlin type has incorrect fullyQualified name")
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
