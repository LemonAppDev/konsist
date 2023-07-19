package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.core.ext.sep

internal interface KoSourceSetProviderCore : KoSourceSetProvider, KoPathProviderCore {
    override val sourceSetName: String
        get() = projectPath
            .substringAfter("${sep}src$sep")
            .substringBefore(sep)

    override fun resideInSourceSet(sourceSet: String): Boolean = sourceSet == sourceSetName
}
