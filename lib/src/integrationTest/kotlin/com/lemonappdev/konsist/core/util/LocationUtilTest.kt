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
            residesInLocation("..", currentPackage1) shouldBeEqualTo true
            residesInLocation("..", currentPackage2) shouldBeEqualTo true
            residesInLocation("..", currentPackage3) shouldBeEqualTo true
        }
    }

    @Test
    fun `throws exception when desired location is empty`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        val actual = { sut.residesInLocation("", currentPackage) }
        actual shouldThrow IllegalArgumentException::class withMessage "Location name is empty"
    }

    @Test
    fun `throws exception when desired location is single dot`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        val actual = { sut.residesInLocation(".", currentPackage) }
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect location format: ."
    }

    @Test
    fun `has given fragment with one word`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        assertSoftly(sut) {
            residesInLocation("com", currentPackage) shouldBeEqualTo false
            residesInLocation("..com..", currentPackage) shouldBeEqualTo true
            residesInLocation("..com", currentPackage) shouldBeEqualTo false
            residesInLocation("com..", currentPackage) shouldBeEqualTo true
            residesInLocation("domain..", currentPackage) shouldBeEqualTo false
            residesInLocation("..domain", currentPackage) shouldBeEqualTo false
            residesInLocation("..domain..", currentPackage) shouldBeEqualTo true
            residesInLocation("usecase..", currentPackage) shouldBeEqualTo false
            residesInLocation("..usecase", currentPackage) shouldBeEqualTo true
            residesInLocation("..usecase..", currentPackage) shouldBeEqualTo true
            residesInLocation("..update", currentPackage) shouldBeEqualTo false
            residesInLocation("update..", currentPackage) shouldBeEqualTo false
            residesInLocation("..update..", currentPackage) shouldBeEqualTo true
            residesInLocation("..nonexisting..", currentPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment without two dots`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        assertSoftly(sut) {
            residesInLocation("com.domain", currentPackage) shouldBeEqualTo false
            residesInLocation("com.domain.update", currentPackage) shouldBeEqualTo false
            residesInLocation("com.domain.update.usecase", currentPackage) shouldBeEqualTo true
            residesInLocation("com.domain.usecase", currentPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with first and last word`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        assertSoftly(sut) {
            residesInLocation("com..usecase", currentPackage) shouldBeEqualTo true
            residesInLocation("..com..usecase", currentPackage) shouldBeEqualTo true
            residesInLocation("com..usecase..", currentPackage) shouldBeEqualTo true
            residesInLocation("..com..usecase..", currentPackage) shouldBeEqualTo true
        }
    }

    @Test
    fun `has given fragment with dots at end`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        assertSoftly(sut) {
            residesInLocation("com.domain..", currentPackage) shouldBeEqualTo true
            residesInLocation("com.domain.update..", currentPackage) shouldBeEqualTo true
            residesInLocation("com.domain.update.usecase..", currentPackage) shouldBeEqualTo true
            residesInLocation("com.domain.usecase..", currentPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with dots in few places`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        assertSoftly(sut) {
            residesInLocation("domain..usecase..", currentPackage) shouldBeEqualTo false
            residesInLocation("com.domain..usecase..", currentPackage) shouldBeEqualTo true
            residesInLocation("..domain..usecase", currentPackage) shouldBeEqualTo true
            residesInLocation("..domain..usecase..", currentPackage) shouldBeEqualTo true
            residesInLocation("..domain..update..", currentPackage) shouldBeEqualTo true
            residesInLocation("com..domain..update..usecase", currentPackage) shouldBeEqualTo true
            residesInLocation("domain..update..usecase", currentPackage) shouldBeEqualTo false
            residesInLocation("..domain.update..", currentPackage) shouldBeEqualTo true
        }
    }

    @Test
    fun `has given fragment with repeating word`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        assertSoftly(sut) {
            residesInLocation("..domain..domain..update.usecase", currentPackage) shouldBeEqualTo false
            residesInLocation("com..domain..update..com..usecase", currentPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with the wrong order`() {
        // given
        val currentPackage = "com.domain.update.usecase"
        val sut = LocationUtil

        // then
        assertSoftly(sut) {
            residesInLocation("..update..domain..", currentPackage) shouldBeEqualTo false
            residesInLocation("..usecase..domain..", currentPackage) shouldBeEqualTo false
        }
    }

    @Test
    fun `has given fragment with the package name 'data' prefix 'data' is the same as selector`() {
        // given
        val currentPackage = "com.lemonappdev.konsist.datasomething.FixtureType"

        // then
        LocationUtil.residesInLocation("com.lemonappdev.konsist..data..", currentPackage) shouldBeEqualTo false
    }

    @Test
    fun `has given fragment with the package name 'data' suffux is the same as selector`() {
        // given
        val currentPackage = "com.lemonappdev.konsist.somethingdata.FixtureType"

        // then
        LocationUtil.residesInLocation("com.lemonappdev.konsist..data..", currentPackage) shouldBeEqualTo false
    }

    @Test
    fun `has given fragment with the package name 'data' content is the same as selector`() {
        // given
        val currentPackage = "com.lemonappdev.konsist.somethingdatasomething.FixtureType"

        // then
        LocationUtil.residesInLocation("com.lemonappdev.konsist..data..", currentPackage) shouldBeEqualTo false
    }
}
