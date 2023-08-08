package com.lemonappdev.konsist.core.util

object LocationUtil {
    internal const val WILD_CARD_SYNTAX = ".."

    /**
     * Use '..' as a wildcard for any number of characters.
     *
     * This class can be used with both file paths and packages.
     */
    fun resideInLocation(desiredLocation: String, currentLocation: String): Boolean {
        require(desiredLocation.isNotEmpty()) { "Location name is empty" }
        require(desiredLocation != ".") { "Incorrect location format: $desiredLocation" }

        if (desiredLocation == "..") return true

        var desiredPackageRegexString = desiredLocation.toPackageRegex()

        return currentLocation.matches(desiredPackageRegexString)
    }
}

private fun String.toPackageRegex(): Regex {
    val segments = split("..").filter { it.isNotEmpty() }
    val prefixOptional = startsWith("..")
    val suffixOptional = endsWith("..")

    return buildString {
        if (prefixOptional) append("(?:[^.]+\\.)*?") // Match any package prefix or no prefix at all

        segments.forEachIndexed { index, segment ->
            if (index > 0 && index < segments.size) append("(?:\\.[^.]+)*?\\.") // Match any package in between segments with at least one dot
            append(Regex.escape(segment)) // Match the exact segment
        }

        if (suffixOptional) append("(?:\\.[^.]+)*?") // Match any package suffix or no suffix at all
        else append("$") // If there is no suffix, the pattern should match the end of the string
    }.toRegex()
}

fun String.replaceLast(oldValue: String, newValue: String, ignoreCase: Boolean = false): String {
    val index = lastIndexOf(oldValue, ignoreCase = ignoreCase)
    return if (index < 0) this else this.replaceRange(index, index + oldValue.length, newValue)
}
