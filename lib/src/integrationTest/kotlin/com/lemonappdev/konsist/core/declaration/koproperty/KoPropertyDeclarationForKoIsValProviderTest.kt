package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.properties
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoIsValProviderTest {
    @Test
    fun `property-has-no-val-keyword`() {
        // given
        val sut =
            getSnippetFile("property-has-no-val-keyword")
                .properties()
                .first()

        // then
        sut.isVal shouldBeEqualTo false
    }

    @Test
    fun `property-has-val-keyword`() {
        // given
        val sut =
            getSnippetFile("property-has-val-keyword")
                .properties()
                .first()

        // then
        sut.isVal shouldBeEqualTo true
    }

    @Test
    fun `property-defined-in-constructor-has-no-val-keyword`() {
        // given
        val sut =
            getSnippetFile("property-defined-in-constructor-has-no-val-keyword")
                .classes()
                .properties()
                .first()

        // then
        sut.isVal shouldBeEqualTo false
    }

    @Test
    fun `property-defined-in-constructor-has-val-keyword`() {
        // given
        val sut =
            getSnippetFile("property-defined-in-constructor-has-val-keyword")
                .classes()
                .properties()
                .first()

        // then
        sut.isVal shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koproperty/snippet/forkoisvalprovider/",
            fileName,
        )
}
