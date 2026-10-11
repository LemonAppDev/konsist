package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.core.ext.sep

internal interface KoSourceSetProviderCore :
    KoSourceSetProvider,
    KoPathProviderCore,
    KoBaseProviderCore {
    override val sourceSetName: String
        get() =
            projectPath
                .substringAfter("${sep}src$sep")
                .substringBefore(sep)

    override fun residesInSourceSet(sourceSetName: String): Boolean = sourceSetName == this.sourceSetName

    @Deprecated("Will be removed in version 1.0.0-rc1", ReplaceWith("residesInSourceSet(sourceSetName)"))
    override fun resideInSourceSet(sourceSetName: String): Boolean = residesInSourceSet(sourceSetName)
}
