package com.lemonappdev.konsist.core.declaration.type

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoKotlinTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNullableProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal class KoTypeDeclarationCore private constructor(
    private val ktTypeReference: KtTypeReference,
) :
    KoTypeDeclaration,
    KoBaseProviderCore,
    KoNameProviderCore,
    KoTextProviderCore,
    KoLocationProviderCore,
    KoNullableProviderCore,
    KoContainingFileProviderCore,
    KoKotlinTypeProviderCore,
//    KoGenericTypeProviderCore,
    KoPackageProviderCore,
    KoResideInPackageProviderCore {
    override val psiElement: PsiElement by lazy { ktTypeReference }

    override val ktElement: KtElement by lazy { ktTypeReference }

    override val name: String by lazy { ktTypeReference.name ?: ktTypeReference.text }

//    override val textUsedToFqn: String by lazy { sourceType.substringBefore("<") }

    override fun toString(): String = name

    override val packagee: KoPackageDeclaration? by lazy { containingFile.packagee }

    override val sourceDeclaration: KoBaseTypeDeclaration by lazy {
        TypeUtil.getBasicType(
            listOf(ktTypeReference),
            ktTypeReference.isExtensionDeclaration(),
            this.castToKoBaseDeclaration(),
            containingFile,
        ) ?: throw IllegalArgumentException("Source declaration cannot be a null")
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoTypeDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktTypeReference: KtTypeReference,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoTypeDeclaration =
            cache.getOrCreateInstance(ktTypeReference, containingDeclaration.castToKoBaseDeclaration()) {
                KoTypeDeclarationCore(
                    ktTypeReference,
                )
            }
    }
}
