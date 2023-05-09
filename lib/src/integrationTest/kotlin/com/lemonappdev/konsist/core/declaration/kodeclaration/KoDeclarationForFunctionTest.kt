package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.INLINE
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.OPERATOR
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import com.lemonappdev.konsist.api.KoModifier.SUSPEND
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForFunctionTest {
    @Test
    fun `function-has-modifiers`() {
        // given
        val sut = getSnippetFile("function-has-modifiers")
            .functions(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN, SUSPEND, INLINE, OPERATOR)
    }

    @Test
    fun `function-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("function-has-modifiers-and-annotation-with-parameter")
            .functions(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN)
    }

    @Test
    fun `function-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("function-has-modifiers-and-annotation-without-parameter")
            .functions(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN)
    }

    @Test
    fun `function-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("function-has-modifiers-and-annotations")
            .functions(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN)
    }

    @Test
    fun `function-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("function-has-protected-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `function-has-public-modifier`() {
        // given
        val sut = getSnippetFile("function-has-public-modifier")
            .functions(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-two-modifiers`() {
        // given
        val sut = getSnippetFile("function-has-two-modifiers")
            .functions(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PROTECTED) shouldBeEqualTo true
            hasModifiers(SUSPEND) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
            hasModifiers(PROTECTED, SUSPEND) shouldBeEqualTo true
            hasModifiers(SUSPEND, PROTECTED) shouldBeEqualTo true
            hasModifiers(PRIVATE, SUSPEND) shouldBeEqualTo false
            hasModifiers(PROTECTED, SUSPEND, PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-no-modifier`() {
        // given
        val sut = getSnippetFile("function-has-no-modifier")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forfunction/", fileName)
}
