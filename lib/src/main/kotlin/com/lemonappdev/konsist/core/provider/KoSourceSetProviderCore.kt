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

    override fun resideInSourceSet(sourceSetName: String): Boolean = sourceSetName == this.sourceSetName
}
