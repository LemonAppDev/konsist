package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration

/**
 * Sequence containing declarations that have name.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withName(vararg names: String) = filter {
    names.any { name -> it.name == name }
}

/**
 * Sequence containing declarations that don't have name.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withoutName(vararg names: String) = filter {
    names.none { name -> it.name == name }
}

/**
 * Sequence containing declarations that have name with prefix.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withNamePrefix(vararg prefixes: String) = filter {
    prefixes.any { prefix -> it.hasNameWithPrefix(prefix) }
}

/**
 * Sequence containing declarations that don't have name with prefix.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withoutNamePrefix(vararg prefixes: String) = filter {
    prefixes.none { prefix -> it.hasNameWithPrefix(prefix) }
}

/**
 * Sequence containing declarations that have name with suffix.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withNameSuffix(vararg suffixes: String) = filter {
    suffixes.any { suffix -> it.hasNameWithSuffix(suffix) }
}

/**
 * Sequence containing declarations that don't have name with suffix.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withoutNameSuffix(vararg suffixes: String) = filter {
    suffixes.none { suffix -> it.hasNameWithSuffix(suffix) }
}

/**
 * Sequence containing declarations that have name containing String.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withNameContaining(vararg texts: String) = filter {
    texts.any { text -> it.hasNameContaining(text) }
}

/**
 * Sequence containing declarations that don't have name containing String.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withoutNameContaining(vararg texts: String) = filter {
    texts.none { text -> it.hasNameContaining(text) }
}

/**
 * Sequence containing declarations that have name matching regex.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withNameMatching(vararg regexes: Regex) = filter {
    regexes.any { text -> it.hasNameMatching(text) }
}

/**
 * Sequence containing declarations that don't have name matching regex.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withoutNameMatching(vararg regexes: Regex) = filter {
    regexes.none { text -> it.hasNameMatching(text) }
}
