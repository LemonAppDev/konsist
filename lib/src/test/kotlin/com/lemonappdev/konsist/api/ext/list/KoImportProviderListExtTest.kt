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
    fun `withImportNamed(name) returns declaration with given import`() {
        // given
        val name = "SampleName"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportWithName(name) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportWithName(name) } returns false
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
                every { hasImportWithName(name1, name2) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImportNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutImportNamed(name) returns declaration without given import`() {
        // given
        val name = "SampleName"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportWithName(name) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportWithName(name) } returns false
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
                every { hasImportWithName(name1, name2) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImportNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllImportsNamed(name) returns declaration with given import`() {
        // given
        val name = "SampleName"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(name) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(name) } returns false
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
                every { hasImportsWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllImportsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllImportsNamed(name) returns declaration without given import`() {
        // given
        val name = "SampleName"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(name) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(name) } returns false
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
                every { hasImportsWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImportsWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllImportsNamed(name1, name2)

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

    @Test
    fun `withAllImports(String) returns declaration with all of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports(import1, import2) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports(import1, import2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllImports(import1, import2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllImports(String) returns declaration without any of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports(import1, import2) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports(import1, import2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllImports(import1, import2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeImports(String) returns declaration with given import`() {
        // given
        val import = "SampleImport"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports(import) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports(import) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeImports(import)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeImports(String) returns declarations with at least one of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports(import1) } returns true
                every { hasImports(import2) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports(import1) } returns false
                every { hasImports(import2) } returns true
            }
        val declaration3: KoImportProvider =
            mockk {
                every { hasImports(import1) } returns false
                every { hasImports(import2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeImports(import1, import2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeImports(String) returns declaration with given import`() {
        // given
        val import = "SampleImport"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports(import) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports(import) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeImports(import)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeImports(String) returns declarations with at least one of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val declaration1: KoImportProvider =
            mockk {
                every { hasImports(import1) } returns true
                every { hasImports(import2) } returns true
            }
        val declaration2: KoImportProvider =
            mockk {
                every { hasImports(import1) } returns false
                every { hasImports(import2) } returns true
            }
        val declaration3: KoImportProvider =
            mockk {
                every { hasImports(import1) } returns false
                every { hasImports(import2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeImports(import1, import2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
