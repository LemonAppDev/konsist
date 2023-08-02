package com.lemonappdev.konsist.core.declaration.kosecondaryconstructordeclaration.forkomodifierprovider

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

class KoSecondaryConstructorDeclarationForKoModifierProviderTest {
    @Test
    fun `secondary-constructor-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-no-modifiers")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            it.modifiers shouldBeEqualTo emptyList()
            numModifiers shouldBeEqualTo 0
            it.hasModifiers() shouldBeEqualTo false
            it.hasModifiers(OPEN) shouldBeEqualTo false
            it.hasModifiers(OPEN, DATA) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `secondary-constructor-modifiers`(fileName: String) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo listOf(PRIVATE)
            numModifiers shouldBeEqualTo 1
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/kosecondaryconstructordeclaration/forkomodifierprovider/snippet/forkomodifierprovider/",
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("secondary-constructor-has-modifier"),
            arguments("secondary-constructor-has-modifiers-and-annotation-with-parameter"),
            arguments("secondary-constructor-has-modifiers-and-annotation-without-parameter"),
            arguments("secondary-constructor-has-modifiers-and-annotations"),
        )
    }
}
