package com.lemonappdev.konsist.core.declaration.kotypealias.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeAliasDeclarationForKoModifierProviderTest {
    @Test
    fun `typealias-without-modifiers`() {
        // given
        val sut =
            getSnippetFile("typealias-without-modifiers")
                .typeAliases
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
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `typealias-modifiers`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .typeAliases
                .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo listOf(PRIVATE)
            numModifiers shouldBeEqualTo 1
            hasModifier(PRIVATE) shouldBeEqualTo true
            hasModifier(PROTECTED) shouldBeEqualTo false
            hasModifier(PRIVATE, DATA) shouldBeEqualTo true
            hasAllModifiers(PRIVATE) shouldBeEqualTo true
            hasAllModifiers(PROTECTED) shouldBeEqualTo false
            hasAllModifiers(PRIVATE, DATA) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypealias/forkomodifier/snippet/forkomodifierprovider/",
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("typealias-has-modifiers-and-annotation-with-parameter"),
                arguments("typealias-has-modifiers-and-annotation-without-parameter"),
                arguments("typealias-has-modifiers-annotation-and-comment"),
                arguments("typealias-has-modifiers-and-annotations"),
                arguments("typealias-has-modifiers-and-annotation-with-angle-brackets"),
                arguments("typealias-has-modifiers-and-kdoc"),
                arguments("typealias-has-modifiers-kdoc-and-annotation-before-them"),
                arguments("typealias-has-modifiers-multiline-comment-and-annotation-before-them"),
                arguments("typealias-has-modifiers-and-comment-before-them"),
                arguments("typealias-has-modifiers-and-comment-after-them"),
            )
    }
}
