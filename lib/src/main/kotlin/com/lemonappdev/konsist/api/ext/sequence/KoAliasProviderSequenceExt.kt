package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoAliasProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider

/**
 * Sequence containing all declarations with an alias.
 *
 * @param names The names of aliases to include.
 * @return A sequence containing declarations with the specified aliases (or any alias if [names] is empty).
 */
fun <T> Sequence<T>.withAlias(vararg names: String): Sequence<T>
        where T : KoAliasProvider,
              T : KoNameProvider = filter {
    when {
        names.isEmpty() -> it.alias != it.name
        else -> names.any { name -> it.alias == name }
    }
}

/**
 * Sequence containing all declarations without an alias.
 *
 * @param names The names of aliases to exclude.
 * @return A sequence containing declarations without specified aliases (or none alias if [names] is empty).
 */
fun <T> Sequence<T>.withoutAlias(vararg names: String): Sequence<T>
        where T : KoAliasProvider,
              T : KoNameProvider = filter {
    when {
        names.isEmpty() -> it.alias == it.name
        else -> names.none { name -> it.alias == name }
    }
}
