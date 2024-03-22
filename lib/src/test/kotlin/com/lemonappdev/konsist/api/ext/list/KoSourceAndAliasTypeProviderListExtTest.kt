package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider
import com.lemonappdev.konsist.testdata.SampleClass1
import com.lemonappdev.konsist.testdata.SampleClass2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSourceAndAliasTypeProviderListExtTest {
    @Test
    fun `withSourceTypeOf(empty list) returns all declarations`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceTypeOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceTypeOf(empty set) returns all declarations`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceTypeOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceTypeOf(KClass) returns declaration with given source declaration`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceTypeOf(SampleClass1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSourceTypeOf(KClass) returns declarations with one of given source declarations`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSourceTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceTypeOf(list of KClass) returns declarations with one of given source declarations`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleClass1::class, SampleClass2::class)

        // when
        val sut = declarations.withSourceTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceTypeOf(set of KClass) returns declarations with one of given source declarations`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleClass1::class, SampleClass2::class)

        // when
        val sut = declarations.withSourceTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSourceTypeOf(empty list) returns none declaration`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceTypeOf(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutSourceTypeOf(empty set) returns none declaration`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceTypeOf(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutSourceTypeOf(KClass) returns declaration without given source declaration`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceTypeOf(SampleClass1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSourceTypeOf(KClass) returns declaration without any of given source declarations`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSourceTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutSourceTypeOf(list of KClass) returns declaration without any of given source declarations`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleClass1::class, SampleClass2::class)

        // when
        val sut = declarations.withoutSourceTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutSourceTypeOf(set of KClass) returns declaration without any of given source declarations`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleClass1::class, SampleClass2::class)

        // when
        val sut = declarations.withoutSourceTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withSourceType(empty list) returns all declarations`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceType(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceType(empty set) returns all declarations`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceType(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceType(type) returns declaration with given source type`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceType(sourceType1)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSourceType(type) returns declarations with one of given source declarations`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSourceType(sourceType1, sourceType2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceType(list of type) returns declarations with one of given source declarations`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val sourceTypes = listOf(sourceType1, sourceType2)

        // when
        val sut = declarations.withSourceType(sourceTypes)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceType(set of type) returns declarations with one of given source declarations`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val sourceTypes = setOf(sourceType1, sourceType2)

        // when
        val sut = declarations.withSourceType(sourceTypes)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSourceType(empty list) returns none declaration`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceType(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutSourceType(empty set) returns none declaration`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceType(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutSourceType(type) returns declaration without given source type`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceType(sourceType1)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSourceType(type) returns declaration without any of given source type`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSourceType(sourceType1, sourceType2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutSourceType(list of type) returns declaration without any of given source type`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val sourceTypes = listOf(sourceType1, sourceType2)

        // when
        val sut = declarations.withoutSourceType(sourceTypes)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutSourceType(set of type) returns declaration without any of given source type`() {
        // given
        val sourceType1 = "SampleClass1"
        val sourceType2 = "SampleClass2"
        val sourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { sourceType } returns sourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val sourceTypes = setOf(sourceType1, sourceType2)

        // when
        val sut = declarations.withoutSourceType(sourceTypes)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withBareSourceTypeOf(empty list) returns all declaration`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withBareSourceTypeOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withBareSourceTypeOf(empty set) returns all declaration`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withBareSourceTypeOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withBareSourceTypeOf(KClass) returns declaration with given source declaration`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withBareSourceTypeOf(SampleClass1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withBareSourceTypeOf(KClass) returns declarations with one of given source declarations`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val bareSourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withBareSourceTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withBareSourceTypeOf(list of KClass) returns declarations with one of given source declarations`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val bareSourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleClass1::class, SampleClass2::class)

        // when
        val sut = declarations.withBareSourceTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withBareSourceTypeOf(set of KClass) returns declarations with one of given source declarations`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val bareSourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleClass1::class, SampleClass2::class)

        // when
        val sut = declarations.withBareSourceTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutBareSourceTypeOf(empty list) returns none declaration`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutBareSourceTypeOf(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutBareSourceTypeOf(empty set) returns none declaration`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutBareSourceTypeOf(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutBareSourceTypeOf(KClass) returns declaration without given source declaration`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutBareSourceTypeOf(SampleClass1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutBareSourceTypeOf(KClass) returns declaration without any of given source declarations`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val bareSourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutBareSourceTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutBareSourceTypeOf(list of KClass) returns declaration without any of given source declarations`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val bareSourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleClass1::class, SampleClass2::class)

        // when
        val sut = declarations.withoutBareSourceTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutBareSourceTypeOf(set of KClass) returns declaration without any of given source declarations`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val bareSourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleClass1::class, SampleClass2::class)

        // when
        val sut = declarations.withoutBareSourceTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withBareSourceType(empty list) returns all declarations`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withBareSourceType(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withBareSourceType(empty set) returns all declarations`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withBareSourceType(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withBareSourceType(type) returns declaration with given source type`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withBareSourceType(bareSourceType1)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withBareSourceType(type) returns declarations with one of given source declarations`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val bareSourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withBareSourceType(bareSourceType1, bareSourceType2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withBareSourceType(list of type) returns declarations with one of given source declarations`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val bareSourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val bareSourceTypes= listOf(bareSourceType1, bareSourceType2)

        // when
        val sut = declarations.withBareSourceType(bareSourceTypes)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withBareSourceType(set of type) returns declarations with one of given source declarations`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val bareSourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val bareSourceTypes= setOf(bareSourceType1, bareSourceType2)

        // when
        val sut = declarations.withBareSourceType(bareSourceTypes)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutBareSourceType(empty list) returns none declaration`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutBareSourceType(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutBareSourceType(empty set) returns none declaration`() {
        // given
        val declaration1: KoSourceAndAliasTypeProvider = mockk()
        val declaration2: KoSourceAndAliasTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutBareSourceType(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutBareSourceType(type) returns declaration without given source type`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutBareSourceType(bareSourceType1)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutBareSourceType(type) returns declaration without any of given source type`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val bareSourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutBareSourceType(bareSourceType1, bareSourceType2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutBareSourceType(list of type) returns declaration without any of given source type`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val bareSourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val bareSourceTypes= listOf(bareSourceType1, bareSourceType2)

        // when
        val sut = declarations.withoutBareSourceType(bareSourceTypes)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutBareSourceType(set of type) returns declaration without any of given source type`() {
        // given
        val bareSourceType1 = "SampleClass1"
        val bareSourceType2 = "SampleClass2"
        val bareSourceType3 = "SampleClass3"
        val declaration1: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType1
            }
        val declaration2: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType2
            }
        val declaration3: KoSourceAndAliasTypeProvider =
            mockk {
                every { bareSourceType } returns bareSourceType3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val bareSourceTypes= setOf(bareSourceType1, bareSourceType2)

        // when
        val sut = declarations.withoutBareSourceType(bareSourceTypes)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
