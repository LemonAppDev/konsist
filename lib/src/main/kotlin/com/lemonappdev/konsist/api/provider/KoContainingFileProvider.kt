package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.container.KoFile

interface KoContainingFileProvider : KoProvider {
    /**
     * File containing the declaration.
     */
    val containingFile: KoFile
}
