package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.ABSTRACT
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForInterfaceTest {
    @Test
    fun `interface-has-modifiers`() {
        // given
        val sut = getSnippetFile("interface-has-modifiers")
            .interfaces()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PUBLIC, ABSTRACT)
    }

    @Test
    fun `interface-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("interface-has-modifiers-and-annotation-with-parameter")
            .interfaces()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PUBLIC, ABSTRACT)
    }

    @Test
    fun `interface-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("interface-has-modifiers-and-annotation-without-parameter")
            .interfaces()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PUBLIC, ABSTRACT)
    }

    @Test
    fun `interface-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("interface-has-modifiers-and-annotations")
            .interfaces()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PUBLIC, ABSTRACT)
    }

    @Test
    fun `interface-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-protected-modifier")
            .interfaces()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `interface-has-public-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-public-modifier")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-two-modifiers`() {
        // given
        val sut = getSnippetFile("interface-has-two-modifiers")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(ABSTRACT) shouldBeEqualTo true
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
            hasModifiers(ABSTRACT, PUBLIC) shouldBeEqualTo true
            hasModifiers(PUBLIC, ABSTRACT) shouldBeEqualTo true
            hasModifiers(PRIVATE, ABSTRACT) shouldBeEqualTo false
            hasModifiers(ABSTRACT, PUBLIC, PRIVATE) shouldBeEqualTo false
            hasModifiers() shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("interface-has-no-modifiers")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forinterface/", fileName)
}
