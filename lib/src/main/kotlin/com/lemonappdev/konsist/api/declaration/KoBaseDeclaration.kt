package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore

/**
 * Represents a base declaration.
 */
interface KoBaseDeclaration :
    KoContainingFileProvider,
    KoKDocProvider,
    KoLocationProvider,
    KoParentProvider,
    KoPathProvider,
    KoTextProvider
