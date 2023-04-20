package com.lemonappdev.konsist.util

object PackageHelper {
    @Suppress("detekt.CyclomaticComplexMethod")
    fun resideInPackage(declarationPackage: String, packageDirective: String, separator: Char = '.'): Boolean {
        val mainPackageSplitByOneSeparator = packageDirective
            .split(separator)
            .filter { it.isNotBlank() }
            .toMutableList()

        val declarationPackageSplitByTwoDots = declarationPackage
            .split("..")
            .filter { it.isNotBlank() }

        val declarationPackageSplitByOneSeparator = declarationPackage
            .replace("..", "$separator$separator")
            .split(separator)
            .filter { it.isNotBlank() }

        @Suppress("detekt.ComplexCondition")
        return if (
            declarationPackageSplitByTwoDots.size == 1 &&
            declarationPackage.startsWith("..") &&
            declarationPackage.endsWith("..")
        ) {
            if (!declarationPackageSplitByTwoDots.first().contains(separator)) {
                mainPackageSplitByOneSeparator.contains(declarationPackageSplitByTwoDots.first())
            } else {
                mainPackageSplitByOneSeparator.containsAll(declarationPackageSplitByOneSeparator)
            }
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
            declarationPackageSplitByOneSeparator.forEach {
                if (mainPackageSplitByOneSeparator.contains(it)) {
                    val index = mainPackageSplitByOneSeparator.indexOf(it)
                    mainPackageSplitByOneSeparator.removeAll { element -> mainPackageSplitByOneSeparator.indexOf(element) <= index }
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
