package com.lemonappdev.konsist.core.util

object LocationHelper {
    private const val wildcardSyntax = ".."

    /**
     * Use '..' as a wildcard for any number of characters.
     *
     * This class cna be used with both file paths and packages.
     */
    fun resideInLocation(desiredLocation: String, currentLocaion: String): Boolean {
        val regexAnyNumberOfCharacters = ".*"
        val windowsFilePathSeparator = """\"""
        val linuxFilePathSeparator = "/"

        val desiredPackageRegexString = desiredLocation
            .replace(windowsFilePathSeparator, linuxFilePathSeparator)
            .replace(wildcardSyntax, regexAnyNumberOfCharacters)

        val desiredPackageRegex = Regex(desiredPackageRegexString)

        return currentLocaion.matches(desiredPackageRegex)
    }
}
