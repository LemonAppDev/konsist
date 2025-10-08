package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore

internal interface KoSourceDeclarationCore :
    KoSourceDeclaration,
    KoBaseProviderCore,
    KoNameProviderCore,
    KoTextProviderCore
