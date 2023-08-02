package com.lemonappdev.konsist.core.declaration.koconstructordeclaration.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoConstructorDeclarationForKoModifierProviderTest {
    @Test
    fun `constructor-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("constructor-has-no-modifiers")
            .classes()
            .first()
            .constructors
            .first()

        // then
        assertSoftly(sut) {
            it.modifiers shouldBeEqualTo emptyList()
            it.numModifiers shouldBeEqualTo 0
            it.hasModifiers() shouldBeEqualTo false
            it.hasModifiers(OPEN) shouldBeEqualTo false
            it.hasModifiers(OPEN, DATA) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `constructor-modifiers`(fileName: String) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .constructors
            .first()

        // then
        assertSoftly(sut) {
            it.modifiers shouldBeEqualTo listOf(PRIVATE)
            it.numModifiers shouldBeEqualTo 1
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koconstructordeclaration/forkomodifierprovider/snippet/forkomodifierprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("constructor-has-modifier"),
            arguments("constructor-has-modifiers-and-annotation-with-parameter"),
            arguments("constructor-has-modifiers-and-annotation-without-parameter"),
            arguments("constructor-has-modifiers-and-annotations"),
        )
    }
}
