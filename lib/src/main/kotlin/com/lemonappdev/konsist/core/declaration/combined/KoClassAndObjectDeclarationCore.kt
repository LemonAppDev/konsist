package com.lemonappdev.konsist.core.declaration.combined

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.core.provider.KoInitBlockProviderCore
import com.lemonappdev.konsist.core.provider.KoParentClassProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoDataModifierProviderCore

internal interface KoClassAndObjectDeclarationCore :
    KoClassAndObjectDeclaration,
    KoClassAndInterfaceAndObjectDeclarationCore,
    KoDataModifierProviderCore,
    KoInitBlockProviderCore,
    KoParentClassProviderCore
