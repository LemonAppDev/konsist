package com.lemonappdev.konsist.core.declaration.koimportalias

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoImportAliasDeclarationForKoPathProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-file-path`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.sourceImportAlias

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("koimportalias/snippet/forkopathprovider/$fileName.kt") shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-project-file-path`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.sourceImportAlias

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koimportalias/snippet/" +
                    "forkopathprovider/$fileName.kt",
            )
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-reside-in-file-path`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.sourceImportAlias

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..koimportalias/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..koimportalias..$fileName.kt", true) shouldBeEqualTo true
            it?.resideInPath("koimportalias/snippet/", true) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-reside-in-project-file-path`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.sourceImportAlias

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..koimportalias/snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..koimportalias..$fileName.kt", false) shouldBeEqualTo true
            it?.resideInPath("koimportalias/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koimportalias/snippet/forkopathprovider/",
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("nullable-import-alias-type-path"),
                arguments("not-nullable-import-alias-type-path"),
            )
    }
}
