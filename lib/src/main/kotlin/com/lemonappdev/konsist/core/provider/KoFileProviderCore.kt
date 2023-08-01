package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoFileProvider

internal interface KoFileProviderCore : KoFileProvider, KoBaseProviderCore {
    val koFiles: Sequence<KoFileDeclaration>

    override val files: Sequence<KoFileDeclaration>
        get() = koFiles.sortedBy { it.path }

    override fun hasFiles(vararg names: String): Boolean = when {
        names.isEmpty() -> files.toString().isNotEmpty()
        else -> names.all {
            files.any { file -> file.name == it }
        }
    }
}
