package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration

interface KoGenericTypeDeclarationProvider : KoBaseProvider {
    /**
     * The generic type being declared.
     *
     * For example:
     * 1) in `List<T>`, `List` is the generic type,
     * 2) in `List<Set<String>>`, `List` is the generic type.
     */
    val genericType: KoTypeDeclaration

    /**
     * The type argument for the generic type.
     *
     * For example:
     * 1) in `List<String>`, `String` is the type argument,
     * 2) in `List<Set<String>>`, `Set<String>` is the type argument,
     */
    val typeArguments: List<KoTypeDeclaration>

    /**
     * A list of type declarations representing all type arguments, including nested ones.
     *
     * This property recursively retrieves type arguments from the generic type declaration.
     * For example:
     * 1) In `List<String>`, the list will contain `String`.
     * 2) In `List<Set<String>>`, the list will contain `Set` and `String`.
     */
    val typeArgumentsFlatten: List<KoTypeDeclaration>
}
