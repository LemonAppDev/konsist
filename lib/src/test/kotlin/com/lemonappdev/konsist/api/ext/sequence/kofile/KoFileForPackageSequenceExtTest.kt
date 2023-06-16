package com.lemonappdev.konsist.api.ext.sequence.kofile

import com.lemonappdev.konsist.api.ext.sequence.withPackage
import com.lemonappdev.konsist.api.ext.sequence.withoutPackage
import com.lemonappdev.konsist.core.container.KoFileImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoFileForPackageSequenceExtTest {
    @Test
    fun `withPackage(String) returns file with given package name`() {
        // given
        val packagee = "SamplePackage"
        val file1: KoFileImpl = mockk {
            every { hasPackage(packagee) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasPackage(packagee) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withPackage(packagee)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withPackage(String) returns files with one of given package names`() {
        // given
        val package1 = "SamplePackage1"
        val package2 = "SamplePackage2"
        val file1: KoFileImpl = mockk {
            every { hasPackage(package1) } returns true
            every { hasPackage(package2) } returns false
        }
        val file2: KoFileImpl = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns true
        }
        val file3: KoFileImpl = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withPackage(package1, package2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutPackage(String) returns file without given package name`() {
        // given
        val packagee = "SamplePackage"
        val file1: KoFileImpl = mockk {
            every { hasPackage(packagee) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasPackage(packagee) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutPackage(packagee)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withoutPackage(String) returns file without any of given package names`() {
        // given
        val package1 = "SamplePackage1"
        val package2 = "SamplePackage2"
        val file1: KoFileImpl = mockk {
            every { hasPackage(package1) } returns true
            every { hasPackage(package2) } returns false
        }
        val file2: KoFileImpl = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns true
        }
        val file3: KoFileImpl = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withoutPackage(package1, package2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }
}
