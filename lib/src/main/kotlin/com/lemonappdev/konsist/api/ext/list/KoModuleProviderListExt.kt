package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoModuleProvider

/**
 * List containing files with module.
 *
 * @param module The module to include.
 * @param modules The modules to include.
 * @return A list containing files that reside in any of the specified modules.
 */
fun <T : KoModuleProvider> List<T>.withModule(module: String, vararg modules: String): List<T> = filter {
    it.resideInModule(module) || modules.any { module -> it.resideInModule(module) }
}

/**
 * List containing files without module.
 *
 * @param module The module to exclude.
 * @param modules The modules to exclude.
 * @return A list containing files that don't reside in any of the specified modules.
 */
fun <T : KoModuleProvider> List<T>.withoutModule(module: String, vararg modules: String): List<T> = filter {
    !it.resideInModule(module) && modules.none { module -> it.resideInModule(module) }
}
