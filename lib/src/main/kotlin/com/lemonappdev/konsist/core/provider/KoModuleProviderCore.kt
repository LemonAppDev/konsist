package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.core.ext.sep
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

            val moduleName =
                projectPath
                    .substringBefore("${sep}src$sep")
                    .substringAfter(sep)

            return if (moduleName == projectName || moduleName == "") {
                "root"
            } else {
                moduleName
            }
        }

    override fun resideInModule(name: String): Boolean = name == moduleName
}
