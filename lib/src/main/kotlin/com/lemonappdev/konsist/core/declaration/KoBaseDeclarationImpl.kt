package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoParentDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.psi.KtElement

internal open class KoBaseDeclarationImpl(override val ktElement: KtElement) :
    KoBaseDeclaration,
    KoParentDeclarationProviderCore,
    KoContainingFileProviderCore,
    KoKDocProviderCore,
    KoNameProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore,
    KoTextProviderCore {
    override val parentDeclaration: KoParentDeclarationProvider? by lazy { null }

    override val psiElement: PsiElement by lazy { ktElement }

    override fun toString(): String {
        return locationWithText
    }
}
