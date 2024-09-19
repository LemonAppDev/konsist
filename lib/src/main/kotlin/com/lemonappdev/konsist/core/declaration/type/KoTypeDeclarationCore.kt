package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoGenericTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoIsGenericTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoIsNullableProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNullableProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceAndAliasTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtNullableType
import org.jetbrains.kotlin.psi.KtTypeReference

internal class KoTypeDeclarationCore private constructor(
    override val ktTypeReference: KtTypeReference?,
    override val ktNameReferenceExpression: KtNameReferenceExpression?,
    override val containingDeclaration: KoBaseDeclaration,
) : KoTypeDeclaration,
    KoBaseProviderCore,
    KoNameProviderCore,
    KoTextProviderCore,
    KoPathProviderCore,
    KoLocationProviderCore,
    KoNullableProviderCore,
    KoIsNullableProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoTypeProviderCore,
    KoSourceAndAliasTypeProviderCore,
    KoGenericTypeProviderCore,
    KoIsGenericTypeProviderCore,
    KoPackageProviderCore,
    KoResideInPackageProviderCore,
    KoAnnotationProviderCore,
    KoTypeDeclarationProviderCore {

    // Ensure that at least one of the parameters is not null
    init {
        require(ktTypeReference != null || ktNameReferenceExpression != null) {
            "Either KtTypeReference or KtNameReferenceExpression must be provided"
        }
    }

    override val psiElement: PsiElement by lazy {
        ktTypeReference ?: ktNameReferenceExpression ?: error("Both KtTypeReference and KtNameReferenceExpression are null")
    }

    override val ktElement: KtElement by lazy {
        ktTypeReference ?: ktNameReferenceExpression ?: error("Both KtTypeReference and KtNameReferenceExpression are null")
    }

    override val ktAnnotated: KtAnnotated? by lazy { ktTypeReference }

    override val name: String by lazy {
        val typeReference = ktTypeReference?.children?.lastOrNull()
            ?: ktNameReferenceExpression

        if (typeReference is KtNullableType) {
            typeReference.children.firstOrNull()?.text ?: ""
        } else {
            typeReference?.text ?: ""
        }
    }

    override val packagee: KoPackageDeclaration? by lazy { containingFile.packagee }

    /*
    Remove in version 0.18.0
     */
    override val isGenericType: Boolean
        get() = super<KoTypeDeclarationProviderCore>.isGenericType

    /*
    Remove in version 0.18.0
     */
    override val isNullable: Boolean
        get() = super<KoIsNullableProviderCore>.isNullable

    override fun toString(): String = text

    internal companion object {
        private val cache: KoDeclarationCache<KoTypeDeclaration> = KoDeclarationCache()

        // Factory method for KtTypeReference
        internal fun getInstance(
            ktTypeReference: KtTypeReference,
            containingDeclaration: KoBaseDeclaration,
        ): KoTypeDeclaration =
            cache.getOrCreateInstance(ktTypeReference, containingDeclaration) {
                KoTypeDeclarationCore(ktTypeReference, null, containingDeclaration)
            }

        // Factory method for KtNameReferenceExpression
        internal fun getInstance(
            ktNameReferenceExpression: KtNameReferenceExpression,
            containingDeclaration: KoBaseDeclaration,
        ): KoTypeDeclaration =
            cache.getOrCreateInstance(ktNameReferenceExpression, containingDeclaration) {
                KoTypeDeclarationCore(null, ktNameReferenceExpression, containingDeclaration)
            }
    }
}
