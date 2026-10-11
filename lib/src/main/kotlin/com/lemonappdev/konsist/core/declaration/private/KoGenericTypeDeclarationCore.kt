package com.lemonappdev.konsist.core.declaration.private

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.cache.KoPropertyCache
import com.lemonappdev.konsist.core.declaration.type.KoBaseTypeDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeArgumentProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtUserType

internal class KoGenericTypeDeclarationCore private constructor(
    override val ktUserType: KtUserType,
    override val containingDeclaration: KoBaseDeclaration,
) : KoBaseTypeDeclarationCore,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoTypeArgumentProviderCore,
    KoSourceDeclarationProviderCore {
    override val psiElement: PsiElement
        get() = ktUserType

    override val ktElement: KtElement
        get() = ktUserType

    override val ktTypeProjection: KtTypeProjection?
        get() = null

    @Volatile
    private var cachedName: Any? = KoPropertyCache.NOT_COMPUTED

    override val name: String
        get() = KoPropertyCache.getOrCompute(cachedName, { cachedName = it }) { ktUserType.text }

    @Volatile
    private var cachedPackagee: Any? = KoPropertyCache.NOT_COMPUTED

    override val packagee: KoPackageDeclaration?
        get() = KoPropertyCache.getOrCompute(cachedPackagee, { cachedPackagee = it }) { containingFile.packagee }

    @Volatile
    private var cachedSourceDeclaration: Any? = KoPropertyCache.NOT_COMPUTED

    override val sourceDeclaration: KoDeclarationCastProvider?
        get() =
            KoPropertyCache.getOrCompute(cachedSourceDeclaration, { cachedSourceDeclaration = it }) {
                containingDeclaration as? KoDeclarationCastProvider
            }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoGenericTypeDeclarationCore> = KoDeclarationCache()

        internal fun getInstance(
            ktUserType: KtUserType,
            containingDeclaration: KoBaseDeclaration,
        ): KoGenericTypeDeclarationCore =
            cache.getOrCreateInstance(ktUserType, containingDeclaration) {
                KoGenericTypeDeclarationCore(ktUserType, containingDeclaration)
            }
    }
}
