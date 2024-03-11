package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationTest {
    @Test
    fun `parent-of-class-to-string`() {
        // given
        val sut = getSnippetFile("parent-of-class-to-string")
            .classes()
            .parents
            .first()

        // then
        sut.toString() shouldBeEqualTo "SampleParentClass"
    }

    @Test
    fun `parent-of-interface-to-string`() {
        // given
        val sut = getSnippetFile("parent-of-interface-to-string")
            .interfaces()
            .parents
            .first()

        // then
        sut.toString() shouldBeEqualTo "SampleParentInterface"
    }

    @Test
    fun `parent-of-object-to-string`() {
        // given
        val sut = getSnippetFile("parent-of-object-to-string")
            .objects()
            .parents
            .first()

        // then
        sut.toString() shouldBeEqualTo "SampleParentClass"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparent/snippet/forgeneral/", fileName)
}
