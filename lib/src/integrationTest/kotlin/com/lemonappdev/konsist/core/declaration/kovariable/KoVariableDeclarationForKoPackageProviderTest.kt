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

class KoVariableDeclarationForKoPackageProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesWithoutPackage")
    fun `variable-is-not-in-package`(declarations: List<KoVariableProvider>) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @ParameterizedTest
    @MethodSource("provideValuesWithPackage")
    fun `variable-is-in-package`(declarations: List<KoVariableProvider>) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    companion object {
        private fun getSnippetFile(fileName: String) =
            getSnippetKoScope("core/declaration/kovariable/snippet/forkopackageprovider/", fileName)

        @Suppress("unused")
        @JvmStatic
        fun provideValuesWithoutPackage() =
            listOf(
                arguments(getSnippetFile("variable-in-function-is-not-in-package").functions()),
                arguments(getSnippetFile("variable-in-init-block-is-not-in-package").classes().initBlocks),
                arguments(getSnippetFile("variable-in-enum-constant-is-not-in-package").classes().enumConstants),
                arguments(getSnippetFile("variable-in-getter-is-not-in-package").properties().getters),
                arguments(getSnippetFile("variable-in-setter-is-not-in-package").properties().setters),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesWithPackage() =
            listOf(
                arguments(getSnippetFile("variable-in-function-is-in-package").functions()),
                arguments(getSnippetFile("variable-in-init-block-is-in-package").classes().initBlocks),
                arguments(getSnippetFile("variable-in-enum-constant-is-in-package").classes().enumConstants),
                arguments(getSnippetFile("variable-in-getter-is-in-package").properties().getters),
                arguments(getSnippetFile("variable-in-setter-is-in-package").properties().setters),
            )
    }
}
