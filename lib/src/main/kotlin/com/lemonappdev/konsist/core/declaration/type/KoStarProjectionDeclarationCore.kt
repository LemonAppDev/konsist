package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.core.declaration.KoSourceDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement

internal object KoStarProjectionDeclarationCore :
    KoStarProjectionDeclaration,
    KoSourceDeclarationCore,
    KoBaseProviderCore {
    override val ktElement: KtElement? by lazy { null }

    override val psiElement: PsiElement? by lazy { null }

    override val name: String by lazy { text }

    override val text: String by lazy { "*" }

    override val sourceDeclaration: KoSourceDeclaration by lazy {
        KoStarProjectionDeclarationCore
    }

    override fun toString(): String = text
}
