package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoGenericTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoKotlinTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNullableTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceAndAliasTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtTypeReference

internal class KoTypeDeclarationCore private constructor(
    override val ktTypeReference: KtTypeReference,
) :
    KoTypeDeclaration,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoGenericTypeProviderCore,
    KoKotlinTypeProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoNullableTypeProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoSourceAndAliasTypeProviderCore,
    KoTextProviderCore {
    override val psiElement: PsiElement by lazy { ktTypeReference }

    override val ktElement: KtElement by lazy { ktTypeReference }

    override val name: String by lazy {
        when {
            isAlias -> aliasType + if (isNullable) "?" else ""
            else -> ktTypeReference.text
        }
    }

    override val fullyQualifiedName: String by lazy {
        val isInImports = containingFile
            .imports
            .map { it.name }
            .firstOrNull { it.contains(sourceType) }

        val isInFile = containingFile
            .declarations()
            .mapNotNull { (it as? KoFullyQualifiedNameProvider)?.fullyQualifiedName }
            .firstOrNull { it.contains(sourceType) }

        val packagee = containingFile.packagee?.fullyQualifiedName

        return@lazy when {
            isInImports != null -> isInImports
            isInFile != null -> isInFile + if (isNullable) "?" else ""
            packagee != null -> "$packagee.$name"
            else -> name
        }
    }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoTypeDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktTypeReference: KtTypeReference,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoTypeDeclaration =
            cache.getOrCreateInstance(ktTypeReference, containingDeclaration) {
                KoTypeDeclarationCore(
                    ktTypeReference
                )
            }
    }
}
