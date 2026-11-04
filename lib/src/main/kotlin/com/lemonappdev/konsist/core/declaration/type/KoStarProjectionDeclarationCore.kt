package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.core.declaration.KoSourceDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationCastProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement

object KoStarProjectionDeclarationCore :
    KoStarProjectionDeclaration,
    KoSourceDeclarationCore,
    KoBaseProviderCore,
    KoDeclarationCastProviderCore {
    override val ktElement: KtElement?
        get() = null

    override val psiElement: PsiElement?
        get() = null

    override val name: String
        get() = text

    override val text: String
        get() = "*"

    override fun toString(): String = text
}
