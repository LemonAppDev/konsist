package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.provider.KoFileProvider

internal interface KoFileProviderCore: KoFileProvider {
    val koFiles: Sequence<KoFile>

    override val files: Sequence<KoFile>
    get() = koFiles.sortedBy { it.path }
}
