package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.provider.enumConstants
import com.lemonappdev.konsist.api.ext.list.provider.getters
import com.lemonappdev.konsist.api.ext.list.provider.initBlocks
import com.lemonappdev.konsist.api.ext.list.provider.setters
import com.lemonappdev.konsist.api.ext.list.provider.variables
import com.lemonappdev.konsist.api.provider.KoVariableProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoVariableDeclarationTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `variable-to-string`(declarations: List<KoVariableProvider>) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        sut.toString() shouldBeEqualTo "sampleVariable"
    }

    companion object {
        private fun getSnippetFile(fileName: String) =
            TestSnippetProvider.getSnippetKoScope("core/declaration/kovariable/snippet/forgeneral/", fileName)

        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments(getSnippetFile("variable-in-function-to-string").functions()),
                arguments(getSnippetFile("variable-in-init-block-to-string").classes().initBlocks),
                arguments(getSnippetFile("variable-in-enum-constant-to-string").classes().enumConstants),
                arguments(getSnippetFile("variable-in-getter-to-string").properties().getters),
                arguments(getSnippetFile("variable-in-setter-to-string").properties().setters),
            )
    }
}
