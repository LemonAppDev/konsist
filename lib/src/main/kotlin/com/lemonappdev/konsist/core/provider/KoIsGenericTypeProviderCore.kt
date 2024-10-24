package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.api.provider.KoIsGenericTypeProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider

internal interface KoIsGenericTypeProviderCore :
    KoIsGenericTypeProvider,
    KoBaseProviderCore {
    override val isGenericType: Boolean
        get() {
            val regex = "\\w+<[a-zA-Z*<>(), ]+>".toRegex()

            val type =
                if ((this as? KoDeclarationCastProvider)?.isTypeAlias == true) {
                    (this as? KoDeclarationCastProvider)
                        ?.asTypeAliasDeclaration()
                        ?.type
                        ?.text
                } else {
                    (this as? KoNameProvider)?.name
                }

            return type?.let { regex.matches(it) } ?: false
        }
}
