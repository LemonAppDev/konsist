package com.lemonappdev.konsist.core.util

object PackageHelper {
    @Suppress("detekt.CyclomaticComplexMethod", "detekt.LongMethod")
    fun resideInPackage(declarationPackage: String, packagee: String, separator: Char = '.'): Boolean {
        val mainPackageSplitByOneSeparator = packagee
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
            packagee.endsWith(declarationPackageSplitByTwoDots.first())
        } else if (
            declarationPackageSplitByTwoDots.size == 1 &&
            !declarationPackage.startsWith("..") &&
            declarationPackage.endsWith("..")
        ) {
            packagee.startsWith(declarationPackageSplitByTwoDots.first())
        } else if (
            (
                packagee.startsWith(declarationPackageSplitByTwoDots.first()) &&
                    packagee.endsWith(declarationPackageSplitByTwoDots.last())
                ) ||
            (
                !packagee.startsWith(declarationPackageSplitByTwoDots.first()) &&
                    declarationPackage.startsWith("..")
                ) ||
            (
                !packagee.endsWith(declarationPackageSplitByTwoDots.last()) &&
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
