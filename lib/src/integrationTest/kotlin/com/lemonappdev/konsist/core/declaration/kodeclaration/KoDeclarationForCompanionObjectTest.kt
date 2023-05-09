package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.COMPANION
import com.lemonappdev.konsist.api.KoModifier.FINAL
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForCompanionObjectTest {
    @Test
    fun `companion-object-has-modifiers`() {
        // given
        val sut = getSnippetFile("companion-object-has-modifiers")
            .companionObjects()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, FINAL, COMPANION)
    }

    @Test
    fun `companion-object-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("companion-object-has-modifiers-and-annotation-with-parameter")
            .companionObjects()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, FINAL, COMPANION)
    }

    @Test
    fun `companion-object-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("companion-object-has-modifiers-and-annotation-without-parameter")
            .companionObjects()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, FINAL, COMPANION)
    }

    @Test
    fun `companion-object-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("companion-object-has-modifiers-and-annotations")
            .companionObjects()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, FINAL, COMPANION)
    }

    @Test
    fun `companion-object-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("companion-object-has-protected-modifier")
            .companionObjects()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `companion-object-has-public-modifier`() {
        // given
        val sut = getSnippetFile("companion-object-has-public-modifier")
            .companionObjects()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `companion-object-has-two-modifiers`() {
        // given
        val sut = getSnippetFile("companion-object-has-two-modifiers")
            .companionObjects()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PROTECTED) shouldBeEqualTo true
            hasModifiers(FINAL) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
            hasModifiers(PROTECTED, FINAL) shouldBeEqualTo true
            hasModifiers(FINAL, PROTECTED) shouldBeEqualTo true
            hasModifiers(FINAL, PRIVATE) shouldBeEqualTo false
            hasModifiers(PROTECTED, FINAL, PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `companion-object-has-no-modifier`() {
        // given
        val sut = getSnippetFile("companion-object-has-no-modifiers")
            .companionObjects()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kodeclaration/snippet/forcompanionobject/", fileName)
}
