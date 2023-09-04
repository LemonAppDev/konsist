package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to argument names.
 *
 */
interface KoArgumentNameProvider : KoBaseProvider {
    val argumentName: String?

    val value: String
}
