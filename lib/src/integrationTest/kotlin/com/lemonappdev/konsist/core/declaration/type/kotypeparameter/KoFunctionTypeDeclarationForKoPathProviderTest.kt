package com.lemonappdev.konsist.core.declaration.type.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.returnTypes
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeParameterDeclarationForKoPathProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-file-path`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
.functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("type/kotypeparameter/snippet/forkopathprovider/$fileName.kt") shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-project-file-path`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
.functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/type/kotypeparameter/snippet/" +
                    "forkopathprovider/$fileName.kt",
            )
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-reside-in-file-path`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
.functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..type/kotypeparameter/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..type/kotypeparameter..$fileName.kt", true) shouldBeEqualTo true
            it?.resideInPath("type/kotypeparameter/snippet/", true) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-reside-in-project-file-path`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
.functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..type/kotypeparameter/snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..type/kotypeparameter..$fileName.kt", false) shouldBeEqualTo true
            it?.resideInPath("type/kotypeparameter/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotypeparameter/snippet/forkopathprovider/",
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("nullable-type-parameter-path"),
                arguments("not-nullable-type-parameter-path"),
            )
    }
}
