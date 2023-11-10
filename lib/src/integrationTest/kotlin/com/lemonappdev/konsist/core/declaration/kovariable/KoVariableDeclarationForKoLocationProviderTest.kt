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

class KoVariableDeclarationForKoLocationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesForLocation")
    fun `variable-location`(declarations: List<KoVariableProvider>, location: String) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:$location"
    }

    @ParameterizedTest
    @MethodSource("provideValuesForLocationWithText")
    fun `variable-location-with-text`(declarations: List<KoVariableProvider>) {
        // given
        val projectPath = declarations
            .variables
            .first()
            .projectPath

        val sut = declarations
            .variables
            .first()

        // then
        val declaration = "Declaration:\nval sampleVariable = \"\""
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    companion object {
        private fun getSnippetFile(fileName: String) =
            getSnippetKoScope("core/declaration/kovariable/snippet/forkolocationprovider/", fileName)

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForLocation() = listOf(
            arguments(getSnippetFile("variable-in-function-location").functions(), "2:5"),
            arguments(getSnippetFile("variable-in-init-block-location").classes().initBlocks, "3:9"),
            arguments(getSnippetFile("variable-in-enum-constant-location").classes().enumConstants, "3:9"),
            arguments(getSnippetFile("variable-in-getter-location").properties().getters, "3:9"),
            arguments(getSnippetFile("variable-in-setter-location").properties().setters, "3:9"),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForLocationWithText() = listOf(
            arguments(getSnippetFile("variable-in-function-location-with-text").functions()),
            arguments(getSnippetFile("variable-in-init-block-location-with-text").classes().initBlocks),
            arguments(getSnippetFile("variable-in-enum-constant-location-with-text").classes().enumConstants),
            arguments(getSnippetFile("variable-in-getter-location-with-text").properties().getters),
            arguments(getSnippetFile("variable-in-setter-location-with-text").properties().setters),
        )
    }
}
