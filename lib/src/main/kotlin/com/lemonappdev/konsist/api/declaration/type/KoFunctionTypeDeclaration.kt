package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoFunctionTypeDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider

/**
 * Represents a function type declaration in Kotlin.
 *
 * This interface defines the structure for representing function types, including their parameter types and return type.
 */
@Deprecated("Will be removed in version 0.19.0")
interface KoFunctionTypeDeclaration :
    KoBaseTypeDeclaration,
    KoContainingFileProvider,
    KoContainingDeclarationProvider,
    KoLocationProvider,
    KoPathProvider,
    KoModuleProvider,
    KoSourceSetProvider,
    KoFunctionTypeDeclarationProvider
