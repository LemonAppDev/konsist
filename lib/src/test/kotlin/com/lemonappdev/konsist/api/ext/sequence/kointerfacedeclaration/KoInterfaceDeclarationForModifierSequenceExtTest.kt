package com.lemonappdev.konsist.api.ext.sequence.kointerfacedeclaration

import com.lemonappdev.konsist.api.ext.sequence.*
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForModifierSequenceExtTest {
    @Test
    fun `withActualModifier() returns interface with actual modifier`() {
        // given
        val interface1: KoInterfaceDeclarationImpl = mockk {
            every { hasActualModifier() } returns true
        }
        val interface2: KoInterfaceDeclarationImpl = mockk {
            every { hasActualModifier() } returns false
        }
        val interfaces = sequenceOf(interface1, interface2)

        // when
        val sut = interfaces.withActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(interface1)
    }

    @Test
    fun `withoutActualModifier() returns interface without actual modifier`() {
        // given
        val interface1: KoInterfaceDeclarationImpl = mockk {
            every { hasActualModifier() } returns true
        }
        val interface2: KoInterfaceDeclarationImpl = mockk {
            every { hasActualModifier() } returns false
        }
        val interfaces = sequenceOf(interface1, interface2)

        // when
        val sut = interfaces.withoutActualModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(interface2)
    }

    @Test
    fun `withExpectModifier() returns interface with expect modifier`() {
        // given
        val interface1: KoInterfaceDeclarationImpl = mockk {
            every { hasExpectModifier() } returns true
        }
        val interface2: KoInterfaceDeclarationImpl = mockk {
            every { hasExpectModifier() } returns false
        }
        val interfaces = sequenceOf(interface1, interface2)

        // when
        val sut = interfaces.withExpectModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(interface1)
    }

    @Test
    fun `withoutExpectModifier() returns interface without expect modifier`() {
        // given
        val interface1: KoInterfaceDeclarationImpl = mockk {
            every { hasExpectModifier() } returns true
        }
        val interface2: KoInterfaceDeclarationImpl = mockk {
            every { hasExpectModifier() } returns false
        }
        val interfaces = sequenceOf(interface1, interface2)

        // when
        val sut = interfaces.withoutExpectModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(interface2)
    }

    @Test
    fun `withFunModifier() returns interface with fun modifier`() {
        // given
        val interface1: KoInterfaceDeclarationImpl = mockk {
            every { hasFunModifier() } returns true
        }
        val interface2: KoInterfaceDeclarationImpl = mockk {
            every { hasFunModifier() } returns false
        }
        val interfaces = sequenceOf(interface1, interface2)

        // when
        val sut = interfaces.withFunModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(interface1)
    }

    @Test
    fun `withoutFunModifier() returns interface without fun modifier`() {
        // given
        val interface1: KoInterfaceDeclarationImpl = mockk {
            every { hasFunModifier() } returns true
        }
        val interface2: KoInterfaceDeclarationImpl = mockk {
            every { hasFunModifier() } returns false
        }
        val interfaces = sequenceOf(interface1, interface2)

        // when
        val sut = interfaces.withoutFunModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(interface2)
    }
}
