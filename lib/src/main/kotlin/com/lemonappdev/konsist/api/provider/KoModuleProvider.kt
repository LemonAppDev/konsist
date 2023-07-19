package com.lemonappdev.konsist.api.provider

interface KoModuleProvider : KoProvider {
    /**
     * The file's module name.
     */
    val moduleName: String

    /**
     * Whether file reside in module.
     *
     * @param module The name of the module to check. If this is the top-module, use "root".
     * @return `true` if a file resides in the specified module, `false` otherwise.
     */
    fun resideInModule(module: String): Boolean
}
