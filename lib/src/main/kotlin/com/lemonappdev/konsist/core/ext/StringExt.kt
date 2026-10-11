package com.lemonappdev.konsist.core.ext

/*
 * Checks if the qualified name ends with the given name at a dot boundary.
 * For `com.app.Test` and `Test` value will be true, for `com.app.UnitTest` and `Test` value will be false.
 */
internal fun String.endsWithName(
    name: String,
    ignoreCase: Boolean = false,
): Boolean = equals(name, ignoreCase) || endsWith(".$name", ignoreCase)
