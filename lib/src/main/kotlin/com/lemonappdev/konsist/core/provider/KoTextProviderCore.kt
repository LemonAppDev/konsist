package com.lemonappdev.konsist.core.provider

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.provider.KoTextProvider

internal interface KoTextProviderCore : KoTextProvider, KoBaseProviderCore {
    val psiElement: PsiElement

    override val text: String
        get() = psiElement.text

    override fun print() {
        print(toString())
    }
}
