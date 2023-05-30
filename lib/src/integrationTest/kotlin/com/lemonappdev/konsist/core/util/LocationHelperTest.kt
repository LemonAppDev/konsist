package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.core.util.LocationHelper.resideInLocation
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class LocationHelperTest {
    @Test
    fun `reside in any package`() {
        // given
        val desiredPackage1 = "com.domain.update.usecase"
        val desiredPackage2 = "com.domain"
        val desiredPackage3 = "org.example.project"
        val sut = LocationHelper

        // then
        assertSoftly(sut) {
            resideInLocation("..", desiredPackage1) shouldBeEqualTo true
            resideInLocation("..", desiredPackage2) shouldBeEqualTo true
            resideInLocation("..", desiredPackage3) shouldBeEqualTo true
        }
    }

    @Test
    fun `throws exception when desired location is empty`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = LocationHelper

        // then
        val actual = { sut.resideInLocation("", desiredPackage) }
        actual shouldThrow IllegalArgumentException::class withMessage "Location name is empty"
    }

    @Test
    fun `throws exception when desired location is single dot`() {
        // given
        val desiredPackage = "com.domain.update.usecase"
        val sut = LocationHelper

        // then
        val actual = { sut.resideInLocation(".", desiredPackage) }
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect location format: ."
    }

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
