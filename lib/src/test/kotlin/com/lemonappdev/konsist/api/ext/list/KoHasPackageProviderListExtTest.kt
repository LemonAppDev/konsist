package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoHasPackageProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoHasPackageProviderListExtTest {
    private interface SampleTestDeclaration : KoPackageProvider, KoHasPackageProvider

    @Test
    fun `withPackage() returns file with any package`() {
        // given
        val file1: SampleTestDeclaration = mockk {
            every { packagee } returns mockk()
        }
        val file2: SampleTestDeclaration = mockk {
            every { packagee } returns null
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withPackage()

        // then
        sut shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withPackage(String) returns files with one of given package names`() {
        // given
        val package1 = "SamplePackage1"
        val package2 = "SamplePackage2"
        val file1: SampleTestDeclaration = mockk {
            every { hasPackage(package1) } returns true
            every { hasPackage(package2) } returns false
        }
        val file2: SampleTestDeclaration = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns true
        }
        val file3: SampleTestDeclaration = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns false
        }
        val files = listOf(file1, file2, file3)

        // when
        val sut = files.withPackage(package1, package2)

        // then
        sut shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutPackage() returns file without given package name`() {
        // given
        val file1: SampleTestDeclaration = mockk {
            every { packagee } returns mockk()
        }
        val file2: SampleTestDeclaration = mockk {
            every { packagee } returns null
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withoutPackage()

        // then
        sut shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withoutPackage(String) returns file without any of given package names`() {
        // given
        val package1 = "SamplePackage1"
        val package2 = "SamplePackage2"
        val file1: SampleTestDeclaration = mockk {
            every { hasPackage(package1) } returns true
            every { hasPackage(package2) } returns false
        }
        val file2: SampleTestDeclaration = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns true
        }
        val file3: SampleTestDeclaration = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns false
        }
        val files = listOf(file1, file2, file3)

        // when
        val sut = files.withoutPackage(package1, package2)

        // then
        sut shouldBeEqualTo listOf(file3)
    }
}
