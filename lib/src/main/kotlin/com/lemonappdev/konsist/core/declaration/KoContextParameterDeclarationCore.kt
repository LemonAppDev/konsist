package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoContextParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.cache.KoPropertyCache
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNonNullableTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtParameter

internal class KoContextParameterDeclarationCore private constructor(
    private val ktParameter: KtParameter,
    override val containingDeclaration: KoBaseDeclaration,
) : KoContextParameterDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoContainingDeclarationProviderCore,
    KoContainingFileProviderCore,
    KoLocationProviderCore,
    KoModuleProviderCore,
    KoNameProviderCore,
    KoNonNullableTypeProviderCore,
    KoPackageDeclarationProviderCore,
    KoPathProviderCore,
    KoResideInPackageProviderCore,
    KoSourceSetProviderCore,
    KoTextProviderCore {
    override val ktAnnotationEntries: List<KtAnnotationEntry>?
        get() = ktParameter.annotationEntries

    override val psiElement: PsiElement
        get() = ktParameter

    override val ktElement: KtElement
        get() = ktParameter

    @Volatile
    private var cachedType: Any? = KoPropertyCache.NOT_COMPUTED

    override val type: KoTypeDeclaration
        get() =
            KoPropertyCache.getOrCompute(cachedType, { cachedType = it }) {
                ktParameter
                    .typeReference
                    ?.let { KoTypeDeclarationCore.getInstance(it, this) }
                    ?: throw KoInternalException("Context parameter type cannot be null")
            }

    override fun representsType(
        name: String?,
        ignoreCase: Boolean,
    ): Boolean = name?.let { type.hasName(it, ignoreCase) } == true

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoContextParameterDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktParameter: KtParameter,
            containingDeclaration: KoBaseDeclaration,
        ): KoContextParameterDeclaration =
            cache.getOrCreateInstance(ktParameter, containingDeclaration) {
                KoContextParameterDeclarationCore(ktParameter, containingDeclaration)
            }
    }
}
