package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.core.util.PathUtil

internal interface KoSourceSetProviderCore :
    KoSourceSetProvider,
    KoPathProviderCore,
    KoBaseProviderCore {
    override val sourceSetName: String
        get() = PathUtil.getSourceSetName(projectPath)

    override fun residesInSourceSet(sourceSetName: String): Boolean = sourceSetName == this.sourceSetName
}
