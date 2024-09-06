package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoEnumNameProvider
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.psi.psiUtil.parents

internal interface KoEnumNameProviderCore :
    KoEnumNameProvider,
    KoNameProviderCore,
    KoBaseProviderCore {
    val ktEnumEntry: KtEnumEntry
    override val enumName: String
        get() =
            ktEnumEntry.parents
                .filterIsInstance<KtClass>()
                .first()
                .name ?: ""

    override val fullEnumName: String
        get() = "$enumName.$name"
}
