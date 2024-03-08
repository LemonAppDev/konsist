package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.enumConstants
import com.lemonappdev.konsist.api.ext.list.getters
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.setters
import com.lemonappdev.konsist.api.ext.list.variables
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoVariableProvider
import org.amshove.kluent.shouldContain
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoVariableDeclarationForKoContainingDeclarationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `variable-parent-declaration`(
        declarations: List<KoVariableProvider>,
        result: String,
    ) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        (sut.containingDeclaration as KoTextProvider).text shouldContain result
    }

    companion object {
        private fun getSnippetFile(fileName: String) =
            getSnippetKoScope("core/declaration/kovariable/snippet/forkocontainingdeclarationprovider/", fileName)

        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments(
                    getSnippetFile("variable-in-function-parent-declaration").functions(),
                    "fun sampleFunction()",
                ),
                arguments(
                    getSnippetFile("variable-in-init-block-parent-declaration").classes().initBlocks,
                    "init {",
                ),
                arguments(
                    getSnippetFile("variable-in-enum-constant-parent-declaration").classes().enumConstants,
                    "SAMPLE_CONSTANT_1",
                ),
                arguments(
                    getSnippetFile("variable-in-getter-parent-declaration").properties().getters,
                    "get() {",
                ),
                arguments(
                    getSnippetFile("variable-in-setter-parent-declaration").properties().setters,
                    "set(value) {",
                ),
            )
    }
}
