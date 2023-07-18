package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.ext.container.hasAnnotationOf
import kotlin.reflect.KClass

/**
 * Sequence containing files with any of the specified names.
 *
 * @param name The name to include.
 * @param names The names to include.
 * @return A sequence containing files with the specified names.
 */
fun <T : KoFile> Sequence<T>.withName(name: String, vararg names: String): Sequence<T> = filter {
    it.name == name || names.any { name -> it.name == name }
}

/**
 * Sequence containing files without any of the specified names.
 *
 * @param name The name to exclude.
 * @param names The names to exclude.
 * @return A sequence containing files without the specified names.
 */
fun <T : KoFile> Sequence<T>.withoutName(name: String, vararg names: String): Sequence<T> = filter {
    it.name != name && names.none { name -> it.name == name }
}

/**
 * Sequence containing files with name with prefix.
 *
 * @param prefix The prefix to include.
 * @param prefixes The prefixes to include.
 * @return A sequence containing files with names starting with the specified prefixes.
 */
fun <T : KoFile> Sequence<T>.withNameStartingWith(prefix: String, vararg prefixes: String): Sequence<T> = filter {
    it.hasNameStartingWith(prefix) || prefixes.any { prefix -> it.hasNameStartingWith(prefix) }
}

/**
 * Sequence containing files without name with prefix.
 *
 * @param prefix The prefix to exclude.
 * @param prefixes The prefixes to exclude.
 * @return A sequence containing files without names starting with the specified prefixes.
 */
fun <T : KoFile> Sequence<T>.withoutNameStartingWith(prefix: String, vararg prefixes: String): Sequence<T> = filter {
    !it.hasNameStartingWith(prefix) && prefixes.none { prefix -> it.hasNameStartingWith(prefix) }
}

/**
 * Sequence containing files with name with suffix.
 *
 * @param suffix The suffix to include.
 * @param suffixes The suffixes to include.
 * @return A sequence containing files with names ending with the specified suffixes.
 */
fun <T : KoFile> Sequence<T>.withNameEndingWith(suffix: String, vararg suffixes: String): Sequence<T> = filter {
    it.hasNameEndingWith(suffix) || suffixes.any { suffix -> it.hasNameEndingWith(suffix) }
}

/**
 * Sequence containing files without name with suffix.
 *
 * @param suffix The suffix to exclude.
 * @param suffixes The suffixes to exclude.
 * @return A sequence containing files without names ending with the specified suffixes.
 */
fun <T : KoFile> Sequence<T>.withoutNameEndingWith(suffix: String, vararg suffixes: String): Sequence<T> = filter {
    !it.hasNameEndingWith(suffix) && suffixes.none { suffix -> it.hasNameEndingWith(suffix) }
}

/**
 * Sequence containing files with name containing String.
 *
 * @param text The text to include.
 * @param texts The texts to include.
 * @return A sequence containing files with names containing the specified texts.
 */
fun <T : KoFile> Sequence<T>.withNameContaining(text: String, vararg texts: String): Sequence<T> = filter {
    it.hasNameContaining(text) || texts.any { text -> it.hasNameContaining(text) }
}

/**
 * Sequence containing files without name containing String.
 *
 * @param text The text to exclude.
 * @param texts The texts to exclude.
 * @return A sequence containing files without names containing the specified texts.
 */
fun <T : KoFile> Sequence<T>.withoutNameContaining(text: String, vararg texts: String): Sequence<T> = filter {
    !it.hasNameContaining(text) && texts.none { text -> it.hasNameContaining(text) }
}

/**
 * Sequence containing files with name matching regex.
 *
 * @param regex The regular expression to include.
 * @param regexes The regular expressions to include.
 * @return A sequence containing files with names matching the specified regular expressions.
 */
fun <T : KoFile> Sequence<T>.withNameMatching(regex: Regex, vararg regexes: Regex): Sequence<T> = filter {
    it.hasNameMatching(regex) || regexes.any { regex -> it.hasNameMatching(regex) }
}

/**
 * Sequence containing files without name matching regex.
 *
 * @param regex The regular expression to exclude.
 * @param regexes The regular expressions to exclude.
 * @return A sequence containing files without names matching the specified regular expressions.
 */
fun <T : KoFile> Sequence<T>.withoutNameMatching(regex: Regex, vararg regexes: Regex): Sequence<T> = filter {
    !it.hasNameMatching(regex) && regexes.none { regex -> it.hasNameMatching(regex) }
}

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
 * Sequence containing files with path.
 *
 * @param path The path to include.
 * @param paths The paths to include.
 * @return A sequence containing files that reside in any of the specified paths.
 */
fun <T : KoFile> Sequence<T>.withPath(path: String, vararg paths: String): Sequence<T> = filter {
    it.resideInPath(path) || paths.any { path -> it.resideInPath(path) }
}

/**
 * Sequence containing files without path.
 *
 * @param path The path to exclude.
 * @param paths The paths to exclude.
 * @return A sequence containing files that don't reside in any of the specified paths.
 */
fun <T : KoFile> Sequence<T>.withoutPath(path: String, vararg paths: String): Sequence<T> = filter {
    !it.resideInPath(path) && paths.none { path -> it.resideInPath(path) }
}

/**
 * Sequence containing files with project file path.
 *
 * @param path The project path to include.
 * @param paths The project paths to include.
 * @return A sequence containing files that reside in any of the specified project paths.
 */
fun <T : KoFile> Sequence<T>.withProjectPath(path: String, vararg paths: String): Sequence<T> = filter {
    it.resideInProjectPath(path) || paths.any { path -> it.resideInProjectPath(path) }
}

/**
 * Sequence containing files without project file path.
 *
 * @param path The project path to exclude.
 * @param paths The project paths to exclude.
 * @return A sequence containing files that don't reside in any of the specified project paths.
 */
fun <T : KoFile> Sequence<T>.withoutProjectPath(path: String, vararg paths: String): Sequence<T> = filter {
    !it.resideInProjectPath(path) && paths.none { path -> it.resideInProjectPath(path) }
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
 * Sequence containing files with all annotations.
 *
 * @return A sequence containing files with any annotation.
 */
fun <T : KoFile> Sequence<T>.withAnnotations(): Sequence<T> = filter { it.hasAnnotations() }

/**
 * Sequence containing files with all the specified annotations.
 *
 * @param annotation The annotation to include.
 * @param annotations The annotations to include.
 * @return A sequence containing files with all the specified annotations.
 */
fun <T : KoFile> Sequence<T>.withAllAnnotations(annotation: String, vararg annotations: String): Sequence<T> = filter {
    it.hasAnnotations(annotation, *annotations)
}

/**
 * Sequence containing files with some annotations.
 *
 * @param annotation The annotation to include.
 * @param annotations The annotations to include.
 * @return A sequence containing files with at least one of the specified annotations.
 */
fun <T : KoFile> Sequence<T>.withSomeAnnotations(annotation: String, vararg annotations: String): Sequence<T> = filter {
    it.hasAnnotations(annotation) || annotations.any { annotation -> it.hasAnnotations(annotation) }
}

/**
 * Sequence containing files with no annotation.
 *
 * @return A sequence containing files with no annotation.
 */
fun <T : KoFile> Sequence<T>.withoutAnnotations(): Sequence<T> = filterNot { it.hasAnnotations() }

/**
 * Sequence containing files without specified annotations.
 *
 * @param annotation The annotation to exclude.
 * @param annotations The annotations to exclude.
 * @return A sequence containing files without any of the specified annotations.
 */
fun <T : KoFile> Sequence<T>.withoutAllAnnotations(annotation: String, vararg annotations: String): Sequence<T> = filterNot {
    it.hasAnnotations(annotation, *annotations)
}

/**
 * Sequence containing files without some annotations.
 *
 * @param annotation The annotation to exclude.
 * @param annotations The annotations to exclude.
 * @return A sequence containing files without at least one of the specified annotations.
 */
fun <T : KoFile> Sequence<T>.withoutSomeAnnotations(annotation: String, vararg annotations: String): Sequence<T> = filter {
    !it.hasAnnotations(annotation) && if (annotations.isNotEmpty()) {
        annotations.any { annotation -> !it.hasAnnotations(annotation) }
    } else {
        true
    }
}

/**
 * Sequence containing files with all the specified annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to include.
 * @param annotations The Kotlin classes representing annotations to include.
 * @return A sequence containing files with all the specified annotations.
 */
fun <T : KoFile> Sequence<T>.withAllAnnotationsOf(annotation: KClass<*>, vararg annotations: KClass<*>): Sequence<T> =
    filter { it.hasAnnotationsOf(annotation, *annotations) }

/**
 * Sequence containing files with some annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to include.
 * @param annotations The Kotlin classes representing annotations to include.
 * @return A sequence containing files with at least one of the specified the annotations.
 */
fun <T : KoFile> Sequence<T>.withSomeAnnotationsOf(annotation: KClass<*>, vararg annotations: KClass<*>): Sequence<T> = filter {
    it.hasAnnotationsOf(annotation) || annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Sequence containing files without all the specified annotations.
 *
 * @param annotation The Kotlin class representing annotation to exclude.
 * @param annotations The Kotlin classes representing annotations to exclude.
 * @return A sequence containing files without all the specified annotations.
 */
fun <T : KoFile> Sequence<T>.withoutAllAnnotationsOf(annotation: KClass<*>, vararg annotations: KClass<*>): Sequence<T> =
    filterNot { it.hasAnnotationsOf(annotation, *annotations) }

/**
 * Sequence containing files without some annotations.
 *
 * @param annotation The Kotlin class representing annotation to exclude.
 * @param annotations The Kotlin classes representing annotations to exclude.
 * @return A sequence containing files without at least one of the specified annotations.
 */
fun <T : KoFile> Sequence<T>.withoutSomeAnnotationsOf(annotation: KClass<*>, vararg annotations: KClass<*>): Sequence<T> = filter {
    !it.hasAnnotationsOf(annotation) && if (annotations.isNotEmpty()) {
        annotations.any { annotation -> !it.hasAnnotationsOf(annotation) }
    } else {
        true
    }
}

/**
 * Sequence containing files with all annotations of type.
 *
 * @return A sequence containing files with the specified annotation.
 */
inline fun <reified T> Sequence<KoFile>.withAnnotationOf(): Sequence<KoFile> = filter { it.hasAnnotationOf<T>() }

/**
 * Sequence containing files without annotations of type.
 *
 * @return A sequence containing files without specified annotation.
 */
inline fun <reified T> Sequence<KoFile>.withoutAnnotationOf(): Sequence<KoFile> = filterNot { it.hasAnnotationOf<T>() }

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
