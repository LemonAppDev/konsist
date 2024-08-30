package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAliasProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoIsWildcardProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoMatchesProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoWildcardProvider

/**
 * Represents a Kotlin import declaration.
 */
interface KoImportDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoAliasProvider,
    KoContainingFileProvider,
    KoLocationProvider,
    KoMatchesProvider,
    KoNameProvider,
    KoPathProvider,
    KoModuleProvider,
    KoSourceSetProvider,
    KoTextProvider,
    KoWildcardProvider,
    KoIsWildcardProvider,
    KoRepresentsTypeProvider
