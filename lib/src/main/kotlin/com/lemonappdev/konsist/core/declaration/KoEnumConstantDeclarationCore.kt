package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoEnumConstantDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.cache.KoPropertyCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoArgumentProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoClassProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoEnumNameProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoOrdinalProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoPropertyProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.KtValueArgument
import org.jetbrains.kotlin.psi.KtValueArgumentList

internal class KoEnumConstantDeclarationCore private constructor(
    override val ktEnumEntry: KtEnumEntry,
    override val containingDeclaration: KoBaseDeclaration,
) : KoEnumConstantDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoArgumentProviderCore,
    KoContainingFileProviderCore,
    KoEnumNameProviderCore,
    KoKDocProviderCore,
    KoClassProviderCore,
    KoDeclarationProviderCore,
    KoFunctionProviderCore,
    KoPropertyProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoOrdinalProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoPackageDeclarationProviderCore,
    KoResideInPackageProviderCore,
    KoTextProviderCore {
    override val ktTypeParameterListOwner: KtTypeParameterListOwner
        get() = ktEnumEntry

    override val ktAnnotationEntries: List<KtAnnotationEntry>?
        get() = ktEnumEntry.annotationEntries

    override val psiElement: PsiElement
        get() = ktEnumEntry

    override val ktElement: KtElement
        get() = ktEnumEntry

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoBaseDeclaration> = KoDeclarationProviderCoreUtil.getKoDeclarations(ktEnumEntry, includeNested, includeLocal, this)

    @Volatile
    private var cachedArguments: Any? = KoPropertyCache.NOT_COMPUTED

    override val arguments: List<KoArgumentDeclaration>
        get() =
            KoPropertyCache.getOrCompute(cachedArguments, { cachedArguments = it }) {
                ktEnumEntry
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
            }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoEnumConstantDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktEnumEntry: KtEnumEntry,
            containingDeclaration: KoBaseDeclaration,
        ): KoEnumConstantDeclaration =
            cache.getOrCreateInstance(ktEnumEntry, containingDeclaration) {
                KoEnumConstantDeclarationCore(ktEnumEntry, containingDeclaration)
            }
    }
}
