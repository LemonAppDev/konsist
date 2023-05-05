package com.lemonappdev.konsist.core.util

object PackageHelper {
    private const val multiplePackagesSyntax = ".."

    @Suppress("detekt.CyclomaticComplexMethod", "detekt.LongMethod")
    fun resideInPackage(desiredPackage: String, currentPackage: String): Boolean {
        val regexAnyNumberOfCharacters = ".*"
        val windowsFilePathSeparator = """\"""
        val linuxFilePathSeparator = "/"

        val desiredPackageRegexString = desiredPackage
            .replace(windowsFilePathSeparator, linuxFilePathSeparator)
            .replace(multiplePackagesSyntax, regexAnyNumberOfCharacters)

        val desiredPackageRegex = Regex(desiredPackageRegexString)

        return currentPackage.matches(desiredPackageRegex)
    }
}
