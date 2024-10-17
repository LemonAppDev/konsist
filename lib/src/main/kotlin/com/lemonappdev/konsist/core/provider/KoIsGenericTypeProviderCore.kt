package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoIsGenericTypeProvider

internal interface KoIsGenericTypeProviderCore :
    KoIsGenericTypeProvider,
    KoBaseProviderCore {
    override val isGenericType: Boolean
        get() {
            val regex = "\\w+<[a-zA-Z*<>, ]+>".toRegex()

            val type =
                if ((this as? KoTypeDeclaration)?.isTypeAlias == true) {
                    (this as? KoTypeDeclaration)?.bareSourceType
                } else {
                    (this as? KoTypeDeclaration)?.name
                }

            return type?.let { regex.matches(it) } ?: false
        }
}
