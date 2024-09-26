package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSourceDeclarationProviderListExtTest {
    @Test
    fun `sourceDeclarations returns source declaration types from all declarations`() {
        // given
        val sourceDeclaration1: KoBaseDeclaration = mockk()
        val sourceDeclaration2: KoBaseDeclaration = mockk()
        val declaration1: KoSourceDeclarationProvider =
            mockk { every { sourceDeclaration } returns sourceDeclaration1 }
        val declaration2: KoSourceDeclarationProvider =
            mockk { every { sourceDeclaration } returns sourceDeclaration2 }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.sourceDeclarations

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `withSourceDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val type1: KoClassDeclaration =
            mockk { every { name } returns name1 }
        val type2: KoClassDeclaration =
            mockk { every { name } returns name2 }
        val declaration1: KoSourceDeclarationProvider =
            mockk { every { sourceDeclaration } returns type1 }
        val declaration2: KoSourceDeclarationProvider =
            mockk { every { sourceDeclaration } returns type2 }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceDeclaration { (it as? KoNameProvider)?.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSourceDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val type1: KoClassDeclaration =
            mockk { every { name } returns name1 }
        val type2: KoClassDeclaration =
            mockk { every { name } returns name2 }
        val declaration1: KoSourceDeclarationProvider =
            mockk { every { sourceDeclaration } returns type1 }
        val declaration2: KoSourceDeclarationProvider =
            mockk { every { sourceDeclaration } returns type2 }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceDeclaration { (it as? KoNameProvider)?.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSourceDeclarationOf(empty list) returns declaration with any source declaration`() {
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
    fun `withSourceDeclarationOf(empty set) returns declaration with any source declaration`() {
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
    fun `withoutSourceDeclarationOf(empty list) returns declaration without any source declaration`() {
        // given
        val declaration1: KoSourceDeclarationProvider = mockk()
        val declaration2: KoSourceDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutSourceDeclarationOf(empty set) returns declaration without any source declaration`() {
        // given
        val declaration1: KoSourceDeclarationProvider = mockk()
        val declaration2: KoSourceDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withSourceDeclarationOf(KClass) returns declaration with one of given source declaration`() {
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
    fun `withoutSourceDeclarationOf(KClass) returns declaration without one of given source declaration`() {
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
