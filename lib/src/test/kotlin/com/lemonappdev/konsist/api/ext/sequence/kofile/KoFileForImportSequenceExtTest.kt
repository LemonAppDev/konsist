package com.lemonappdev.konsist.api.ext.sequence.kofile

import com.lemonappdev.konsist.api.ext.sequence.withImports
import com.lemonappdev.konsist.api.ext.sequence.withSomeImports
import com.lemonappdev.konsist.api.ext.sequence.withoutImports
import com.lemonappdev.konsist.core.container.KoFileImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoFileForImportSequenceExtTest {
    @Test
    fun `withImport() returns file with any import`() {
        // given
        val file1: KoFileImpl = mockk {
            every { hasImports() } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasImports() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withImports()

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutImport() returns file without any import`() {
        // given
        val file1: KoFileImpl = mockk {
            every { hasImports() } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasImports() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutImports()

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withImports(String) returns file with given import`() {
        // given
        val import = "SampleImport"
        val file1: KoFileImpl = mockk {
            every { hasImports(import) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasImports(import) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withImports(import)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutImports(String) returns file without given import`() {
        // given
        val import = "SampleImport"
        val file1: KoFileImpl = mockk {
            every { hasImports(import) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasImports(import) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutImports(import)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withImports(String) returns file with all of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoFileImpl = mockk {
            every { hasImports(import1, import2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasImports(import1, import2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withImports(import1, import2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutImports(String) returns file without any of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoFileImpl = mockk {
            every { hasImports(import1, import2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasImports(import1, import2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutImports(import1, import2)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withSomeImports(String) returns files with at least one of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoFileImpl = mockk {
            every { hasImports(import1) } returns true
            every { hasImports(import2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasImports(import1) } returns false
            every { hasImports(import2) } returns true
        }
        val file3: KoFileImpl = mockk {
            every { hasImports(import1) } returns false
            every { hasImports(import2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withSomeImports(import1, import2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }
}
