package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.core.util.PathUtil.toMacOsSeparator

object ModuleUtil {
    private const val ROOT_MODULE_NAME = "root"

    /**
     * Returns module name with "/" separators (regardless of OS), e.g. "feature/data", or "root" for the top-level module.
     */
    fun getModuleName(projectPath: String): String {
        val moduleName =
            toMacOsSeparator(projectPath)
                .substringBefore("/src/")
                .substringAfter("/")

        return moduleName.ifEmpty { ROOT_MODULE_NAME }
    }
}
