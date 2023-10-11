package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationTest {
    @Test
    fun `parent-from-file-of-class-to-string`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-class-to-string")
            .classes()
            .parents
            .first()

        // then
        sut.toString() shouldBeEqualTo "SampleParentClass"
    }

    @Test
    fun `parent-from-file-of-interface-to-string`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-interface-to-string")
            .interfaces()
            .parents
            .first()

        // then
        sut.toString() shouldBeEqualTo "SampleParentInterface"
    }

    @Test
    fun `parent-from-file-of-object-to-string`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-object-to-string")
            .objects()
            .parents
            .first()

        // then
        sut.toString() shouldBeEqualTo "SampleParentClass"
    }

    @Test
    fun `parent-from-import-of-class-to-string`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-class-to-string")
            .classes()
            .parents
            .first()

        // then
        sut.toString() shouldBeEqualTo "SampleParentClass"
    }

    @Test
    fun `parent-from-import-of-interface-to-string`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-interface-to-string")
            .interfaces()
            .parents
            .first()

        // then
        sut.toString() shouldBeEqualTo "SampleParentInterface"
    }

    @Test
    fun `parent-from-import-of-object-to-string`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-object-to-string")
            .objects()
            .parents
            .first()

        // then
        sut.toString() shouldBeEqualTo "SampleParentClass"
    }

    @Test
    fun `external-parent-of-class-to-string`() {
        // given
        val sut = getSnippetFile("external-parent-of-class-to-string")
            .classes()
            .parents
            .first()

        // then
        sut.toString() shouldBeEqualTo "ExternalParent"
    }

    @Test
    fun `external-parent-of-interface-to-string`() {
        // given
        val sut = getSnippetFile("external-parent-of-interface-to-string")
            .interfaces()
            .parents
            .first()

        // then
        sut.toString() shouldBeEqualTo "ExternalParent"
    }

    @Test
    fun `external-parent-of-object-to-string`() {
        // given
        val sut = getSnippetFile("external-parent-of-object-to-string")
            .objects()
            .parents
            .first()

        // then
        sut.toString() shouldBeEqualTo "ExternalParent"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparent/snippet/forgeneral/", fileName)
}
