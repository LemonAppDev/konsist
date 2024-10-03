package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoReceiverTypeProviderListExtTest {
    @Test
    fun `receiverTypes returns receiver types from all declarations`() {
        // given
        val receiverType1: KoTypeDeclaration = mockk()
        val receiverType2: KoTypeDeclaration = mockk()
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { receiverType } returns receiverType1
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { receiverType } returns receiverType2
            }
        val declaration3: KoReceiverTypeProvider =
            mockk {
                every { receiverType } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.receiverTypes

        // then
        sut shouldBeEqualTo listOf(receiverType1, receiverType2)
    }

    @Test
    fun `withReceiverType() returns declaration with any receiver`() {
        // given
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverType() } returns true
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverType() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReceiverType()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withReceiverType{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val type1: KoTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val type2: KoTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { receiverType } returns type1
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { receiverType } returns type2
            }
        val declaration3: KoReceiverTypeProvider =
            mockk {
                every { receiverType } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withReceiverType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutReceiverType() returns declaration without any receiver`() {
        // given
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverType() } returns true
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverType() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReceiverType()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutReceiverType{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val type1: KoTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val type2: KoTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { receiverType } returns type1
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { receiverType } returns type2
            }
        val declaration3: KoReceiverTypeProvider =
            mockk {
                every { receiverType } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutReceiverType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withReceiverTypeOf(empty list) returns declaration with any receiver`() {
        // given
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverType() } returns true
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverType() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReceiverTypeOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withReceiverTypeOf(empty set) returns declaration with any receiver`() {
        // given
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverType() } returns true
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverType() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReceiverTypeOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutReceiverTypeOf(empty list) returns declaration without any receiver`() {
        // given
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverType() } returns true
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverType() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReceiverTypeOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutReceiverTypeOf(empty set) returns declaration without any receiver`() {
        // given
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverType() } returns true
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverType() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReceiverTypeOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withReceiverTypeOf(KClass) returns declaration with one of given receiver`() {
        // given
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns true
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReceiverTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withReceiverTypeOf(KClass) returns declarations with one of given receivers`() {
        // given
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns true
                every { hasReceiverTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns false
                every { hasReceiverTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns false
                every { hasReceiverTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withReceiverTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withReceiverTypeOf(list of KClass) returns declarations with one of given receivers`() {
        // given
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns true
                every { hasReceiverTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns false
                every { hasReceiverTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns false
                every { hasReceiverTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withReceiverTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withReceiverTypeOf(set of KClass) returns declarations with one of given receivers`() {
        // given
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns true
                every { hasReceiverTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns false
                every { hasReceiverTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns false
                every { hasReceiverTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withReceiverTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutReceiverTypeOf(KClass) returns declaration without one of given receiver`() {
        // given
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns true
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReceiverTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutReceiverTypeOf(KClass) returns declaration without any of given receivers`() {
        // given
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns true
                every { hasReceiverTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns false
                every { hasReceiverTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns false
                every { hasReceiverTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutReceiverTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutReceiverTypeOf(list of KClass) returns declaration without any of given receivers`() {
        // given
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns true
                every { hasReceiverTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns false
                every { hasReceiverTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns false
                every { hasReceiverTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutReceiverTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutReceiverTypeOf(set of KClass) returns declaration without any of given receivers`() {
        // given
        val declaration1: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns true
                every { hasReceiverTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns false
                every { hasReceiverTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoReceiverTypeProvider =
            mockk {
                every { hasReceiverTypeOf(SampleType1::class) } returns false
                every { hasReceiverTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutReceiverTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
