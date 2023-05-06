package com.lemonappdev.konsist.core.util

import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class LocationHelperTest {
    @Test
    fun `has given fragment with one word`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = LocationHelper

        // then
        assertSoftly(sut) {
            resideInLocation("com", desiredPackage) shouldBeEqualTo false
            resideInLocation("..com..", desiredPackage) shouldBeEqualTo true
            resideInLocation("..com", desiredPackage) shouldBeEqualTo false
            resideInLocation("com..", desiredPackage) shouldBeEqualTo true
            resideInLocation("domain..", desiredPackage) shouldBeEqualTo false
            resideInLocation("..domain", desiredPackage) shouldBeEqualTo false
            resideInLocation("..domain..", desiredPackage) shouldBeEqualTo true
            resideInLocation("usecase..", desiredPackage) shouldBeEqualTo false
            resideInLocation("..usecase", desiredPackage) shouldBeEqualTo true
            resideInLocation("..usecase..", desiredPackage) shouldBeEqualTo true
            resideInLocation("..update", desiredPackage) shouldBeEqualTo false
            resideInLocation("update..", desiredPackage) shouldBeEqualTo false
            resideInLocation("..update..", desiredPackage) shouldBeEqualTo true
            resideInLocation("..nonexisting..", desiredPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment without two dots`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = LocationHelper

        // then
        assertSoftly(sut) {
            resideInLocation("com.domain", desiredPackage) shouldBeEqualTo false
            resideInLocation("com.domain.update", desiredPackage) shouldBeEqualTo false
            resideInLocation("com.domain.update.usecase", desiredPackage) shouldBeEqualTo true
            resideInLocation("com.domain.usecase", desiredPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with first and last word`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = LocationHelper

        // then
        assertSoftly(sut) {
            resideInLocation("com..usecase", desiredPackage) shouldBeEqualTo true
            resideInLocation("..com..usecase", desiredPackage) shouldBeEqualTo true
            resideInLocation("com..usecase..", desiredPackage) shouldBeEqualTo true
            resideInLocation("..com..usecase..", desiredPackage) shouldBeEqualTo true
        }
    }

    @Test
    fun `has given fragment with dots at end`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = LocationHelper

        // then
        assertSoftly(sut) {
            resideInLocation("com.domain..", desiredPackage) shouldBeEqualTo true
            resideInLocation("com.domain.update..", desiredPackage) shouldBeEqualTo true
            resideInLocation("com.domain.update.usecase..", desiredPackage) shouldBeEqualTo true
            resideInLocation("com.domain.usecase..", desiredPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with dots in few places`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = LocationHelper

        // then
        assertSoftly(sut) {
            resideInLocation("domain..usecase..", desiredPackage) shouldBeEqualTo false
            resideInLocation("com.domain..usecase..", desiredPackage) shouldBeEqualTo true
            resideInLocation("..domain..usecase", desiredPackage) shouldBeEqualTo true
            resideInLocation("..domain..usecase..", desiredPackage) shouldBeEqualTo true
            resideInLocation("..domain..update..", desiredPackage) shouldBeEqualTo true
            resideInLocation("com..domain..update..usecase", desiredPackage) shouldBeEqualTo true
            resideInLocation("domain..update..usecase", desiredPackage) shouldBeEqualTo false
            resideInLocation("..domain.update..", desiredPackage) shouldBeEqualTo true
        }
    }

    @Test
    fun `has given fragment with repeating word`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = LocationHelper

        // then
        assertSoftly(sut) {
            resideInLocation("..domain..domain..update.usecase", desiredPackage) shouldBeEqualTo false
            resideInLocation("com..domain..update..com..usecase", desiredPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with the wrong order`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = LocationHelper

        // then
        assertSoftly(sut) {
            resideInLocation("..update..domain..", desiredPackage) shouldBeEqualTo false
            resideInLocation("..usecase..domain..", desiredPackage) shouldBeEqualTo false
        }
    }
}
