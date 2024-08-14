package com.lemonappdev.konsist.api.declaration.combined

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoChildProvider
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.api.provider.modifier.KoActualModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoExpectModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoSealedModifierProvider

interface KoClassAndObjectDeclaration :
    KoClassAndInterfaceAndObjectDeclaration,
    KoParentDeclaration,
    KoActualModifierProvider,
    KoChildProvider,
    KoExpectModifierProvider,
    KoSealedModifierProvider,
    KoDataModifierProvider,
    KoInitBlockProvider,
    KoParentClassProvider
