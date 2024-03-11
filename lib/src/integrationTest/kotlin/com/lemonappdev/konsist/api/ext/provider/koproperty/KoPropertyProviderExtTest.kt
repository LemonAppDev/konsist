package com.lemonappdev.konsist.api.ext.provider.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocPropertyTags
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyProviderExtTest {
    @Test
    fun `declaration-with-properties-has-valid-kdoc-property-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-with-properties-has-valid-kdoc-property-tag")
                .declarationsOf<KoPropertyProvider>()
                .filterNot { it is KoFileDeclaration }
                .first()

        // then
        sut.hasValidKDocPropertyTags() shouldBeEqualTo true
    }

    @Test
    fun `declaration-with-properties-has-other-names-for-property-and-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-with-properties-has-other-names-for-property-and-tag")
                .declarationsOf<KoPropertyProvider>()
                .filterNot { it is KoFileDeclaration }
                .first()

        // then
        sut.hasValidKDocPropertyTags() shouldBeEqualTo false
    }

    @Test
    fun `declaration-with-properties-has-double-property-tag-for-property`() {
        // given
        val sut =
            getSnippetFile("declaration-with-properties-has-double-property-tag-for-property")
                .declarationsOf<KoPropertyProvider>()
                .filterNot { it is KoFileDeclaration }
                .first()

        // then
        sut.hasValidKDocPropertyTags() shouldBeEqualTo false
    }

    @Test
    fun `declaration-with-properties-has-more-properties-than-tags`() {
        // given
        val sut =
            getSnippetFile("declaration-with-properties-has-more-properties-than-tags")
                .declarationsOf<KoPropertyProvider>()
                .filterNot { it is KoFileDeclaration }
                .first()

        // then
        sut.hasValidKDocPropertyTags() shouldBeEqualTo false
    }

    @Test
    fun `declaration-with-properties-has-more-tags-than-properties`() {
        // given
        val sut =
            getSnippetFile("declaration-with-properties-has-more-tags-than-properties")
                .declarationsOf<KoPropertyProvider>()
                .filterNot { it is KoFileDeclaration }
                .first()

        // then
        sut.hasValidKDocPropertyTags() shouldBeEqualTo false
    }

    @Test
    fun `declaration-with-properties-has-no-kdoc`() {
        // given
        val sut =
            getSnippetFile("declaration-with-properties-has-no-kdoc")
                .declarationsOf<KoPropertyProvider>()
                .filterNot { it is KoFileDeclaration }
                .first()

        // then
        sut.hasValidKDocPropertyTags() shouldBeEqualTo false
    }

    @Test
    fun `declaration-without-properties-has-valid-kdoc-property-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-without-properties-has-valid-kdoc-property-tag")
                .declarationsOf<KoPropertyProvider>()
                .filterNot { it is KoFileDeclaration }
                .first()

        // then
        sut.hasValidKDocPropertyTags() shouldBeEqualTo true
    }

    @Test
    fun `declaration-without-properties-has-property-tag-in-kdoc`() {
        // given
        val sut =
            getSnippetFile("declaration-without-properties-has-property-tag-in-kdoc")
                .declarationsOf<KoPropertyProvider>()
                .filterNot { it is KoFileDeclaration }
                .first()

        // then
        sut.hasValidKDocPropertyTags() shouldBeEqualTo false
    }

    @Test
    fun `declaration-without-properties-has-no-kdoc`() {
        // given
        val sut =
            getSnippetFile("declaration-without-properties-has-no-kdoc")
                .declarationsOf<KoPropertyProvider>()
                .filterNot { it is KoFileDeclaration }
                .first()

        // then
        sut.hasValidKDocPropertyTags() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) = TestSnippetProvider.getSnippetKoScope("api/ext/provider/koproperty/snippet/", fileName)
}
