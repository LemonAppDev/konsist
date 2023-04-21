package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoNamedDeclaration

fun Sequence<KoNamedDeclaration>.withName(vararg names: String) = filter {
    names.any { name -> it.name == name }
}

fun Sequence<KoNamedDeclaration>.withoutName(vararg names: String) = filter {
    names.none { name -> it.name == name }
}

fun Sequence<KoNamedDeclaration>.withNamePrefix(vararg prefixes: String) = filter {
    prefixes.any { prefix -> it.hasNameWithPrefix(prefix) }
}

fun Sequence<KoNamedDeclaration>.withoutNamePrefix(vararg prefixes: String) = filter {
    prefixes.none { prefix -> it.hasNameWithPrefix(prefix) }
}

fun Sequence<KoNamedDeclaration>.withNameSuffix(vararg suffixes: String) = filter {
    suffixes.any { suffix -> it.hasNameWithSuffix(suffix) }
}

fun Sequence<KoNamedDeclaration>.withoutNameSuffix(vararg suffixes: String) = filter {
    suffixes.none { suffix -> it.hasNameWithSuffix(suffix) }
}

fun Sequence<KoNamedDeclaration>.withNameContaining(vararg texts: String) = filter {
    texts.any { text -> it.hasNameContaining(text) }
}

fun Sequence<KoNamedDeclaration>.withoutNameContaining(vararg texts: String) = filter {
    texts.none { text -> it.hasNameContaining(text) }
}
