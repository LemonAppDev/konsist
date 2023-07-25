package com.lemonappdev.konsist.core.declaration.kosecondaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSecondaryConstructorDeclarationForKoPathProviderTest {
    @Test
    fun `secondary-constructor-file-path`() {
        // given
        val sut = getSnippetFile("secondary-constructor-file-path")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut.path) {
            it.startsWith("//") shouldBeEqualTo false
            it
                .endsWith("kosecondaryconstructordeclaration/snippet/forkopathprovider/secondary-constructor-file-path.kt")
                .shouldBeEqualTo(true)
        }
    }

    @Test
    fun `secondary-constructor-project-file-path`() {
        // given
        val sut = getSnippetFile("secondary-constructor-project-file-path")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kosecondaryconstructordeclaration/snippet/" +
                        "forkopathprovider/secondary-constructor-project-file-path.kt",
            )
    }

    @Test
    fun `secondary-constructor-reside-in-file-path`() {
        // given
        val sut = getSnippetFile("secondary-constructor-reside-in-file-path")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            it.resideInPath("..snippet..", true) shouldBeEqualTo true
            it.resideInPath("..kosecondaryconstructordeclaration/snippet..", true) shouldBeEqualTo true
            it.resideInPath("..kosecondaryconstructordeclaration..secondary-constructor-reside-in-file-path.kt", true) shouldBeEqualTo true
            it.resideInPath("kosecondaryconstructordeclaration/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-reside-in-project-file-path`() {
        // given
        val sut = getSnippetFile("secondary-constructor-reside-in-project-file-path")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            it.resideInPath("..snippet..", false) shouldBeEqualTo true
            it.resideInPath("..kosecondaryconstructordeclaration/snippet..", false) shouldBeEqualTo true
            it.resideInPath(
                "..kosecondaryconstructordeclaration..secondary-constructor-reside-in-project-file-path.kt",
                false
            ) shouldBeEqualTo true
            it.resideInPath("kosecondaryconstructordeclaration/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kosecondaryconstructordeclaration/snippet/forkopathprovider/", fileName)
}
