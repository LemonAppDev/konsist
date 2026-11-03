package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.core.util.ModuleUtil

internal interface KoModuleProviderCore :
    KoModuleProvider,
    KoPathProviderCore,
    KoBaseProviderCore {
    override val moduleName: String
        get() = ModuleUtil.getModuleName(projectPath)

    override fun residesInModule(name: String): Boolean = ModuleUtil.residesInModule(moduleName, name)
}
