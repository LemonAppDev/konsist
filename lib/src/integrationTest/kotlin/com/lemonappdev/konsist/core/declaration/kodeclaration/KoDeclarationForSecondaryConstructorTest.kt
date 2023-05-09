package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForSecondaryConstructorTest {
    @Test
    fun `secondary-constructor-has-modifier`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-modifier")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `secondary-constructor-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-modifiers-and-annotation-with-parameter")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `secondary-constructor-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-modifiers-and-annotation-without-parameter")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `secondary-constructor-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-modifiers-and-annotations")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `secondary-constructor`() {
        // given
        val sut = getSnippetFile("secondary-constructor")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.name shouldBeEqualTo "SampleClass"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kodeclaration/snippet/forsecondaryconstructor/", fileName)
}
