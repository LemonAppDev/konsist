package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.api.provider.KoIsFunctionTypeProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider

internal interface KoIsFunctionTypeProviderCore :
    KoIsFunctionTypeProvider,
    KoBaseProviderCore {
    override val isFunctionType: Boolean
        get() {
            val type =
                if ((this as? KoSourceDeclarationProvider)?.sourceDeclaration?.isTypeAlias == true) {
                    (this as? KoSourceDeclarationProvider)
                        ?.sourceDeclaration
                        ?.asTypeAliasDeclaration()
                        ?.type
                        ?.text
                } else {
                    (this as? KoNameProvider)?.name
                }

            return type?.contains("->") ?: false
        }
}
