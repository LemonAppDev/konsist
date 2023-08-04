package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoImportProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoImportProviderListExtTest {
    @Test
    fun `withImports() returns declaration with any import`() {
        // given
        val declaration1: KoImportProvider = mockk {
            every { hasImports() } returns true
        }
        val declaration2: KoImportProvider = mockk {
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
        val declaration1: KoImportProvider = mockk {
            every { hasImports() } returns true
        }
        val declaration2: KoImportProvider = mockk {
            every { hasImports() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImports()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllImports(String) returns declaration with all of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val declaration1: KoImportProvider = mockk {
            every { hasImports(import1, import2) } returns true
        }
        val declaration2: KoImportProvider = mockk {
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
        val declaration1: KoImportProvider = mockk {
            every { hasImports(import1, import2) } returns true
        }
        val declaration2: KoImportProvider = mockk {
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
        val declaration1: KoImportProvider = mockk {
            every { hasImports(import) } returns true
        }
        val declaration2: KoImportProvider = mockk {
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
        val declaration1: KoImportProvider = mockk {
            every { hasImports(import1) } returns true
            every { hasImports(import2) } returns true
        }
        val declaration2: KoImportProvider = mockk {
            every { hasImports(import1) } returns false
            every { hasImports(import2) } returns true
        }
        val declaration3: KoImportProvider = mockk {
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
        val declaration1: KoImportProvider = mockk {
            every { hasImports(import) } returns true
        }
        val declaration2: KoImportProvider = mockk {
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
        val declaration1: KoImportProvider = mockk {
            every { hasImports(import1) } returns true
            every { hasImports(import2) } returns true
        }
        val declaration2: KoImportProvider = mockk {
            every { hasImports(import1) } returns false
            every { hasImports(import2) } returns true
        }
        val declaration3: KoImportProvider = mockk {
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
