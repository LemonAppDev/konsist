package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoDeclarationForModifierWithConstructorTest {
    @ParameterizedTest
    @MethodSource("provideValuesForSecondaryConstructorModifiers")
    fun `secondary-constructor-modifiers`(fileName: String) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.modifiers.toList() shouldBeEqualTo listOf(PRIVATE)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/formodifierwithconstructor/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForSecondaryConstructorModifiers() = listOf(
            arguments("secondary-constructor-has-modifier"),
            arguments("secondary-constructor-has-modifiers-and-annotation-with-parameter"),
            arguments("secondary-constructor-has-modifiers-and-annotation-without-parameter"),
            arguments("secondary-constructor-has-modifiers-and-annotations"),
        )
    }
}
