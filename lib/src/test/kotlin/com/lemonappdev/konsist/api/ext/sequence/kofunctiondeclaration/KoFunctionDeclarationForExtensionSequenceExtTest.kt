package com.lemonappdev.konsist.api.ext.sequence.kofunctiondeclaration

import com.lemonappdev.konsist.api.ext.sequence.withExtension
import com.lemonappdev.konsist.api.ext.sequence.withoutExtension
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForExtensionSequenceExtTest {
    @Test
    fun `withExtension() returns function with extension`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { isExtension() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { isExtension() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withExtension()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutExtension() returns function without extension`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { isExtension() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { isExtension() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutExtension()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }
}
