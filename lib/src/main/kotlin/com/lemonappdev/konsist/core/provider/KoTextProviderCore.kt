package com.lemonappdev.konsist.core.provider

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.provider.KoTextProvider

internal interface KoTextProviderCore : KoTextProvider, KoBaseProviderCore {
    val psiElement: PsiElement

    override val text: String
        get() = psiElement.text
}
