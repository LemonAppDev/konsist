package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoTextProvider
import org.jetbrains.kotlin.com.intellij.psi.PsiElement

internal interface KoTextProviderCore :
    KoTextProvider,
    KoBaseProviderCore {
    val psiElement: PsiElement

    override val text: String
        get() = psiElement.text ?: ""

    override fun hasTextStartingWith(prefix: String): Boolean = text.startsWith(prefix)

    override fun hasTextEndingWith(suffix: String): Boolean = text.endsWith(suffix)

    override fun hasTextContaining(str: String): Boolean = text.contains(str)

    override fun hasTextMatching(regex: Regex): Boolean = text.matches(regex)
}
