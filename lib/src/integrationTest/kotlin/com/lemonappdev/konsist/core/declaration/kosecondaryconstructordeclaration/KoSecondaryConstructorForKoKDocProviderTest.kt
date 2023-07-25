package com.lemonappdev.konsist.core.declaration.kosecondaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoSecondaryConstructorForKoKDocProviderTest {
    @Test
    fun `secondary-constructor-without-kdoc`() {
        // given
        val sut = getSnippetFile("secondary-constructor-without-kdoc")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc() shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-with-kdoc`() {
        // given
        val sut = getSnippetFile("secondary-constructor-with-kdoc")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc() shouldBeEqualTo true
        }
    }

    @Test
    fun `secondary-constructor-with-one-line-kdoc`() {
        // given
        val sut = getSnippetFile("secondary-constructor-with-one-line-kdoc")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc() shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `secondary-constructor-with-kdoc-with-param-tags`(
        fileName: String,
        verifyParamTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut
            .hasValidKDoc(
                verifyDescription = false,
                verifyParamTag = verifyParamTag,
            )
            .shouldBeEqualTo(value)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kosecondaryconstructordeclaration/snippet/forkokdocprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("secondary-constructor-with-kdoc-with-complete-param-tags", true, true),
            arguments("secondary-constructor-with-kdoc-with-complete-param-tags", false, true),
            arguments("secondary-constructor-with-kdoc-with-not-complete-param-tags", true, false),
            arguments("secondary-constructor-with-kdoc-with-not-complete-param-tags", false, true),
            arguments("secondary-constructor-with-kdoc-with-too-many-param-tags", true, false),
            arguments("secondary-constructor-with-kdoc-with-too-many-param-tags", false, true),
            arguments("secondary-constructor-with-kdoc-without-param-tags", true, false),
            arguments("secondary-constructor-with-kdoc-without-param-tags", false, true),
        )
    }
}
