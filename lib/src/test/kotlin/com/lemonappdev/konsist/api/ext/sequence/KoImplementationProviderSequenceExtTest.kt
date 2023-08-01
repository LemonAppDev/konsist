package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoImplementationProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImplementationProviderSequenceExtTest {
    @Test
    fun `withImplementation() returns declaration with implementation`() {
        // given
        val declaration1: KoImplementationProvider = mockk {
            every { hasImplementation } returns true
        }
        val declaration2: KoImplementationProvider = mockk {
            every { hasImplementation } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withImplementation()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutImplementation() returns declaration without implementation`() {
        // given
        val declaration1: KoImplementationProvider = mockk {
            every { hasImplementation } returns true
        }
        val declaration2: KoImplementationProvider = mockk {
            every { hasImplementation } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImplementation()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }
}
