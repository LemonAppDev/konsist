package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoOrdinalProvider
import org.jetbrains.kotlin.psi.KtClassBody
import org.jetbrains.kotlin.psi.KtEnumEntry

internal interface KoOrdinalProviderCore :
    KoOrdinalProvider,
    KoBaseProviderCore {
    val ktEnumEntry: KtEnumEntry

    override val ordinal: Int
        get() =
            (ktEnumEntry.parent as KtClassBody)
                .children
                .filterIsInstance<KtEnumEntry>()
                .indexOf(ktEnumEntry)
}
