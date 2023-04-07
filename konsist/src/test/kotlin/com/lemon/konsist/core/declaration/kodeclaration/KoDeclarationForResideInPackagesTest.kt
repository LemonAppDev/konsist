package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForResideInPackagesTest {
    @Test
    fun `reside-in-packages-with-one-word`() {
        // given
        val sut = getSut("reside-in-packages-with-one-word")
            .classes()
            .first()

        // then
        sut.apply {
            resideInPackages("com") shouldBeEqualTo false
            resideInPackages("..com..") shouldBeEqualTo true
            resideInPackages("..com") shouldBeEqualTo false
            resideInPackages("com..") shouldBeEqualTo true
            resideInPackages("domain..") shouldBeEqualTo false
            resideInPackages("..domain") shouldBeEqualTo false
            resideInPackages("..domain..") shouldBeEqualTo true
            resideInPackages("usecase..") shouldBeEqualTo false
            resideInPackages("..usecase") shouldBeEqualTo true
            resideInPackages("..usecase..") shouldBeEqualTo true
            resideInPackages("..update") shouldBeEqualTo false
            resideInPackages("update..") shouldBeEqualTo false
            resideInPackages("..update..") shouldBeEqualTo true
            resideInPackages("..nonexisting..") shouldBeEqualTo false
        }
    }

    @Test
    fun `reside-in-packages-without-two-dots`() {
        // given
        val sut = getSut("reside-in-packages-without-two-dots")
            .classes()
            .first()

        // then
        sut.apply {
            resideInPackages("com.domain") shouldBeEqualTo false
            resideInPackages("com.domain.update") shouldBeEqualTo false
            resideInPackages("com.domain.update.usecase") shouldBeEqualTo true
            resideInPackages("com.domain.usecase") shouldBeEqualTo false
        }
    }

    @Test
    fun `reside-in-packages-with-first-and-last-word`() {
        // given
        val sut = getSut("reside-in-packages-with-first-and-last-word")
            .classes()
            .first()

        // then
        sut.apply {
            resideInPackages("com..usecase") shouldBeEqualTo true
            resideInPackages("..com..usecase") shouldBeEqualTo true
            resideInPackages("com..usecase..") shouldBeEqualTo true
            resideInPackages("..com..usecase..") shouldBeEqualTo true
        }
    }

    @Test
    fun `reside-in-packages-with-dots-at-end`() {
        // given
        val sut = getSut("reside-in-packages-with-dots-at-end")
            .classes()
            .first()

        // then
        sut.apply {
            resideInPackages("com.domain..") shouldBeEqualTo true
            resideInPackages("com.domain.update..") shouldBeEqualTo true
            resideInPackages("com.domain.update.usecase..") shouldBeEqualTo true
            resideInPackages("com.domain.usecase..") shouldBeEqualTo false
        }
    }

    @Test
    fun `reside-in-packages-with-dots-in-few-places`() {
        // given
        val sut = getSut("reside-in-packages-with-dots-in-few-places")
            .classes()
            .first()

        // then
        sut.apply {
            resideInPackages("domain..usecase..") shouldBeEqualTo false
            resideInPackages("com.domain..usecase..") shouldBeEqualTo true
            resideInPackages("..domain..usecase") shouldBeEqualTo true
            resideInPackages("..domain..usecase..") shouldBeEqualTo true
            resideInPackages("..domain..update..") shouldBeEqualTo true
            resideInPackages("com..domain..update..usecase") shouldBeEqualTo true
            resideInPackages("domain..update..usecase") shouldBeEqualTo false
        }
    }

    @Test
    fun `reside-in-packages-with-repeating-word`() {
        // given
        val sut = getSut("reside-in-packages-with-repeating-word")
            .classes()
            .first()

        // then
        sut.apply {
            resideInPackages("..domain..domain..update.usecase") shouldBeEqualTo false
            resideInPackages("com..domain..update..com..usecase") shouldBeEqualTo false
        }
    }

    @Test
    fun `reside-in-packages-with-the-wrong-order`() {
        // given
        val sut = getSut("reside-in-packages-with-the-wrong-order")
            .classes()
            .first()

        // then
        sut.apply {
            resideInPackages("..update..domain..") shouldBeEqualTo false
            resideInPackages("..usecase..domain..") shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("kodeclaration/snippet/forresideinpackage/", fileName)
}
