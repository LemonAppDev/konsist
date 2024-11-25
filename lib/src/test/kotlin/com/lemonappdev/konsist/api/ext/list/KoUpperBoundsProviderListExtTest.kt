package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoUpperBoundsProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoUpperBoundsProviderListExtTest {
    @Test
    fun `upperBounds returns upper bounds from all declarations`() {
        // given
        val upperBound1: KoTypeDeclaration = mockk()
        val upperBound2: KoTypeDeclaration = mockk()
        val upperBound3: KoTypeDeclaration = mockk()
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { upperBounds } returns listOf(upperBound1, upperBound2)
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { upperBounds } returns listOf(upperBound3)
            }
        val declaration3: KoUpperBoundsProvider =
            mockk {
                every { upperBounds } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.upperBounds

        // then
        sut shouldBeEqualTo listOf(upperBound1, upperBound2, upperBound3)
    }

    @Test
    fun `withUpperBounds() returns declaration with any upper bound`() {
        // given
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBounds() } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBounds() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withUpperBounds()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withUpperBoundNamed(empty list) returns declaration with any upper bound`() {
        // given
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBounds() } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBounds() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withUpperBoundNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withUpperBoundNamed(empty set) returns declaration with any upper bound`() {
        // given
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBounds() } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBounds() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withUpperBoundNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutUpperBounds() returns declaration with no upper bound`() {
        // given
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBounds() } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBounds() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutUpperBounds()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutUpperBoundNamed(empty list) returns declaration with no upper bound`() {
        // given
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBounds() } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBounds() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutUpperBoundNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutUpperBoundNamed(empty set) returns declaration with no upper bound`() {
        // given
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBounds() } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBounds() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutUpperBoundNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withUpperBoundNamed(name) returns declaration with given upper bound`() {
        // given
        val name = "SampleName"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(listOf(name)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withUpperBoundNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withUpperBoundNamed(String) returns declaration with any of given upper bounds`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withUpperBoundNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withUpperBoundNamed(list of String) returns declaration with any of given upper bounds`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withUpperBoundNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withUpperBoundNamed(set of String) returns declaration with any of given upper bounds`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withUpperBoundNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutUpperBoundNamed(name) returns declaration without given upper bound`() {
        // given
        val name = "SampleName"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(listOf(name)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutUpperBoundNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutUpperBoundNamed(String) returns declaration without any of given upper bounds`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutUpperBoundNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutUpperBoundNamed(list of String) returns declaration without any of given upper bounds`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutUpperBoundNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutUpperBoundNamed(set of String) returns declaration without any of given upper bounds`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutUpperBoundNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllUpperBoundsNamed(name) returns declaration with given upper bound`() {
        // given
        val name = "SampleName"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllUpperBoundsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllUpperBoundsNamed(String) returns declaration with all given upper bounds`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllUpperBoundsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllUpperBoundsNamed(list of String) returns declaration with all given upper bounds`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllUpperBoundsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllUpperBoundsNamed(set of String) returns declaration with all given upper bounds`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllUpperBoundsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllUpperBoundsNamed(name) returns declaration without given upper bound`() {
        // given
        val name = "SampleName"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllUpperBoundsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllUpperBoundsNamed(String) returns declaration without all of given upper bounds`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllUpperBoundsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllUpperBoundsNamed(list of String) returns declaration without all of given upper bounds`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllUpperBoundsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllUpperBoundsNamed(set of String) returns declaration without all of given upper bounds`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBoundsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllUpperBoundsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withUpperBound{} returns declaration with upper bound which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBound(predicate) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBound(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withUpperBound(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutUpperBound{} returns declaration without upper bound which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBound(predicate) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasUpperBound(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutUpperBound(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllUpperBounds{} returns declaration with all upper bounds satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasAllUpperBounds(predicate) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasAllUpperBounds(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllUpperBounds(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllUpperBounds{} returns declaration with all upper bounds which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { hasAllUpperBounds(predicate) } returns true
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { hasAllUpperBounds(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllUpperBounds(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withUpperBounds{} returns declaration with upper bounds which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoTypeDeclaration>) -> Boolean =
            { it.all { upperBound -> upperBound.hasNameEndingWith(suffix) } }
        val upperBound1: KoTypeDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val upperBound2: KoTypeDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { upperBounds } returns listOf(upperBound1)
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { upperBounds } returns listOf(upperBound2)
            }
        val declaration3: KoUpperBoundsProvider =
            mockk {
                every { upperBounds } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withUpperBounds(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutUpperBounds{} returns declaration without upper bounds which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoTypeDeclaration>) -> Boolean =
            { it.all { upperBound -> upperBound.hasNameEndingWith(suffix) } }
        val upperBound1: KoTypeDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val upperBound2: KoTypeDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoUpperBoundsProvider =
            mockk {
                every { upperBounds } returns listOf(upperBound1)
            }
        val declaration2: KoUpperBoundsProvider =
            mockk {
                every { upperBounds } returns listOf(upperBound2)
            }
        val declaration3: KoUpperBoundsProvider =
            mockk {
                every { upperBounds } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutUpperBounds(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
