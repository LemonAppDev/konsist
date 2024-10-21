package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsFunctionTypeProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoTypeDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoTypeProvider

internal interface KoIsFunctionTypeProviderCore :
    KoIsFunctionTypeProvider,
    KoBaseProviderCore {
    override val isFunctionType: Boolean
        get() {
            val type =
                if ((this as? KoTypeProvider)?.isTypeAlias == true) {
                    (this as? KoTypeDeclarationProvider)
                        ?.asTypeAliasDeclaration()
                        ?.type
                        ?.text
                } else {
                    (this as? KoNameProvider)?.name
                }

            return type?.contains("->") ?: false
        }
}
