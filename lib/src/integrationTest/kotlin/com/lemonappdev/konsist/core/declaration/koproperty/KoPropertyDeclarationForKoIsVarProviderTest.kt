package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.properties
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoIsVarProviderTest {
    @Test
    fun `property-has-no-var-keyword`() {
        // given
        val sut =
            getSnippetFile("property-has-no-var-keyword")
                .properties()
                .first()

        // then
        sut.isVar shouldBeEqualTo false
    }

    @Test
    fun `property-has-var-keyword`() {
        // given
        val sut =
            getSnippetFile("property-has-var-keyword")
                .properties()
                .first()

        // then
        sut.isVar shouldBeEqualTo true
    }

    @Test
    fun `property-defined-in-constructor-has-no-var-keyword`() {
        // given
        val sut =
            getSnippetFile("property-defined-in-constructor-has-no-var-keyword")
                .classes()
                .properties()
                .first()

        // then
        sut.isVar shouldBeEqualTo false
    }

    @Test
    fun `property-defined-in-constructor-has-var-keyword`() {
        // given
        val sut =
            getSnippetFile("property-defined-in-constructor-has-var-keyword")
                .classes()
                .properties()
                .first()

        // then
        sut.isVar shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koproperty/snippet/forkoisvarprovider/",
            fileName,
        )
}
