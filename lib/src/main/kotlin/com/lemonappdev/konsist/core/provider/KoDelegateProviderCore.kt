package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoDelegateProvider
import com.lemonappdev.konsist.core.util.EndOfLine
import org.jetbrains.kotlin.psi.KtProperty

internal interface KoDelegateProviderCore :
    KoDelegateProvider,
    KoBaseProviderCore {
    val ktProperty: KtProperty?

    override val delegateName: String?
        get() = ktProperty
            ?.delegate
            ?.text
            ?.replace(EndOfLine.UNIX.value, " ")
            ?.substringAfter("by ")
            ?.substringBefore("{")
            ?.removeSuffix(" ")

    override fun hasDelegate(delegateName: String?): Boolean =
        when (delegateName) {
            null -> this.delegateName != null
            else -> this.delegateName == delegateName
        }
}
