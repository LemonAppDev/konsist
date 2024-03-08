package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoFileExtensionProvider
import com.lemonappdev.konsist.core.ext.sep
import org.jetbrains.kotlin.psi.KtFile

internal interface KoFileExtensionProviderCore : KoFileExtensionProvider, KoBaseProviderCore {
    val ktFile: KtFile

    override val extension: String
        get() = nameWithExtension.substringAfterLast('.')

    override val nameWithExtension: String
        get() =
            ktFile
                .name
                .split(sep)
                .last()

    override fun hasExtension(extension: String): Boolean = extension == this.extension
}
