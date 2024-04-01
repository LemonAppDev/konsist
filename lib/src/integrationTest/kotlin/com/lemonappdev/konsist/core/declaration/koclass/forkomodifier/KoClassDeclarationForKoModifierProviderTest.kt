package com.lemonappdev.konsist.core.declaration.koclass.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
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

class KoClassDeclarationForKoModifierProviderTest {
    @Test
    fun `class-without-modifiers`() {
        // given
        val sut =
            getSnippetFile("class-without-modifiers")
                .classes()
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
            hasModifiers(OPEN) shouldBeEqualTo false
            hasModifiers(OPEN, DATA) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-private-and-open-modifiers`() {
        // given
        val sut =
            getSnippetFile("class-with-private-and-open-modifiers")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo true
            hasModifier(emptyList()) shouldBeEqualTo true
            hasModifier(emptySet()) shouldBeEqualTo true
            hasAllModifiers(emptyList()) shouldBeEqualTo true
            hasAllModifiers(emptySet()) shouldBeEqualTo true
            numModifiers shouldBeEqualTo 2
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
            hasAllModifiers(OPEN, PRIVATE) shouldBeEqualTo true
            hasAllModifiers(listOf(OPEN)) shouldBeEqualTo true
            hasAllModifiers(listOf(DATA)) shouldBeEqualTo false
            hasAllModifiers(listOf(OPEN, DATA)) shouldBeEqualTo false
            hasAllModifiers(listOf(OPEN, PRIVATE)) shouldBeEqualTo true
            hasAllModifiers(setOf(OPEN)) shouldBeEqualTo true
            hasAllModifiers(setOf(DATA)) shouldBeEqualTo false
            hasAllModifiers(setOf(OPEN, DATA)) shouldBeEqualTo false
            hasAllModifiers(setOf(OPEN, PRIVATE)) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo true
            hasModifiers(OPEN) shouldBeEqualTo true
            hasModifiers(PROTECTED) shouldBeEqualTo false
            hasModifiers(PRIVATE, OPEN) shouldBeEqualTo true
            hasModifiers(OPEN, PRIVATE) shouldBeEqualTo true
            hasModifiers(PROTECTED, OPEN) shouldBeEqualTo false
            hasModifiers(PROTECTED, OPEN, PRIVATE) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `class-modifiers`(
        fileName: String,
        modifiers: List<KoModifier>,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()

        // then
        sut.modifiers shouldBeEqualTo modifiers
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/forkomodifier/snippet/forkomodifierprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("class-has-modifiers", listOf(PRIVATE, OPEN)),
                arguments("class-has-modifiers-and-annotation-with-parameter", listOf(PRIVATE, OPEN)),
                arguments("class-has-modifiers-and-annotation-without-parameter", listOf(PRIVATE, OPEN)),
                arguments("class-has-modifiers-annotation-and-comment", listOf(PRIVATE, OPEN)),
                arguments("class-has-modifiers-and-annotations", listOf(PRIVATE, OPEN)),
                arguments("class-has-modifiers-and-annotation-with-angle-brackets", listOf(PRIVATE, OPEN)),
                arguments("class-has-modifiers-and-kdoc", listOf(PRIVATE, OPEN)),
                arguments("class-has-modifiers-kdoc-and-annotation-before-them", listOf(PRIVATE, OPEN)),
                arguments("class-has-modifiers-multiline-comment-and-annotation-before-them", listOf(PRIVATE, OPEN)),
                arguments("class-has-modifiers-and-comment-before-them", listOf(PRIVATE, OPEN)),
                arguments("class-has-modifiers-and-comment-after-them", listOf(PRIVATE, OPEN)),
            )
    }
}
