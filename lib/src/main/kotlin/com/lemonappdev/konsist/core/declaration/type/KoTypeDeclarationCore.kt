package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoGenericTypeProviderCore
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
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtNullableType
import org.jetbrains.kotlin.psi.KtTypeReference

internal class KoTypeDeclarationCore private constructor(
    override val ktTypeReference: KtTypeReference,
    override val containingDeclaration: KoBaseDeclaration,
) :
    KoTypeDeclaration,
        KoBaseProviderCore,
        KoNameProviderCore,
        KoTextProviderCore,
        KoPathProviderCore,
        KoLocationProviderCore,
        KoNullableProviderCore,
        KoContainingFileProviderCore,
        KoContainingDeclarationProviderCore,
        KoModuleProviderCore,
        KoSourceSetProviderCore,
        KoTypeProviderCore,
        KoSourceAndAliasTypeProviderCore,
        KoGenericTypeProviderCore,
        KoPackageProviderCore,
        KoResideInPackageProviderCore,
        KoTypeDeclarationProviderCore {
        override val psiElement: PsiElement by lazy { ktTypeReference }

        override val ktElement: KtElement by lazy { ktTypeReference }

        override val name: String by lazy {
            val typeReference =
                ktTypeReference
                    .children
                    // The last item is chosen because when a type is preceded by an annotation or modifier,
                    // the type being searched for is the last item in the list.
                    .lastOrNull()

            if (typeReference is KtNullableType) {
                typeReference
                    .children
                    .firstOrNull()
                    ?.text ?: ""
            } else {
                typeReference?.text ?: ""
            }
        }

        override val packagee: KoPackageDeclaration? by lazy { containingFile.packagee }

        override fun toString(): String = text

        internal companion object {
            private val cache: KoDeclarationCache<KoTypeDeclaration> = KoDeclarationCache()

            internal fun getInstance(
                ktTypeReference: KtTypeReference,
                containingDeclaration: KoBaseDeclaration,
            ): KoTypeDeclaration =
                cache.getOrCreateInstance(ktTypeReference, containingDeclaration) {
                    KoTypeDeclarationCore(ktTypeReference, containingDeclaration)
                }
        }
    }
