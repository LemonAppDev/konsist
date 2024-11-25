package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoInterfaceDelegateProvider
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal interface KoInterfaceDelegateProviderCore :
    KoInterfaceDelegateProvider,
    KoDelegateProviderCore,
    KoBaseProviderCore {
    val ktSuperTypeListEntry: KtSuperTypeListEntry?

    override val delegateName: String?
        get() =
            (ktSuperTypeListEntry as? KtDelegatedSuperTypeEntry)
                ?.delegateExpression
                ?.text
}
