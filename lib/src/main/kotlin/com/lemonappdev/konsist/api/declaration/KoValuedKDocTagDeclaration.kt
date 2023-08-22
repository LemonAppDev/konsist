package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoKDocTagValueProvider

/**
 * Represents a documentation tag
 */
interface KoValuedKDocTagDeclaration : KoKDocTagDeclaration, KoKDocTagValueProvider
