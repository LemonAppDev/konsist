package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isTopLevelKtOrJavaMember

interface KoTopLevelProviderCore : KoTopLevelProvider {
    val ktTypeParameterListOwner: KtTypeParameterListOwner

    override fun isTopLevel(): Boolean = ktTypeParameterListOwner.isTopLevelKtOrJavaMember()
}
