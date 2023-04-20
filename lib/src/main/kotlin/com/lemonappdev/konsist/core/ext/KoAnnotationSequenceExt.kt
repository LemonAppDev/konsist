package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoAnnotation

fun Sequence<KoAnnotation>.withType(vararg types: String) = filter { koAnnotation ->
    types.any { koAnnotation.representsType(it) }
}

fun Sequence<KoAnnotation>.withoutType(vararg types: String) = filter { koAnnotation ->
    types.none { koAnnotation.representsType(it) }
}

inline fun <reified T> Sequence<KoAnnotation>.withType() = filter { it.representsType<T>() }

inline fun <reified T> Sequence<KoAnnotation>.withoutType() = filterNot { it.representsType<T>() }

fun Sequence<KoAnnotation>.withName(vararg names: String) = filter { koAnnotation ->
    names.any { koAnnotation.name == it }
}

fun Sequence<KoAnnotation>.withoutName(vararg names: String) = filter { koAnnotation ->
    names.none { koAnnotation.name == it }
}

fun Sequence<KoAnnotation>.withFullyQualifiedClassName(vararg names: String) = filter { koAnnotation ->
    names.any { koAnnotation.fullyQualifiedName == it }
}

fun Sequence<KoAnnotation>.withoutFullyQualifiedClassName(vararg names: String) = filter { koAnnotation ->
    names.none { koAnnotation.fullyQualifiedName == it }
}
