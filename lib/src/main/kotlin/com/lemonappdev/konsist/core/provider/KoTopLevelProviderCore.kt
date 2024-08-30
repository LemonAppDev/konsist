package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isTopLevelKtOrJavaMember

@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsTopLevelProviderCore"))
internal interface KoTopLevelProviderCore : KoTopLevelProvider, KoBaseProviderCore {
    val ktTypeParameterListOwner: KtTypeParameterListOwner

    override val isTopLevel: Boolean
        get() = ktTypeParameterListOwner.isTopLevelKtOrJavaMember()
}
