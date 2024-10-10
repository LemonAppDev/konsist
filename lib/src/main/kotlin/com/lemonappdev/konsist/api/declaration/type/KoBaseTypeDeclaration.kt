package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Represents a Kotlin base type declaration.
 *
 * This interface serves as the base for various types of declarations in Kotlin, providing essential functionalities
 * and information about a declaration.
 */
interface KoBaseTypeDeclaration :
    KoBaseDeclaration,
    KoSourceDeclaration,
    KoBaseProvider,
    KoNameProvider,
    KoTextProvider,
    KoPackageProvider,
    KoResideInPackageProvider
