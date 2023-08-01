package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPrimaryConstructorProviderSequenceExtTest {
    @Test
    fun `withPrimaryConstructor() returns declaration with primary constructor`() {
        // given
        val declaration1: KoPrimaryConstructorProvider = mockk {
            every { hasPrimaryConstructor } returns true
        }
        val declaration2: KoPrimaryConstructorProvider = mockk {
            every { hasPrimaryConstructor } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withPrimaryConstructor()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPrimaryConstructor() returns declaration without primary constructor`() {
        // given
        val declaration1: KoPrimaryConstructorProvider = mockk {
            every { hasPrimaryConstructor } returns true
        }
        val declaration2: KoPrimaryConstructorProvider = mockk {
            every { hasPrimaryConstructor } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPrimaryConstructor()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }
}
