package com.lemonappdev.konsist.core.declaration.combined

import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration

internal interface KoInterfaceAndObjectDeclarationCore :
    KoInterfaceAndObjectDeclaration,
    KoClassAndInterfaceAndObjectDeclarationCore
