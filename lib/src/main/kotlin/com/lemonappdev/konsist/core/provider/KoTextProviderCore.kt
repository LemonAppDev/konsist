package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoTextProvider
import org.jetbrains.kotlin.com.intellij.psi.PsiElement

internal interface KoTextProviderCore : KoTextProvider, KoBaseProviderCore {
    val psiElement: PsiElement

    override val text: String
        get() = psiElement.text
}
