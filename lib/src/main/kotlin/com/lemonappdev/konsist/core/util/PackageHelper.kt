package com.lemonappdev.konsist.core.util

object PackageHelper {
    private const val multiplePackagesSyntax = ".."

    @Suppress("detekt.CyclomaticComplexMethod", "detekt.LongMethod")
    fun resideInPackage(desiredPackage: String, currentPackage: String, separator: Char = '.'): Boolean {
        val regexAnyNumberOfCharacters = ".*"
        val desiredPackageRegexString = desiredPackage.replace(multiplePackagesSyntax, regexAnyNumberOfCharacters)
        val desiredPackageRegex = Regex(desiredPackageRegexString)

        return currentPackage.matches(desiredPackageRegex)
    }
}
