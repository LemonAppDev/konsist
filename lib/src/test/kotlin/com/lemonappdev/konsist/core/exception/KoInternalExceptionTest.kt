package com.lemonappdev.konsist.core.exception

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.provider.KoTextProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoInternalExceptionTest {
    @Test
    fun `test with declaration`() {
        // given
        val message = "message"
        val declarationText = "declaration text"
        val declaration: KoTextProvider = mockk {
            every { text } returns declarationText
        }
        val func = { throw KoInternalException(message, koBaseProvider = declaration) }

        // when
        func shouldThrow KoInternalException::class withMessage "$message, declaration:\n$declarationText"
    }

    @Test
    fun `test without declaration`() {
        // given
        val message = "message"
        val func = { throw KoInternalException(message, koBaseProvider = null) }

        // when
        func shouldThrow KoInternalException::class withMessage message
    }

    @Test
    fun `test with file`() {
        // given
        val message = "message"
        val fileText = "file text"
        val file: KoFile = mockk {
            every { text } returns fileText
        }
        val func = { throw KoInternalException(message, koFile = file) }

        // when
        func shouldThrow KoInternalException::class withMessage "$message, file:\n$fileText"
    }

    @Test
    fun `test without file`() {
        // given
        val message = "message"
        val func = { throw KoInternalException(message, koFile = null) }

        // when
        func shouldThrow KoInternalException::class withMessage message
    }
}
