package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.container.KoFile

interface KoFileProvider {
    /**
     * Sequence of the files.
     */
    val files: Sequence<KoFile>
}
