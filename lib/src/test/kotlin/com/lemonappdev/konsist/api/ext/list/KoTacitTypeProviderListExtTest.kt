package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoTacitTypeProvider
import com.lemonappdev.konsist.testdata.SampleClass1
import com.lemonappdev.konsist.testdata.SampleClass2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTacitTypeProviderListExtTest {
    @Test
    fun `withTacitType(type) returns declaration with given tacit type`() {
        // given
        val tacitType = "SampleClass1"
        val declaration1: KoTacitTypeProvider = mockk {
            every { hasTacitType(tacitType) } returns true
        }
        val declaration2: KoTacitTypeProvider = mockk {
            every { hasTacitType(tacitType) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTacitType(tacitType)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTacitType(type) returns declarations with one of given tacit declarations`() {
        // given
        val tacitType1 = "SampleClass1"
        val tacitType2 = "SampleClass2"
        val declaration1: KoTacitTypeProvider = mockk {
            every { hasTacitType(tacitType1) } returns true
            every { hasTacitType(tacitType2) } returns false
        }
        val declaration2: KoTacitTypeProvider = mockk {
            every { hasTacitType(tacitType1) } returns false
            every { hasTacitType(tacitType2) } returns true
        }
        val declaration3: KoTacitTypeProvider = mockk {
            every { hasTacitType(tacitType1) } returns false
            every { hasTacitType(tacitType2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withTacitType(tacitType1, tacitType2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTacitType(type) returns declaration without given tacit type`() {
        // given
        val tacitType = "SampleClass1"
        val declaration1: KoTacitTypeProvider = mockk {
            every { hasTacitType(tacitType) } returns true
        }
        val declaration2: KoTacitTypeProvider = mockk {
            every { hasTacitType(tacitType) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTacitType(tacitType)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTacitType(type) returns declaration without any of given tacit type`() {
        // given
        val tacitType1 = "SampleClass1"
        val tacitType2 = "SampleClass2"
        val declaration1: KoTacitTypeProvider = mockk {
            every { hasTacitType(tacitType1) } returns true
            every { hasTacitType(tacitType2) } returns false
        }
        val declaration2: KoTacitTypeProvider = mockk {
            every { hasTacitType(tacitType1) } returns false
            every { hasTacitType(tacitType2) } returns true
        }
        val declaration3: KoTacitTypeProvider = mockk {
            every { hasTacitType(tacitType1) } returns false
            every { hasTacitType(tacitType2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTacitType(tacitType1, tacitType2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withTacitTypeOf(KClass) returns declaration with given tacit declaration`() {
        // given
        val declaration1: KoTacitTypeProvider = mockk {
            every { hasTacitTypeOf(SampleClass1::class) } returns true
        }
        val declaration2: KoTacitTypeProvider = mockk {
            every { hasTacitTypeOf(SampleClass1::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTacitTypeOf(SampleClass1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTacitTypeOf(KClass) returns declarations with one of given tacit declarations`() {
        // given
        val declaration1: KoTacitTypeProvider = mockk {
            every { hasTacitTypeOf(SampleClass1::class) } returns true
            every { hasTacitTypeOf(SampleClass2::class) } returns false
        }
        val declaration2: KoTacitTypeProvider = mockk {
            every { hasTacitTypeOf(SampleClass1::class) } returns false
            every { hasTacitTypeOf(SampleClass2::class) } returns true
        }
        val declaration3: KoTacitTypeProvider = mockk {
            every { hasTacitTypeOf(SampleClass1::class) } returns false
            every { hasTacitTypeOf(SampleClass2::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withTacitTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTacitTypeOf(KClass) returns declaration without given tacit declaration`() {
        // given
        val declaration1: KoTacitTypeProvider = mockk {
            every { hasTacitTypeOf(SampleClass1::class) } returns true
        }
        val declaration2: KoTacitTypeProvider = mockk {
            every { hasTacitTypeOf(SampleClass1::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTacitTypeOf(SampleClass1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTacitTypeOf(KClass) returns declaration without any of given tacit declarations`() {
        // given
        val declaration1: KoTacitTypeProvider = mockk {
            every { hasTacitTypeOf(SampleClass1::class) } returns true
            every { hasTacitTypeOf(SampleClass2::class) } returns false
        }
        val declaration2: KoTacitTypeProvider = mockk {
            every { hasTacitTypeOf(SampleClass1::class) } returns false
            every { hasTacitTypeOf(SampleClass2::class) } returns true
        }
        val declaration3: KoTacitTypeProvider = mockk {
            every { hasTacitTypeOf(SampleClass1::class) } returns false
            every { hasTacitTypeOf(SampleClass2::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTacitTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
