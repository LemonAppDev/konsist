package com.lemonappdev.konsist.core.declaration.koproperty.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.FINAL
import com.lemonappdev.konsist.api.KoModifier.OPEN
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
            hasModifier(OPEN) shouldBeEqualTo false
            hasModifier(OPEN, DATA) shouldBeEqualTo false
            hasAllModifiers(OPEN) shouldBeEqualTo false
            hasAllModifiers(OPEN, DATA) shouldBeEqualTo false
            hasModifiers(OPEN) shouldBeEqualTo false
            hasModifiers(OPEN, DATA) shouldBeEqualTo false
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
            hasModifier(OPEN) shouldBeEqualTo true
            hasModifier(DATA) shouldBeEqualTo false
            hasModifier(OPEN, DATA) shouldBeEqualTo true
            hasAllModifiers(OPEN) shouldBeEqualTo true
            hasAllModifiers(DATA) shouldBeEqualTo false
            hasAllModifiers(OPEN, DATA) shouldBeEqualTo false
            hasAllModifiers(OPEN, PROTECTED) shouldBeEqualTo true
            hasModifiers(PROTECTED) shouldBeEqualTo true
            hasModifiers(OPEN) shouldBeEqualTo true
            hasModifiers(FINAL) shouldBeEqualTo false
            hasModifiers(PROTECTED, OPEN) shouldBeEqualTo true
            hasModifiers(OPEN, PROTECTED) shouldBeEqualTo true
            hasModifiers(PROTECTED, FINAL) shouldBeEqualTo false
            hasModifiers(FINAL, OPEN, PROTECTED) shouldBeEqualTo false
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
            )
    }
}
