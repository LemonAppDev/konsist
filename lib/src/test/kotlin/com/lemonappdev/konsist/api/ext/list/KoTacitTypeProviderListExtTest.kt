package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoTacitTypeProvider
import com.lemonappdev.konsist.testdata.FixtureClass1
import com.lemonappdev.konsist.testdata.FixtureClass2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTacitTypeProviderListExtTest {
    @Test
    fun `withTacitType(empty list) returns all declarations`() {
        // given
        val declaration1: KoTacitTypeProvider = mockk()
        val declaration2: KoTacitTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTacitType(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTacitType(empty set) returns all declarations`() {
        // given
        val declaration1: KoTacitTypeProvider = mockk()
        val declaration2: KoTacitTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTacitType(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTacitType(empty list) returns none declaration`() {
        // given
        val declaration1: KoTacitTypeProvider = mockk()
        val declaration2: KoTacitTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTacitType(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutTacitType(empty set) returns none declaration`() {
        // given
        val declaration1: KoTacitTypeProvider = mockk()
        val declaration2: KoTacitTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTacitType(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withTacitType(type) returns declaration with given tacit type`() {
        // given
        val tacitType = "FixtureClass1"
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType) } returns true
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
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
        val tacitType1 = "FixtureClass1"
        val tacitType2 = "FixtureClass2"
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns true
                every { hasTacitType(tacitType2) } returns false
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns false
                every { hasTacitType(tacitType2) } returns true
            }
        val declaration3: KoTacitTypeProvider =
            mockk {
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
    fun `withTacitType(list of type) returns declarations with one of given tacit declarations`() {
        // given
        val tacitType1 = "FixtureClass1"
        val tacitType2 = "FixtureClass2"
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns true
                every { hasTacitType(tacitType2) } returns false
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns false
                every { hasTacitType(tacitType2) } returns true
            }
        val declaration3: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns false
                every { hasTacitType(tacitType2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val types = listOf(tacitType1, tacitType2)

        // when
        val sut = declarations.withTacitType(types)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTacitType(set of type) returns declarations with one of given tacit declarations`() {
        // given
        val tacitType1 = "FixtureClass1"
        val tacitType2 = "FixtureClass2"
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns true
                every { hasTacitType(tacitType2) } returns false
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns false
                every { hasTacitType(tacitType2) } returns true
            }
        val declaration3: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns false
                every { hasTacitType(tacitType2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val types = setOf(tacitType1, tacitType2)

        // when
        val sut = declarations.withTacitType(types)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTacitType(type) returns declaration without given tacit type`() {
        // given
        val tacitType = "FixtureClass1"
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType) } returns true
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
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
        val tacitType1 = "FixtureClass1"
        val tacitType2 = "FixtureClass2"
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns true
                every { hasTacitType(tacitType2) } returns false
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns false
                every { hasTacitType(tacitType2) } returns true
            }
        val declaration3: KoTacitTypeProvider =
            mockk {
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
    fun `withoutTacitType(list of type) returns declaration without any of given tacit type`() {
        // given
        val tacitType1 = "FixtureClass1"
        val tacitType2 = "FixtureClass2"
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns true
                every { hasTacitType(tacitType2) } returns false
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns false
                every { hasTacitType(tacitType2) } returns true
            }
        val declaration3: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns false
                every { hasTacitType(tacitType2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val types = listOf(tacitType1, tacitType2)

        // when
        val sut = declarations.withoutTacitType(types)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutTacitType(set of type) returns declaration without any of given tacit type`() {
        // given
        val tacitType1 = "FixtureClass1"
        val tacitType2 = "FixtureClass2"
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns true
                every { hasTacitType(tacitType2) } returns false
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns false
                every { hasTacitType(tacitType2) } returns true
            }
        val declaration3: KoTacitTypeProvider =
            mockk {
                every { hasTacitType(tacitType1) } returns false
                every { hasTacitType(tacitType2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val types = setOf(tacitType1, tacitType2)

        // when
        val sut = declarations.withoutTacitType(types)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withTacitTypeOf(empty list) returns all declarations`() {
        // given
        val declaration1: KoTacitTypeProvider = mockk()
        val declaration2: KoTacitTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTacitTypeOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTacitTypeOf(empty set) returns all declarations`() {
        // given
        val declaration1: KoTacitTypeProvider = mockk()
        val declaration2: KoTacitTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTacitTypeOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTacitTypeOf(empty list) returns none declaration`() {
        // given
        val declaration1: KoTacitTypeProvider = mockk()
        val declaration2: KoTacitTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTacitTypeOf(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutTacitTypeOf(empty set) returns none declaration`() {
        // given
        val declaration1: KoTacitTypeProvider = mockk()
        val declaration2: KoTacitTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTacitTypeOf(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withTacitTypeOf(KClass) returns declaration with given tacit declaration`() {
        // given
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns true
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTacitTypeOf(FixtureClass1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTacitTypeOf(KClass) returns declarations with one of given tacit declarations`() {
        // given
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns true
                every { hasTacitTypeOf(FixtureClass2::class) } returns false
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns false
                every { hasTacitTypeOf(FixtureClass2::class) } returns true
            }
        val declaration3: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns false
                every { hasTacitTypeOf(FixtureClass2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withTacitTypeOf(FixtureClass1::class, FixtureClass2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTacitTypeOf(list of KClass) returns declarations with one of given tacit declarations`() {
        // given
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns true
                every { hasTacitTypeOf(FixtureClass2::class) } returns false
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns false
                every { hasTacitTypeOf(FixtureClass2::class) } returns true
            }
        val declaration3: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns false
                every { hasTacitTypeOf(FixtureClass2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val types = listOf(FixtureClass1::class, FixtureClass2::class)

        // when
        val sut = declarations.withTacitTypeOf(types)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTacitTypeOf(set of KClass) returns declarations with one of given tacit declarations`() {
        // given
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns true
                every { hasTacitTypeOf(FixtureClass2::class) } returns false
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns false
                every { hasTacitTypeOf(FixtureClass2::class) } returns true
            }
        val declaration3: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns false
                every { hasTacitTypeOf(FixtureClass2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val types = setOf(FixtureClass1::class, FixtureClass2::class)

        // when
        val sut = declarations.withTacitTypeOf(types)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTacitTypeOf(KClass) returns declaration without given tacit declaration`() {
        // given
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns true
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTacitTypeOf(FixtureClass1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTacitTypeOf(KClass) returns declaration without any of given tacit declarations`() {
        // given
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns true
                every { hasTacitTypeOf(FixtureClass2::class) } returns false
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns false
                every { hasTacitTypeOf(FixtureClass2::class) } returns true
            }
        val declaration3: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns false
                every { hasTacitTypeOf(FixtureClass2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTacitTypeOf(FixtureClass1::class, FixtureClass2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutTacitTypeOf(list of KClass) returns declaration without any of given tacit declarations`() {
        // given
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns true
                every { hasTacitTypeOf(FixtureClass2::class) } returns false
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns false
                every { hasTacitTypeOf(FixtureClass2::class) } returns true
            }
        val declaration3: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns false
                every { hasTacitTypeOf(FixtureClass2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val types = listOf(FixtureClass1::class, FixtureClass2::class)

        // when
        val sut = declarations.withoutTacitTypeOf(types)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutTacitTypeOf(set of KClass) returns declaration without any of given tacit declarations`() {
        // given
        val declaration1: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns true
                every { hasTacitTypeOf(FixtureClass2::class) } returns false
            }
        val declaration2: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns false
                every { hasTacitTypeOf(FixtureClass2::class) } returns true
            }
        val declaration3: KoTacitTypeProvider =
            mockk {
                every { hasTacitTypeOf(FixtureClass1::class) } returns false
                every { hasTacitTypeOf(FixtureClass2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val types = setOf(FixtureClass1::class, FixtureClass2::class)

        // when
        val sut = declarations.withoutTacitTypeOf(types)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
