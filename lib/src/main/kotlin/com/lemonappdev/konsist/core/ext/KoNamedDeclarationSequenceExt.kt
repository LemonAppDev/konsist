package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoNamedDeclaration

fun Sequence<KoNamedDeclaration>.withNames(vararg names: String) = filter { koNamedDeclaration ->
    names.any { koNamedDeclaration.name == it }
}

fun Sequence<KoNamedDeclaration>.withoutNames(vararg names: String) = filter { koNamedDeclaration ->
    names.none { koNamedDeclaration.name == it }
}

fun Sequence<KoNamedDeclaration>.withNamePrefixes(vararg prefixes: String) = filter { koNamedDeclaration ->
    prefixes.all { koNamedDeclaration.hasNameWithPrefix(it) }
}

fun Sequence<KoNamedDeclaration>.withSomeNamePrefixes(vararg prefixes: String) = filter { koNamedDeclaration ->
    prefixes.any { koNamedDeclaration.hasNameWithPrefix(it) }
}

fun Sequence<KoNamedDeclaration>.withoutNamePrefixes(vararg prefixes: String) = filter { koNamedDeclaration ->
    prefixes.none { koNamedDeclaration.hasNameWithPrefix(it) }
}

fun Sequence<KoNamedDeclaration>.withNameSuffixes(vararg suffixes: String) = filter { koNamedDeclaration ->
    suffixes.all { koNamedDeclaration.hasNameWithSuffix(it) }
}

fun Sequence<KoNamedDeclaration>.withSomeNameSuffixes(vararg suffixes: String) = filter { koNamedDeclaration ->
    suffixes.any { koNamedDeclaration.hasNameWithSuffix(it) }
}

fun Sequence<KoNamedDeclaration>.withoutNameSuffixes(vararg suffixes: String) = filter { koNamedDeclaration ->
    suffixes.none { koNamedDeclaration.hasNameWithSuffix(it) }
}

fun Sequence<KoNamedDeclaration>.withNameContains(vararg texts: String) = filter { koNamedDeclaration ->
    texts.all { koNamedDeclaration.hasNameContaining(it) }
}

fun Sequence<KoNamedDeclaration>.withSomeNameContains(vararg texts: String) = filter { koNamedDeclaration ->
    texts.any { koNamedDeclaration.hasNameContaining(it) }
}

fun Sequence<KoNamedDeclaration>.withoutNameContains(vararg texts: String) = filter { koNamedDeclaration ->
    texts.none { koNamedDeclaration.hasNameContaining(it) }
}
