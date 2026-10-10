package com.lemonappdev.konsist.core.util

object LocationUtil {
    // Wildcard '..' with optional separator ('/' or '\') on each side, e.g. "..", "/..", "../", "\..\"
    private val WILDCARD_WITH_SEPARATORS_REGEX = Regex("""[/\\]?\.\.[/\\]?""")

    /**
     * Use '..' as a wildcard for any number of packages (or path segments), including none.
     *
     * This class can be used with both file paths and packages.
     * Paths accept both '/' and '\' separators. Leading separators and separators next to '..' are ignored,
     * e.g. "/feature/data/.." is the same as "feature/data..".
     */
    fun resideInLocation(
        desiredLocation: String,
        currentLocation: String,
    ): Boolean {
        require(desiredLocation.isNotEmpty()) { "Location name is empty" }
        require(desiredLocation != ".") { "Incorrect location format: $desiredLocation" }

        val desiredLocationCanonical =
            desiredLocation
                .lowercase()
                // Current location is matched without its leading separator, so the pattern must be too,
                // e.g. copied projectPath "/feature/data/.." -> "feature/data/.."
                .trimStart('/', '\\')
                // Separator next to '..' would become an extra '.' after conversion and break the wildcard,
                // e.g. "data/.." -> "data..." (segments "data" and "."), so it is removed: "data/.." -> "data.."
                // Package patterns have no separators, so they stay unchanged.
                .replace(WILDCARD_WITH_SEPARATORS_REGEX, "..")
                .toDotSeparatedLocation()

        // Checked after normalization, so "/.." and "\.." match any location too
        if (desiredLocationCanonical == "..") return true

        val desiredPackageRegexString = desiredLocationCanonical.toPackageRegex()

        val currentLocationCanonical =
            currentLocation
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
    val segments =
        split("..")
            .filter { it.isNotEmpty() }

    val prefixOptional = startsWith("..")
    val suffixOptional = endsWith("..")

    return buildString {
        // Leading '..': zero or more packages, each followed by '.', e.g. "", "com.", "com.app."
        if (prefixOptional) append("(?:[^.]+\\.)*?")

        segments.forEachIndexed { index, segment ->
            // '..' between segments: zero or more '.package', then '.', e.g. ".", ".domain.", ".domain.usecase."
            if (index > 0 && index < segments.size) append("(?:\\.[^.]+)*?\\.")
            append(Regex.escape(segment)) // Match the exact segment ('.' inside it is literal, not a wildcard)
        }

        if (suffixOptional) {
            // Trailing '..': zero or more '.package', e.g. "", ".data", ".data.repository"
            append("(?:\\.[^.]+)*?")
        } else {
            // No trailing '..': location must end with the last segment
            append("$")
        }
    }
}
