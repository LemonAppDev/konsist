package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration

/**
 * Sequence containing all declarations that have 'data' modifier.
 */
fun Sequence<KoObjectDeclaration>.withDataModifier(): Sequence<KoObjectDeclaration> = filter { it.hasDataModifier() }

/**
 * Sequence containing all declarations that don't have 'data' modifier.
 */
fun Sequence<KoObjectDeclaration>.withoutDataModifier(): Sequence<KoObjectDeclaration> = filterNot { it.hasDataModifier() }

/**
 * Sequence containing all declarations that have 'companion' modifier.
 */
fun Sequence<KoObjectDeclaration>.withCompanionModifier(): Sequence<KoObjectDeclaration> = filter { it.hasCompanionModifier() }

/**
 * Sequence containing all declarations that don't have 'companion' modifier.
 */
fun Sequence<KoObjectDeclaration>.withoutCompanionModifier(): Sequence<KoObjectDeclaration> = filterNot { it.hasCompanionModifier() }

/**
 * Sequence containing declarations that have 'companion' modifier and not defaulted name.
 */
fun Sequence<KoObjectDeclaration>.withNamedCompanionObject(): Sequence<KoObjectDeclaration> =
    filter { it.hasCompanionModifier() && it.name != "Companion" }

/**
 * Sequence containing declarations that have 'companion' modifier and defaulted name.
 */
fun Sequence<KoObjectDeclaration>.withoutNamedCompanionObject(): Sequence<KoObjectDeclaration> =
    filter { it.hasCompanionModifier() && it.name == "Companion" }

/**
 * Sequence containing declarations that have 'companion' modifier and name.
 */
fun Sequence<KoObjectDeclaration>.withNamedCompanionObjectAs(vararg names: String): Sequence<KoObjectDeclaration> = filter {
    it.hasCompanionModifier() && names.any { name -> it.name == name }
}

/**
 * Sequence containing declarations that have 'companion' modifier and don't have name.
 */
fun Sequence<KoObjectDeclaration>.withoutNamedCompanionObjectAs(vararg names: String): Sequence<KoObjectDeclaration> = filter {
    it.hasCompanionModifier() && names.none { name -> it.name == name }
}
