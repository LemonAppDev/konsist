package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider

/**
 * Represents a Kotlin type declaration.
 *
 * This interface defines the structure for representing various Kotlin types, such as String, Int and collections, such as List, Map etc.
 */
interface KoKotlinTypeDeclaration :
    KoBaseTypeDeclaration,
    KoFullyQualifiedNameProvider
