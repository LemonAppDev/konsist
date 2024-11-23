package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.jetbrains.kotlin.psi.KtElement

internal interface KoNameProviderCore :
    KoNameProvider,
    KoBaseProviderCore {
    val ktElement: KtElement

    override val name: String
        get() = ktElement.name ?: ""

    override fun hasNameStartingWith(
        prefix: String,
        ignoreCase: Boolean,
    ): Boolean = name.startsWith(prefix, ignoreCase)

    override fun hasNameEndingWith(
        suffix: String,
        ignoreCase: Boolean,
    ): Boolean = name.endsWith(suffix, ignoreCase)

    override fun hasNameContaining(
        text: String,
        ignoreCase: Boolean,
    ): Boolean = name.contains(text, ignoreCase)

    override fun hasNameMatching(regex: Regex): Boolean = name.matches(regex)
}
