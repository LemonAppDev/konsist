package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSourceDeclarationProviderListExtTest {
    @Test
    fun `sourceDeclarations returns source declarations from all declarations`() {
        // given
        val sourceDeclaration1: KoClassDeclaration = mockk()
        val sourceDeclaration2: KoKotlinTypeDeclaration = mockk()
        val sourceDeclaration3: KoExternalDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider =
            mockk {
                every { sourceDeclaration } returns sourceDeclaration1
            }
        val declaration2: KoSourceDeclarationProvider =
            mockk {
                every { sourceDeclaration } returns sourceDeclaration2
            }
        val declaration3: KoSourceDeclarationProvider =
            mockk {
                every { sourceDeclaration } returns sourceDeclaration3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.sourceDeclarations()

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2, sourceDeclaration3)
    }

    @Test
    fun `withSourceDeclaration{} returns source declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceDeclaration1: KoClassDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceDeclaration2: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoSourceDeclarationProvider =
            mockk {
                every { sourceDeclaration } returns sourceDeclaration1
            }
        val declaration2: KoSourceDeclarationProvider =
            mockk {
                every { sourceDeclaration } returns sourceDeclaration2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSourceDeclaration{} returns source declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceDeclaration1: KoClassDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceDeclaration2: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoSourceDeclarationProvider =
            mockk {
                every { sourceDeclaration } returns sourceDeclaration1
            }
        val declaration2: KoSourceDeclarationProvider =
            mockk {
                every { sourceDeclaration } returns sourceDeclaration2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }


    @Test
    fun `withSourceDeclarationOf(empty list) returns all source declarations`() {
        // given
        val declaration1: KoSourceDeclarationProvider = mockk()
        val declaration2: KoSourceDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceDeclarationOf(empty set) returns all source declarations`() {
        // given
        val declaration1: KoSourceDeclarationProvider = mockk()
        val declaration2: KoSourceDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceDeclarationOf(KClass) returns declaration with given source declaration`() {
        // given
        val declaration1: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSourceDeclarationOf(KClass) returns declarations with one of given source declarations`() {
        // given
        val declaration1: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSourceDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceDeclarationOf(list of KClass) returns declarations with one of given source declarations`() {
        // given
        val declaration1: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withSourceDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceDeclarationOf(set of KClass) returns declarations with one of given source declarations`() {
        // given
        val declaration1: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withSourceDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSourceDeclarationOf(empty list) returns none source declaration`() {
        // given
        val declaration1: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutSourceDeclarationOf(empty set) returns none source declaration`() {
        // given
        val declaration1: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutSourceDeclarationOf(KClass) returns declaration without given source declaration`() {
        // given
        val declaration1: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSourceDeclarationOf(KClass) returns declaration without any of given source declarations`() {
        // given
        val declaration1: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSourceDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutSourceDeclarationOf(list of KClass) returns declaration without any of given source declarations`() {
        // given
        val declaration1: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutSourceDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutSourceDeclarationOf(set of KClass) returns declaration without any of given source declarations`() {
        // given
        val declaration1: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoSourceDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutSourceDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
