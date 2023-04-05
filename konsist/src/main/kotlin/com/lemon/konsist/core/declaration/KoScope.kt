package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.const.KoModifier
import com.lemon.konsist.exception.KoPreconditionFailedException
import com.lemon.konsist.ext.isKotlinFile
import com.lemon.konsist.ext.toKoFile
import java.io.File

class KoScope(
    private val koFiles: List<KoFile>,
) {
    constructor(koFile: KoFile) : this(listOf(koFile))

    fun files() = koFiles.sortedBy { it.path }

    fun classes(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ) =
        koFiles.flatMap { it.classes(modifiers, includeNested, includeLocal) }

    fun interfaces(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ) =
        koFiles.flatMap { it.interfaces(modifiers, includeNested) }

    fun objects(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ) =
        koFiles.flatMap { it.objects(modifiers, includeNested) }

    fun functions(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ) =
        koFiles.flatMap { it.functions(modifiers, includeNested, includeLocal) }

    fun declarations(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ) =
        koFiles.flatMap { it.declarations(modifiers, includeNested) }

    fun properties(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ) =
        koFiles.flatMap { it.properties(modifiers, includeNested, includeLocal) }

    fun imports() = koFiles.flatMap { it.imports }

    fun packages() = koFiles.map { it.packageDirective }

    companion object {
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

        fun fromFile(path: String): KoScope {
            val file = File(path)

            if (!file.exists()) {
                throw KoPreconditionFailedException("File does not exist: $path")
            }

            val koKoFile = file.toKoFile()
            return KoScope(koKoFile)
        }

        fun fromProject() = KoScope(kotlinFiles.toList())

        fun fromPackage(packageNameStart: String): KoScope {
            val koFiles = kotlinFiles
                .filter { it.packageDirective?.fullyQualifiedName?.startsWith(packageNameStart) ?: false }
                .toList()
            return KoScope(koFiles)
        }

        fun fromPath(path: String): KoScope {
            val koFiles = kotlinFiles
                .filter {
                    println("it.path: ${it.path} \n")
                    it.path.startsWith(path)
                }
                .toList()
            return KoScope(koFiles)
        }
    }
}
