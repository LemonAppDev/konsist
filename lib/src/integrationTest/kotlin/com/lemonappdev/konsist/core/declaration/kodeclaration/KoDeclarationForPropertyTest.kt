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

class KoDeclarationForPropertyTest {


    @Test
    fun `property-has-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("property-has-fully-qualified-name")
            .properties()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.sampleProperty"
    }

    @Test
    fun `property-is-in-package`() {
        // given
        val sut = getSnippetFile("property-is-in-package")
            .properties()
            .first()

        // then
        sut.packagee shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `property-is-not-in-package`() {
        // given
        val sut = getSnippetFile("property-is-not-in-package")
            .properties()
            .first()

        // then
        sut.packagee shouldBeEqualTo ""
    }

    @Test
    fun `property-has-modifiers`() {
        // given
        val sut = getSnippetFile("property-has-modifiers")
            .properties(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN)
    }

    @Test
    fun `property-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("property-has-modifiers-and-annotation-with-parameter")
            .properties(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN)
    }

    @Test
    fun `property-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("property-has-modifiers-and-annotation-without-parameter")
            .properties(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN)
    }

    @Test
    fun `property-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("property-has-modifiers-and-annotations")
            .properties(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN)
    }

    @Test
    fun `property-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("property-has-protected-modifier")
            .properties()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `property-has-public-modifier`() {
        // given
        val sut = getSnippetFile("property-has-public-modifier")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-two-modifiers`() {
        // given
        val sut = getSnippetFile("property-has-two-modifiers")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PROTECTED) shouldBeEqualTo true
            hasModifiers(OPEN) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
            hasModifiers(PROTECTED, OPEN) shouldBeEqualTo true
            hasModifiers(OPEN, PROTECTED) shouldBeEqualTo true
            hasModifiers(PRIVATE, OPEN) shouldBeEqualTo false
            hasModifiers(PROTECTED, OPEN, PRIVATE) shouldBeEqualTo false
            hasModifiers() shouldBeEqualTo true
        }
    }

    @Test
    fun `property-has-no-modifier`() {
        // given
        val sut = getSnippetFile("property-has-no-modifier")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-kdoc`() {
        // given
        val sut = getSnippetFile("property-has-kdoc")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc() shouldBeEqualTo true
        }
    }

    @Test
    fun `property-has-no-kdoc`() {
        // given
        val sut = getSnippetFile("property-has-no-kdoc")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forproperty/", fileName)
}
