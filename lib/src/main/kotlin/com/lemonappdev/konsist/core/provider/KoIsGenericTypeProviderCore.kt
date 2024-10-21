package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsGenericTypeProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoTypeDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoTypeProvider

internal interface KoIsGenericTypeProviderCore :
    KoIsGenericTypeProvider,
    KoBaseProviderCore {
    override val isGenericType: Boolean
        get() {
            val regex = "\\w+<[a-zA-Z*<>(), ]+>".toRegex()

            val type =
                if ((this as? KoTypeProvider)?.isTypeAlias == true) {
                    (this as? KoTypeDeclarationProvider)
                        ?.asTypeAliasDeclaration()
                        ?.type
                        ?.text
                } else {
                    (this as? KoNameProvider)?.name
                }

            return type?.let { regex.matches(it) } ?: false
        }
}
