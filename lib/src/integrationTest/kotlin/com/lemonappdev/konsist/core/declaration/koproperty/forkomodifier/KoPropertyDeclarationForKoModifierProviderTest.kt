package com.lemonappdev.konsist.core.declaration.koproperty.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier
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

class KoPropertyDeclarationForKoModifierProviderTest {
    @Test
    fun `property-has-no-modifiers`() {
        // given
        val sut =
            getSnippetFile("property-has-no-modifiers")
                .properties()
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
    fun `property-has-protected-and-open-modifiers`() {
        // given
        val sut =
            getSnippetFile("property-has-protected-and-open-modifiers")
                .properties(includeNested = true)
                .first()

        // then
        assertSoftly(sut) {
            numModifiers shouldBeEqualTo 2
            hasModifiers() shouldBeEqualTo true
            hasModifier(emptyList()) shouldBeEqualTo true
            hasModifier(emptySet()) shouldBeEqualTo true
            hasAllModifiers(emptyList()) shouldBeEqualTo true
            hasAllModifiers(emptySet()) shouldBeEqualTo true
            hasModifier(OPEN) shouldBeEqualTo true
            hasModifier(DATA) shouldBeEqualTo false
            hasModifier(OPEN, DATA) shouldBeEqualTo true
            hasModifier(listOf(OPEN)) shouldBeEqualTo true
            hasModifier(listOf(DATA)) shouldBeEqualTo false
            hasModifier(listOf(OPEN, DATA)) shouldBeEqualTo true
            hasModifier(setOf(OPEN)) shouldBeEqualTo true
            hasModifier(setOf(DATA)) shouldBeEqualTo false
            hasModifier(setOf(OPEN, DATA)) shouldBeEqualTo true
            hasAllModifiers(OPEN) shouldBeEqualTo true
            hasAllModifiers(DATA) shouldBeEqualTo false
            hasAllModifiers(OPEN, DATA) shouldBeEqualTo false
            hasAllModifiers(OPEN, PROTECTED) shouldBeEqualTo true
            hasAllModifiers(listOf(OPEN)) shouldBeEqualTo true
            hasAllModifiers(listOf(DATA)) shouldBeEqualTo false
            hasAllModifiers(listOf(OPEN, DATA)) shouldBeEqualTo false
            hasAllModifiers(listOf(OPEN, PROTECTED)) shouldBeEqualTo true
            hasAllModifiers(setOf(OPEN)) shouldBeEqualTo true
            hasAllModifiers(setOf(DATA)) shouldBeEqualTo false
            hasAllModifiers(setOf(OPEN, DATA)) shouldBeEqualTo false
            hasAllModifiers(setOf(OPEN, PROTECTED)) shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `property-modifiers`(
        fileName: String,
        modifiers: List<KoModifier>,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .properties(includeNested = true)
                .first()

        // then
        sut.modifiers shouldBeEqualTo modifiers
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koproperty/forkomodifier/snippet/forkomodifierprovider/",
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("property-has-modifiers-and-annotation-with-parameter", listOf(PROTECTED, OPEN)),
                arguments("property-has-modifiers-and-annotation-without-parameter", listOf(PROTECTED, OPEN)),
                arguments("property-has-modifiers-annotation-and-comment", listOf(PROTECTED, OPEN)),
                arguments("property-has-modifiers-and-annotations", listOf(PROTECTED, OPEN)),
                arguments("property-has-modifiers-and-annotation-with-angle-brackets", listOf(PROTECTED, OPEN)),
                arguments("property-has-modifiers-and-kdoc", listOf(PROTECTED, OPEN)),
                arguments("property-has-modifiers-kdoc-and-annotation-before-them", listOf(PROTECTED, OPEN)),
                arguments("property-has-modifiers-multiline-comment-and-annotation-before-them", listOf(PROTECTED, OPEN)),
                arguments("property-has-modifiers-and-comment-before-them", listOf(PROTECTED, OPEN)),
                arguments("property-has-modifiers-and-comment-after-them", listOf(PROTECTED, OPEN)),
                arguments("property-has-modifier-and-annotation-with-string-parameter-containing-link", listOf(PRIVATE)),
            )
    }
}
