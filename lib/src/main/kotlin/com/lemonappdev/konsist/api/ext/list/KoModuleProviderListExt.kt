package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoModuleProvider

/**
 * List containing elements with module.
 *
 * @param name The module name to include.
 * @param names The module name(s) to include.
 * @return A list containing elements that reside in any of the specified modules.
 */
fun <T : KoModuleProvider> List<T>.withModule(name: String, vararg names: String): List<T> = filter {
    it.resideInModule(name) || names.any { module -> it.resideInModule(module) }
}

/**
 * List containing elements without module.
 *
 * @param name The module name to exclude.
 * @param names The module name(s) to exclude.
 * @return A list containing elements that don't reside in any of the specified modules.
 */
fun <T : KoModuleProvider> List<T>.withoutModule(name: String, vararg names: String): List<T> = filter {
    !it.resideInModule(name) && names.none { module -> it.resideInModule(module) }
}
