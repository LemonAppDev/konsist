package com.lemonappdev.konsist.util

object PackageHelper {
    @Suppress("detekt.CyclomaticComplexMethod")
    fun resideInPackage(declarationPackage: String, packageDirective: String): Boolean {
        val mainPackageSplitByOneDot = packageDirective
            .split(".")
            .filter { it.isNotBlank() }
            .toMutableList()

        val declarationPackageSplitByTwoDots = declarationPackage
            .split("..")
            .filter { it.isNotBlank() }

        val declarationPackageSplitByOneDot = declarationPackage
            .split(".")
            .filter { it.isNotBlank() }

        @Suppress("detekt.ComplexCondition")
        return if (
            declarationPackageSplitByTwoDots.size == 1 &&
            declarationPackage.startsWith("..") &&
            declarationPackage.endsWith("..")
        ) {
            mainPackageSplitByOneDot.contains(declarationPackageSplitByTwoDots.first())
        } else if (
            declarationPackageSplitByTwoDots.size == 1 &&
            declarationPackage.startsWith("..") &&
            !declarationPackage.endsWith("..")
        ) {
            packageDirective.endsWith(declarationPackageSplitByTwoDots.first())
        } else if (
            declarationPackageSplitByTwoDots.size == 1 &&
            !declarationPackage.startsWith("..") &&
            declarationPackage.endsWith("..")
        ) {
            packageDirective.startsWith(declarationPackageSplitByTwoDots.first())
        } else if (
            (
                packageDirective.startsWith(declarationPackageSplitByTwoDots.first()) &&
                    packageDirective.endsWith(declarationPackageSplitByTwoDots.last())
                ) ||
            (
                !packageDirective.startsWith(declarationPackageSplitByTwoDots.first()) &&
                    declarationPackage.startsWith("..")
                ) ||
            (
                !packageDirective.endsWith(declarationPackageSplitByTwoDots.last()) &&
                    declarationPackage.endsWith("..")
                )
        ) {
            var counter = 0
            declarationPackageSplitByOneDot.forEach {
                if (mainPackageSplitByOneDot.contains(it)) {
                    val index = mainPackageSplitByOneDot.indexOf(it)
                    mainPackageSplitByOneDot.removeAll { element -> mainPackageSplitByOneDot.indexOf(element) <= index }
                } else {
                    counter++
                }
            }
            counter == 0
        } else {
            false
        }
    }
}
