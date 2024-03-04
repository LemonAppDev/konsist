package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider

internal interface KoGenericTypeProviderCore :
    KoGenericTypeProvider,
    KoSourceAndAliasTypeProviderCore,
    KoBaseProviderCore {
    override val isGenericType: Boolean
        get() {
            val regex = "\\w+<[^<>]+>".toRegex()

            val type = if ((this as? KoTypeDeclaration)?.sourceDeclaration is KoTypeAliasDeclaration) {
                bareSourceType
            } else {
                sourceType
            }

            return regex.matches(type)
        }
}
