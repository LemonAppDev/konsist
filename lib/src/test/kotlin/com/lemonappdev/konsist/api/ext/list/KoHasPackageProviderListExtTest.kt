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
    fun `withMatchingPackage() returns declaration which has matching package`() {
        // given
        val declaration1: KoHasPackageProvider =
            mockk {
                every { hasMatchingPackage } returns true
            }
        val declaration2: KoHasPackageProvider =
            mockk {
                every { hasMatchingPackage } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withMatchingPackage()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutMatchingPackage() returns declaration which has not matching package`() {
        // given
        val declaration1: KoHasPackageProvider =
            mockk {
                every { hasMatchingPackage } returns true
            }
        val declaration2: KoHasPackageProvider =
            mockk {
                every { hasMatchingPackage } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutMatchingPackage()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withPackage() returns declaration with any package`() {
        // given
        val declaration1: SampleTestDeclaration =
            mockk {
                every { packagee } returns mockk()
            }
        val declaration2: SampleTestDeclaration =
            mockk {
                every { packagee } returns null
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPackage()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withPackage(String) returns declarations with one of given package names`() {
        // given
        val package1 = "SamplePackage1"
        val package2 = "SamplePackage2"
        val declaration1: SampleTestDeclaration =
            mockk {
                every { hasPackage(package1) } returns true
                every { hasPackage(package2) } returns false
            }
        val declaration2: SampleTestDeclaration =
            mockk {
                every { hasPackage(package1) } returns false
                every { hasPackage(package2) } returns true
            }
        val declaration3: SampleTestDeclaration =
            mockk {
                every { hasPackage(package1) } returns false
                every { hasPackage(package2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withPackage(package1, package2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutPackage() returns declaration without given package name`() {
        // given
        val declaration1: SampleTestDeclaration =
            mockk {
                every { packagee } returns mockk()
            }
        val declaration2: SampleTestDeclaration =
            mockk {
                every { packagee } returns null
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPackage()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutPackage(String) returns declaration without any of given package names`() {
        // given
        val package1 = "SamplePackage1"
        val package2 = "SamplePackage2"
        val declaration1: SampleTestDeclaration =
            mockk {
                every { hasPackage(package1) } returns true
                every { hasPackage(package2) } returns false
            }
        val declaration2: SampleTestDeclaration =
            mockk {
                every { hasPackage(package1) } returns false
                every { hasPackage(package2) } returns true
            }
        val declaration3: SampleTestDeclaration =
            mockk {
                every { hasPackage(package1) } returns false
                every { hasPackage(package2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutPackage(package1, package2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
