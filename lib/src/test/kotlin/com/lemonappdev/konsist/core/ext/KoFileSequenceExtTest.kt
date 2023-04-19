package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoFile
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileSequenceExtTest {
    @Test
    fun `withImports(String) returns file1 with given import`() {
        // given
        val import = "SampleImport"
        val file1: KoFile = mockk {
            every { hasImport(import) } returns true
        }
        val file2: KoFile = mockk {
            every { hasImport(import) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withImports(import)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutImports(String) returns file2 without given import`() {
        // given
        val import = "SampleImport"
        val file1: KoFile = mockk {
            every { hasImport(import) } returns true
        }
        val file2: KoFile = mockk {
            every { hasImport(import) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutImports(import)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withImports(String) returns file1 with imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoFile = mockk {
            every { hasImport(import1) } returns true
            every { hasImport(import2) } returns true
        }
        val file2: KoFile = mockk {
            every { hasImport(import1) } returns false
            every { hasImport(import2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasImport(import1) } returns false
            every { hasImport(import2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withImports(import1, import2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutImports(String) returns file3 without imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoFile = mockk {
            every { hasImport(import1) } returns true
            every { hasImport(import2) } returns true
        }
        val file2: KoFile = mockk {
            every { hasImport(import1) } returns false
            every { hasImport(import2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasImport(import1) } returns false
            every { hasImport(import2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withoutImports(import1, import2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withSomeImports(String) returns file1 and file2 which have at least one of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoFile = mockk {
            every { hasImport(import1) } returns true
            every { hasImport(import2) } returns true
        }
        val file2: KoFile = mockk {
            every { hasImport(import1) } returns false
            every { hasImport(import2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasImport(import1) } returns false
            every { hasImport(import2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withSomeImports(import1, import2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withPackage(String) returns file1 with given package`() {
        // given
        val packageName = "SamplePackage"
        val file1: KoFile = mockk {
            every { hasPackage(packageName) } returns true
        }
        val file2: KoFile = mockk {
            every { hasPackage(packageName) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withPackage(packageName)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutPackage(String) returns file2 without given package`() {
        // given
        val packageName = "SamplePackage"
        val file1: KoFile = mockk {
            every { hasPackage(packageName) } returns true
        }
        val file2: KoFile = mockk {
            every { hasPackage(packageName) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutPackage(packageName)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withPath(String) returns file1 with given path`() {
        // given
        val path1 = "SamplePath"
        val path2 = "OtherPath"
        val file1: KoFile = mockk {
            every { path } returns path1
        }
        val file2: KoFile = mockk {
            every { path } returns path2
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withPath(path1)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutPath(String) returns file2 without given path`() {
        // given
        val path1 = "SamplePath"
        val path2 = "OtherPath"
        val file1: KoFile = mockk {
            every { path } returns path1
        }
        val file2: KoFile = mockk {
            every { path } returns path2
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutPath(path1)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }
}