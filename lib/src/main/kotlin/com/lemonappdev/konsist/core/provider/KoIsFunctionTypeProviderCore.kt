package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.api.provider.KoIsFunctionTypeProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider

internal interface KoIsFunctionTypeProviderCore :
    KoIsFunctionTypeProvider,
    KoBaseProviderCore {
    override val isFunctionType: Boolean
        get() {
            val type =
                if ((this as? KoDeclarationCastProvider)?.isTypeAlias == true) {
                    (this as? KoDeclarationCastProvider)
                        ?.asTypeAliasDeclaration()
                        ?.type
                        ?.text
                } else {
                    (this as? KoNameProvider)?.name
                }

            return type?.contains("->") ?: false
        }
}
