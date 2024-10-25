package com.lemonappdev.konsist.core.declaration.combined

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.core.provider.KoChildProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoActualModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoExpectModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoSealedModifierProviderCore

internal interface KoClassAndInterfaceDeclarationCore :
    KoClassAndInterfaceDeclaration,
    KoClassAndInterfaceAndObjectDeclarationCore,
    KoActualModifierProviderCore,
    KoChildProviderCore,
    KoExpectModifierProviderCore,
    KoSealedModifierProviderCore
