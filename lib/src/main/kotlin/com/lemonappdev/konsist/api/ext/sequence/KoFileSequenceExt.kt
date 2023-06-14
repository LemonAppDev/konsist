package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.ext.container.hasAnnotationOf
import kotlin.reflect.KClass

/**
 * Sequence containing files that have name.
 *
 * @param names The names to include.
 * @return A sequence containing files with the specified names.
 */
fun <T : KoFile> Sequence<T>.withName(vararg names: String): Sequence<T> = filter {
    names.any { name -> it.name == name }
}

/**
 * Sequence containing files that don't have name.
 *
 * @param names The names to exclude.
 * @return A sequence containing files without the specified names.
 */
fun <T : KoFile> Sequence<T>.withoutName(vararg names: String): Sequence<T> = filter {
    names.none { name -> it.name == name }
}

/**
 * Sequence containing files that have name with prefix.
 *
 * @param prefixes The prefixes to include.
 * @return A sequence containing files with names starting with the specified prefixes.
 */
fun <T : KoFile> Sequence<T>.withNameStartingWith(vararg prefixes: String): Sequence<T> = filter {
    prefixes.any { prefix -> it.hasNameStartingWith(prefix) }
}

/**
 * Sequence containing files that don't have name with prefix.
 *
 * @param prefixes The prefixes to exclude.
 * @return A sequence containing files without names starting with the specified prefixes.
 */
fun <T : KoFile> Sequence<T>.withoutNameStartingWith(vararg prefixes: String): Sequence<T> = filter {
    prefixes.none { prefix -> it.hasNameStartingWith(prefix) }
}

/**
 * Sequence containing files that have name with suffix.
 *
 * @param suffixes The suffixes to include.
 * @return A sequence containing files with names ending with the specified suffixes.
 */
fun <T : KoFile> Sequence<T>.withNameEndingWith(vararg suffixes: String): Sequence<T> = filter {
    suffixes.any { suffix -> it.hasNameEndingWith(suffix) }
}

/**
 * Sequence containing files that don't have name with suffix.
 *
 * @param suffixes The suffixes to exclude.
 * @return A sequence containing files without names ending with the specified suffixes.
 */
fun <T : KoFile> Sequence<T>.withoutNameEndingWith(vararg suffixes: String): Sequence<T> = filter {
    suffixes.none { suffix -> it.hasNameEndingWith(suffix) }
}

/**
 * Sequence containing files that have name containing String.
 *
 * @param texts The texts to include.
 * @return A sequence containing files with names containing the specified texts.
 */
fun <T : KoFile> Sequence<T>.withNameContaining(vararg texts: String): Sequence<T> = filter {
    texts.any { text -> it.hasNameContaining(text) }
}

/**
 * Sequence containing files that don't have name containing String.
 *
 * @param texts The texts to exclude.
 * @return A sequence containing files without names containing the specified texts.
 */
fun <T : KoFile> Sequence<T>.withoutNameContaining(vararg texts: String): Sequence<T> = filter {
    texts.none { text -> it.hasNameContaining(text) }
}

/**
 * Sequence containing files that have name matching regex.
 *
 * @param regexes The regular expressions to include.
 * @return A sequence containing files with names matching the specified regular expressions.
 */
fun <T : KoFile> Sequence<T>.withNameMatching(vararg regexes: Regex): Sequence<T> = filter {
    regexes.any { text -> it.hasNameMatching(text) }
}

/**
 * Sequence containing files that don't have name matching regex.
 *
 * @param regexes The regular expressions to exclude.
 * @return A sequence containing files without names matching the specified regular expressions.
 */
fun <T : KoFile> Sequence<T>.withoutNameMatching(vararg regexes: Regex): Sequence<T> = filter {
    regexes.none { text -> it.hasNameMatching(text) }
}

/**
 * Sequence containing files that have extension.
 *
 * @param extensions The extensions to include.
 * @return A sequence containing files with extensions matching the specified extensions.
 */
fun <T : KoFile> Sequence<T>.withExtension(vararg extensions: String): Sequence<T> = filter {
    extensions.any { extension -> it.hasExtension(extension) }
}

/**
 * Sequence containing files that don't have extension.
 *
 * @param extensions The extensions to exclude.
 * @return A sequence containing files without extensions matching the specified extensions.
 */
fun <T : KoFile> Sequence<T>.withoutExtension(vararg extensions: String): Sequence<T> = filter {
    extensions.none { extension -> it.hasExtension(extension) }
}

/**
 * Sequence containing files that have path.
 *
 * @param paths The paths to include.
 * @return A sequence containing files that reside in any of the specified paths.
 */
fun <T : KoFile> Sequence<T>.withPath(vararg paths: String): Sequence<T> = filter {
    paths.any { path -> it.resideInPath(path) }
}

/**
 * Sequence containing files that don't have path.
 *
 * @param paths The paths to exclude.
 * @return A sequence containing files that don't reside in any of the specified paths.
 */
fun <T : KoFile> Sequence<T>.withoutPath(vararg paths: String): Sequence<T> = filter {
    paths.none { path -> it.resideInPath(path) }
}

/**
 * Sequence containing files that have project file path.
 *
 * @param paths The project paths to include.
 * @return A sequence containing files that reside in any of the specified project paths.
 */
fun <T : KoFile> Sequence<T>.withProjectPath(vararg paths: String): Sequence<T> = filter {
    paths.any { path -> it.resideInProjectPath(path) }
}

/**
 * Sequence containing files that don't have project file path.
 *
 * @param paths The project paths to exclude.
 * @return A sequence containing files that don't reside in any of the specified project paths.
 */
fun <T : KoFile> Sequence<T>.withoutProjectPath(vararg paths: String): Sequence<T> = filter {
    paths.none { path -> it.resideInProjectPath(path) }
}

/**
 * Sequence containing files that have module.
 *
 * @param modules The modules to include.
 * @return A sequence containing files that reside in any of the specified modules.
 */
fun <T : KoFile> Sequence<T>.withModule(vararg modules: String): Sequence<T> = filter {
    modules.any { module -> it.resideInModule(module) }
}

/**
 * Sequence containing files that don't have module.
 *
 * @param modules The modules to exclude.
 * @return A sequence containing files that don't reside in any of the specified modules.
 */
fun <T : KoFile> Sequence<T>.withoutModule(vararg modules: String): Sequence<T> = filter {
    modules.none { module -> it.resideInModule(module) }
}

/**
 * Sequence containing files that have source set.
 *
 * @param sourceSetNames The sourceSetNames to include.
 * @return A sequence containing files that reside in any of the specified source sets.
 */
fun <T : KoFile> Sequence<T>.withSourceSet(vararg sourceSetNames: String): Sequence<T> = filter {
    sourceSetNames.any { sourceSet -> it.resideInSourceSet(sourceSet) }
}

/**
 * Sequence containing files that don't have source set.
 *
 * @param sourceSetNames The sourceSetNames to exclude.
 * @return A sequence containing files that don't reside in any of the specified source sets.
 */
fun <T : KoFile> Sequence<T>.withoutSourceSet(vararg sourceSetNames: String): Sequence<T> = filter {
    sourceSetNames.none { sourceSet -> it.resideInSourceSet(sourceSet) }
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
 * @param imports The import(s) to include.
 * @return A sequence containing files that have at least one of the specified import(s).
 */
fun Sequence<KoFile>.withSomeImports(vararg imports: String): Sequence<KoFile> = filter {
    imports.any { import -> it.hasImports(import) }
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
 * @param packages The package names to include.
 * @return A sequence containing files that have a package matching any of the specified package names.
 */
fun Sequence<KoFile>.withPackage(vararg packages: String): Sequence<KoFile> = filter {
    packages.any { packagee -> it.hasPackage(packagee) }
}

/**
 * Sequence containing files that have some package.
 *
 * @param packages The package names to exclude.
 * @return A sequence containing files that don't have a package matching any of the specified package names.
 */
fun Sequence<KoFile>.withoutPackage(vararg packages: String): Sequence<KoFile> = filter {
    packages.none { packagee -> it.hasPackage(packagee) }
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
 * @param annotations The annotations to include.
 * @return A sequence containing files that have at least one of the specified annotations.
 */
fun Sequence<KoFile>.withSomeAnnotations(vararg annotations: String): Sequence<KoFile> = filter {
    annotations.any { annotation -> it.hasAnnotations(annotation) }
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
 * @param annotations The Kotlin class(es) representing annotation(s) to include.
 * @return A sequence containing files that have all the specified annotations.
 */
fun Sequence<KoFile>.withAnnotationsOf(vararg annotations: KClass<*>): Sequence<KoFile> =
    filter { it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing files that have some annotations of type.
 *
 * @param annotations The Kotlin class(es) representing annotation(s) to include.
 * @return A sequence containing files that have at least one of the specified the annotations.
 */
fun Sequence<KoFile>.withSomeAnnotationsOf(vararg annotations: KClass<*>): Sequence<KoFile> = filter {
    annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Sequence containing files that don't have some annotations of type.
 *
 * @param annotations The Kotlin class(es) representing annotation(s) to exclude.
 * @return A sequence containing files that don't have all the specified annotations.
 */
fun Sequence<KoFile>.withoutAnnotationsOf(vararg annotations: KClass<*>): Sequence<KoFile> =
    filter { !it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing files that have type alias.
 *
 * @param typeAliasNames The type alias name(s) to include.
 * @return A sequence containing files that have all the specified type alias(es) (or any type alias if [typeAliasNames] is empty).
 */
fun Sequence<KoFile>.withTypeAliases(vararg typeAliasNames: String): Sequence<KoFile> = filter {
    when {
        typeAliasNames.isEmpty() -> it.hasTypeAliases()
        else -> it.hasTypeAliases(*typeAliasNames)
    }
}

/**
 * Sequence containing files that have some type aliases.
 *
 * @param typeAliasNames The type alias name(s) to include.
 * @return A sequence containing files that have at least one of the specified type alias(es).
 */
fun Sequence<KoFile>.withSomeTypeAliases(vararg typeAliasNames: String): Sequence<KoFile> = filter {
    typeAliasNames.any { typeAlias -> it.hasTypeAliases(typeAlias) }
}

/**
 * Sequence containing files that don't have type aliases.
 *
 * @param typeAliasNames The type alias name(s) to exclude.
 * @return A sequence containing files that don't have all the specified type alias(es) (or none type alias if [typeAliasNames] is empty).
 */
fun Sequence<KoFile>.withoutTypeAliases(vararg typeAliasNames: String): Sequence<KoFile> = filter {
    when {
        typeAliasNames.isEmpty() -> !it.hasTypeAliases()
        else -> !it.hasTypeAliases(*typeAliasNames)
    }
}
