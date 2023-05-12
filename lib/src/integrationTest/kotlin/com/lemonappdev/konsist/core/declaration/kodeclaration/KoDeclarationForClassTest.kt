package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForClassTest {

    @Test
    fun `class-is-in-package`() {
        // given
        val sut = getSnippetFile("class-is-in-package")
            .classes()
            .first()

        // then
        sut.packagee shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `class-is-not-in-package`() {
        // given
        val sut = getSnippetFile("class-is-not-in-package")
            .classes()
            .first()

        // then
        sut.packagee shouldBeEqualTo ""
    }

    @Test
    fun `class-has-modifiers`() {
        // given
        val sut = getSnippetFile("class-has-modifiers")
            .classes()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, OPEN)
    }

    @Test
    fun `class-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("class-has-modifiers-and-annotation-with-parameter")
            .classes()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, OPEN)
    }

    @Test
    fun `class-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("class-has-modifiers-and-annotation-without-parameter")
            .classes()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, OPEN)
    }

    @Test
    fun `class-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("class-has-modifiers-and-annotations")
            .classes()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, OPEN)
    }

    @Test
    fun `class-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("class-has-protected-modifier")
            .classes()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `class-has-public-modifier`() {
        // given
        val sut = getSnippetFile("class-has-public-modifier")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-two-modifiers`() {
        // given
        val sut = getSnippetFile("class-has-two-modifiers")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PRIVATE) shouldBeEqualTo true
            hasModifiers(OPEN) shouldBeEqualTo true
            hasModifiers(PROTECTED) shouldBeEqualTo false
            hasModifiers(PRIVATE, OPEN) shouldBeEqualTo true
            hasModifiers(OPEN, PRIVATE) shouldBeEqualTo true
            hasModifiers(PROTECTED, OPEN) shouldBeEqualTo false
            hasModifiers(PROTECTED, OPEN, PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-no-modifier`() {
        // given
        val sut = getSnippetFile("class-has-no-modifier")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-kdoc`() {
        // given
        val sut = getSnippetFile("class-has-kdoc")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc() shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-no-kdoc`() {
        // given
        val sut = getSnippetFile("class-has-no-kdoc")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forclass/", fileName)
}
