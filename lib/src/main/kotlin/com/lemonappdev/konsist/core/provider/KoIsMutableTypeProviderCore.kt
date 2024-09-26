package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsMutableTypeProvider
import com.lemonappdev.konsist.api.provider.KoIsTopLevelProvider
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isTopLevelKtOrJavaMember

internal interface KoIsMutableTypeProviderCore :
    KoIsMutableTypeProvider,
    KoBaseProviderCore,
    KoNameProviderCore {
    override val isMutableType: Boolean
        get() = name.startsWith("Mutable")
}
