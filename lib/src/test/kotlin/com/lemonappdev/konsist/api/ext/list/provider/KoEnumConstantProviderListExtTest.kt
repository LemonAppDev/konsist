package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoEnumConstantDeclaration
import com.lemonappdev.konsist.api.provider.KoEnumConstantProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantProviderListExtTest {
    @Test
    fun `enumConstants returns constants from all declarations`() {
        // given
        val constants1: KoEnumConstantDeclaration = mockk()
        val constants2: KoEnumConstantDeclaration = mockk()
        val constants3: KoEnumConstantDeclaration = mockk()
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { enumConstants } returns listOf(constants1, constants2)
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { enumConstants } returns listOf(constants3)
            }
        val declaration3: KoEnumConstantProvider =
            mockk {
                every { enumConstants } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.enumConstants

        // then
        sut shouldBeEqualTo listOf(constants1, constants2, constants3)
    }

    @Test
    fun `withEnumConstants() returns declaration with any constant`() {
        // given
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withEnumConstants()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withEnumConstantNamed(empty list) returns declaration with any constant`() {
        // given
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withEnumConstantNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withEnumConstantNamed(empty set) returns declaration with any constant`() {
        // given
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withEnumConstantNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllEnumConstantsNamed(empty list) returns declaration with any constant`() {
        // given
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllEnumConstantsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllEnumConstantsNamed(empty set) returns declaration with any constant`() {
        // given
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllEnumConstantsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutEnumConstants() returns declaration without any constant`() {
        // given
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutEnumConstants()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutEnumConstantNamed(empty list) returns declaration without any constant`() {
        // given
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutEnumConstantNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutEnumConstantNamed(empty set) returns declaration without any constant`() {
        // given
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutEnumConstantNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllEnumConstantsNamed(empty list) returns declaration without any constant`() {
        // given
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllEnumConstantsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllEnumConstantsNamed(empty set) returns declaration without any constant`() {
        // given
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstants() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllEnumConstantsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withEnumConstantNamed(name) returns declaration with given enum constant`() {
        // given
        val name = "SampleName"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(listOf(name)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withEnumConstantNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withEnumConstantNamed(String) returns declaration with any of given enum constants`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withEnumConstantNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withEnumConstantNamed(list of String) returns declaration with any of given enum constants`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withEnumConstantNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withEnumConstantNamed(set of String) returns declaration with any of given enum constants`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withEnumConstantNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutEnumConstantNamed(name) returns declaration without given enum constant`() {
        // given
        val name = "SampleName"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(listOf(name)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutEnumConstantNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutEnumConstantNamed(String) returns declaration without any of given enum constants`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutEnumConstantNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutEnumConstantNamed(list of String) returns declaration without any of given enum constants`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutEnumConstantNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutEnumConstantNamed(set of String) returns declaration without any of given enum constants`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutEnumConstantNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllEnumConstantsNamed(name) returns declaration with given enum constant`() {
        // given
        val name = "SampleName"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllEnumConstantsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllEnumConstantsNamed(String) returns declaration with all given enum constants`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllEnumConstantsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllEnumConstantsNamed(list of String) returns declaration with all given enum constants`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllEnumConstantsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllEnumConstantsNamed(set of String) returns declaration with all given enum constants`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllEnumConstantsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllEnumConstantsNamed(name) returns declaration without given enum constant`() {
        // given
        val name = "SampleName"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllEnumConstantsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllEnumConstantsNamed(String) returns declaration without all of given enum constants`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllEnumConstantsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllEnumConstantsNamed(list of String) returns declaration without all of given enum constants`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllEnumConstantsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllEnumConstantsNamed(set of String) returns declaration without all of given enum constants`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstantsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllEnumConstantsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withEnumConstant{} returns declaration with enum constant which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoEnumConstantDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstant(predicate) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstant(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withEnumConstant(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutEnumConstant{} returns declaration without enum constant which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoEnumConstantDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstant(predicate) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasEnumConstant(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutEnumConstant(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllEnumConstants{} returns declaration with all enum constants satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoEnumConstantDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasAllEnumConstants(predicate) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasAllEnumConstants(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllEnumConstants(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllEnumConstants{} returns declaration with all enum constants which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoEnumConstantDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { hasAllEnumConstants(predicate) } returns true
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { hasAllEnumConstants(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllEnumConstants(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withEnumConstants{} returns declaration with enum constants which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoEnumConstantDeclaration>) -> Boolean =
            { it.all { enumConstant -> enumConstant.hasNameEndingWith(suffix) } }
        val enumConstant1: KoEnumConstantDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val enumConstant2: KoEnumConstantDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { enumConstants } returns listOf(enumConstant1)
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { enumConstants } returns listOf(enumConstant2)
            }
        val declaration3: KoEnumConstantProvider =
            mockk {
                every { enumConstants } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withEnumConstants(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutEnumConstants{} returns declaration without enum constants which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoEnumConstantDeclaration>) -> Boolean =
            { it.all { enumConstant -> enumConstant.hasNameEndingWith(suffix) } }
        val enumConstant1: KoEnumConstantDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val enumConstant2: KoEnumConstantDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoEnumConstantProvider =
            mockk {
                every { enumConstants } returns listOf(enumConstant1)
            }
        val declaration2: KoEnumConstantProvider =
            mockk {
                every { enumConstants } returns listOf(enumConstant2)
            }
        val declaration3: KoEnumConstantProvider =
            mockk {
                every { enumConstants } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutEnumConstants(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
