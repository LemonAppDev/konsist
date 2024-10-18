package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoIsFunctionTypeProvider

internal interface KoIsFunctionTypeProviderCore :
    KoIsFunctionTypeProvider,
    KoBaseProviderCore {
    override val isFunctionType: Boolean
        get() {
            val type =
                if ((this as? KoTypeDeclaration)?.isTypeAlias == true) {
                    (this as? KoTypeDeclaration)?.bareSourceType
                } else {
                    (this as? KoTypeDeclaration)?.name
                }

            return type?.contains("->") ?: false
        }
}
