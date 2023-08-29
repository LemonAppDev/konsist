package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationTest {
    @Test
    fun `class-to-string`() {
        // given
        val sut = getSnippetFile("class-to-string")
            .classes()
            .first()

        // then
        sut.toString() shouldBeEqualTo sut.locationWithText
    }

    @Test
    fun `class-without-constructors-has-valid-constructor-parameter-kdoc`() {
        // given
        val sut = getSnippetFile("class-without-constructors-has-valid-constructor-parameter-kdoc")
            .classes()
            .first()

        // then
        sut.hasValidConstructorParameterKDoc() shouldBeEqualTo true
    }

    @Test
    fun `class-has-valid-constructor-parameter-kdoc`() {
        // given
        val sut = getSnippetFile("class-has-valid-constructor-parameter-kdoc")
            .classes()
            .first()

        // then
        sut.hasValidConstructorParameterKDoc() shouldBeEqualTo true
    }

    @Test
    fun `class-has-no-valid-constructor-parameter-kdoc-when-secondary-constructor-has-no-valid-param-kdoc`() {
        // given
        val sut =
            getSnippetFile(
                "class-has-no-valid-constructor-parameter-kdoc-when-secondary-constructor-has-no-valid-param-kdoc"
            )
                .classes()
                .first()

        // then
        sut.hasValidConstructorParameterKDoc() shouldBeEqualTo false
    }

    @Test
    fun `class-has-no-valid-constructor-parameter-kdoc-when-parameters-have-other-names-than-tags`() {
        // given
        val sut =
            getSnippetFile("class-has-no-valid-constructor-parameter-kdoc-when-parameters-have-other-names-than-tags")
                .classes()
                .first()

        // then
        sut.hasValidConstructorParameterKDoc() shouldBeEqualTo false
    }

    @Test
    fun `class-has-no-valid-constructor-parameter-kdoc-when-the-number-of-parameters-and-tags-is-different`() {
        // given
        val sut =
            getSnippetFile(
                "class-has-no-valid-constructor-parameter-kdoc-when-the-number-of-parameters-and-tags-is-different"
            )
                .classes()
                .first()

        // then
        sut.hasValidConstructorParameterKDoc() shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koclass/snippet/forgeneral/", fileName)
}
