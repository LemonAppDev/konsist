package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoGenericTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtTypeArgumentList
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType

internal class KoGenericTypeDeclarationCore private constructor(
    private val ktUserType: KtUserType?,
    private val ktTypeProjection: KtTypeProjection?,
    override val containingDeclaration: KoBaseDeclaration,
) : KoGenericTypeDeclaration,
    KoBaseTypeDeclarationCore,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore {

    init {
        require(ktUserType != null || ktTypeProjection != null) { "Either KtUserType or KtTypeProjection must be provided" }
    }

    override val psiElement: PsiElement by lazy {
        ktUserType ?: ktTypeProjection ?: error("Both KtUserType and KtTypeProjection are null")
    }

    override val ktElement: KtElement by lazy {
        ktUserType ?: ktTypeProjection ?: error("Both KtUserType and KtTypeProjection are null")
    }

    override val name: String by lazy {
        ktUserType?.text ?: ktTypeProjection?.text ?: error("Both KtUserType and KtTypeProjection are null")
    }

    override val packagee: KoPackageDeclaration? by lazy { containingFile.packagee }

    override val typeArgument: KoTypeDeclaration by lazy {
        val ktTypeReference = when {
            ktUserType != null -> ktUserType
                .children
                .filterIsInstance<KtTypeArgumentList>()
                .firstOrNull()
                ?.children
                ?.filterIsInstance<KtTypeProjection>()
                ?.firstOrNull()
                ?.children
                ?.filterIsInstance<KtTypeReference>()
                ?.firstOrNull()

            ktTypeProjection != null -> ktTypeProjection
                .children
                .filterIsInstance<KtTypeReference>()
                .firstOrNull()

            else -> null
        }

        require(ktTypeReference != null) { "Type argument cannot be null." }

        KoTypeDeclarationCore.getInstance(ktTypeReference, this.castToKoBaseDeclaration())
    }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoGenericTypeDeclaration> = KoDeclarationCache()

        // Factory method to create instance using KtUserType
        internal fun getInstance(
            ktUserType: KtUserType,
            containingDeclaration: KoBaseDeclaration,
        ): KoGenericTypeDeclaration =
            cache.getOrCreateInstance(ktUserType, containingDeclaration) {
                KoGenericTypeDeclarationCore(ktUserType, null, containingDeclaration)
            }

        // Factory method to create instance using KtTypeProjection
        internal fun getInstance(
            ktTypeProjection: KtTypeProjection,
            containingDeclaration: KoBaseDeclaration,
        ): KoGenericTypeDeclaration =
            cache.getOrCreateInstance(ktTypeProjection, containingDeclaration) {
                KoGenericTypeDeclarationCore(null, ktTypeProjection, containingDeclaration)
            }
    }
}
