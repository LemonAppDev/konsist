package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoSourceSetProvider

internal interface KoSourceSetProviderCore :
    KoSourceSetProvider,
    KoPathProviderCore,
    KoBaseProviderCore {
    override val sourceSetName: String
        get() =
            projectPath
                .substringAfter("/src/")
                .substringBefore("/")

    override fun resideInSourceSet(sourceSetName: String): Boolean = sourceSetName == this.sourceSetName
}
