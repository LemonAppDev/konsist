package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseSourceDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider

/**
 * Represents a Kotlin base type declaration.
 *
 * This interface serves as the base for various types of declarations in Kotlin, providing essential functionalities
 * and information about a declaration.
 */
interface KoBaseTypeDeclaration :
    KoBaseSourceDeclaration,
    KoSourceDeclaration,
    KoPackageProvider,
    KoResideInPackageProvider
