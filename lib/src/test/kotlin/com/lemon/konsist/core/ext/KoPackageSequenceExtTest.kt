package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoPackage
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageSequenceExtTest {
    @Test
    fun `withQualifiedName() returns package1 with given name`() {
        // given
        val package1: KoPackage = mockk()
        every { package1.qualifiedName } returns "com.samplepackage"
        val package2: KoPackage = mockk()
        every { package2.qualifiedName } returns "com.otherpackage"
        val packages = sequenceOf(package1, package2)

        // when
        val sut = packages.withQualifiedName("com.samplepackage")

        // then
        sut.toList() shouldBeEqualTo listOf(package1)
    }

    @Test
    fun `withQualifiedName() returns empty list when none package has given name`() {
        // given
        val package1: KoPackage = mockk()
        every { package1.qualifiedName } returns "com.samplepackage1"
        val package2: KoPackage = mockk()
        every { package2.qualifiedName } returns "com.samplepackage2"
        val packages = sequenceOf(package1, package2)

        // when
        val sut = packages.withQualifiedName("com.nonexistingpackage")

        // then
        sut.toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutQualifiedName() returns package without given name`() {
        // given
        val package1: KoPackage = mockk()
        every { package1.qualifiedName } returns "com.samplepackage"
        val package2: KoPackage = mockk()
        every { package2.qualifiedName } returns "com.otherpackage"
        val packages = sequenceOf(package1, package2)

        // when
        val sut = packages.withoutQualifiedName("com.samplepackage")

        // then
        sut.toList() shouldBeEqualTo listOf(package2)
    }

    @Test
    fun `withoutQualifiedName() returns empty list when all packages have given name`() {
        // given
        val package1: KoPackage = mockk()
        every { package1.qualifiedName } returns "com.samplepackage"
        val package2: KoPackage = mockk()
        every { package2.qualifiedName } returns "com.samplepackage"
        val packages = sequenceOf(package1, package2)

        // when
        val sut = packages.withoutQualifiedName("com.samplepackage")

        // then
        sut.toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutQualifiedName() returns list of packages when none package has given name`() {
        // given
        val package1: KoPackage = mockk()
        every { package1.qualifiedName } returns "com.samplepackage"
        val package2: KoPackage = mockk()
        every { package2.qualifiedName } returns "com.samplepackage"
        val packages = sequenceOf(package1, package2)

        // when
        val sut = packages.withoutQualifiedName("com.nonexistingpackage")

        // then
        sut.toList() shouldBeEqualTo listOf(package1, package2)
    }
}
