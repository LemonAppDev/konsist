package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc receiver tag.
 */
interface KoKDocReceiverTagProvider : KoBaseProvider {
    /**
     * The `@receiver` tag.
     */
    val receiverTag: KoKDocTagDeclaration?

    /**
     * Determines whatever the declaration has receiver tag.
     */
    val hasReceiverTag: Boolean
}
