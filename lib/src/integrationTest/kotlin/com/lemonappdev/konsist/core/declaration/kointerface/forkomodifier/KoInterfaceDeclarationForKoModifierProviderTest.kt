package com.lemonappdev.konsist.core.declaration.kointerface.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.ABSTRACT
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoInterfaceDeclarationForKoModifierProviderTest {
    @Test
    fun `interface-has-no-modifier`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-modifier")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo emptyList()
            numModifiers shouldBeEqualTo 0
            hasModifiers() shouldBeEqualTo false
            hasModifier(OPEN) shouldBeEqualTo false
            hasModifier(OPEN, DATA) shouldBeEqualTo false
            hasAllModifiers(OPEN) shouldBeEqualTo false
            hasAllModifiers(OPEN, DATA) shouldBeEqualTo false
            hasModifiers(OPEN) shouldBeEqualTo false
            hasModifiers(OPEN, DATA) shouldBeEqualTo false
            hasActualModifier shouldBeEqualTo false
            hasExpectModifier shouldBeEqualTo false
            hasFunModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-public-and-abstract-modifiers`() {
        // given
        val sut =
            getSnippetFile("interface-has-public-and-abstract-modifiers")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            numModifiers shouldBeEqualTo 2
            hasModifiers() shouldBeEqualTo true
            hasModifier(PUBLIC) shouldBeEqualTo true
            hasModifier(PROTECTED) shouldBeEqualTo false
            hasModifier(PUBLIC, DATA) shouldBeEqualTo true
            hasAllModifiers(PUBLIC) shouldBeEqualTo true
            hasAllModifiers(PROTECTED) shouldBeEqualTo false
            hasAllModifiers(PUBLIC, DATA) shouldBeEqualTo false
            hasAllModifiers(PUBLIC, ABSTRACT) shouldBeEqualTo true
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(ABSTRACT) shouldBeEqualTo true
            hasModifiers(PROTECTED) shouldBeEqualTo false
            hasModifiers(PUBLIC, ABSTRACT) shouldBeEqualTo true
            hasModifiers(ABSTRACT, PUBLIC) shouldBeEqualTo true
            hasModifiers(PROTECTED, ABSTRACT) shouldBeEqualTo false
            hasModifiers(PROTECTED, ABSTRACT, PUBLIC) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `interface-modifiers`(
        fileName: String,
        modifiers: List<KoModifier>,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .interfaces()
                .first()

        // then
        sut.modifiers shouldBeEqualTo modifiers
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/forkomodifier/snippet/forkomodifierprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("interface-has-modifiers-and-annotation-with-parameter", listOf(PUBLIC, ABSTRACT)),
                arguments("interface-has-modifiers-and-annotation-without-parameter", listOf(PUBLIC, ABSTRACT)),
                arguments("interface-has-modifiers-annotation-and-comment", listOf(PUBLIC, ABSTRACT)),
                arguments("interface-has-modifiers-and-annotations", listOf(PUBLIC, ABSTRACT)),
                arguments("interface-has-modifiers-and-annotation-with-angle-brackets", listOf(PUBLIC, ABSTRACT)),
                arguments("interface-has-modifiers-and-kdoc", listOf(PUBLIC, ABSTRACT)),
                arguments("interface-has-modifiers-kdoc-and-annotation-before-them", listOf(PUBLIC, ABSTRACT)),
                arguments("interface-has-modifiers-multiline-comment-and-annotation-before-them", listOf(PUBLIC, ABSTRACT)),
                arguments("interface-has-modifiers-and-comment-before-them", listOf(PUBLIC, ABSTRACT)),
                arguments("interface-has-modifiers-and-comment-after-them", listOf(PUBLIC, ABSTRACT)),
            )
    }
}
