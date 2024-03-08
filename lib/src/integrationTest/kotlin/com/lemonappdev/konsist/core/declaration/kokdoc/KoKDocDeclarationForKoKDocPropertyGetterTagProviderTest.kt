package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY_GETTER
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocDeclarationForKoKDocPropertyGetterTagProviderTest {
    @Test
    fun `kdoc-without-property-getter-tag`() {
        // given
        val sut =
            getSnippetFile("kdoc-without-property-getter-tag")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.propertyGetterTag shouldBeEqualTo null
            it?.hasPropertyGetterTag shouldBeEqualTo false
        }
    }

    @Test
    fun `property-with-property-getter-tag`() {
        // given
        val sut =
            getSnippetFile("property-with-property-getter-tag")
                .properties(includeNested = true)
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.propertyGetterTag?.name shouldBeEqualTo PROPERTY_GETTER
            it?.propertyGetterTag?.description shouldBeEqualTo "Retrieves the value of the [name] property."
            it?.hasPropertyGetterTag shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdoc/snippet/forkokdocpropertygettertagprovider/", fileName)
}
