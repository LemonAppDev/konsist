package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoPathProviderTest {
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

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("type/kotype/snippet/forkopathprovider/$fileName.kt") shouldBeEqualTo true
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

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/type/kotype/snippet/" +
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

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..type/kotype/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..type/kotype..$fileName.kt", true) shouldBeEqualTo true
            it?.resideInPath("type/kotype/snippet/", true) shouldBeEqualTo false
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

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..type/kotype/snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..type/kotype..$fileName.kt", false) shouldBeEqualTo true
            it?.resideInPath("type/kotype/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/type/kotype/snippet/forkopathprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("nullable-kotlin-basic-type-path"),
                arguments("not-nullable-kotlin-basic-type-path"),
                arguments("nullable-kotlin-collection-type-path"),
                arguments("not-nullable-kotlin-collection-type-path"),
                arguments("nullable-class-type-path"),
                arguments("not-nullable-class-type-path"),
                arguments("nullable-interface-type-path"),
                arguments("not-nullable-interface-type-path"),
                arguments("nullable-object-type-path"),
                arguments("not-nullable-object-type-path"),
                arguments("nullable-function-type-path"),
                arguments("not-nullable-function-type-path"),
                arguments("nullable-import-alias-type-path"),
                arguments("not-nullable-import-alias-type-path"),
                arguments("nullable-typealias-type-path"),
                arguments("not-nullable-typealias-type-path"),
                arguments("nullable-external-type-path"),
                arguments("not-nullable-external-type-path"),
            )
    }
}
