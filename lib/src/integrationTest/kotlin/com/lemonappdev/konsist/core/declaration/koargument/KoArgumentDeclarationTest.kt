package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.annotations
import com.lemonappdev.konsist.api.ext.list.arguments
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationTest {
    @Test
    fun `argument-in-enum-const-to-string`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-to-string")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        sut.toString() shouldBeEqualTo sut.locationWithText
    }

    @Test
    fun `argument-in-annotation-to-string`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-to-string")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        sut.toString() shouldBeEqualTo sut.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koargument/snippet/forgeneral/", fileName)
}
