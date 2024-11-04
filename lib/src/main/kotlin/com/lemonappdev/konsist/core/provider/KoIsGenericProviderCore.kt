package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.api.provider.KoIsGenericProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoTypeParameterProvider

internal interface KoIsGenericProviderCore :
    KoIsGenericProvider,
    KoBaseProviderCore {
    override val isGeneric: Boolean
        get() {
            if ((this as? KoTypeParameterProvider)?.typeParameters?.isNotEmpty() == true) {
                return true
            }

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
