package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldNotThrow
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

    @Test
    fun `throws an exception when layer is defined by package without three dots at the end`() {
        // given
        val sut = { Layer("Layer", "package...") }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Layer Layer must be defined by package ending with '..'. Now: package... .
        """.trimIndent()
    }

    @Test
    fun `throws an exception when layer is defined by package with dots without two dots at the end`() {
        // given
        val sut = { Layer("Layer", "first.package") }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Layer Layer must be defined by package ending with '..'. Now: first.package .
        """.trimIndent()
    }

    @Test
    fun `throws an exception when layer is defined by package containing dots with more than two dots at the end`() {
        // given
        val sut = { Layer("Layer", "first.second.third_p.package....") }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Layer Layer must be defined by package ending with '..'. Now: first.second.third_p.package.... .
        """.trimIndent()
    }

    @Test
    fun `do not throw an exception when the package ends with two dots`() {
        // given
        val sut = { Layer("Layer", "first.second.package..") }

        // then
        sut shouldNotThrow KoPreconditionFailedException::class withMessage """
            Layer Layer must be defined by package ending with '..'. Now: first.second.third_p.package.... .
        """.trimIndent()
    }
}
