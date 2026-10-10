package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about its module.
 */
interface KoModuleProvider : KoBaseProvider {
    /**
     * The declaration's module name.
     * Uses `/` separators on all OSes, e.g. "feature/data", or "root" for the top-level module.
     */
    val moduleName: String

    /**
     * Determines whatever declaration reside in module.
     *
     * @param name The name of the module to check. If this is the top-module, use "root" (or ":").
     * Gradle project path (e.g. `:feature:auth`) is the preferred format, but `feature/auth` and `feature\auth`
     * are also accepted on all OSes.
     * @return `true` if a declaration resides in the specified module, `false` otherwise.
     */
    fun resideInModule(name: String): Boolean
}
