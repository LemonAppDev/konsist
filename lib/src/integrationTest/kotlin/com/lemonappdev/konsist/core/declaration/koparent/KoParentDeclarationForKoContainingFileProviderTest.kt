package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoContainingFileProviderTest {
    @Test
    fun `parent-of-class-containing-file`() {
        // given
        val sut = getSnippetFile("parent-of-class-containing-file")
            .classes()
            .parents
            .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    @Test
    fun `parent-of-interface-containing-file`() {
        // given
        val sut = getSnippetFile("parent-of-interface-containing-file")
            .interfaces()
            .parents
            .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    @Test
    fun `parent-of-object-containing-file`() {
        // given
        val sut = getSnippetFile("parent-of-object-containing-file")
            .objects()
            .parents
            .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparent/snippet/forkocontainingfileprovider/", fileName)
}
