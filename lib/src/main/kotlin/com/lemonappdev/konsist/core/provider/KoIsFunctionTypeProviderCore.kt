package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsFunctionTypeProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider

internal interface KoIsFunctionTypeProviderCore :
    KoIsFunctionTypeProvider,
    KoBaseProviderCore {
    override val isFunctionType: Boolean
        get() {
            if ((this as? KoFunctionTypeDeclarationProviderCore)?.ktFunctionType != null) {
                return true
            }

            val typeAliasType =
                (this as? KoSourceDeclarationProvider)
                    ?.sourceDeclaration
                    ?.asTypeAliasDeclaration()
                    ?.type

            return (typeAliasType as? KoFunctionTypeDeclarationProviderCore)?.ktFunctionType != null
        }
}
