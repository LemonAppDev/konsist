package com.lemonappdev.konsist.core.util

object LocationUtil {

    /**
     *  Regex to match packages names ending with 2 (two) dots '.' at the end.
     * Regex to match packages names ending with 2 (two) dots '.' at the end.
     *
     *    .*   = Matches zero or more of any character
     *    \w+  = Matches one or more word characters (alphanumeric characters or underscore).
     *   \.{2} = escaped char '.' (dot) appearing 2 times
     *     $   = Matches end of string
     */
    internal const val REGEX_PACKAGE_NAME_END_TWO_DOTS = ".*\\w+\\.{2}\$"

    /**
     * Regex to match packages names without single dot at the beginning.
     *
     *    ^   = Matches beginning of string
     *  (?:)  = non-capturing group.
     *   \w   = Matches one word character (alphanumeric character or underscore).
     *    |   = OR
     *  \.{2} = escaped char '.' (dot) appearing 2 times
     *   \w   = Matches one word character (alphanumeric character or underscore).
     *   .*   = Matches zero or more of any character
     */
    internal const val REGEX_PACKAGE_NAME_WITHOUT_SINGLE_DOT_AT_THE_BEGINNING = "^(?:\\w|\\.{2}\\w).*"

    /**
     *  Regex to match packages names not containing more than two dots in on place.
     *
     *   (?:)    = non-capturing group.
     *    \w+    = Matches one or more word characters (alphanumeric characters or underscore).
     *     |     = OR
     * \.{2}\w+  = Matches two dots followed by one or more word characters.
     *  \.{0,2}  = Matches zero, one, or two dots optionally.
     *    \w+    = Matches one or more word characters (alphanumeric characters or underscore).
     *     +     = Match 1 or more of the preceding token.
     *   \.{2}   = escaped char '.' (dot) appearing 2 times
     *     $     = Matches end of string
     */
    internal const val REGEX_PACKAGE_NAME_WITHOUT_FEW_DOTS_IN_ONE_PLACE = "(?:\\w+|\\.{2}\\w+)(?:\\.{0,2}\\w+)+\\.{2}\$"

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

