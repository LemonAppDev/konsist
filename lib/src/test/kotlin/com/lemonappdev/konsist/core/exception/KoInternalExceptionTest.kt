package com.lemonappdev.konsist.core.exception

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
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
        val declaration: KoBaseDeclaration = mockk {
            every { text } returns declarationText
        }
        val func = { throw KoInternalException(message, koBaseDeclaration = declaration) }

        // when
        func shouldThrow KoInternalException::class withMessage "$message, declaration:\n$declarationText"
    }

    @Test
    fun `test without declaration`() {
        // given
        val message = "message"
        val declarationText = "declaration text"
        val func = { throw KoInternalException(message, koBaseDeclaration = null) }

        // when
        func shouldThrow KoInternalException::class withMessage "$message"
    }
}
