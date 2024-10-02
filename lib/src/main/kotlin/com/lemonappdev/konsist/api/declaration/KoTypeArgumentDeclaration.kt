package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider

/**
 * Represents a type argument declaration.
 */
interface KoTypeArgumentDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoNameProvider {
    /**
     * A list of type arguments associated with this declaration, if any.
     * If there are no type arguments, this returns `null`.
     *
     * Example:
     * In `List<String>`, `String` is a type argument for `List`.
     */
    val typeArguments: List<KoTypeArgumentDeclaration>?

    /**
     * The source type declaration associated with this type argument.
     * This represents the original type that this type argument refers to.
     *
     * Example:
     * In `List<String>`, `List` is the source type declaration for the `String` type argument.
     */
    val sourceDeclaration: KoTypeDeclaration
}
