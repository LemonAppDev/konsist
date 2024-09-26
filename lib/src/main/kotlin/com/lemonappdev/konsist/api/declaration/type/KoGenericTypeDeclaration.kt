package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider

/**
 * Represents a generic type declaration.
 *
 * This interface defines the structure for representing generic types.
 */
interface KoGenericTypeDeclaration :
    KoBaseTypeDeclaration,
    KoContainingFileProvider,
    KoContainingDeclarationProvider,
    KoLocationProvider,
    KoPathProvider,
    KoModuleProvider,
    KoSourceSetProvider {
    /**
     * The generic type being declared.
     *
     * For example:
     * 1) in `List<T>`, `List` is the generic type,
     * 2) in `List<Set<String>>`, `List` is the generic type.
     */
    val type: KoTypeDeclaration

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
