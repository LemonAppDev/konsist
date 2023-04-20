package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoNamedDeclaration

fun Sequence<KoNamedDeclaration>.withName(vararg names: String) = filter { koNamedDeclaration ->
    names.any { koNamedDeclaration.name == it }
}

fun Sequence<KoNamedDeclaration>.withoutName(vararg names: String) = filter { koNamedDeclaration ->
    names.none { koNamedDeclaration.name == it }
}

fun Sequence<KoNamedDeclaration>.withNamePrefix(vararg prefixes: String) = filter { koNamedDeclaration ->
    prefixes.any { koNamedDeclaration.hasNameWithPrefix(it) }
}

fun Sequence<KoNamedDeclaration>.withoutNamePrefix(vararg prefixes: String) = filter { koNamedDeclaration ->
    prefixes.none { koNamedDeclaration.hasNameWithPrefix(it) }
}

fun Sequence<KoNamedDeclaration>.withNameSuffix(vararg suffixes: String) = filter { koNamedDeclaration ->
    suffixes.any { koNamedDeclaration.hasNameWithSuffix(it) }
}

fun Sequence<KoNamedDeclaration>.withoutNameSuffix(vararg suffixes: String) = filter { koNamedDeclaration ->
    suffixes.none { koNamedDeclaration.hasNameWithSuffix(it) }
}

fun Sequence<KoNamedDeclaration>.withNameContaining(vararg texts: String) = filter { koNamedDeclaration ->
    texts.any { koNamedDeclaration.hasNameContaining(it) }
}

fun Sequence<KoNamedDeclaration>.withoutNameContaining(vararg texts: String) = filter { koNamedDeclaration ->
    texts.none { koNamedDeclaration.hasNameContaining(it) }
}
