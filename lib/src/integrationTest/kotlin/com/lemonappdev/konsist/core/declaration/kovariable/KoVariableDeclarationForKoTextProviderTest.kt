package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.enumConstants
import com.lemonappdev.konsist.api.ext.list.getters
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.setters
import com.lemonappdev.konsist.api.ext.list.variables
import com.lemonappdev.konsist.api.provider.KoVariableProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoVariableDeclarationForKoTextProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `variable-text`(declarations: List<KoVariableProvider>) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        sut
            .text
            .shouldBeEqualTo("val sampleVariable = \"\"")
    }

    companion object {
        private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kovariable/snippet/forkotextprovider/", fileName)

        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments(getSnippetFile("variable-in-function-text").functions()),
                arguments(getSnippetFile("variable-in-init-block-text").classes().initBlocks),
                arguments(getSnippetFile("variable-in-enum-constant-text").classes().enumConstants),
                arguments(getSnippetFile("variable-in-getter-text").properties().getters),
                arguments(getSnippetFile("variable-in-setter-text").properties().setters),
            )
    }
}
