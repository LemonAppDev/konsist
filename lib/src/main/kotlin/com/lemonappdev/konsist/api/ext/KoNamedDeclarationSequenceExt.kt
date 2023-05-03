package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoNamedDeclarationImpl

fun <T : KoNamedDeclarationImpl> Sequence<T>.withName(vararg names: String) = filter {
    names.any { name -> it.name == name }
}

fun <T : KoNamedDeclarationImpl> Sequence<T>.withoutName(vararg names: String) = filter {
    names.none { name -> it.name == name }
}

fun <T : KoNamedDeclarationImpl> Sequence<T>.withNamePrefix(vararg prefixes: String) = filter {
    prefixes.any { prefix -> it.hasNameWithPrefix(prefix) }
}

fun <T : KoNamedDeclarationImpl> Sequence<T>.withoutNamePrefix(vararg prefixes: String) = filter {
    prefixes.none { prefix -> it.hasNameWithPrefix(prefix) }
}

fun <T : KoNamedDeclarationImpl> Sequence<T>.withNameSuffix(vararg suffixes: String) = filter {
    suffixes.any { suffix -> it.hasNameWithSuffix(suffix) }
}

fun <T : KoNamedDeclarationImpl> Sequence<T>.withoutNameSuffix(vararg suffixes: String) = filter {
    suffixes.none { suffix -> it.hasNameWithSuffix(suffix) }
}

fun <T : KoNamedDeclarationImpl> Sequence<T>.withNameContaining(vararg texts: String) = filter {
    texts.any { text -> it.hasNameContaining(text) }
}

fun <T : KoNamedDeclarationImpl> Sequence<T>.withoutNameContaining(vararg texts: String) = filter {
    texts.none { text -> it.hasNameContaining(text) }
}

fun <T : KoNamedDeclarationImpl> Sequence<T>.withNameMatching(vararg regexes: Regex) = filter {
    regexes.any { text -> it.hasNameMatching(text) }
}

fun <T : KoNamedDeclarationImpl> Sequence<T>.withoutNameMatching(vararg regexes: Regex) = filter {
    regexes.none { text -> it.hasNameMatching(text) }
}
