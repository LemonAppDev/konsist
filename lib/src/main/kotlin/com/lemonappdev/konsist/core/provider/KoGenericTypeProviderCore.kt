package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider

@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsGenericTypeProviderCore"))
internal interface KoGenericTypeProviderCore :
    KoGenericTypeProvider,
    KoSourceAndAliasTypeProviderCore,
    KoBaseProviderCore {
    override val isGenericType: Boolean
        get() {
            val regex = "\\w+<[^<>]+>".toRegex()

            val type =
                if ((this as? KoTypeDeclaration)?.sourceDeclaration?.isTypeAlias == true) {
                    bareSourceType
                } else {
                    sourceType
                }

            return regex.matches(type)
        }
}
