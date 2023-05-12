package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
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



    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kodeclaration/snippet/forsecondaryconstructor/", fileName)
}
