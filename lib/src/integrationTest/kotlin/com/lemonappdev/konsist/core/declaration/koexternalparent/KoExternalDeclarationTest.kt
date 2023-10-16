package com.lemonappdev.konsist.core.declaration.koexternalparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.externalParents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExternalDeclarationTest {
    @Test
    fun `external-parent-of-class-to-string`() {
        // given
        val sut = getSnippetFile("external-parent-of-class-to-string")
            .classes()
            .externalParents
            .first()

        // then
        sut.toString() shouldBeEqualTo "SampleExternalClass"
    }

    @Test
    fun `external-parent-of-interface-to-string`() {
        // given
        val sut = getSnippetFile("external-parent-of-interface-to-string")
            .interfaces()
            .externalParents
            .first()

        // then
        sut.toString() shouldBeEqualTo "SampleExternalInterface"
    }

    @Test
    fun `external-parent-of-object-to-string`() {
        // given
        val sut = getSnippetFile("external-parent-of-object-to-string")
            .objects()
            .externalParents
            .first()

        // then
        sut.toString() shouldBeEqualTo "SampleExternalClass"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koexternalparent/snippet/forgeneral/", fileName)
}
