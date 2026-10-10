package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.core.ext.toMacOsSeparator
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.util.ModuleUtil

internal interface KoModuleProviderCore :
    KoModuleProvider,
    KoPathProviderCore,
    KoBaseProviderCore {
    override val moduleName: String
        get() = ModuleUtil.getModuleName(projectPath, PathProvider.rootProjectPath)

    override fun resideInModule(name: String): Boolean = name.toMacOsSeparator() == moduleName
}
