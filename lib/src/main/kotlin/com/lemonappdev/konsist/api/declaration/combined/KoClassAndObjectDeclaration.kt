package com.lemonappdev.konsist.api.declaration.combined

import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.api.provider.modifier.KoDataModifierProvider

interface KoClassAndObjectDeclaration :
    KoClassAndInterfaceAndObjectDeclaration,
    KoDataModifierProvider,
    KoInitBlockProvider,
    KoParentClassProvider
