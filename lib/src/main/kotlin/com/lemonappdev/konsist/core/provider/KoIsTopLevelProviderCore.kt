package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsTopLevelProvider
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isTopLevelKtOrJavaMember

internal interface KoIsTopLevelProviderCore :
    KoIsTopLevelProvider,
    KoBaseProviderCore {
    val ktTypeParameterListOwner: KtTypeParameterListOwner

    override val isTopLevel: Boolean
        get() = ktTypeParameterListOwner.isTopLevelKtOrJavaMember()
}
