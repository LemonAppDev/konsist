package com.lemonappdev.konsist.api.declaration.combined

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoChildProvider
import com.lemonappdev.konsist.api.provider.modifier.KoActualModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoExpectModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoSealedModifierProvider

interface KoClassAndInterfaceDeclaration :
    KoClassAndInterfaceAndObjectDeclaration,
    KoParentDeclaration,
    KoActualModifierProvider,
    KoChildProvider,
    KoExpectModifierProvider,
    KoSealedModifierProvider
