package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.const.KoModifier
import com.lemon.konsist.exception.KoPreconditionFailedException
import com.lemon.konsist.ext.isKotlinFile
import com.lemon.konsist.ext.toKoFile
import com.lemon.konsist.util.PackageHelper
import java.io.File

class KoScope(
    private var koFiles: Sequence<KoFile>,
) {
    constructor(koFile: KoFile) : this(sequenceOf(koFile))

    fun files(): Sequence<KoFile> = koFiles.sortedBy { it.path }

    fun classes(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoClass> =
        koFiles.flatMap { it.classes(modifiers, includeNested, includeLocal) }

    fun interfaces(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoInterface> =
        koFiles.flatMap { it.interfaces(modifiers, includeNested) }

    fun objects(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoObject> =
        koFiles.flatMap { it.objects(modifiers, includeNested) }

    fun companionObjects(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoCompanionObject> =
        koFiles.flatMap { it.companionObjects(modifiers, includeNested) }

    fun functions(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoFunction> =
        koFiles.flatMap { it.functions(modifiers, includeNested, includeLocal) }

    fun declarations(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoDeclaration> =
        koFiles.flatMap { it.declarations(modifiers, includeNested) }

    fun properties(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoProperty> =
        koFiles.flatMap { it.properties(modifiers, includeNested, includeLocal) }

    fun imports() = koFiles.flatMap { it.imports }

    fun packages() = koFiles.mapNotNull { it.packageDirective }

    fun typeAliases() = koFiles.flatMap { it.typeAliases }

    override fun toString(): String = files()
        .toList()
        .joinToString("\n") { it.path }

    operator fun plus(scope: KoScope): KoScope = KoScope(files() + scope.files())

    operator fun minus(scope: KoScope): KoScope {
        return KoScope(files() - scope.files().toSet())
    }

    operator fun plusAssign(scope: KoScope) {
        koFiles += scope.files()
    }

    operator fun minusAssign(scope: KoScope) {
        koFiles -= scope.files()
    }

    companion object {
        /**
         * Return repository root directory File
         */
        private val projectRootDirectoryFilePath by lazy {
            File("")
                .absoluteFile
                .path
                .dropLastWhile { it != '/' }
        }

        private val projectKotlinFiles by lazy {
            val prodDirectory = File(projectRootDirectoryFilePath)

            prodDirectory
                .walk()
                .filter { it.isKotlinFile }
                .map { it.toKoFile() }
        }

        fun fromProjectFiles(module: String? = null, sourceSet: String? = null): KoScope {
            if (module == null && sourceSet == null) {
                return KoScope(projectKotlinFiles)
            }

            var pathPrefix = "$projectRootDirectoryFilePath${module?.lowercase()}"

            if (sourceSet != null) {
                pathPrefix = "$pathPrefix/src/$sourceSet/"
            }

            val koFiles = projectKotlinFiles
                .filter { it.path.startsWith(pathPrefix) }

            return KoScope(koFiles)
        }

        fun fromProjectTestFiles(module: String? = null, sourceSet: String? = null): KoScope {
            val koFiles = fromProjectFiles(module, sourceSet)
                .files()
                .filter { isTestFile(it) }

            return KoScope(koFiles)
        }

        fun fromProjectProductionFiles(module: String? = null, sourceSet: String? = null): KoScope {
            val koFiles = fromProjectFiles(module, sourceSet)
                .files()
                .filter { !isTestFile(it) }

            return KoScope(koFiles)
        }

        private fun isTestFile(it: KoFile): Boolean {
            val path = it.path.lowercase()
            return path.contains("test/") || path.contains("/test")
        }

        fun fromPackage(packageName: String): KoScope {
            val koFiles = projectKotlinFiles
                .filter {
                    it.packageDirective?.let { koPackage ->
                        PackageHelper.resideInPackage(packageName, koPackage.qualifiedName)
                    } ?: false
                }

            return KoScope(koFiles)
        }

        fun fromFile(path: String): KoScope {
            val file = File(path)

            if (!file.exists()) {
                throw KoPreconditionFailedException("File does not exist: $path")
            }

            val koKoFile = file.toKoFile()

            return KoScope(koKoFile)
        }

        fun fromPath(path: String): KoScope {
            val koFiles = projectKotlinFiles
                .filter { it.path.startsWith(path) }

            return KoScope(koFiles)
        }
    }
}
