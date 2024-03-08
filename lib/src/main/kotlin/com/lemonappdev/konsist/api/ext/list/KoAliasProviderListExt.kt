package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoAliasProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider

/**
 * List containing import alias declarations.
 */
val <T : KoAliasProvider> List<T>.importAliases: List<KoImportAliasDeclaration>
    get() = mapNotNull { it.alias }

/**
 * List containing declarations with an alias.
 *
 * @param names The names of aliases to include.
 * @return A list containing declarations with the specified aliases (or any alias if [names] is empty).
 */
@Deprecated("Will be removed in v0.16.0", ReplaceWith("withAlias { it.name == ... }"))
fun <T : KoAliasProvider> List<T>.withAlias(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> it.alias?.name != (it as? KoNameProvider)?.name
        else -> names.any { name -> it.alias?.name == name }
    }
}

/**
 * List containing declarations without an alias.
 *
 * @param names The names of aliases to exclude.
 * @return A list containing declarations without specified aliases (or none alias if [names] is empty).
 */
@Deprecated("Will be removed in v0.16.0", ReplaceWith("withoutAlias { it.name == ... }"))
fun <T : KoAliasProvider> List<T>.withoutAlias(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> it.alias?.name == (it as? KoNameProvider)?.name
        else -> names.none { name -> it.alias?.name == name }
    }
}

/**
 * List containing declarations with the specified import alias.
 *
 * @param predicate The predicate function to determine if a declaration import alias satisfies a condition.
 * @return A list containing declarations with the specified import alias (or any import alias if [predicate] is null).
 */
fun <T : KoAliasProvider> List<T>.withAlias(predicate: ((KoImportAliasDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasAlias()
            else -> it.alias?.let { alias -> predicate(alias) } ?: false
        }
    }

/**
 * List containing declarations without the specified import alias.
 *
 * @param predicate The predicate function to determine if a declaration import alias satisfies a condition.
 * @return A list containing declarations without the specified import alias (or none import alias if [predicate] is null).
 */
fun <T : KoAliasProvider> List<T>.withoutAlias(predicate: ((KoImportAliasDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasAlias()
            else -> it.alias?.let { alias -> predicate(alias) } ?: false
        }
    }
