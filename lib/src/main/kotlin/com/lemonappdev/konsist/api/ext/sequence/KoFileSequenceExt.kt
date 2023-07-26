package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.container.KoFile

/**
 * Sequence containing files with extension.
 *
 * @param extension The extension to include.
 * @param extensions The extensions to include.
 * @return A sequence containing files with extensions matching the specified extensions.
 */
fun <T : KoFile> Sequence<T>.withExtension(extension: String, vararg extensions: String): Sequence<T> = filter {
    it.hasExtension(extension) || extensions.any { extension -> it.hasExtension(extension) }
}

/**
 * Sequence containing files without extension.
 *
 * @param extension The extension to exclude.
 * @param extensions The extensions to exclude.
 * @return A sequence containing files without extensions matching the specified extensions.
 */
fun <T : KoFile> Sequence<T>.withoutExtension(extension: String, vararg extensions: String): Sequence<T> = filter {
    !it.hasExtension(extension) && extensions.none { extension -> it.hasExtension(extension) }
}

/**
 * Sequence containing files with module.
 *
 * @param module The module to include.
 * @param modules The modules to include.
 * @return A sequence containing files that reside in any of the specified modules.
 */
fun <T : KoFile> Sequence<T>.withModule(module: String, vararg modules: String): Sequence<T> = filter {
    it.resideInModule(module) || modules.any { module -> it.resideInModule(module) }
}

/**
 * Sequence containing files without module.
 *
 * @param module The module to exclude.
 * @param modules The modules to exclude.
 * @return A sequence containing files that don't reside in any of the specified modules.
 */
fun <T : KoFile> Sequence<T>.withoutModule(module: String, vararg modules: String): Sequence<T> = filter {
    !it.resideInModule(module) && modules.none { module -> it.resideInModule(module) }
}

/**
 * Sequence containing files with source set.
 *
 * @param sourceSet The sourceSet to include.
 * @param sourceSets The sourceSets to include.
 * @return A sequence containing files that reside in any of the specified source sets.
 */
fun <T : KoFile> Sequence<T>.withSourceSet(sourceSet: String, vararg sourceSets: String): Sequence<T> = filter {
    it.resideInSourceSet(sourceSet) || sourceSets.any { sourceSet -> it.resideInSourceSet(sourceSet) }
}

/**
 * Sequence containing files without source set.
 *
 * @param sourceSet The sourceSet to exclude.
 * @param sourceSets The sourceSets to exclude.
 * @return A sequence containing files that don't reside in any of the specified source sets.
 */
fun <T : KoFile> Sequence<T>.withoutSourceSet(sourceSet: String, vararg sourceSets: String): Sequence<T> = filter {
    !it.resideInSourceSet(sourceSet) && sourceSets.none { sourceSet -> it.resideInSourceSet(sourceSet) }
}

/**
 * Sequence containing files with any import.
 *
 * @return A sequence containing files with any import.
 */
fun Sequence<KoFile>.withImports(): Sequence<KoFile> = filter { it.hasImports() }

/**
 * Sequence containing files with all specified imports.
 *
 * @param import The import to include.
 * @param imports The import(s) to include.
 * @return A sequence containing files with the specified import(s).
 */
fun Sequence<KoFile>.withAllImports(import: String, vararg imports: String): Sequence<KoFile> = filter {
    it.hasImports(import, *imports)
}

/**
 * Sequence containing files with some imports.
 *
 * @param import The import to include.
 * @param imports The imports to include.
 * @return A sequence containing files with at least one of the specified import(s).
 */
fun Sequence<KoFile>.withSomeImports(import: String, vararg imports: String): Sequence<KoFile> = filter {
    it.hasImports(import) || imports.any { import -> it.hasImports(import) }
}

/**
 * Sequence containing files with no import.
 *
 * @return A sequence containing files with no import.
 */
fun Sequence<KoFile>.withoutImports(): Sequence<KoFile> = filterNot { it.hasImports() }

/**
 * Sequence containing files without all specified imports.
 *
 * @param import The import to exclude.
 * @param imports The import(s) to exclude.
 * @return A sequence containing files without specified import(s).
 */
fun Sequence<KoFile>.withoutAllImports(import: String, vararg imports: String): Sequence<KoFile> = filterNot {
    it.hasImports(import, *imports)
}

/**
 * Sequence containing files without some imports.
 *
 * @param import The import to exclude.
 * @param imports The imports to exclude.
 * @return A sequence containing files without at least one of the specified import(s).
 */
fun Sequence<KoFile>.withoutSomeImports(import: String, vararg imports: String): Sequence<KoFile> = filter {
    !it.hasImports(import) && if (imports.isNotEmpty()) {
        imports.any { import -> !it.hasImports(import) }
    } else {
        true
    }
}

/**
 * Sequence containing files with package.
 *
 * @param packages The package names to include.
 * @return A sequence containing files with a package matching any of the specified package names
 * (or any package if [packages] is empty).
 */
fun Sequence<KoFile>.withPackage(vararg packages: String): Sequence<KoFile> = filter {
    when {
        packages.isEmpty() -> it.packagee != null
        else -> packages.any { packagee -> it.hasPackage(packagee) }
    }
}

/**
 * Sequence containing files with some package.
 *
 * @param packages The package names to exclude.
 * @return A sequence containing files without a package matching any of the specified package names
 * (or none package if [packages] is empty).
 */
fun Sequence<KoFile>.withoutPackage(vararg packages: String): Sequence<KoFile> = filter {
    when {
        packages.isEmpty() -> it.packagee == null
        else -> packages.none { packagee -> it.hasPackage(packagee) }
    }
}

/**
 * Sequence containing files with any type alias.
 *
 * @return A sequence containing files with any type alias.
 */
fun Sequence<KoFile>.withTypeAliases(): Sequence<KoFile> = filter { it.hasTypeAliases() }

/**
 * Sequence containing files with all specified type aliases.
 *
 * @param typeAlias The type alias to include.
 * @param typeAliases The type aliases to include.
 * @return A sequence containing files with all the specified type alias(es).
 */
fun Sequence<KoFile>.withAllTypeAliases(typeAlias: String, vararg typeAliases: String): Sequence<KoFile> = filter {
    it.hasTypeAliases(typeAlias, *typeAliases)
}

/**
 * Sequence containing files with some type aliases.
 *
 * @param typeAlias The type alias to include.
 * @param typeAliases The type aliases to include.
 * @return A sequence containing files with at least one of the specified type alias(es).
 */
fun Sequence<KoFile>.withSomeTypeAliases(typeAlias: String, vararg typeAliases: String): Sequence<KoFile> = filter {
    it.hasTypeAliases(typeAlias) || typeAliases.any { typeAlias -> it.hasTypeAliases(typeAlias) }
}

/**
 * Sequence containing files with no type aliases.
 *
 * @return A sequence containing files with no type aliases.
 */
fun Sequence<KoFile>.withoutTypeAliases(): Sequence<KoFile> = filterNot { it.hasTypeAliases() }

/**
 * Sequence containing files without all the specified type aliases.
 *
 * @param typeAlias The type alias to exclude.
 * @param typeAliases The type aliases to exclude.
 * @return A sequence containing files without all the specified type alias(es).
 */
fun Sequence<KoFile>.withoutAllTypeAliases(typeAlias: String, vararg typeAliases: String): Sequence<KoFile> = filterNot {
    it.hasTypeAliases(typeAlias, *typeAliases)
}

/**
 * Sequence containing files without some type aliases.
 *
 * @param typeAlias The type alias to exclude.
 * @param typeAliases The type aliases to exclude.
 * @return A sequence containing files without at least one of the specified type alias(es).
 */
fun Sequence<KoFile>.withoutSomeTypeAliases(typeAlias: String, vararg typeAliases: String): Sequence<KoFile> = filter {
    !it.hasTypeAliases(typeAlias) && if (typeAliases.isNotEmpty()) {
        typeAliases.any { typeAlias -> !it.hasTypeAliases(typeAlias) }
    } else {
        true
    }
}
