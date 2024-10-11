package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeArgumentDeclarationForKoPathProviderTest {
    @Test
    fun `not-generic-type-argument-file-path`() {
        // given
        val sut =
            getSnippetFile("not-generic-type-argument-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("kotypeargument/snippet/forkopathprovider/not-generic-type-argument-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `not-generic-type-argument-project-file-path`() {
        // given
        val sut =
            getSnippetFile("not-generic-type-argument-project-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kotypeargument/snippet/" +
                        "forkopathprovider/not-generic-type-argument-project-file-path.kt",
            )
    }

    @Test
    fun `not-generic-type-argument-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("not-generic-type-argument-reside-in-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument..not-generic-type-argument-reside-in-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeargument/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `not-generic-type-argument-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("not-generic-type-argument-reside-in-project-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument..not-generic-type-argument-reside-in-project-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeargument/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-type-argument-file-path`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("kotypeargument/snippet/forkopathprovider/generic-type-argument-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `generic-type-argument-project-file-path`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-project-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kotypeargument/snippet/" +
                        "forkopathprovider/generic-type-argument-project-file-path.kt",
            )
    }

    @Test
    fun `generic-type-argument-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-reside-in-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument..generic-type-argument-reside-in-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeargument/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-type-argument-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-reside-in-project-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument..generic-type-argument-reside-in-project-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeargument/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-complex-type-argument-file-path`() {
        // given
        val sut =
            getSnippetFile("generic-complex-type-argument-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("kotypeargument/snippet/forkopathprovider/generic-complex-type-argument-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `generic-complex-type-argument-project-file-path`() {
        // given
        val sut =
            getSnippetFile("generic-complex-type-argument-project-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kotypeargument/snippet/" +
                        "forkopathprovider/generic-complex-type-argument-project-file-path.kt",
            )
    }

    @Test
    fun `generic-complex-type-argument-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("generic-complex-type-argument-reside-in-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument..generic-complex-type-argument-reside-in-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeargument/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-complex-type-argument-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("generic-complex-type-argument-reside-in-project-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument..generic-complex-type-argument-reside-in-project-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeargument/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `star-projection-type-argument-file-path`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-argument-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("kotypeargument/snippet/forkopathprovider/star-projection-type-argument-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `star-projection-type-argument-project-file-path`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-argument-project-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kotypeargument/snippet/" +
                        "forkopathprovider/star-projection-type-argument-project-file-path.kt",
            )
    }

    @Test
    fun `star-projection-type-argument-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-argument-reside-in-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument..star-projection-type-argument-reside-in-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeargument/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `star-projection-type-argument-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-argument-reside-in-project-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument..star-projection-type-argument-reside-in-project-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeargument/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `out-projection-type-argument-file-path`() {
        // given
        val sut =
            getSnippetFile("out-projection-type-argument-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("kotypeargument/snippet/forkopathprovider/out-projection-type-argument-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `out-projection-type-argument-project-file-path`() {
        // given
        val sut =
            getSnippetFile("out-projection-type-argument-project-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kotypeargument/snippet/" +
                        "forkopathprovider/out-projection-type-argument-project-file-path.kt",
            )
    }

    @Test
    fun `out-projection-type-argument-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("out-projection-type-argument-reside-in-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument..out-projection-type-argument-reside-in-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeargument/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `out-projection-type-argument-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("out-projection-type-argument-reside-in-project-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument..out-projection-type-argument-reside-in-project-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeargument/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `in-projection-type-argument-file-path`() {
        // given
        val sut =
            getSnippetFile("in-projection-type-argument-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("kotypeargument/snippet/forkopathprovider/in-projection-type-argument-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `in-projection-type-argument-project-file-path`() {
        // given
        val sut =
            getSnippetFile("in-projection-type-argument-project-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kotypeargument/snippet/" +
                        "forkopathprovider/in-projection-type-argument-project-file-path.kt",
            )
    }

    @Test
    fun `in-projection-type-argument-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("in-projection-type-argument-reside-in-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument..in-projection-type-argument-reside-in-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeargument/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `in-projection-type-argument-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("in-projection-type-argument-reside-in-project-file-path")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeargument..in-projection-type-argument-reside-in-project-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeargument/snippet/", true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypeargument/snippet/forkopathprovider/", fileName)
}
