package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoFileProvider

internal interface KoFileProviderCore : KoFileProvider, KoBaseProviderCore {
    val koFiles: List<KoFileDeclaration>

    override val files: List<KoFileDeclaration>
        get() = koFiles.sortedBy { it.path }

    override val numFiles: Int
        get() = files.size

    override fun hasFiles(vararg names: String): Boolean = when {
        names.isEmpty() -> files.toString().isNotEmpty()
        else -> names.all {
            files.any { file -> file.name == it }
        }
    }
}
