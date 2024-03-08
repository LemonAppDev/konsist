package com.lemonappdev.konsist.api.ext.provider.koparameters

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocParamTags
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParametersProviderExtTest {
    @Test
    fun `declaration-with-parameters-has-valid-kdoc-param-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-with-parameters-has-valid-kdoc-param-tag")
                .declarationsOf<KoParametersProvider>()
                .first()

        // then
        sut.hasValidKDocParamTags() shouldBeEqualTo true
    }

    @Test
    fun `declaration-with-parameters-has-other-names-for-parameter-and-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-with-parameters-has-other-names-for-parameter-and-tag")
                .declarationsOf<KoParametersProvider>()
                .first()

        // then
        sut.hasValidKDocParamTags() shouldBeEqualTo false
    }

    @Test
    fun `declaration-with-parameters-has-double-param-tag-for-parameter`() {
        // given
        val sut =
            getSnippetFile("declaration-with-parameters-has-double-param-tag-for-parameter")
                .declarationsOf<KoParametersProvider>()
                .first()

        // then
        sut.hasValidKDocParamTags() shouldBeEqualTo false
    }

    @Test
    fun `declaration-with-parameters-has-more-parameters-than-tags`() {
        // given
        val sut =
            getSnippetFile("declaration-with-parameters-has-more-parameters-than-tags")
                .declarationsOf<KoParametersProvider>()
                .first()

        // then
        sut.hasValidKDocParamTags() shouldBeEqualTo false
    }

    @Test
    fun `declaration-with-parameters-has-more-tags-than-parameters`() {
        // given
        val sut =
            getSnippetFile("declaration-with-parameters-has-more-tags-than-parameters")
                .declarationsOf<KoParametersProvider>()
                .first()

        // then
        sut.hasValidKDocParamTags() shouldBeEqualTo false
    }

    @Test
    fun `declaration-with-parameters-has-no-kdoc`() {
        // given
        val sut =
            getSnippetFile("declaration-with-parameters-has-no-kdoc")
                .declarationsOf<KoParametersProvider>()
                .first()

        // then
        sut.hasValidKDocParamTags() shouldBeEqualTo false
    }

    @Test
    fun `primary-constructor-with-parameters-has-valid-kdoc-param-tag`() {
        // given
        val sut =
            getSnippetFile("primary-constructor-with-parameters-has-valid-kdoc-param-tag")
                .classes()
                .first()
                .primaryConstructor

        // then
        sut?.hasValidKDocParamTags() shouldBeEqualTo true
    }

    @Test
    fun `primary-constructor-with-parameters-has-other-names-for-parameter-and-tag`() {
        // given
        val sut =
            getSnippetFile("primary-constructor-with-parameters-has-other-names-for-parameter-and-tag")
                .classes()
                .first()
                .primaryConstructor

        // then
        sut?.hasValidKDocParamTags() shouldBeEqualTo false
    }

    @Test
    fun `primary-constructor-with-parameters-has-double-param-tag-for-parameter`() {
        // given
        val sut =
            getSnippetFile("primary-constructor-with-parameters-has-double-param-tag-for-parameter")
                .classes()
                .first()
                .primaryConstructor

        // then
        sut?.hasValidKDocParamTags() shouldBeEqualTo false
    }

    @Test
    fun `primary-constructor-with-parameters-has-more-parameters-than-tags`() {
        // given
        val sut =
            getSnippetFile("primary-constructor-with-parameters-has-more-parameters-than-tags")
                .classes()
                .first()
                .primaryConstructor

        // then
        sut?.hasValidKDocParamTags() shouldBeEqualTo false
    }

    @Test
    fun `primary-constructor-with-parameters-has-more-tags-than-parameters`() {
        // given
        val sut =
            getSnippetFile("primary-constructor-with-parameters-has-more-tags-than-parameters")
                .classes()
                .first()
                .primaryConstructor

        // then
        sut?.hasValidKDocParamTags() shouldBeEqualTo false
    }

    @Test
    fun `primary-constructor-with-parameters-has-no-kdoc`() {
        // given
        val sut =
            getSnippetFile("primary-constructor-with-parameters-has-no-kdoc")
                .classes()
                .first()
                .primaryConstructor

        // then
        sut?.hasValidKDocParamTags() shouldBeEqualTo false
    }

    @Test
    fun `declaration-without-parameters-has-valid-kdoc-param-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-without-parameters-has-valid-kdoc-param-tag")
                .declarationsOf<KoParametersProvider>()
                .first()

        // then
        sut.hasValidKDocParamTags() shouldBeEqualTo true
    }

    @Test
    fun `declaration-without-parameters-has-param-tag-in-kdoc`() {
        // given
        val sut =
            getSnippetFile("declaration-without-parameters-has-param-tag-in-kdoc")
                .declarationsOf<KoParametersProvider>()
                .first()

        // then
        sut.hasValidKDocParamTags() shouldBeEqualTo false
    }

    @Test
    fun `declaration-without-parameters-has-no-kdoc`() {
        // given
        val sut =
            getSnippetFile("declaration-without-parameters-has-no-kdoc")
                .declarationsOf<KoParametersProvider>()
                .first()

        // then
        sut.hasValidKDocParamTags() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) = TestSnippetProvider.getSnippetKoScope("api/ext/provider/koparameters/snippet/", fileName)
}
