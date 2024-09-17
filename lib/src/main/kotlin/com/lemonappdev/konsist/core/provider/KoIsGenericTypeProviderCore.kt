package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoIsGenericTypeProvider
import com.lemonappdev.konsist.api.provider.KoTypeDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoTypeProvider

internal interface KoIsGenericTypeProviderCore :
    KoIsGenericTypeProvider,
    KoTypeProvider,
    KoBaseProviderCore {
    override val isGenericType: Boolean
        get() = (this as? KoTypeProvider)?.isGenericType == true
}
