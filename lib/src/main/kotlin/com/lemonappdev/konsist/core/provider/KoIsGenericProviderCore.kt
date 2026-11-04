package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsGenericProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoTypeParameterProvider

internal interface KoIsGenericProviderCore :
    KoIsGenericProvider,
    KoBaseProviderCore {
    override val isGeneric: Boolean
        get() {
            if (this is KoTypeParameterProvider) {
                return typeParameters.isNotEmpty()
            }

            if ((this as? KoTypeArgumentProviderCore)?.ktTypeProjections?.isNotEmpty() == true) {
                return true
            }

            val typeAliasType =
                (this as? KoSourceDeclarationProvider)
                    ?.sourceDeclaration
                    ?.asTypeAliasDeclaration()
                    ?.type

            return (typeAliasType as? KoTypeArgumentProviderCore)?.ktTypeProjections?.isNotEmpty() == true
        }
}
