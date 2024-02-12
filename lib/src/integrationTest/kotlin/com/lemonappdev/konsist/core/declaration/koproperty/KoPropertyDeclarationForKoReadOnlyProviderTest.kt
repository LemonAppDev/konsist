package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoReadOnlyProviderTest {
    @Test
    fun `property-is-val`() {
        // given
        val sut = getSnippetFile("property-is-val")
            .properties()
            .first()

        // then
        sut.isReadOnly shouldBeEqualTo true
    }

    @Test
    fun `property-is-var`() {
        // given
        val sut = getSnippetFile("property-is-var")
            .properties()
            .first()

        // then
        sut.isReadOnly shouldBeEqualTo false
    }

    @Test
    fun `property-is-lateinit-var`() {
        // given
        val sut = getSnippetFile("property-is-lateinit-var")
            .properties()
            .first()

        // then
        sut.isReadOnly shouldBeEqualTo false
    }

    @Test
    fun `property-is-const-val`() {
        // given
        val sut = getSnippetFile("property-is-const-val")
            .properties()
            .first()

        // then
        sut.isReadOnly shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koproperty/snippet/forkoreadonlyprovider/", fileName)
}
