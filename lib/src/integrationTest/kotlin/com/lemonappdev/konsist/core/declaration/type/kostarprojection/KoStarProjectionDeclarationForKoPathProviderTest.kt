package com.lemonappdev.konsist.core.declaration.type.kostarprojection

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.api.ext.list.returnTypes
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoStarProjectionDeclarationForKoPathProviderTest {
    @Test
    fun `declaration-file-path`() {
        // given
        val sut =
            getSnippetFile("star-projection-path")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration as? KoStarProjectionDeclaration

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("type/kostarprojection/snippet/forkopathprovider/star-projection-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `declaration-project-file-path`() {
        // given
        val sut =
            getSnippetFile("star-projection-path")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration as? KoStarProjectionDeclaration

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/type/kostarprojection/snippet/" +
                    "forkopathprovider/star-projection-path.kt",
            )
    }

    @Test
    fun `declaration-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("star-projection-path")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration as? KoStarProjectionDeclaration

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..type/kostarprojection/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..type/kostarprojection..star-projection-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("type/kostarprojection/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `declaration-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("star-projection-path")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration as? KoStarProjectionDeclaration

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..type/kostarprojection/snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..type/kostarprojection..star-projection-path.kt", false) shouldBeEqualTo true
            it?.resideInPath("type/kostarprojection/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kostarprojection/snippet/forkopathprovider/",
            fileName,
        )
}
