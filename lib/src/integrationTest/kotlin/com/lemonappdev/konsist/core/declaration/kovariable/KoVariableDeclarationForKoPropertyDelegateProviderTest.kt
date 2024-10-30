package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider
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

class KoVariableDeclarationForKoPropertyDelegateProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesForLazyDelegate")
    fun `variable-has-lazy-delegate`(declarations: List<KoVariableProvider>) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        assertSoftly(sut) {
            delegateName shouldBeEqualTo "lazy"
            hasDelegate() shouldBeEqualTo true
            hasDelegate("lazy") shouldBeEqualTo true
            hasDelegate("Delegates.observable()") shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForNoDelegate")
    fun `variable-has-no-delegate`(declarations: List<KoVariableProvider>) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        assertSoftly(sut) {
            delegateName shouldBeEqualTo null
            hasDelegate() shouldBeEqualTo false
            hasDelegate("lazy") shouldBeEqualTo false
        }
    }

    companion object {
        private fun getSnippetFile(fileName: String) =
            TestSnippetProvider.getSnippetKoScope(
                "core/declaration/kovariable/snippet/forkopropertydelegateprovider/",
                fileName,
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForLazyDelegate() =
            listOf(
                arguments(getSnippetFile("variable-in-function-has-lazy-delegate").functions()),
                arguments(getSnippetFile("variable-in-init-block-has-lazy-delegate").classes().initBlocks),
                arguments(getSnippetFile("variable-in-enum-constant-has-lazy-delegate").classes().enumConstants),
                arguments(getSnippetFile("variable-in-getter-has-lazy-delegate").properties().getters),
                arguments(getSnippetFile("variable-in-setter-has-lazy-delegate").properties().setters),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForNoDelegate() =
            listOf(
                arguments(getSnippetFile("variable-in-function-has-no-delegate").functions()),
                arguments(getSnippetFile("variable-in-init-block-has-no-delegate").classes().initBlocks),
                arguments(getSnippetFile("variable-in-enum-constant-has-no-delegate").classes().enumConstants),
                arguments(getSnippetFile("variable-in-getter-has-no-delegate").properties().getters),
                arguments(getSnippetFile("variable-in-setter-has-no-delegate").properties().setters),
            )
    }
}
