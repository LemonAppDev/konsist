package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForTypeAliasTest {



    @Test
    fun `typealias-has-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-modifier")
            .typeAliases()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `typealias-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("typealias-has-modifiers-and-annotation-with-parameter")
            .typeAliases()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `typealias-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("typealias-has-modifiers-and-annotation-without-parameter")
            .typeAliases()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `typealias-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("typealias-has-modifiers-and-annotations")
            .typeAliases()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `typealias-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-protected-modifier")
            .typeAliases()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `typealias-has-public-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-public-modifier")
            .typeAliases()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("typealias-has-no-modifiers")
            .typeAliases()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-kdoc`() {
        // given
        val sut = getSnippetFile("typealias-has-kdoc")
            .typeAliases()
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc() shouldBeEqualTo true
        }
    }

    @Test
    fun `typealias-has-no-kdoc`() {
        // given
        val sut = getSnippetFile("typealias-has-no-kdoc")
            .typeAliases()
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kodeclaration/snippet/fortypealias/", fileName)
}
