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

class KoVariableDeclarationForKoNameProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `variable-name`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "sampleVariable"
            hasNameStartingWith("sample") shouldBeEqualTo true
            hasNameStartingWith("other") shouldBeEqualTo false
            hasNameEndingWith("able") shouldBeEqualTo true
            hasNameEndingWith("other") shouldBeEqualTo false
            hasNameContaining("leVari") shouldBeEqualTo true
            hasNameContaining("levari") shouldBeEqualTo false
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    companion object {
        private fun getSnippetFile(fileName: String) =
            getSnippetKoScope("core/declaration/kovariable/snippet/forkonameprovider/", fileName)

        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(getSnippetFile("variable-in-function-name").functions()),
            arguments(getSnippetFile("variable-in-init-block-name").classes().initBlocks),
            arguments(getSnippetFile("variable-in-enum-constant-name").classes().enumConstants),
            arguments(getSnippetFile("variable-in-getter-name").properties().getters),
            arguments(getSnippetFile("variable-in-setter-name").properties().setters),
        )
    }
}
