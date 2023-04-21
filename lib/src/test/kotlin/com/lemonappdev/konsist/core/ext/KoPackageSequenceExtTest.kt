package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoPackage
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageSequenceExtTest {
    @Test
    fun `withQualifiedName() returns packages with one of given names`() {
        // given
        val qualifiedName1 = "com.samplepackage1"
        val qualifiedName2 = "com.samplepackage2"
        val qualifiedName3 = "com.samplepackage3"
        val package1: KoPackage = mockk {
            every { qualifiedName } returns qualifiedName1
        }
        val package2: KoPackage = mockk {
            every { qualifiedName } returns qualifiedName2
        }
        val package3: KoPackage = mockk {
            every { qualifiedName } returns qualifiedName3
        }
        val packages = sequenceOf(package1, package2, package3)

        // when
        val sut = packages.withQualifiedName(qualifiedName1, qualifiedName2)

        // then
        sut.toList() shouldBeEqualTo listOf(package1, package2)
    }

    @Test
    fun `withoutQualifiedName() returns package without given names`() {
        // given
        val qualifiedName1 = "com.samplepackage1"
        val qualifiedName2 = "com.samplepackage2"
        val qualifiedName3 = "com.samplepackage3"
        val package1: KoPackage = mockk {
            every { qualifiedName } returns qualifiedName1
        }
        val package2: KoPackage = mockk {
            every { qualifiedName } returns qualifiedName2
        }
        val package3: KoPackage = mockk {
            every { qualifiedName } returns qualifiedName3
        }
        val packages = sequenceOf(package1, package2, package3)

        // when
        val sut = packages.withoutQualifiedName(qualifiedName1, qualifiedName2)

        // then
        sut.toList() shouldBeEqualTo listOf(package3)
    }
}
