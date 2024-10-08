package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoImportProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportProviderListExtTest {
    @Test
    fun `imports returns imports from all declarations`() {
        // given
        val import1: KoImportDeclaration = mockk()
        val import2: KoImportDeclaration = mockk()
        val import3: KoImportDeclaration = mockk()
        val declaration1: KoImportProvider =
            mockk {
                every { imports } returns listOf(import1, import2)
            }
        val declaration2: KoImportProvider =
            mockk {
                every { imports } returns listOf(import3)
            }
        val declaration3: KoImportProvider =
            mockk {
                every { imports } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.imports

        // then
        sut shouldBeEqualTo listOf(import1, import2, import3)
    }

    @Test
    fun `withImports() returns declaration with any import`() {
        // given
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports() } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImports()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withImportNamed(empty list) returns declaration with any import`() {
        // given
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports() } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImportNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withImportNamed(empty set) returns declaration with any import`() {
        // given
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports() } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImportNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllImportsNamed(empty list) returns declaration with any import`() {
        // given
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports() } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllImportsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllImportsNamed(empty set) returns declaration with any import`() {
        // given
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports() } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllImportsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutImports() returns declaration without any import`() {
        // given
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports() } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImports()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutImportNamed(empty list) returns declaration without any import`() {
        // given
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports() } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImportNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutImportNamed(empty set) returns declaration without any import`() {
        // given
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports() } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImportNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllImportsNamed(empty list) returns declaration without any import`() {
        // given
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports() } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllImportsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllImportsNamed(empty set) returns declaration without any import`() {
        // given
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports() } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllImportsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withImportNamed(name) returns declaration with given import`() {
        // given
        val name = "SampleName"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportWithName(listOf(name)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImportNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withImportNamed(String) returns declaration with any of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImportNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withImportNamed(list of String) returns declaration with any of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withImportNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withImportNamed(set of String) returns declaration with any of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withImportNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutImportNamed(name) returns declaration without given import`() {
        // given
        val name = "SampleName"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportWithName(listOf(name)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImportNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutImportNamed(String) returns declaration without any of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImportNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutImportNamed(list of String) returns declaration without any of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutImportNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutImportNamed(set of String) returns declaration without any of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutImportNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllImportsNamed(name) returns declaration with given import`() {
        // given
        val name = "SampleName"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllImportsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllImportsNamed(String) returns declaration with all given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllImportsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllImportsNamed(list of String) returns declaration with all given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllImportsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllImportsNamed(set of String) returns declaration with all given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllImportsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllImportsNamed(name) returns declaration without given import`() {
        // given
        val name = "SampleName"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllImportsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllImportsNamed(String) returns declaration without all of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllImportsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllImportsNamed(list of String) returns declaration without all of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllImportsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllImportsNamed(set of String) returns declaration without all of given imports`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllImportsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withImport{} returns declaration with import which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoImportDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoImportProvider =
            mockk {
                every { hasImport(predicate) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImport(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImport(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutImport{} returns declaration without import which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoImportDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoImportProvider =
            mockk {
                every { hasImport(predicate) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImport(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImport(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllImports{} returns declaration with all imports satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoImportDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoImportProvider =
            mockk {
                every { hasAllImports(predicate) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasAllImports(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllImports(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllImports{} returns declaration with all imports which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoImportDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoImportProvider =
            mockk {
                every { hasAllImports(predicate) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasAllImports(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllImports(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withImports{} returns declaration with imports which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoImportDeclaration>) -> Boolean =
            { it.all { import -> import.hasNameEndingWith(suffix) } }
        val import1: KoImportDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val import2: KoImportDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoImportProvider =
            mockk {
                every { imports } returns listOf(import1)
            }
        val declaration2: KoImportProvider =
            mockk {
                every { imports } returns listOf(import2)
            }
        val declaration3: KoImportProvider =
            mockk {
                every { imports } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withImports(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutImports{} returns declaration without imports which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoImportDeclaration>) -> Boolean =
            { it.all { import -> import.hasNameEndingWith(suffix) } }
        val import1: KoImportDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val import2: KoImportDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoImportProvider =
            mockk {
                every { imports } returns listOf(import1)
            }
        val declaration2: KoImportProvider =
            mockk {
                every { imports } returns listOf(import2)
            }
        val declaration3: KoImportProvider =
            mockk {
                every { imports } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutImports(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
