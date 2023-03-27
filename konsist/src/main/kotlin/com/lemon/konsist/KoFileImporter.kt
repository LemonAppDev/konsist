package com.lemon.konsist

import com.lemon.konsist.core.declaration.KoScope
import com.lemon.konsist.ext.isKotlinFile
import com.lemon.konsist.ext.toKoFile
import java.io.File

object KoFileImporter {
    /**
     * Return repository root directory File
     */
    private val projectRootDirectoryFilePath by lazy {
        File("")
            .absoluteFile
            .path
    }

    private val kotlinFiles by lazy {
        val prodDirectory = File("$projectRootDirectoryFilePath/src/main/")

        val prodSourceSet = prodDirectory
            .walk()
            .filter { it.isKotlinFile }
            .map { it.toKoFile() }

        val testDirectory = File("$projectRootDirectoryFilePath/src/test/")

        val testSourceSet = testDirectory
            .walk()
            .filter { it.isKotlinFile }
            .map { it.toKoFile() }

        prodSourceSet + testSourceSet
    }

    fun importPackage(packageNameStart: String): KoScope {
        val koFiles = kotlinFiles
            .filter { it.packageName.startsWith(packageNameStart) }
            .toList()
        return KoScope(koFiles)
    }

    fun importFile(path: String): KoScope {
        val file = File(path)
        val koKoFile = file.toKoFile()
        return KoScope(koKoFile)
    }
}
