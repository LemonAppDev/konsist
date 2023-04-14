package com.lemon.konsist.core.util

import com.lemon.konsist.util.PackageHelper
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class PackageHelperTest {
    @Test
    fun `has-given-fragment-with-one-word`() {
        // given
        val name = "com.domain.update.usecase"
        val sut = PackageHelper

        // then
        sut.apply {
            resideInPackage("com", name) shouldBeEqualTo false
            resideInPackage("..com..", name) shouldBeEqualTo true
            resideInPackage("..com", name) shouldBeEqualTo false
            resideInPackage("com..", name) shouldBeEqualTo true
            resideInPackage("domain..", name) shouldBeEqualTo false
            resideInPackage("..domain", name) shouldBeEqualTo false
            resideInPackage("..domain..", name) shouldBeEqualTo true
            resideInPackage("usecase..", name) shouldBeEqualTo false
            resideInPackage("..usecase", name) shouldBeEqualTo true
            resideInPackage("..usecase..", name) shouldBeEqualTo true
            resideInPackage("..update", name) shouldBeEqualTo false
            resideInPackage("update..", name) shouldBeEqualTo false
            resideInPackage("..update..", name) shouldBeEqualTo true
            resideInPackage("..nonexisting..", name) shouldBeEqualTo false
        }
    }

    @Test
    fun `has-given-fragment-without-two-dots`() {
        // given
        val name = "com.domain.update.usecase"
        val sut = PackageHelper

        // then
        sut.apply {
            resideInPackage("com.domain", name) shouldBeEqualTo false
            resideInPackage("com.domain.update", name) shouldBeEqualTo false
            resideInPackage("com.domain.update.usecase", name) shouldBeEqualTo true
            resideInPackage("com.domain.usecase", name) shouldBeEqualTo false
        }
    }

    @Test
    fun `has-given-fragment-with-first-and-last-word`() {
        // given
        val name = "com.domain.update.usecase"
        val sut = PackageHelper

        // then
        sut.apply {
            resideInPackage("com..usecase", name) shouldBeEqualTo true
            resideInPackage("..com..usecase", name) shouldBeEqualTo true
            resideInPackage("com..usecase..", name) shouldBeEqualTo true
            resideInPackage("..com..usecase..", name) shouldBeEqualTo true
        }
    }

    @Test
    fun `has-given-fragment-with-dots-at-end`() {
        // given
        val name = "com.domain.update.usecase"
        val sut = PackageHelper

        // then
        sut.apply {
            resideInPackage("com.domain..", name) shouldBeEqualTo true
            resideInPackage("com.domain.update..", name) shouldBeEqualTo true
            resideInPackage("com.domain.update.usecase..", name) shouldBeEqualTo true
            resideInPackage("com.domain.usecase..", name) shouldBeEqualTo false
        }
    }

    @Test
    fun `has-given-fragment-with-dots-in-few-places`() {
        // given
        val name = "com.domain.update.usecase"
        val sut = PackageHelper

        // then
        sut.apply {
            resideInPackage("domain..usecase..", name) shouldBeEqualTo false
            resideInPackage("com.domain..usecase..", name) shouldBeEqualTo true
            resideInPackage("..domain..usecase", name) shouldBeEqualTo true
            resideInPackage("..domain..usecase..", name) shouldBeEqualTo true
            resideInPackage("..domain..update..", name) shouldBeEqualTo true
            resideInPackage("com..domain..update..usecase", name) shouldBeEqualTo true
            resideInPackage("domain..update..usecase", name) shouldBeEqualTo false
        }
    }

    @Test
    fun `has-given-fragment-with-repeating-word`() {
        // given
        val name = "com.domain.update.usecase"
        val sut = PackageHelper

        // then
        sut.apply {
            resideInPackage("..domain..domain..update.usecase", name) shouldBeEqualTo false
            resideInPackage("com..domain..update..com..usecase", name) shouldBeEqualTo false
        }
    }

    @Test
    fun `has-given-fragment-with-the-wrong-order`() {
        // given
        val name = "com.domain.update.usecase"
        val sut = PackageHelper

        // then
        sut.apply {
            resideInPackage("..update..domain..", name) shouldBeEqualTo false
            resideInPackage("..usecase..domain..", name) shouldBeEqualTo false
        }
    }
}
