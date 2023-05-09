package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForObjectTest {
    @Test
    fun `object-has-modifiers`() {
        // given
        val sut = getSnippetFile("object-has-modifiers")
            .objects()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, DATA)
    }

    @Test
    fun `object-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("object-has-modifiers-and-annotation-with-parameter")
            .objects()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, DATA)
    }

    @Test
    fun `object-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("object-has-modifiers-and-annotation-without-parameter")
            .objects()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, DATA)
    }

    @Test
    fun `object-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("object-has-modifiers-and-annotations")
            .objects()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, DATA)
    }

    @Test
    fun `object-is-in-package`() {
        // given
        val sut = getSnippetFile("object-is-in-package")
            .objects()
            .first()

        // then
        sut.packagee shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `object-is-not-in-package`() {
        // given
        val sut = getSnippetFile("object-is-not-in-package")
            .objects()
            .first()

        // then
        sut.packagee shouldBeEqualTo ""
    }

    @Test
    fun `object-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("object-has-protected-modifier")
            .objects()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `object-has-public-modifier`() {
        // given
        val sut = getSnippetFile("object-has-public-modifier")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-two-modifiers`() {
        // given
        val sut = getSnippetFile("object-has-two-modifiers")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PRIVATE) shouldBeEqualTo true
            hasModifiers(DATA) shouldBeEqualTo true
            hasModifiers(PROTECTED) shouldBeEqualTo false
            hasModifiers(PRIVATE, DATA) shouldBeEqualTo true
            hasModifiers(DATA, PRIVATE) shouldBeEqualTo true
            hasModifiers(PROTECTED, DATA) shouldBeEqualTo false
            hasModifiers(PROTECTED, DATA, PRIVATE) shouldBeEqualTo false
            hasModifiers() shouldBeEqualTo true
        }
    }

    @Test
    fun `object-has-no-modifier`() {
        // given
        val sut = getSnippetFile("object-has-no-modifiers")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-kdoc`() {
        // given
        val sut = getSnippetFile("object-has-kdoc")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            koDoc shouldNotBeEqualTo null
            hasKoDoc() shouldBeEqualTo true
        }
    }

    @Test
    fun `object-has-no-kdoc`() {
        // given
        val sut = getSnippetFile("object-has-no-kdoc")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            koDoc shouldBeEqualTo null
            hasKoDoc() shouldBeEqualTo false
        }
    }

    private fun
    getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kodeclaration/snippet/forobject/", fileName)
}
