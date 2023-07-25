package com.lemonappdev.konsist.core.declaration.koprimaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPrimaryConstructorDeclarationForKoPathProviderTest {
    @Test
    fun `primary-constructor-file-path`() {
        // given
        val sut = getSnippetFile("primary-constructor-file-path")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("koprimaryconstructordeclaration/snippet/forkopathprovider/primary-constructor-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `primary-constructor-project-file-path`() {
        // given
        val sut = getSnippetFile("primary-constructor-project-file-path")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koprimaryconstructordeclaration/snippet/" +
                    "forkopathprovider/primary-constructor-project-file-path.kt",
            )
    }

    @Test
    fun `primary-constructor-reside-in-file-path`() {
        // given
        val sut = getSnippetFile("primary-constructor-reside-in-file-path")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..koprimaryconstructordeclaration/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..koprimaryconstructordeclaration..primary-constructor-reside-in-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("koprimaryconstructordeclaration/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `primary-constructor-reside-in-project-file-path`() {
        // given
        val sut = getSnippetFile("primary-constructor-reside-in-project-file-path")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..koprimaryconstructordeclaration/snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..koprimaryconstructordeclaration..primary-constructor-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            it?.resideInPath("koprimaryconstructordeclaration/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koprimaryconstructordeclaration/snippet/forkopathprovider/", fileName)
}
