package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider

/**
 * Represents a type argument declaration.
 */
interface KoTypeArgumentDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoNameProvider,
    KoSourceDeclarationProvider {
    /**
     * A list of type arguments associated with this declaration, if any.
     * If there are no type arguments, this returns `null`.
     *
     * Example:
     * In `List<String>`, `String` is a type argument for `List`.
     */
    val typeArguments: List<KoTypeArgumentDeclaration>?
}
