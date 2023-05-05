package com.lemonappdev.konsist.core.util

object LocationHelper {
    private const val wildcardSyntax = ".."

    /**
     * Use '..' as a wildcard for any number of characters.
     *
     * This class cna be used with both file paths and packages.
     */
    fun resideInLocation(desiredLocation: String, currentLocation: String): Boolean {
        val regexAnyNumberOfCharacters = ".*"

        val desiredPackageRegexString = desiredLocation
            .replace(wildcardSyntax, regexAnyNumberOfCharacters)

        val desiredPackageRegex = Regex(desiredPackageRegexString)

        return currentLocation.matches(desiredPackageRegex)
    }
}
