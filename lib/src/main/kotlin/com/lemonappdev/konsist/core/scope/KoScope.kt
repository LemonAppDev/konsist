package com.lemonappdev.konsist.core.scope

import com.lemonappdev.konsist.core.const.KoModifier
import com.lemonappdev.konsist.core.declaration.KoClassDeclaration
import com.lemonappdev.konsist.core.declaration.KoCompanionObjectDeclaration
import com.lemonappdev.konsist.core.declaration.KoDeclaration
import com.lemonappdev.konsist.core.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.core.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclaration
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
    private var koFiles: Sequence<KoFileDeclaration>,
) {
    constructor(koFileDeclaration: KoFileDeclaration) : this(sequenceOf(koFileDeclaration))

    fun files(): Sequence<KoFileDeclaration> = koFiles.sortedBy { it.filePath }

    fun classes(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoClassDeclaration> =
        koFiles.flatMap { it.classes(modifiers, includeNested, includeLocal) }

    fun interfaces(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoInterfaceDeclaration> =
        koFiles.flatMap { it.interfaces(modifiers, includeNested) }

    fun objects(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoObjectDeclaration> =
        koFiles.flatMap { it.objects(modifiers, includeNested) }

    fun companionObjects(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoCompanionObjectDeclaration> =
        koFiles.flatMap { it.companionObjects(modifiers, includeNested) }

    fun functions(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoFunctionDeclaration> =
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
    ): Sequence<KoPropertyDeclaration> =
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

        /**
         * Returns a [KoScope] containing all of Kotlin files in the project.
         */
        fun fromProject(module: String? = null, sourceSet: String? = null): KoScope {
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

        /**
         * Returns a [KoScope] containing all of Kotlin production files in the project.
         */
        fun fromProduction(module: String? = null, sourceSet: String? = null): KoScope {
            val koFiles = fromProject(module, sourceSet)
                .files()
                .filterNot { isTestFile(it) }

            return KoScope(koFiles)
        }

        /**
         * Returns a [KoScope] containing all of Kotlin test files in the project.
         */
        fun fromTest(module: String? = null, sourceSet: String? = null): KoScope {
            val koFiles = fromProject(module, sourceSet)
                .files()
                .filter { isTestFile(it) }

            return KoScope(koFiles)
        }

        /**
         * Returns a [KoScope] containing all of Kotlin files in the given package.
         */
        fun fromPackage(packageName: String): KoScope {
            val koFiles = projectKotlinFiles
                .filter {
                    it.packagee?.let { koPackage ->
                        PackageHelper.resideInPackage(packageName, koPackage.qualifiedName)
                    } ?: false
                }

            return KoScope(koFiles)
        }

        /**
         * Returns a [KoScope] containing all of Kotlin files in the given directory.
         */
        fun fromPath(path: String): KoScope {
            val koFiles = projectKotlinFiles
                .filter { it.filePath.startsWith(path) }

            return KoScope(koFiles)
        }

        /**
         * Returns a [KoScope] of a given file.
         */
        fun fromFile(path: String): KoScope {
            val file = File(path)

            if (!file.exists()) {
                throw KoPreconditionFailedException("File does not exist: $path")
            }

            val koKoFile = file.toKoFile()

            return KoScope(koKoFile)
        }

        private fun isTestFile(it: KoFileDeclaration): Boolean {
            val path = it.filePath.lowercase()
            return path.contains("test/") || path.contains("/test")
        }
    }

    override fun toString(): String = files()
        .toList()
        .joinToString("\n") { it.filePath }

    fun print() {
        println(toString())
    }
}
