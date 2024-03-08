package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoValueProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtValueArgument

internal class KoArgumentDeclarationCore private constructor(
    private val ktValueArgument: KtValueArgument,
    override val containingDeclaration: KoBaseDeclaration,
) : KoArgumentDeclaration,
    KoBaseProviderCore,
    KoValueProviderCore,
    KoContainingFileProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoResideInPackageProviderCore,
    KoPackageDeclarationProviderCore,
    KoTextProviderCore {
    override val psiElement: PsiElement by lazy { ktValueArgument }

    override val ktElement: KtElement by lazy { ktValueArgument }

    override val ktExpression: KtExpression? by lazy { ktValueArgument.getArgumentExpression() }

    override val name: String
        get() =
            ktValueArgument
                .getArgumentName()
                ?.text ?: ""

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoArgumentDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktValueArgument: KtValueArgument,
            containingDeclaration: KoBaseDeclaration,
        ): KoArgumentDeclaration =
            cache.getOrCreateInstance(ktValueArgument, containingDeclaration) {
                KoArgumentDeclarationCore(ktValueArgument, containingDeclaration)
            }
    }
}
