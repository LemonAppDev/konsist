package com.lemonappdev.konsist.core.declaration.kopsideclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoPsiDeclarationForKDocTest {
    @Test
    fun `declaration-with-kdoc`() {
        // given
        val sut = getSnippetFile("declaration-with-kdoc")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc() shouldBeEqualTo true
        }
    }

    @Test
    fun `declaration-without-kdoc`() {
        // given
        val sut = getSnippetFile("declaration-without-kdoc")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc() shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `hasValidKDoc-with-requirements`(
        fileName: String,
        verifyDescription: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .functions()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = verifyDescription) shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopsideclaration/snippet/forkdoc/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("declaration-without-kdoc", true, false),
            arguments("declaration-without-kdoc", false, false),
            arguments("declaration-with-empty-kdoc", true, false),
            arguments("declaration-with-empty-kdoc", false, true),
            arguments("declaration-with-kdoc-with-description", true, true),
            arguments("declaration-with-kdoc-with-description", false, true),
            arguments("declaration-with-kdoc-without-description", true, false),
            arguments("declaration-with-kdoc-without-description", false, true),
        )
    }
}
