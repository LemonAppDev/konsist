package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAliasProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoWildcardProvider
import com.lemonappdev.konsist.core.provider.KoAliasProviderCore

/**
 * Represents a Kotlin import declaration.
 */
interface KoImportDeclaration :
    KoAliasProvider,
    KoBaseDeclaration,
    KoWildcardProvider,
    KoParentProvider
