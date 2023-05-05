package com.lemonappdev.konsist.core.util

import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class PackageHelperTest {
    @Test
    fun `has given fragment with one word`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = PackageHelper

        // then
        assertSoftly(sut) {
            resideInPackage("com", desiredPackage) shouldBeEqualTo false
            resideInPackage("..com..", desiredPackage) shouldBeEqualTo true
            resideInPackage("..com", desiredPackage) shouldBeEqualTo false
            resideInPackage("com..", desiredPackage) shouldBeEqualTo true
            resideInPackage("domain..", desiredPackage) shouldBeEqualTo false
            resideInPackage("..domain", desiredPackage) shouldBeEqualTo false
            resideInPackage("..domain..", desiredPackage) shouldBeEqualTo true
            resideInPackage("usecase..", desiredPackage) shouldBeEqualTo false
            resideInPackage("..usecase", desiredPackage) shouldBeEqualTo true
            resideInPackage("..usecase..", desiredPackage) shouldBeEqualTo true
            resideInPackage("..update", desiredPackage) shouldBeEqualTo false
            resideInPackage("update..", desiredPackage) shouldBeEqualTo false
            resideInPackage("..update..", desiredPackage) shouldBeEqualTo true
            resideInPackage("..nonexisting..", desiredPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with one word with given separator`() {
        // given
        val desiredPackage = "com/domain/update/usecase"
        val sut = PackageHelper

        // then
        assertSoftly(sut) {
            resideInPackage("com", desiredPackage, '/') shouldBeEqualTo false
            resideInPackage("..com..", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("..com", desiredPackage, '/') shouldBeEqualTo false
            resideInPackage("com..", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("domain..", desiredPackage, '/') shouldBeEqualTo false
            resideInPackage("..domain", desiredPackage, '/') shouldBeEqualTo false
            resideInPackage("..domain..", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("usecase..", desiredPackage, '/') shouldBeEqualTo false
            resideInPackage("..usecase", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("..usecase..", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("..update", desiredPackage, '/') shouldBeEqualTo false
            resideInPackage("update..", desiredPackage, '/') shouldBeEqualTo false
            resideInPackage("..update..", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("..nonexisting..", desiredPackage, '/') shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment without two dots`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = PackageHelper

        // then
        assertSoftly(sut) {
            resideInPackage("com.domain", desiredPackage) shouldBeEqualTo false
            resideInPackage("com.domain.update", desiredPackage) shouldBeEqualTo false
            resideInPackage("com.domain.update.usecase", desiredPackage) shouldBeEqualTo true
            resideInPackage("com.domain.usecase", desiredPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment without two dots with given separator`() {
        // given
        val desiredPackage = "com/domain/update/usecase"
        val sut = PackageHelper

        // then
        assertSoftly(sut) {
            resideInPackage("com/domain", desiredPackage, '/') shouldBeEqualTo false
            resideInPackage("com/domain/update", desiredPackage, '/') shouldBeEqualTo false
            resideInPackage("com/domain/update/usecase", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("com/domain/usecase", desiredPackage, '/') shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with first and last word`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = PackageHelper

        // then
        assertSoftly(sut) {
            resideInPackage("com..usecase", desiredPackage) shouldBeEqualTo true
            resideInPackage("..com..usecase", desiredPackage) shouldBeEqualTo true
            resideInPackage("com..usecase..", desiredPackage) shouldBeEqualTo true
            resideInPackage("..com..usecase..", desiredPackage) shouldBeEqualTo true
        }
    }

    @Test
    fun `has given fragment with first and last word and given separator`() {
        // given
        val desiredPackage = "com/domain/update/usecase"
        val sut = PackageHelper

        // then
        assertSoftly(sut) {
            resideInPackage("com..usecase", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("..com..usecase", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("com..usecase..", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("..com..usecase..", desiredPackage, '/') shouldBeEqualTo true
        }
    }

    @Test
    fun `has given fragment with dots at end`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = PackageHelper

        // then
        assertSoftly(sut) {
            resideInPackage("com.domain..", desiredPackage) shouldBeEqualTo true
            resideInPackage("com.domain.update..", desiredPackage) shouldBeEqualTo true
            resideInPackage("com.domain.update.usecase..", desiredPackage) shouldBeEqualTo true
            resideInPackage("com.domain.usecase..", desiredPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with dots at end and given separator`() {
        // given
        val desiredPackage = "com/domain/update/usecase"
        val sut = PackageHelper

        // then
        assertSoftly(sut) {
            resideInPackage("com/domain..", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("com/domain/update..", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("com/domain/update/usecase..", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("com/domain/usecase..", desiredPackage, '/') shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with dots in few places`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = PackageHelper

        // then
        assertSoftly(sut) {
            resideInPackage("domain..usecase..", desiredPackage) shouldBeEqualTo false
            resideInPackage("com.domain..usecase..", desiredPackage) shouldBeEqualTo true
            resideInPackage("..domain..usecase", desiredPackage) shouldBeEqualTo true
            resideInPackage("..domain..usecase..", desiredPackage) shouldBeEqualTo true
            resideInPackage("..domain..update..", desiredPackage) shouldBeEqualTo true
            resideInPackage("com..domain..update..usecase", desiredPackage) shouldBeEqualTo true
            resideInPackage("domain..update..usecase", desiredPackage) shouldBeEqualTo false
            resideInPackage("..domain.update..", desiredPackage) shouldBeEqualTo true
        }
    }

    @Test
    fun `has given fragment with dots in few places and given separator`() {
        // given
        val desiredPackage = "com/domain/update/usecase"
        val sut = PackageHelper

        // then
        assertSoftly(sut) {
            resideInPackage("domain..usecase..", desiredPackage, '/') shouldBeEqualTo false
            resideInPackage("com/domain..usecase..", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("..domain..usecase", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("..domain..usecase..", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("..domain..update..", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("com..domain..update..usecase", desiredPackage, '/') shouldBeEqualTo true
            resideInPackage("domain..update..usecase", desiredPackage, '/') shouldBeEqualTo false
            resideInPackage("../domain/update..", desiredPackage, '/') shouldBeEqualTo true
        }
    }

    @Test
    fun `has given fragment with repeating word`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = PackageHelper

        // then
        assertSoftly(sut) {
            resideInPackage("..domain..domain..update.usecase", desiredPackage) shouldBeEqualTo false
            resideInPackage("com..domain..update..com..usecase", desiredPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with repeating word and given separator`() {
        // given
        val desiredPackage = "com/domain/update/usecase"
        val sut = PackageHelper

        // then
        assertSoftly(sut) {
            resideInPackage("..domain..domain..update/usecase", desiredPackage, '/') shouldBeEqualTo false
            resideInPackage("com..domain..update..com..usecase", desiredPackage, '/') shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with the wrong order`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = PackageHelper

        // then
        assertSoftly(sut) {
            resideInPackage("..update..domain..", desiredPackage) shouldBeEqualTo false
            resideInPackage("..usecase..domain..", desiredPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with the wrong order and given separator`() {
        // given
        val desiredPackage = "com/domain/update/usecase"
        val sut = PackageHelper

        // then
        assertSoftly(sut) {
            resideInPackage("..update..domain..", desiredPackage, '/') shouldBeEqualTo false
            resideInPackage("..usecase..domain..", desiredPackage, '/') shouldBeEqualTo false
        }
    }
}
