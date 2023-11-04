package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoAliasProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider

/**
 * List containing declarations with an alias.
 *
 * @param names The names of aliases to include.
 * @return A list containing declarations with the specified aliases (or any alias if [names] is empty).
 */
fun <T : KoAliasProvider> List<T>.withAlias(vararg names: String): List<T> =
    filter {
        when {
            names.isEmpty() -> it.alias != (it as? KoNameProvider)?.name
            else -> names.any { name -> it.alias == name }
        }
    }

/**
 * List containing declarations without an alias.
 *
 * @param names The names of aliases to exclude.
 * @return A list containing declarations without specified aliases (or none alias if [names] is empty).
 */
fun <T : KoAliasProvider> List<T>.withoutAlias(vararg names: String): List<T> =
    filter {
        when {
            names.isEmpty() -> it.alias == (it as? KoNameProvider)?.name
            else -> names.none { name -> it.alias == name }
        }
    }
