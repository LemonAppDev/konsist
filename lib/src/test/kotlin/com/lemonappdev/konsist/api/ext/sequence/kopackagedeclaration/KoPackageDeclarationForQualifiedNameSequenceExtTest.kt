package com.lemonappdev.konsist.api.ext.sequence.kopackagedeclaration

import com.lemonappdev.konsist.api.ext.sequence.withQualifiedName
import com.lemonappdev.konsist.api.ext.sequence.withoutQualifiedName
import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageDeclarationForQualifiedNameSequenceExtTest {
    @Test
    fun `withQualifiedName() returns package with given name`() {
        // given
        val qualifiedName1 = "com.samplepackage1"
        val qualifiedName2 = "com.samplepackage2"
        val package1: KoPackageDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName1
        }
        val package2: KoPackageDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName2
        }
        val packages = sequenceOf(package1, package2)

        // when
        val sut = packages.withQualifiedName(qualifiedName1)

        // then
        sut.toList() shouldBeEqualTo listOf(package1)
    }

    @Test
    fun `withQualifiedName() returns packages with one of given names`() {
        // given
        val qualifiedName1 = "com.samplepackage1"
        val qualifiedName2 = "com.samplepackage2"
        val qualifiedName3 = "com.samplepackage3"
        val package1: KoPackageDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName1
        }
        val package2: KoPackageDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName2
        }
        val package3: KoPackageDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName3
        }
        val packages = sequenceOf(package1, package2, package3)

        // when
        val sut = packages.withQualifiedName(qualifiedName1, qualifiedName2)

        // then
        sut.toList() shouldBeEqualTo listOf(package1, package2)
    }

    @Test
    fun `withoutQualifiedName() returns package without given name`() {
        // given
        val qualifiedName1 = "com.samplepackage1"
        val qualifiedName2 = "com.samplepackage2"
        val package1: KoPackageDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName1
        }
        val package2: KoPackageDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName2
        }
        val packages = sequenceOf(package1, package2)

        // when
        val sut = packages.withoutQualifiedName(qualifiedName1)

        // then
        sut.toList() shouldBeEqualTo listOf(package2)
    }

    @Test
    fun `withoutQualifiedName() returns package without any of given names`() {
        // given
        val qualifiedName1 = "com.samplepackage1"
        val qualifiedName2 = "com.samplepackage2"
        val qualifiedName3 = "com.samplepackage3"
        val package1: KoPackageDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName1
        }
        val package2: KoPackageDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName2
        }
        val package3: KoPackageDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName3
        }
        val packages = sequenceOf(package1, package2, package3)

        // when
        val sut = packages.withoutQualifiedName(qualifiedName1, qualifiedName2)

        // then
        sut.toList() shouldBeEqualTo listOf(package3)
    }
}
