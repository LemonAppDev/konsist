package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoEnumConstantDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoArgumentProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoEnumNameProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalClassProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.util.KoLocalDeclarationProviderCoreUtil
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.KtValueArgument
import org.jetbrains.kotlin.psi.KtValueArgumentList

internal class KoEnumConstantDeclarationCore private constructor(
    override val ktEnumEntry: KtEnumEntry,
    override val containingDeclaration: KoContainingDeclarationProvider,
) : KoEnumConstantDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoArgumentProviderCore,
    KoContainingFileProviderCore,
    KoEnumNameProviderCore,
    KoKDocProviderCore,
    KoLocalClassProviderCore,
    KoLocalDeclarationProviderCore,
    KoLocalFunctionProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoPackageDeclarationProviderCore,
    KoResideInPackageProviderCore,
    KoTextProviderCore {
    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktEnumEntry }

    override val ktAnnotated: KtAnnotated by lazy { ktEnumEntry }

    override val psiElement: PsiElement by lazy { ktEnumEntry }

    override val ktElement: KtElement by lazy { ktEnumEntry }

    override val localDeclarations: List<KoBaseDeclaration> by lazy {
        val psiElements = ktEnumEntry
            .body
            ?.children

        KoLocalDeclarationProviderCoreUtil.getKoLocalDeclarations(psiElements, this)
    }

    override val arguments: List<KoArgumentDeclaration>
        get() = ktEnumEntry
            .initializerList
            ?.initializers
            ?.firstOrNull()
            ?.children
            ?.filterIsInstance<KtValueArgumentList>()
            ?.firstOrNull()
            ?.children
            ?.filterIsInstance<KtValueArgument>()
            ?.map { KoArgumentDeclarationCore.getInstance(it, this) }
            .orEmpty()

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoEnumConstantDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktEnumEntry: KtEnumEntry,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoEnumConstantDeclaration =
            cache.getOrCreateInstance(ktEnumEntry, containingDeclaration) {
                KoEnumConstantDeclarationCore(ktEnumEntry, containingDeclaration)
            }
    }
}
