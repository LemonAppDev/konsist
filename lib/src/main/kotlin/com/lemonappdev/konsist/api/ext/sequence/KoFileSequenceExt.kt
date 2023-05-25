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
 * @param paths The root project paths to include.
 * @return A sequence containing files that reside in any of the specified root project paths.
 */
fun <T : KoFile> Sequence<T>.withRootProjectPath(vararg paths: String): Sequence<T> = filter {
    paths.any { path -> it.resideInRootProjectPath(path) }
}

/**
 * Sequence containing files that don't have root project file path.
 *
 * @param paths The root project paths to exclude.
 * @return A sequence containing files that don't reside in any of the specified root project paths.
 */
fun <T : KoFile> Sequence<T>.withoutRootProjectPath(vararg paths: String): Sequence<T> = filter {
    paths.none { path -> it.resideInRootProjectPath(path) }
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
 * Sequence containing declarations that have imports.
 */
fun Sequence<KoFile>.withImports(vararg imports: String): Sequence<KoFile> = filter {
    when {
        imports.isEmpty() -> it.hasImports()
        else -> it.hasImports(*imports)
    }
}

/**
 * Sequence containing declarations that have some imports.
 */
fun Sequence<KoFile>.withSomeImports(vararg imports: String): Sequence<KoFile> = filter {
    imports.any { import -> it.hasImports(import) }
}

/**
 * Sequence containing declarations that don't have imports.
 */
fun Sequence<KoFile>.withoutImports(vararg imports: String): Sequence<KoFile> = filter {
    when {
        imports.isEmpty() -> !it.hasImports()
        else -> !it.hasImports(*imports)
    }
}

/**
 * Sequence containing declarations that have package.
 */
fun Sequence<KoFile>.withPackage(vararg packages: String): Sequence<KoFile> = filter {
    packages.any { packagee -> it.hasPackage(packagee) }
}

/**
 * Sequence containing declarations that have some package.
 */
fun Sequence<KoFile>.withoutPackage(vararg packages: String): Sequence<KoFile> = filter {
    packages.none { packagee -> it.hasPackage(packagee) }
}

/**
 * Sequence containing declarations that have the annotations.
 */
fun Sequence<KoFile>.withAnnotations(vararg annotations: String): Sequence<KoFile> = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have some annotations.
 */
fun Sequence<KoFile>.withSomeAnnotations(vararg annotations: String): Sequence<KoFile> = filter {
    annotations.any { annotation -> it.hasAnnotations(annotation) }
}

/**
 * Sequence containing declarations that don't have annotations.
 */
fun Sequence<KoFile>.withoutAnnotations(vararg annotations: String): Sequence<KoFile> = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have annotation of type.
 */
inline fun <reified T> Sequence<KoFile>.withAnnotationOf(): Sequence<KoFile> = filter { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that don't have some annotations of type.
 */
inline fun <reified T> Sequence<KoFile>.withoutAnnotationOf(): Sequence<KoFile> =
    filterNot { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that have some annotations of type.
 */
fun Sequence<KoFile>.withAnnotationsOf(vararg annotations: KClass<*>): Sequence<KoFile> =
    filter { it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing declarations that have some annotations of type.
 */
fun Sequence<KoFile>.withSomeAnnotationsOf(vararg annotations: KClass<*>): Sequence<KoFile> = filter {
    annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Sequence containing declarations that don't have some annotations of type.
 */
fun Sequence<KoFile>.withoutAnnotationsOf(vararg annotations: KClass<*>): Sequence<KoFile> =
    filter { !it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing declarations that have type alias.
 */
fun Sequence<KoFile>.withTypeAliases(vararg typeAliasNames: String): Sequence<KoFile> = filter {
    when {
        typeAliasNames.isEmpty() -> it.hasTypeAliases()
        else -> it.hasTypeAliases(*typeAliasNames)
    }
}

/**
 * Sequence containing declarations that have some type aliases.
 */
fun Sequence<KoFile>.withSomeTypeAliases(vararg typeAliasNames: String): Sequence<KoFile> = filter {
    typeAliasNames.any { typeAlias -> it.hasTypeAliases(typeAlias) }
}

/**
 * Sequence containing declarations that don't have type aliases.
 */
fun Sequence<KoFile>.withoutTypeAliases(vararg typeAliasNames: String): Sequence<KoFile> = filter {
    when {
        typeAliasNames.isEmpty() -> !it.hasTypeAliases()
        else -> !it.hasTypeAliases(*typeAliasNames)
    }
}
