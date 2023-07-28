package com.lemonappdev.konsist.core.declaration.koprimaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPrimaryConstructorDeclarationForKoContainingFileProviderTest {
    @Test
    fun `primary-constructor-containing-file`() {
        // given
        val sut = getSnippetFile("primary-constructor-containing-file")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut
            ?.containingFile
            ?.nameWithExtension
            ?.endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koprimaryconstructordeclaration/snippet/forkocontainingfileprovider/", fileName)
}
