package com.lemonappdev.konsist.core.util

import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class LocationUtilTest {
    @Test
    fun `reside in any package`() {
        // given
        val currentPackage1 = "com.domain.update.usecase"
        val currentPackage2 = "com.domain"
        val currentPackage3 = "org.example.project"
        val sut = LocationUtil

        // then
        assertSoftly(sut) {
            resideInLocation("..", currentPackage1) shouldBeEqualTo true
            resideInLocation("..", currentPackage2) shouldBeEqualTo true
            resideInLocation("..", currentPackage3) shouldBeEqualTo true
        }
    }

    @Test
    fun `throws exception when desired location is empty`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        val actual = { sut.resideInLocation("", currentPackage) }
        actual shouldThrow IllegalArgumentException::class withMessage "Location name is empty"
    }

    @Test
    fun `throws exception when desired location is single dot`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        val actual = { sut.resideInLocation(".", currentPackage) }
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect location format: ."
    }

    @Test
    fun `has given fragment with one word`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        assertSoftly(sut) {
            resideInLocation("com", currentPackage) shouldBeEqualTo false
            resideInLocation("..com..", currentPackage) shouldBeEqualTo true
            resideInLocation("..com", currentPackage) shouldBeEqualTo false
            resideInLocation("com..", currentPackage) shouldBeEqualTo true
            resideInLocation("domain..", currentPackage) shouldBeEqualTo false
            resideInLocation("..domain", currentPackage) shouldBeEqualTo false
            resideInLocation("..domain..", currentPackage) shouldBeEqualTo true
            resideInLocation("usecase..", currentPackage) shouldBeEqualTo false
            resideInLocation("..usecase", currentPackage) shouldBeEqualTo true
            resideInLocation("..usecase..", currentPackage) shouldBeEqualTo true
            resideInLocation("..update", currentPackage) shouldBeEqualTo false
            resideInLocation("update..", currentPackage) shouldBeEqualTo false
            resideInLocation("..update..", currentPackage) shouldBeEqualTo true
            resideInLocation("..nonexisting..", currentPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment without two dots`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        assertSoftly(sut) {
            resideInLocation("com.domain", currentPackage) shouldBeEqualTo false
            resideInLocation("com.domain.update", currentPackage) shouldBeEqualTo false
            resideInLocation("com.domain.update.usecase", currentPackage) shouldBeEqualTo true
            resideInLocation("com.domain.usecase", currentPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with first and last word`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        assertSoftly(sut) {
            resideInLocation("com..usecase", currentPackage) shouldBeEqualTo true
            resideInLocation("..com..usecase", currentPackage) shouldBeEqualTo true
            resideInLocation("com..usecase..", currentPackage) shouldBeEqualTo true
            resideInLocation("..com..usecase..", currentPackage) shouldBeEqualTo true
        }
    }

    @Test
    fun `has given fragment with dots at end`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        assertSoftly(sut) {
            resideInLocation("com.domain..", currentPackage) shouldBeEqualTo true
            resideInLocation("com.domain.update..", currentPackage) shouldBeEqualTo true
            resideInLocation("com.domain.update.usecase..", currentPackage) shouldBeEqualTo true
            resideInLocation("com.domain.usecase..", currentPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with dots in few places`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        assertSoftly(sut) {
            resideInLocation("domain..usecase..", currentPackage) shouldBeEqualTo false
            resideInLocation("com.domain..usecase..", currentPackage) shouldBeEqualTo true
            resideInLocation("..domain..usecase", currentPackage) shouldBeEqualTo true
            resideInLocation("..domain..usecase..", currentPackage) shouldBeEqualTo true
            resideInLocation("..domain..update..", currentPackage) shouldBeEqualTo true
            resideInLocation("com..domain..update..usecase", currentPackage) shouldBeEqualTo true
            resideInLocation("domain..update..usecase", currentPackage) shouldBeEqualTo false
            resideInLocation("..domain.update..", currentPackage) shouldBeEqualTo true
        }
    }

    @Test
    fun `has given fragment with repeating word`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        assertSoftly(sut) {
            resideInLocation("..domain..domain..update.usecase", currentPackage) shouldBeEqualTo false
            resideInLocation("com..domain..update..com..usecase", currentPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with the wrong order`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        assertSoftly(sut) {
            resideInLocation("..update..domain..", currentPackage) shouldBeEqualTo false
            resideInLocation("..usecase..domain..", currentPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with the package name 'data' prefix 'data' is the same as selector`() {
        // given
        val currentPackage = "com.lemonappdev.konsist.datasomething.SampleType"

        // then
        LocationUtil.resideInLocation("com.lemonappdev.konsist..data..", currentPackage) shouldBeEqualTo false
    }

    @Test
    fun `has given fragment with the package name 'data' suffux is the same as selector`() {
        // given
        val currentPackage = "com.lemonappdev.konsist.somethingdata.SampleType"

        // then
        LocationUtil.resideInLocation("com.lemonappdev.konsist..data..", currentPackage) shouldBeEqualTo false
    }

    @Test
    fun `has given fragment with the package name 'data' content is the same as selector`() {
        // given
        val currentPackage = "com.lemonappdev.konsist.somethingdatasomething.SampleType"

        // then
        LocationUtil.resideInLocation("com.lemonappdev.konsist..data..", currentPackage) shouldBeEqualTo false
    }
}
