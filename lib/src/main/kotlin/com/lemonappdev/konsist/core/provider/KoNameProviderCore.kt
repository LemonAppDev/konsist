package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.jetbrains.kotlin.psi.KtElement

internal interface KoNameProviderCore : KoNameProvider, KoBaseProviderCore {
    val ktElement: KtElement

    override val name: String
        get() = ktElement.name ?: ""

    override fun hasNameStartingWith(prefix: String): Boolean = name.startsWith(prefix)

    override fun hasNameEndingWith(suffix: String): Boolean = name.endsWith(suffix)

    override fun hasNameContaining(text: String): Boolean = name.contains(text)

    override fun hasNameMatching(regex: Regex): Boolean = name.matches(regex)
}
