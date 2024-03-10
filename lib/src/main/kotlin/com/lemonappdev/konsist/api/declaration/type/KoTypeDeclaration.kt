package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoNullableProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTypeProvider

/**
 * Represents a Kotlin type declaration.
 */
interface KoTypeDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoNameProvider,
    KoTextProvider,
    KoPathProvider,
    KoLocationProvider,
    KoNullableProvider,
    KoContainingFileProvider,
    KoContainingDeclarationProvider,
    KoModuleProvider,
    KoSourceSetProvider,
    KoTypeProvider,
    KoGenericTypeProvider,
    KoSourceAndAliasTypeProvider,
    KoPackageProvider,
    KoResideInPackageProvider,
    KoSourceDeclarationProvider
