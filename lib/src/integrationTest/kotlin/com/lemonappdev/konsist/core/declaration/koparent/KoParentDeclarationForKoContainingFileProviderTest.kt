package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoContainingFileProviderTest {
    @Test
    fun `parent-from-file-of-class-containing-file`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-class-containing-file")
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
    fun `parent-from-file-of-interface-containing-file`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-interface-containing-file")
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
    fun `parent-from-file-of-object-containing-file`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-object-containing-file")
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

    @Test
    fun `parent-from-import-of-class-containing-file`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-class-containing-file")
            .classes()
            .parents
            .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("TestData.kt")
            .shouldBeEqualTo(true)
    }

    @Test
    fun `parent-from-import-of-interface-containing-file`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-interface-containing-file")
            .interfaces()
            .parents
            .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("TestData.kt")
            .shouldBeEqualTo(true)
    }

    @Test
    fun `parent-from-import-of-object-containing-file`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-object-containing-file")
            .objects()
            .parents
            .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("TestData.kt")
            .shouldBeEqualTo(true)
    }

    @Test
    fun `external-parent-of-class-containing-file`() {
        // given
        val sut = getSnippetFile("external-parent-of-class-containing-file")
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
    fun `external-parent-of-interface-containing-file`() {
        // given
        val sut = getSnippetFile("external-parent-of-interface-containing-file")
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
    fun `external-parent-of-object-containing-file`() {
        // given
        val sut = getSnippetFile("external-parent-of-object-containing-file")
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
