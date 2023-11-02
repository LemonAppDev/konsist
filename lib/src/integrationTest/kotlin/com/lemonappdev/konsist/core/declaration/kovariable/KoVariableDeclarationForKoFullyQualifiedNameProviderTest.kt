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

class KoVariableDeclarationForKoFullyQualifiedNameProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesWithPackage")
    fun `variable-fully-qualified-name`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "sampleVariable"
    }

    @ParameterizedTest
    @MethodSource("provideValuesWithoutPackage")
    fun `variable-fully-qualified-name-without-package`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "sampleVariable"
    }

    companion object {
        private fun getSnippetFile(fileName: String) =
            getSnippetKoScope(
                "core/declaration/kovariable/snippet/forkodeclarationfullyqualifiednameprovider/",
                fileName,
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesWithPackage() = listOf(
            arguments(getSnippetFile("variable-in-function-fully-qualified-name").functions()),
            arguments(getSnippetFile("variable-in-init-block-fully-qualified-name").classes().initBlocks),
            arguments(getSnippetFile("variable-in-enum-constant-fully-qualified-name").classes().enumConstants),
            arguments(getSnippetFile("variable-in-getter-fully-qualified-name").properties().getters),
            arguments(getSnippetFile("variable-in-setter-fully-qualified-name").properties().setters),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesWithoutPackage() = listOf(
            arguments(
                getSnippetFile("variable-in-function-fully-qualified-name-without-package").functions(),
            ),
            arguments(
                getSnippetFile("variable-in-init-block-fully-qualified-name-without-package").classes().initBlocks,
            ),
            arguments(
                getSnippetFile("variable-in-enum-constant-fully-qualified-name-without-package").classes().enumConstants,
            ),
            arguments(
                getSnippetFile("variable-in-getter-fully-qualified-name-without-package").properties().getters,
            ),
            arguments(
                getSnippetFile("variable-in-setter-fully-qualified-name-without-package").properties().setters,
            ),
        )
    }
}
