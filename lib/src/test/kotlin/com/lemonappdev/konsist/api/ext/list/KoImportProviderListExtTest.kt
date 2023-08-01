package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoImportProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoImportProviderListExtTest {
    @Test
    fun `withImports() returns file with any import`() {
        // given
        val file1: KoImportProvider = mockk {
            every { hasImports() } returns true
        }
        val file2: KoImportProvider = mockk {
            every { hasImports() } returns false
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withImports()

        // then
        sut shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutImports() returns file without any import`() {
        // given
        val file1: KoImportProvider = mockk {
            every { hasImports() } returns true
        }
        val file2: KoImportProvider = mockk {
            every { hasImports() } returns false
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withoutImports()

        // then
        sut shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withAllImports(String) returns file with all of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoImportProvider = mockk {
            every { hasImports(import1, import2) } returns true
        }
        val file2: KoImportProvider = mockk {
            every { hasImports(import1, import2) } returns false
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withAllImports(import1, import2)

        // then
        sut shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAllImports(String) returns file without any of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoImportProvider = mockk {
            every { hasImports(import1, import2) } returns true
        }
        val file2: KoImportProvider = mockk {
            every { hasImports(import1, import2) } returns false
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withoutAllImports(import1, import2)

        // then
        sut shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withSomeImports(String) returns file with given import`() {
        // given
        val import = "SampleImport"
        val file1: KoImportProvider = mockk {
            every { hasImports(import) } returns true
        }
        val file2: KoImportProvider = mockk {
            every { hasImports(import) } returns false
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withSomeImports(import)

        // then
        sut shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withSomeImports(String) returns files with at least one of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoImportProvider = mockk {
            every { hasImports(import1) } returns true
            every { hasImports(import2) } returns true
        }
        val file2: KoImportProvider = mockk {
            every { hasImports(import1) } returns false
            every { hasImports(import2) } returns true
        }
        val file3: KoImportProvider = mockk {
            every { hasImports(import1) } returns false
            every { hasImports(import2) } returns false
        }
        val files = listOf(file1, file2, file3)

        // when
        val sut = files.withSomeImports(import1, import2)

        // then
        sut shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutSomeImports(String) returns file with given import`() {
        // given
        val import = "SampleImport"
        val file1: KoImportProvider = mockk {
            every { hasImports(import) } returns true
        }
        val file2: KoImportProvider = mockk {
            every { hasImports(import) } returns false
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withoutSomeImports(import)

        // then
        sut shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withoutSomeImports(String) returns files with at least one of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoImportProvider = mockk {
            every { hasImports(import1) } returns true
            every { hasImports(import2) } returns true
        }
        val file2: KoImportProvider = mockk {
            every { hasImports(import1) } returns false
            every { hasImports(import2) } returns true
        }
        val file3: KoImportProvider = mockk {
            every { hasImports(import1) } returns false
            every { hasImports(import2) } returns false
        }
        val files = listOf(file1, file2, file3)

        // when
        val sut = files.withoutSomeImports(import1, import2)

        // then
        sut shouldBeEqualTo listOf(file3)
    }
}
