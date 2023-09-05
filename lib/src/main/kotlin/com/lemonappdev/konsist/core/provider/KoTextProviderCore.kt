package com.lemonappdev.konsist.core.provider

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

internal interface KoTextProviderCore : KoTextProvider, KoBaseProviderCore {
    val psiElement: PsiElement

    override val text: String
        get() = psiElement.text

    override fun print(prefix: String?) {
        prefix?.let { println(it) }

        val text = if (this is KoNameProvider) name else toString()
        println(text)
    }
}
