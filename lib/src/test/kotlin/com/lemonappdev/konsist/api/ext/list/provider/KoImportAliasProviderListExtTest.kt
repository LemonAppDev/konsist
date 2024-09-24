package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoImportAliasProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportAliasProviderListExtTest {
    @Test
    fun `import aliases returns import aliases from all declarations`() {
        // given
        val import1: KoImportAliasDeclaration = mockk()
        val import2: KoImportAliasDeclaration = mockk()
        val import3: KoImportAliasDeclaration = mockk()
        val declaration1: KoImportAliasProvider =
            mockk {
                every { importAliases } returns listOf(import1, import2)
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { importAliases } returns listOf(import3)
            }
        val declaration3: KoImportAliasProvider =
            mockk {
                every { importAliases } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.importAliases

        // then
        sut shouldBeEqualTo listOf(import1, import2, import3)
    }

    @Test
    fun `withImportAliases() returns declaration with any import`() {
        // given
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImportAliases()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withImportAliasNamed(empty list) returns declaration with any import`() {
        // given
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImportAliasNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withImportAliasNamed(empty set) returns declaration with any import`() {
        // given
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImportAliasNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllImportAliasesNamed(empty list) returns declaration with any import`() {
        // given
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllImportAliasesNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllImportAliasesNamed(empty set) returns declaration with any import`() {
        // given
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllImportAliasesNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutImportAliases() returns declaration without any import`() {
        // given
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImportAliases()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutImportAliasNamed(empty list) returns declaration without any import`() {
        // given
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImportAliasNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutImportAliasNamed(empty set) returns declaration without any import`() {
        // given
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImportAliasNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllImportAliasesNamed(empty list) returns declaration without any import`() {
        // given
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllImportAliasesNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllImportAliasesNamed(empty set) returns declaration without any import`() {
        // given
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliases() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllImportAliasesNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withImportAliasNamed(name) returns declaration with given import`() {
        // given
        val name = "SampleName"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(listOf(name)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImportAliasNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withImportAliasNamed(String) returns declaration with any of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImportAliasNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withImportAliasNamed(list of String) returns declaration with any of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withImportAliasNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withImportAliasNamed(set of String) returns declaration with any of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withImportAliasNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutImportAliasNamed(name) returns declaration without given import`() {
        // given
        val name = "SampleName"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(listOf(name)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImportAliasNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutImportAliasNamed(String) returns declaration without any of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImportAliasNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutImportAliasNamed(list of String) returns declaration without any of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutImportAliasNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutImportAliasNamed(set of String) returns declaration without any of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutImportAliasNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllImportAliasesNamed(name) returns declaration with given import`() {
        // given
        val name = "SampleName"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllImportAliasesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllImportAliasesNamed(String) returns declaration with all given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllImportAliasesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllImportAliasesNamed(list of String) returns declaration with all given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllImportAliasesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllImportAliasesNamed(set of String) returns declaration with all given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllImportAliasesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllImportAliasesNamed(name) returns declaration without given import`() {
        // given
        val name = "SampleName"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllImportAliasesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllImportAliasesNamed(String) returns declaration without all of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllImportAliasesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllImportAliasesNamed(list of String) returns declaration without all of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllImportAliasesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllImportAliasesNamed(set of String) returns declaration without all of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAliasesWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllImportAliasesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withImport{} returns declaration with import which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoImportAliasDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAlias(predicate) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAlias(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImportAlias(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutImport{} returns declaration without import which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoImportAliasDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasImportAlias(predicate) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasImportAlias(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImportAlias(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllImportAliases{} returns declaration with all import aliases satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoImportAliasDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasAllImportAliases(predicate) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasAllImportAliases(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllImportAliases(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllImportAliases{} returns declaration with all import aliases which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoImportAliasDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoImportAliasProvider =
            mockk {
                every { hasAllImportAliases(predicate) } returns true
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { hasAllImportAliases(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllImportAliases(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withImportAliases{} returns declaration with import aliases which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoImportAliasDeclaration>) -> Boolean =
            { it.all { import -> import.hasNameEndingWith(suffix) } }
        val import1: KoImportAliasDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val import2: KoImportAliasDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoImportAliasProvider =
            mockk {
                every { importAliases } returns listOf(import1)
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { importAliases } returns listOf(import2)
            }
        val declaration3: KoImportAliasProvider =
            mockk {
                every { importAliases } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withImportAliases(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutImportAliases{} returns declaration without import aliases which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoImportAliasDeclaration>) -> Boolean =
            { it.all { import -> import.hasNameEndingWith(suffix) } }
        val import1: KoImportAliasDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val import2: KoImportAliasDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoImportAliasProvider =
            mockk {
                every { importAliases } returns listOf(import1)
            }
        val declaration2: KoImportAliasProvider =
            mockk {
                every { importAliases } returns listOf(import2)
            }
        val declaration3: KoImportAliasProvider =
            mockk {
                every { importAliases } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutImportAliases(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
