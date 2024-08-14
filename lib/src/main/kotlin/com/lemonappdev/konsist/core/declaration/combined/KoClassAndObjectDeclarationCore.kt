package com.lemonappdev.konsist.core.declaration.combined

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.core.declaration.KoParentDeclarationCore
import com.lemonappdev.konsist.core.provider.KoChildProviderCore
import com.lemonappdev.konsist.core.provider.KoInitBlockProviderCore
import com.lemonappdev.konsist.core.provider.KoParentClassProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoActualModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoDataModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoExpectModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoSealedModifierProviderCore

internal interface KoClassAndObjectDeclarationCore :
    KoClassAndObjectDeclaration,
    KoClassAndInterfaceAndObjectDeclarationCore,
    KoParentDeclarationCore,
    KoActualModifierProviderCore,
    KoChildProviderCore,
    KoExpectModifierProviderCore,
    KoSealedModifierProviderCore,
    KoDataModifierProviderCore,
    KoInitBlockProviderCore,
    KoParentClassProviderCore
