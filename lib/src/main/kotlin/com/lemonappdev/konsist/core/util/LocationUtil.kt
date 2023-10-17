package com.lemonappdev.konsist.core.util

object LocationUtil {

    /**
     *  Regex to match packages names ending with 2 (two) dots '.' at the end.
     *
     *   (?:) = non-capturing group.
     *    ^   = Matches the beginning of the string.
     *    \w  = Matches any word char (alpha & underscore).
     *    +   = Match 1 or more of the preceding token.
     *    |   = OR
     *  \.{2}?= allow using '..' as wildcard optionally (only one accepted)
     *    \w  = Matches any word char (alpha & underscore).
     *    +   = Match 1 or more of the preceding token.
     *    |   = OR
     *  \.{2} = escaped char '.' (dot) appearing 2 times
     *    $   = Matches end of string
     */
    internal const val REGEX_PACKAGE_NAME_END_TWO_DOTS = "(?:^\\w+|\\w+\\.\\w+\\.{2}?\\w+|\\w+\\.\\w+)+\\.{2}\$"

    /**
     * Use '..' as a wildcard for any number of characters.
     *
     * This class can be used with both file paths and packages.
     */
    fun resideInLocation(desiredLocation: String, currentLocation: String): Boolean {
        require(desiredLocation.isNotEmpty()) { "Location name is empty" }
        require(desiredLocation != ".") { "Incorrect location format: $desiredLocation" }

        if (desiredLocation == "..") return true

        val desiredPackageRegexString = desiredLocation
            .lowercase()
            .toDotSeparatedLocation()
            .toPackageRegex()

        val currentLocationCanonical = currentLocation
            .toDotSeparatedLocation()
            .removePrefix(".")
            .removeSuffix(".")
            .lowercase()

        return currentLocationCanonical.matches(desiredPackageRegexString.toRegex())
    }
}

private fun String.toDotSeparatedLocation() =
    replace("\\", "/")
        .replace("/", ".")

private fun String.toPackageRegex(): String {
    val segments = split("..")
        .filter { it.isNotEmpty() }

    val prefixOptional = startsWith("..")
    val suffixOptional = endsWith("..")

    return buildString {
        // Match any package prefix or no prefix at all
        if (prefixOptional) append("(?:[^.]+\\.)*?")

        segments.forEachIndexed { index, segment ->
            // Match any package in between segments with at least one dot
            if (index > 0 && index < segments.size) append("(?:\\.[^.]+)*?\\.")
            append(Regex.escape(segment)) // Match the exact segment
        }

        if (suffixOptional) {
            // Match any package suffix or no suffix at all
            append("(?:\\.[^.]+)*?")
        } else {
            // If there is no suffix, the pattern should match the end of the string
            append("$")
        }
    }
}

fun String.replaceLast(oldValue: String, newValue: String, ignoreCase: Boolean = false): String {
    val index = lastIndexOf(oldValue, ignoreCase = ignoreCase)
    return if (index < 0) this else this.replaceRange(index, index + oldValue.length, newValue)
}
