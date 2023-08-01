package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
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
        val sut = getSnippetFile("function-has-no-modifiers")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo emptyList()
            numModifiers shouldBeEqualTo 0
            hasModifiers() shouldBeEqualTo false
            hasModifiers(OPEN) shouldBeEqualTo false
            hasModifiers(OPEN, KoModifier.DATA) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-protected-and-suspend-modifiers`() {
        // given
        val sut = getSnippetFile("function-has-protected-and-suspend-modifiers")
            .functions(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            numModifiers shouldBeEqualTo 2
            hasModifiers() shouldBeEqualTo true
            hasModifiers(PROTECTED) shouldBeEqualTo true
            hasModifiers(SUSPEND) shouldBeEqualTo true
            hasModifiers(OPEN) shouldBeEqualTo false
            hasModifiers(PROTECTED, SUSPEND) shouldBeEqualTo true
            hasModifiers(SUSPEND, PROTECTED) shouldBeEqualTo true
            hasModifiers(PROTECTED, OPEN) shouldBeEqualTo false
            hasModifiers(OPEN, SUSPEND, PROTECTED) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `function-modifiers`(
        fileName: String,
        modifiers: List<KoModifier>,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .functions(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo modifiers
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/forkomodifierprovider/snippet/forkomodifierprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("function-has-modifiers", listOf(PROTECTED, OPEN, SUSPEND, KoModifier.INLINE, KoModifier.OPERATOR)),
            arguments("function-has-modifiers-and-annotation-with-parameter", listOf(PROTECTED, OPEN)),
            arguments("function-has-modifiers-and-annotation-without-parameter", listOf(PROTECTED, OPEN)),
            arguments("function-has-modifiers-annotation-and-comment", listOf(PROTECTED, OPEN)),
            arguments("function-has-modifiers-and-annotations", listOf(PROTECTED, OPEN)),
            arguments("function-has-modifiers-and-annotation-with-angle-brackets", listOf(PROTECTED, OPEN)),
        )
    }
}
