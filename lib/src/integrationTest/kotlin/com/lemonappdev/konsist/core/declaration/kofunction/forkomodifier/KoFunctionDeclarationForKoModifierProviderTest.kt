package com.lemonappdev.konsist.core.declaration.kofunction.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import com.lemonappdev.konsist.api.KoModifier.SUSPEND
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoFunctionDeclarationForKoModifierProviderTest {
    @Test
    fun `function-has-no-modifiers`() {
        // given
        val sut =
            getSnippetFile("function-has-no-modifiers")
                .functions()
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

    @Test
    fun `function-has-protected-and-suspend-modifiers`() {
        // given
        val sut =
            getSnippetFile("function-has-protected-and-suspend-modifiers")
                .functions(includeNested = true)
                .first()

        // then
        assertSoftly(sut) {
            numModifiers shouldBeEqualTo 2
            hasModifiers() shouldBeEqualTo true
            hasModifier(emptyList()) shouldBeEqualTo true
            hasModifier(emptySet()) shouldBeEqualTo true
            hasAllModifiers(emptyList()) shouldBeEqualTo true
            hasAllModifiers(emptySet()) shouldBeEqualTo true
            hasModifier(PROTECTED) shouldBeEqualTo true
            hasModifier(PUBLIC) shouldBeEqualTo false
            hasModifier(PROTECTED, SUSPEND) shouldBeEqualTo true
            hasModifier(listOf(PROTECTED)) shouldBeEqualTo true
            hasModifier(listOf(PUBLIC)) shouldBeEqualTo false
            hasModifier(listOf(PROTECTED, SUSPEND)) shouldBeEqualTo true
            hasModifier(setOf(PROTECTED)) shouldBeEqualTo true
            hasModifier(setOf(PUBLIC)) shouldBeEqualTo false
            hasModifier(setOf(PROTECTED, SUSPEND)) shouldBeEqualTo true
            hasAllModifiers(PROTECTED) shouldBeEqualTo true
            hasAllModifiers(PUBLIC) shouldBeEqualTo false
            hasAllModifiers(PROTECTED, OPEN) shouldBeEqualTo false
            hasAllModifiers(PROTECTED, SUSPEND) shouldBeEqualTo true
            hasAllModifiers(listOf(PROTECTED)) shouldBeEqualTo true
            hasAllModifiers(listOf(PUBLIC)) shouldBeEqualTo false
            hasAllModifiers(listOf(PROTECTED, OPEN)) shouldBeEqualTo false
            hasAllModifiers(listOf(PROTECTED, SUSPEND)) shouldBeEqualTo true
            hasAllModifiers(setOf(PROTECTED)) shouldBeEqualTo true
            hasAllModifiers(setOf(PUBLIC)) shouldBeEqualTo false
            hasAllModifiers(setOf(PROTECTED, OPEN)) shouldBeEqualTo false
            hasAllModifiers(setOf(PROTECTED, SUSPEND)) shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `function-modifiers`(
        fileName: String,
        modifiers: List<KoModifier>,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .functions(includeNested = true)
                .first()

        // then
        sut.modifiers shouldBeEqualTo modifiers
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/forkomodifier/snippet/forkomodifierprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("function-has-modifiers", listOf(PROTECTED, OPEN, SUSPEND, KoModifier.INLINE, KoModifier.OPERATOR)),
                arguments("function-has-modifiers-and-annotation-with-parameter", listOf(PROTECTED, OPEN)),
                arguments("function-has-modifiers-and-annotation-without-parameter", listOf(PROTECTED, OPEN)),
                arguments("function-has-modifiers-annotation-and-comment", listOf(PROTECTED, OPEN)),
                arguments("function-has-modifiers-and-annotations", listOf(PROTECTED, OPEN)),
                arguments("function-has-modifiers-and-annotation-with-angle-brackets", listOf(PROTECTED, OPEN)),
                arguments("function-has-modifiers-and-kdoc", listOf(PROTECTED, OPEN)),
                arguments("function-has-modifiers-kdoc-and-annotation-before-them", listOf(PROTECTED, OPEN)),
                arguments("function-has-modifiers-multiline-comment-and-annotation-before-them", listOf(PROTECTED, OPEN)),
                arguments("function-has-modifiers-and-comment-before-them", listOf(PROTECTED, OPEN)),
                arguments("function-has-modifiers-and-comment-after-them", listOf(PROTECTED, OPEN)),
                arguments("function-has-modifier-and-annotation-with-string-parameter-containing-link", listOf(PRIVATE)),
            )
    }
}
