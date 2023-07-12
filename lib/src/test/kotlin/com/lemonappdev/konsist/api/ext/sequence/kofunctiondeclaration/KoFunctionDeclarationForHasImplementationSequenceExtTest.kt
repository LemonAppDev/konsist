package com.lemonappdev.konsist.api.ext.sequence.kofunctiondeclaration

import com.lemonappdev.konsist.api.ext.sequence.withExtension
import com.lemonappdev.konsist.api.ext.sequence.withImplementation
import com.lemonappdev.konsist.api.ext.sequence.withoutExtension
import com.lemonappdev.konsist.api.ext.sequence.withoutImplementation
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForHasImplementationSequenceExtTest {
    @Test
    fun `withImplementation() returns function with implementation`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasImplementation() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasImplementation() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withImplementation()

        // then
        sut.toList() shouldBeEqualTo listOf(function1)
    }

    @Test
    fun `withoutImplementation() returns function without implementation`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk {
            every { hasImplementation() } returns true
        }
        val function2: KoFunctionDeclarationImpl = mockk {
            every { hasImplementation() } returns false
        }
        val functions = sequenceOf(function1, function2)

        // when
        val sut = functions.withoutImplementation()

        // then
        sut.toList() shouldBeEqualTo listOf(function2)
    }
}
