package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoModuleProvider

/**
 * Sequence containing files with module.
 *
 * @param module The module to include.
 * @param modules The modules to include.
 * @return A sequence containing files that reside in any of the specified modules.
 */
fun <T : KoModuleProvider> Sequence<T>.withModule(module: String, vararg modules: String): Sequence<T> = filter {
    it.resideInModule(module) || modules.any { module -> it.resideInModule(module) }
}

/**
 * Sequence containing files without module.
 *
 * @param module The module to exclude.
 * @param modules The modules to exclude.
 * @return A sequence containing files that don't reside in any of the specified modules.
 */
fun <T : KoModuleProvider> Sequence<T>.withoutModule(module: String, vararg modules: String): Sequence<T> = filter {
    !it.resideInModule(module) && modules.none { module -> it.resideInModule(module) }
}
