package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoPackage
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageSequenceExtTest {
    @Test
    fun `withQualifiedName() returns package1 with given name`() {
        // given
        val qualifiedName1 = "com.samplepackage"
        val qualifiedName2 = "com.otherpackage"
        val package1: KoPackage = mockk {
            every { qualifiedName } returns qualifiedName1
        }
        val package2: KoPackage = mockk {
            every { qualifiedName } returns qualifiedName2
        }
        val packages = sequenceOf(package1, package2)

        // when
        val sut = packages.withQualifiedName(qualifiedName1)

        // then
        sut.toList() shouldBeEqualTo listOf(package1)
    }

    @Test
    fun `withoutQualifiedName() returns package without given name`() {
        // given
        val qualifiedName1 = "com.samplepackage"
        val qualifiedName2 = "com.otherpackage"
        val package1: KoPackage = mockk {
            every { qualifiedName } returns qualifiedName1
        }
        val package2: KoPackage = mockk {
            every { qualifiedName } returns qualifiedName2
        }
        val packages = sequenceOf(package1, package2)

        // when
        val sut = packages.withoutQualifiedName("com.samplepackage")

        // then
        sut.toList() shouldBeEqualTo listOf(package2)
    }
}
