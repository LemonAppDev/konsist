package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoInterface
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceSequenceExtTest {
    @Test
    fun `withActualModifier() returns interface1 with actual modifier`() {
        // given
        val interface1: KoInterface = mockk {
            every { hasActualModifier() } returns true
        }
        val interface2: KoInterface = mockk {
            every { hasActualModifier() } returns false
        }
        val interfaces = sequenceOf(interface1, interface2)

        // when
        val sut = interfaces.withActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(interface1)
    }

    @Test
    fun `withoutActualModifier() returns interface2 without actual modifier`() {
        // given
        val interface1: KoInterface = mockk {
            every { hasActualModifier() } returns true
        }
        val interface2: KoInterface = mockk {
            every { hasActualModifier() } returns false
        }
        val interfaces = sequenceOf(interface1, interface2)

        // when
        val sut = interfaces.withoutActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(interface2)
    }

    @Test
    fun `withExpectModifier() returns interface1 with expect modifier`() {
        // given
        val interface1: KoInterface = mockk {
            every { hasExpectModifier() } returns true
        }
        val interface2: KoInterface = mockk {
            every { hasExpectModifier() } returns false
        }
        val interfaces = sequenceOf(interface1, interface2)

        // when
        val sut = interfaces.withExpectModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(interface1)
    }

    @Test
    fun `withoutExpectModifier() returns interface2 without expect modifier`() {
        // given
        val interface1: KoInterface = mockk {
            every { hasExpectModifier() } returns true
        }
        val interface2: KoInterface = mockk {
            every { hasExpectModifier() } returns false
        }
        val interfaces = sequenceOf(interface1, interface2)

        // when
        val sut = interfaces.withoutExpectModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(interface2)
    }
}
