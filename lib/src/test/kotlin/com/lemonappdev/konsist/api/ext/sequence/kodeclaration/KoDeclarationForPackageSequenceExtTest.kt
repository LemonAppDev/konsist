package com.lemonappdev.konsist.api.ext.sequence.kodeclaration

import com.lemonappdev.konsist.api.ext.sequence.withPackage
import com.lemonappdev.konsist.api.ext.sequence.withoutPackage
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForPackageSequenceExtTest {
    @Test
    fun `withPackage() returns declaration reside in any package`() {
        // given
        val declaration1: KoResideInOrOutsidePackageProvider = mockk {
            every { packagee } returns mockk()
        }
        val declaration2: KoResideInOrOutsidePackageProvider = mockk {
            every { packagee } returns null
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withPackage()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withPackage(String) returns declaration with given package`() {
        // given
        val packagee = "com.sample.samplepackage"
        val declaration1: KoResideInOrOutsidePackageProvider = mockk {
            every { resideInPackage(packagee) } returns true
        }
        val declaration2: KoResideInOrOutsidePackageProvider = mockk {
            every { resideInPackage(packagee) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withPackage(packagee)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withPackage(String) returns declarations with at least one of given package`() {
        // given
        val package1 = "com.sample.samplepackage1"
        val package2 = "com.sample.samplepackage2"
        val declaration1: KoResideInOrOutsidePackageProvider = mockk {
            every { resideInPackage(package1) } returns true
            every { resideInPackage(package2) } returns true
        }
        val declaration2: KoResideInOrOutsidePackageProvider = mockk {
            every { resideInPackage(package1) } returns false
            every { resideInPackage(package2) } returns true
        }
        val declaration3: KoResideInOrOutsidePackageProvider = mockk {
            every { resideInPackage(package1) } returns false
            every { resideInPackage(package2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withPackage(package1, package2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutPackage() returns declaration reside in any package`() {
        // given
        val declaration1: KoResideInOrOutsidePackageProvider = mockk {
            every { packagee } returns mockk()
        }
        val declaration2: KoResideInOrOutsidePackageProvider = mockk {
            every { packagee } returns null
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPackage()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutPackage(String) returns declaration without given package`() {
        // given
        val packagee = "com.sample.samplepackage"
        val declaration1: KoResideInOrOutsidePackageProvider = mockk {
            every { resideOutsidePackage(packagee) } returns false
        }
        val declaration2: KoResideInOrOutsidePackageProvider = mockk {
            every { resideOutsidePackage(packagee) } returns true
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPackage(packagee)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutPackage(String) returns declaration without any of given packages`() {
        // given
        val package1 = "com.sample.samplepackage1"
        val package2 = "com.sample.samplepackage2"
        val declaration1: KoResideInOrOutsidePackageProvider = mockk {
            every { resideOutsidePackage(package1) } returns false
            every { resideOutsidePackage(package2) } returns false
        }
        val declaration2: KoResideInOrOutsidePackageProvider = mockk {
            every { resideOutsidePackage(package1) } returns true
            every { resideOutsidePackage(package2) } returns false
        }
        val declaration3: KoResideInOrOutsidePackageProvider = mockk {
            every { resideOutsidePackage(package1) } returns true
            every { resideOutsidePackage(package2) } returns true
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutPackage(package1, package2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }
}
