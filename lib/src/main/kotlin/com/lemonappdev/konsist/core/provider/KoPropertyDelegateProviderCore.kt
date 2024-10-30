package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoPropertyDelegateProvider
import com.lemonappdev.konsist.core.util.EndOfLine
import org.jetbrains.kotlin.psi.KtProperty

internal interface KoPropertyDelegateProviderCore :
    KoPropertyDelegateProvider,
    KoDelegateProviderCore,
    KoBaseProviderCore {
    val ktProperty: KtProperty?

    override val delegateName: String?
        get() =
            ktProperty
                ?.delegate
                ?.text
                ?.replace(EndOfLine.UNIX.value, " ")
                ?.substringAfter("by ")
                ?.substringBefore("{")
                ?.removeSuffix(" ")
}
