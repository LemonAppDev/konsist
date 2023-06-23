package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.ext.container.hasAnnotationOf
import kotlin.reflect.KClass

/**
 * Sequence containing files that have name.
 *
 * @param name The name to include.
 * @param names The names to include.
 * @return A sequence containing files with the specified names.
 */
fun <T : KoFile> Sequence<T>.withName(name: String, vararg names: String): Sequence<T> = filter {
    it.name == name || names.any { name -> it.name == name }
}

/**
 * Sequence containing files that don't have name.
 *
 * @param name The name to exclude.
 * @param names The names to exclude.
 * @return A sequence containing files without the specified names.
 */
fun <T : KoFile> Sequence<T>.withoutName(name: String, vararg names: String): Sequence<T> = filter {
    it.name != name && names.none { name -> it.name == name }
}

/**
 * Sequence containing files that have name with prefix.
 *
 * @param prefix The prefix to include.
 * @param prefixes The prefixes to include.
 * @return A sequence containing files with names starting with the specified prefixes.
 */
fun <T : KoFile> Sequence<T>.withNameStartingWith(prefix: String, vararg prefixes: String): Sequence<T> = filter {
    it.hasNameStartingWith(prefix) || prefixes.any { prefix -> it.hasNameStartingWith(prefix) }
}

/**
 * Sequence containing files that don't have name with prefix.
 *
 * @param prefix The prefix to exclude.
 * @param prefixes The prefixes to exclude.
 * @return A sequence containing files without names starting with the specified prefixes.
 */
fun <T : KoFile> Sequence<T>.withoutNameStartingWith(prefix: String, vararg prefixes: String): Sequence<T> = filter {
    !it.hasNameStartingWith(prefix) && prefixes.none { prefix -> it.hasNameStartingWith(prefix) }
}

/**
 * Sequence containing files that have name with suffix.
 *
 * @param suffix The suffix to include.
 * @param suffixes The suffixes to include.
 * @return A sequence containing files with names ending with the specified suffixes.
 */
fun <T : KoFile> Sequence<T>.withNameEndingWith(suffix: String, vararg suffixes: String): Sequence<T> = filter {
    it.hasNameEndingWith(suffix) || suffixes.any { suffix -> it.hasNameEndingWith(suffix) }
}

/**
 * Sequence containing files that don't have name with suffix.
 *
 * @param suffix The suffix to exclude.
 * @param suffixes The suffixes to exclude.
 * @return A sequence containing files without names ending with the specified suffixes.
 */
fun <T : KoFile> Sequence<T>.withoutNameEndingWith(suffix: String, vararg suffixes: String): Sequence<T> = filter {
    !it.hasNameEndingWith(suffix) && suffixes.none { suffix -> it.hasNameEndingWith(suffix) }
}

/**
 * Sequence containing files that have name containing String.
 *
 * @param text The text to include.
 * @param texts The texts to include.
 * @return A sequence containing files with names containing the specified texts.
 */
fun <T : KoFile> Sequence<T>.withNameContaining(text: String, vararg texts: String): Sequence<T> = filter {
    it.hasNameContaining(text) || texts.any { text -> it.hasNameContaining(text) }
}

/**
 * Sequence containing files that don't have name containing String.
 *
 * @param text The text to exclude.
 * @param texts The texts to exclude.
 * @return A sequence containing files without names containing the specified texts.
 */
fun <T : KoFile> Sequence<T>.withoutNameContaining(text: String, vararg texts: String): Sequence<T> = filter {
    !it.hasNameContaining(text) && texts.none { text -> it.hasNameContaining(text) }
}

/**
 * Sequence containing files that have name matching regex.
 *
 * @param regex The regular expression to include.
 * @param regexes The regular expressions to include.
 * @return A sequence containing files with names matching the specified regular expressions.
 */
fun <T : KoFile> Sequence<T>.withNameMatching(regex: Regex, vararg regexes: Regex): Sequence<T> = filter {
    it.hasNameMatching(regex) || regexes.any { regex -> it.hasNameMatching(regex) }
}

/**
 * Sequence containing files that don't have name matching regex.
 *
 * @param regex The regular expression to exclude.
 * @param regexes The regular expressions to exclude.
 * @return A sequence containing files without names matching the specified regular expressions.
 */
fun <T : KoFile> Sequence<T>.withoutNameMatching(regex: Regex, vararg regexes: Regex): Sequence<T> = filter {
    !it.hasNameMatching(regex) && regexes.none { regex -> it.hasNameMatching(regex) }
}

/**
 * Sequence containing files that have extension.
 *
 * @param extension The extension to include.
 * @param extensions The extensions to include.
 * @return A sequence containing files with extensions matching the specified extensions.
 */
fun <T : KoFile> Sequence<T>.withExtension(extension: String, vararg extensions: String): Sequence<T> = filter {
    it.hasExtension(extension) || extensions.any { extension -> it.hasExtension(extension) }
}

/**
 * Sequence containing files that don't have extension.
 *
 * @param extension The extension to exclude.
 * @param extensions The extensions to exclude.
 * @return A sequence containing files without extensions matching the specified extensions.
 */
fun <T : KoFile> Sequence<T>.withoutExtension(extension: String, vararg extensions: String): Sequence<T> = filter {
    !it.hasExtension(extension) && extensions.none { extension -> it.hasExtension(extension) }
}

/**
 * Sequence containing files that have path.
 *
 * @param path The path to include.
 * @param paths The paths to include.
 * @return A sequence containing files that reside in any of the specified paths.
 */
fun <T : KoFile> Sequence<T>.withPath(path: String, vararg paths: String): Sequence<T> = filter {
    it.resideInPath(path) || paths.any { path -> it.resideInPath(path) }
}

/**
 * Sequence containing files that don't have path.
 *
 * @param path The path to exclude.
 * @param paths The paths to exclude.
 * @return A sequence containing files that don't reside in any of the specified paths.
 */
fun <T : KoFile> Sequence<T>.withoutPath(path: String, vararg paths: String): Sequence<T> = filter {
    !it.resideInPath(path) && paths.none { path -> it.resideInPath(path) }
}

/**
 * Sequence containing files that have project file path.
 *
 * @param path The project path to include.
 * @param paths The project paths to include.
 * @return A sequence containing files that reside in any of the specified project paths.
 */
fun <T : KoFile> Sequence<T>.withProjectPath(path: String, vararg paths: String): Sequence<T> = filter {
    it.resideInProjectPath(path) || paths.any { path -> it.resideInProjectPath(path) }
}

/**
 * Sequence containing files that don't have project file path.
 *
 * @param path The project path to exclude.
 * @param paths The project paths to exclude.
 * @return A sequence containing files that don't reside in any of the specified project paths.
 */
fun <T : KoFile> Sequence<T>.withoutProjectPath(path: String, vararg paths: String): Sequence<T> = filter {
    !it.resideInProjectPath(path) && paths.none { path -> it.resideInProjectPath(path) }
}

/**
 * Sequence containing files that have module.
 *
 * @param module The module to include.
 * @param modules The modules to include.
 * @return A sequence containing files that reside in any of the specified modules.
 */
fun <T : KoFile> Sequence<T>.withModule(module: String, vararg modules: String): Sequence<T> = filter {
    it.resideInModule(module) || modules.any { module -> it.resideInModule(module) }
}

/**
 * Sequence containing files that don't have module.
 *
 * @param module The module to exclude.
 * @param modules The modules to exclude.
 * @return A sequence containing files that don't reside in any of the specified modules.
 */
fun <T : KoFile> Sequence<T>.withoutModule(module: String, vararg modules: String): Sequence<T> = filter {
    !it.resideInModule(module) && modules.none { module -> it.resideInModule(module) }
}

/**
 * Sequence containing files that have source set.
 *
 * @param sourceSet The sourceSet to include.
 * @param sourceSets The sourceSets to include.
 * @return A sequence containing files that reside in any of the specified source sets.
 */
fun <T : KoFile> Sequence<T>.withSourceSet(sourceSet: String, vararg sourceSets: String): Sequence<T> = filter {
    it.resideInSourceSet(sourceSet) || sourceSets.any { sourceSet -> it.resideInSourceSet(sourceSet) }
}

/**
 * Sequence containing files that don't have source set.
 *
 * @param sourceSet The sourceSet to exclude.
 * @param sourceSets The sourceSets to exclude.
 * @return A sequence containing files that don't reside in any of the specified source sets.
 */
fun <T : KoFile> Sequence<T>.withoutSourceSet(sourceSet: String, vararg sourceSets: String): Sequence<T> = filter {
    !it.resideInSourceSet(sourceSet) && sourceSets.none { sourceSet -> it.resideInSourceSet(sourceSet) }
}

/**
 * Sequence containing files that have imports.
 *
 * @param imports The import(s) to include.
 * @return A sequence containing files that have the specified import(s) (or any import if [imports] is empty).
 */
fun Sequence<KoFile>.withImports(vararg imports: String): Sequence<KoFile> = filter {
    when {
        imports.isEmpty() -> it.hasImports()
        else -> it.hasImports(*imports)
    }
}

/**
 * Sequence containing files that have some imports.
 *
 * @param import The import to include.
 * @param imports The imports to include.
 * @return A sequence containing files that have at least one of the specified import(s).
 */
fun Sequence<KoFile>.withSomeImports(import: String, vararg imports: String): Sequence<KoFile> = filter {
    it.hasImports(import) || imports.any { import -> it.hasImports(import) }
}

/**
 * Sequence containing files that don't have imports.
 *
 * @param imports The import(s) to exclude.
 * @return A sequence containing files that don't have the specified import(s) (or none import if [imports] is empty).
 */
fun Sequence<KoFile>.withoutImports(vararg imports: String): Sequence<KoFile> = filter {
    when {
        imports.isEmpty() -> !it.hasImports()
        else -> !it.hasImports(*imports)
    }
}

/**
 * Sequence containing files that have package.
 *
 * @param packagee The package name to include.
 * @param packages The package names to include.
 * @return A sequence containing files that have a package matching any of the specified package names.
 */
fun Sequence<KoFile>.withPackage(packagee: String, vararg packages: String): Sequence<KoFile> = filter {
    it.hasPackage(packagee) || packages.any { packagee -> it.hasPackage(packagee) }
}

/**
 * Sequence containing files that have some package.
 *
 * @param packagee The package name to exclude.
 * @param packages The package names to exclude.
 * @return A sequence containing files that don't have a package matching any of the specified package names.
 */
fun Sequence<KoFile>.withoutPackage(packagee: String, vararg packages: String): Sequence<KoFile> = filter {
    !it.hasPackage(packagee) && packages.none { packagee -> it.hasPackage(packagee) }
}

/**
 * Sequence containing files that have the annotations.
 *
 * @param annotations The annotations to include.
 * @return A sequence containing files that have all the specified annotations (or any annotation if [annotations] is empty).
 */
fun Sequence<KoFile>.withAnnotations(vararg annotations: String): Sequence<KoFile> = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing files that have some annotations.
 *
 * @param annotation The annotation to include.
 * @param annotations The annotations to include.
 * @return A sequence containing files that have at least one of the specified annotations.
 */
fun Sequence<KoFile>.withSomeAnnotations(annotation: String, vararg annotations: String): Sequence<KoFile> = filter {
    it.hasAnnotations(annotation) || annotations.any { annotation -> it.hasAnnotations(annotation) }
}

/**
 * Sequence containing files that don't have annotations.
 *
 * @param annotations The annotations to exclude.
 * @return A sequence containing files that don't have all the specified annotations (or none annotation if [annotations] is empty).
 */
fun Sequence<KoFile>.withoutAnnotations(vararg annotations: String): Sequence<KoFile> = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing files that have annotation of type.
 *
 * @return A sequence containing files that have the specified annotation.
 */
inline fun <reified T> Sequence<KoFile>.withAnnotationOf(): Sequence<KoFile> = filter { it.hasAnnotationOf<T>() }

/**
 * Sequence containing files that don't have some annotations of type.
 *
 * @return A sequence containing files that don't have the specified annotation.
 */
inline fun <reified T> Sequence<KoFile>.withoutAnnotationOf(): Sequence<KoFile> =
    filterNot { it.hasAnnotationOf<T>() }

/**
 * Sequence containing files that have some annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to include.
 * @param annotations The Kotlin classes representing annotations to include.
 * @return A sequence containing files that have all the specified annotations.
 */
fun Sequence<KoFile>.withAnnotationsOf(annotation: KClass<*>, vararg annotations: KClass<*>): Sequence<KoFile> =
    filter { it.hasAnnotationsOf(annotation, *annotations) }

/**
 * Sequence containing files that have some annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to include.
 * @param annotations The Kotlin classes representing annotations to include.
 * @return A sequence containing files that have at least one of the specified the annotations.
 */
fun Sequence<KoFile>.withSomeAnnotationsOf(annotation: KClass<*>, vararg annotations: KClass<*>): Sequence<KoFile> = filter {
    it.hasAnnotationsOf(annotation) || annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Sequence containing files that don't have some annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to exclude.
 * @param annotations The Kotlin classes representing annotations to exclude.
 * @return A sequence containing files that don't have all the specified annotations.
 */
fun Sequence<KoFile>.withoutAnnotationsOf(annotation: KClass<*>, vararg annotations: KClass<*>): Sequence<KoFile> =
    filter { !it.hasAnnotationsOf(annotation, *annotations) }

/**
 * Sequence containing files that have type alias.
 *
 * @param typeAliases The type aliases to include.
 * @return A sequence containing files that have all the specified type alias(es) (or any type alias if [typeAliases] is empty).
 */
fun Sequence<KoFile>.withTypeAliases(vararg typeAliases: String): Sequence<KoFile> = filter {
    when {
        typeAliases.isEmpty() -> it.hasTypeAliases()
        else -> it.hasTypeAliases(*typeAliases)
    }
}

/**
 * Sequence containing files that have some type aliases.
 *
 * @param typeAlias The type alias to include.
 * @param typeAliases The type aliases to include.
 * @return A sequence containing files that have at least one of the specified type alias(es).
 */
fun Sequence<KoFile>.withSomeTypeAliases(typeAlias: String, vararg typeAliases: String): Sequence<KoFile> = filter {
    it.hasTypeAliases(typeAlias) || typeAliases.any { typeAlias -> it.hasTypeAliases(typeAlias) }
}

/**
 * Sequence containing files that don't have type aliases.
 *
 * @param typeAliases The type aliases to exclude.
 * @return A sequence containing files that don't have all the specified type alias(es) (or none type alias if [typeAliases] is empty).
 */
fun Sequence<KoFile>.withoutTypeAliases(vararg typeAliases: String): Sequence<KoFile> = filter {
    when {
        typeAliases.isEmpty() -> !it.hasTypeAliases()
        else -> !it.hasTypeAliases(*typeAliases)
    }
}
