package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoSecondaryConstructorsProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoSecondaryConstructorsProviderSequenceExtTest {

    @Test
    fun `withSecondaryConstructors() returns declaration with secondary constructor`() {
        // given
        val declaration1: KoSecondaryConstructorsProvider = mockk {
            every { hasSecondaryConstructors() } returns true
        }
        val declaration2: KoSecondaryConstructorsProvider = mockk {
            every { hasSecondaryConstructors() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withSecondaryConstructors()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSecondaryConstructors() returns declaration without secondary constructor`() {
        // given
        val declaration1: KoSecondaryConstructorsProvider = mockk {
            every { hasSecondaryConstructors() } returns true
        }
        val declaration2: KoSecondaryConstructorsProvider = mockk {
            every { hasSecondaryConstructors() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSecondaryConstructors()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }
}
