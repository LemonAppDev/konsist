package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class LayerTest {
    @Test
    fun `throws an exception when layer is defined by package without two dots at the end`() {
        // given
        val sut = { Layer("Layer", "package") }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Layer Layer must be defined by package ending with '..'. Now: package .
        """.trimIndent()
    }
}
