package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.const.KoModifier
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.ext.isKotlinFile
import com.lemonappdev.konsist.core.ext.toKoFile
import com.lemonappdev.konsist.core.filesystem.KoFileFactory
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.filesystem.PathVerifier
import com.lemonappdev.konsist.core.filesystem.ProjectRootDirProviderFactory
import com.lemonappdev.konsist.util.PackageHelper
import java.io.File

@Suppress("detekt.TooManyFunctions")
class KoScope(
    private var koFiles: Sequence<KoFile>,
) {
    constructor(koFile: KoFile) : this(sequenceOf(koFile))

    fun files(): Sequence<KoFile> = koFiles.sortedBy { it.filePath }

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

    fun packages() = koFiles.mapNotNull { it.packagee }

    fun typeAliases() = koFiles.flatMap { it.typeAliases }

    operator fun plus(scope: KoScope): KoScope = KoScope(files() + scope.files())

    operator fun minus(scope: KoScope): KoScope = KoScope(files() - scope.files().toSet())

    operator fun plusAssign(scope: KoScope) {
        koFiles += scope.files()
    }

    operator fun minusAssign(scope: KoScope) {
        koFiles -= scope.files()
    }

    companion object {
        private val pathVerifier = PathVerifier()

        private val pathProvider by lazy {
            PathProvider(
                KoFileFactory(),
                ProjectRootDirProviderFactory(pathVerifier),
            )
        }

        private val projectKotlinFiles by lazy {
            val prodDirectory = pathProvider.rootProjectDirectory

            prodDirectory
                .walk()
                .filter { it.isKotlinFile }
                .map { it.toKoFile() }
        }

        fun fromProjectFiles(module: String? = null, sourceSet: String? = null): KoScope {
            if (module == null && sourceSet == null) {
                return KoScope(projectKotlinFiles)
            }

            var pathPrefix = "${pathProvider.rootProjectPath}/${module?.lowercase()}"

            if (sourceSet != null) {
                pathPrefix = "$pathPrefix/src/$sourceSet/"
            }

            val koFiles = projectKotlinFiles
                .filter { it.filePath.startsWith(pathPrefix) }

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
                .filterNot { isTestFile(it) }

            return KoScope(koFiles)
        }

        private fun isTestFile(it: KoFile): Boolean {
            val path = it.filePath.lowercase()
            return path.contains("test/") || path.contains("/test")
        }

        fun fromPackage(packageName: String): KoScope {
            val koFiles = projectKotlinFiles
                .filter {
                    it.packagee?.let { koPackage ->
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
                .filter { it.filePath.startsWith(path) }

            return KoScope(koFiles)
        }
    }

    override fun toString(): String = files()
        .toList()
        .joinToString("\n") { it.filePath }

    fun print() {
        println(toString())
    }
}
