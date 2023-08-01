package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.provider.KoFileProvider

internal interface KoFileProviderCore : KoFileProvider, KoBaseProviderCore {
    val koFiles: List<KoFile>

    override val files: List<KoFile>
        get() = koFiles.sortedBy { it.path }

    override fun hasFiles(vararg names: String): Boolean = when {
        names.isEmpty() -> files.toString().isNotEmpty()
        else -> names.all {
            files.any { file -> file.name == it }
        }
    }
}
