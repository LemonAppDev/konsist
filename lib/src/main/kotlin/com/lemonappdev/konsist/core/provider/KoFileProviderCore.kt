package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.provider.KoFileProvider

internal interface KoFileProviderCore : KoFileProvider, KoBaseProviderCore {
    val koFiles: Sequence<KoFile>

    override val files: Sequence<KoFile>
        get() = koFiles.sortedBy { it.path }

    override val numFiles: Int
        get() = files.toList().size

    override fun hasFiles(vararg names: String): Boolean = when {
        names.isEmpty() -> files.toString().isNotEmpty()
        else -> names.all {
            files.any { file -> file.name == it }
        }
    }
}
