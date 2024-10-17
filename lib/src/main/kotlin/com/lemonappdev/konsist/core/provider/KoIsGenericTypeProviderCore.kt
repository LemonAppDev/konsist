package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoIsGenericTypeProvider

internal interface KoIsGenericTypeProviderCore :
    KoIsGenericTypeProvider,
    KoSourceAndAliasTypeProviderCore,
    KoBaseProviderCore {
    override val isGenericType: Boolean
        get() {
            val regex = "\\w+<[a-zA-Z*<>]+>".toRegex()

            val type =
                if ((this as? KoTypeDeclaration)?.isTypeAlias == true) {
                    bareSourceType
                } else {
                    sourceType
                }

            return regex.matches(type)
        }
}
