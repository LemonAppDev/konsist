package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.provider.enumConstants
import com.lemonappdev.konsist.api.ext.list.provider.getters
import com.lemonappdev.konsist.api.ext.list.provider.initBlocks
import com.lemonappdev.konsist.api.ext.list.provider.setters
import com.lemonappdev.konsist.api.ext.list.provider.variables
import com.lemonappdev.konsist.api.provider.KoVariableProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoVariableDeclarationForKoPathProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesForFilePath")
    fun `variable-file-path`(
        declarations: List<KoVariableProvider>,
        name: String,
    ) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("kovariable/snippet/forkopathprovider/$name.kt") shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForProjectFilePath")
    fun `variable-project-file-path`(
        declarations: List<KoVariableProvider>,
        name: String,
    ) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kovariable/snippet/" +
                    "forkopathprovider/$name.kt",
            )
    }

    @ParameterizedTest
    @MethodSource("provideValuesForResideInFilePath")
    fun `variable-reside-in-file-path`(
        declarations: List<KoVariableProvider>,
        name: String,
    ) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..kovariable/snippet..", true) shouldBeEqualTo true
            resideInPath("..kovariable..$name.kt", true) shouldBeEqualTo true
            resideInPath("kovariable/snippet/", true) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForResideInProjectFilePath")
    fun `variable-reside-in-project-file-path`(
        declarations: List<KoVariableProvider>,
        name: String,
    ) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..kovariable/snippet..", false) shouldBeEqualTo true
            resideInPath("..kovariable..$name.kt", false) shouldBeEqualTo true
            resideInPath("kovariable/snippet/", false) shouldBeEqualTo false
        }
    }

    companion object {
        private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kovariable/snippet/forkopathprovider/", fileName)

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForFilePath() =
            listOf(
                arguments(
                    getSnippetFile("variable-in-function-file-path").functions(),
                    "variable-in-function-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-init-block-file-path").classes().initBlocks,
                    "variable-in-init-block-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-enum-constant-file-path").classes().enumConstants,
                    "variable-in-enum-constant-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-getter-file-path").properties().getters,
                    "variable-in-getter-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-setter-file-path").properties().setters,
                    "variable-in-setter-file-path",
                ),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForProjectFilePath() =
            listOf(
                arguments(
                    getSnippetFile("variable-in-function-project-file-path").functions(),
                    "variable-in-function-project-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-init-block-project-file-path").classes().initBlocks,
                    "variable-in-init-block-project-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-enum-constant-project-file-path").classes().enumConstants,
                    "variable-in-enum-constant-project-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-getter-project-file-path").properties().getters,
                    "variable-in-getter-project-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-setter-project-file-path").properties().setters,
                    "variable-in-setter-project-file-path",
                ),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForResideInFilePath() =
            listOf(
                arguments(
                    getSnippetFile("variable-in-function-reside-in-file-path").functions(),
                    "variable-in-function-reside-in-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-init-block-reside-in-file-path").classes().initBlocks,
                    "variable-in-init-block-reside-in-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-enum-constant-reside-in-file-path").classes().enumConstants,
                    "variable-in-enum-constant-reside-in-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-getter-reside-in-file-path").properties().getters,
                    "variable-in-getter-reside-in-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-setter-reside-in-file-path").properties().setters,
                    "variable-in-setter-reside-in-file-path",
                ),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForResideInProjectFilePath() =
            listOf(
                arguments(
                    getSnippetFile("variable-in-function-reside-in-project-file-path").functions(),
                    "variable-in-function-reside-in-project-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-init-block-reside-in-project-file-path").classes().initBlocks,
                    "variable-in-init-block-reside-in-project-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-enum-constant-reside-in-project-file-path").classes().enumConstants,
                    "variable-in-enum-constant-reside-in-project-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-getter-reside-in-project-file-path").properties().getters,
                    "variable-in-getter-reside-in-project-file-path",
                ),
                arguments(
                    getSnippetFile("variable-in-setter-reside-in-project-file-path").properties().setters,
                    "variable-in-setter-reside-in-project-file-path",
                ),
            )
    }
}
