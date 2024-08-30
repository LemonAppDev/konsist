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
            hasModifier(emptyList()) shouldBeEqualTo false
            hasModifier(emptySet()) shouldBeEqualTo false
            hasAllModifiers(emptyList()) shouldBeEqualTo false
            hasAllModifiers(emptySet()) shouldBeEqualTo false
            hasModifier(OPEN) shouldBeEqualTo false
            hasModifier(OPEN, DATA) shouldBeEqualTo false
            hasModifier(listOf(OPEN)) shouldBeEqualTo false
            hasModifier(listOf(OPEN, DATA)) shouldBeEqualTo false
            hasModifier(setOf(OPEN)) shouldBeEqualTo false
            hasModifier(setOf(OPEN, DATA)) shouldBeEqualTo false
            hasAllModifiers(OPEN) shouldBeEqualTo false
            hasAllModifiers(OPEN, DATA) shouldBeEqualTo false
            hasAllModifiers(listOf(OPEN)) shouldBeEqualTo false
            hasAllModifiers(listOf(OPEN, DATA)) shouldBeEqualTo false
            hasAllModifiers(setOf(OPEN)) shouldBeEqualTo false
            hasAllModifiers(setOf(OPEN, DATA)) shouldBeEqualTo false
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
            hasModifiers() shouldBeEqualTo true
            hasModifier(emptyList()) shouldBeEqualTo true
            hasModifier(emptySet()) shouldBeEqualTo true
            hasAllModifiers(emptyList()) shouldBeEqualTo true
            hasAllModifiers(emptySet()) shouldBeEqualTo true
            hasModifier(PRIVATE) shouldBeEqualTo true
            hasModifier(PROTECTED) shouldBeEqualTo false
            hasModifier(PRIVATE, DATA) shouldBeEqualTo true
            hasModifier(listOf(PRIVATE)) shouldBeEqualTo true
            hasModifier(listOf(PROTECTED)) shouldBeEqualTo false
            hasModifier(listOf(PRIVATE, DATA)) shouldBeEqualTo true
            hasModifier(setOf(PRIVATE)) shouldBeEqualTo true
            hasModifier(setOf(PROTECTED)) shouldBeEqualTo false
            hasModifier(setOf(PRIVATE, DATA)) shouldBeEqualTo true
            hasAllModifiers(PRIVATE) shouldBeEqualTo true
            hasAllModifiers(PROTECTED) shouldBeEqualTo false
            hasAllModifiers(PRIVATE, DATA) shouldBeEqualTo false
            hasAllModifiers(listOf(PRIVATE)) shouldBeEqualTo true
            hasAllModifiers(listOf(PROTECTED)) shouldBeEqualTo false
            hasAllModifiers(listOf(PRIVATE, DATA)) shouldBeEqualTo false
            hasAllModifiers(setOf(PRIVATE)) shouldBeEqualTo true
            hasAllModifiers(setOf(PROTECTED)) shouldBeEqualTo false
            hasAllModifiers(setOf(PRIVATE, DATA)) shouldBeEqualTo false
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
                arguments("typealias-has-modifier-and-annotation-with-string-parameter-containing-link"),
            )
    }
}
