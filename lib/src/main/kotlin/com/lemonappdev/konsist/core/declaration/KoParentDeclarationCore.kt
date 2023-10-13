package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore

internal interface KoParentDeclarationCore :
    KoParentDeclaration,
    KoBaseProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoNameProviderCore
