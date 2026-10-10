package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.core.ext.sep
import com.lemonappdev.konsist.core.ext.toMacOsSeparator
import com.lemonappdev.konsist.core.filesystem.PathProvider

internal interface KoModuleProviderCore :
    KoModuleProvider,
    KoPathProviderCore,
    KoBaseProviderCore {
    override val moduleName: String
        get() {
            val projectName =
                PathProvider
                    .rootProjectPath
                    .substringAfterLast(sep)

            // Module name always uses "/" separator (e.g. "feature/data"), regardless of the OS
            val moduleName =
                projectPath
                    .toMacOsSeparator()
                    .substringBefore("/src/")
                    .substringAfter("/")

            return if (moduleName == projectName || moduleName == "") {
                "root"
            } else {
                moduleName
            }
        }

    override fun resideInModule(name: String): Boolean = name.toMacOsSeparator() == moduleName
}
