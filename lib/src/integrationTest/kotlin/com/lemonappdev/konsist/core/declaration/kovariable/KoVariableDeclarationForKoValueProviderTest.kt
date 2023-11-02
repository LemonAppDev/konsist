package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.enumConstants
import com.lemonappdev.konsist.api.ext.list.getters
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.setters
import com.lemonappdev.konsist.api.ext.list.variables
import com.lemonappdev.konsist.api.provider.KoVariableProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoVariableDeclarationForKoValueProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `variable-has-value`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            value shouldBeEqualTo "text"
            hasValue() shouldBeEqualTo true
            hasValue("text") shouldBeEqualTo true
            hasValue("other text") shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDelegation")
    fun `variable-with-delegation-has-no-value`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            value shouldBeEqualTo null
            hasValue() shouldBeEqualTo false
            hasValue("0") shouldBeEqualTo false
        }
    }

    companion object {
        private fun getSnippetFile(fileName: String) =
            getSnippetKoScope("core/declaration/kovariable/snippet/forkovalueprovider/", fileName)

        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(getSnippetFile("variable-in-function-has-value").functions()),
            arguments(getSnippetFile("variable-in-init-block-has-value").classes().initBlocks),
            arguments(getSnippetFile("variable-in-enum-constant-has-value").classes().enumConstants),
            arguments(getSnippetFile("variable-in-getter-has-value").properties().getters),
            arguments(getSnippetFile("variable-in-setter-has-value").properties().setters),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDelegation() = listOf(
            arguments(getSnippetFile("variable-with-delegation-in-function-has-no-value").functions()),
            arguments(getSnippetFile("variable-with-delegation-in-init-block-has-no-value").classes().initBlocks),
            arguments(getSnippetFile("variable-with-delegation-in-enum-constant-has-no-value").classes().enumConstants),
            arguments(getSnippetFile("variable-with-delegation-in-getter-has-no-value").properties().getters),
            arguments(getSnippetFile("variable-with-delegation-in-setter-has-no-value").properties().setters),
        )
    }
}
