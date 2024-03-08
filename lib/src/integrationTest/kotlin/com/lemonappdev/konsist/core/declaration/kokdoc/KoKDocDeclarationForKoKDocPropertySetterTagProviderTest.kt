package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY_SETTER
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocDeclarationForKoKDocPropertySetterTagProviderTest {
    @Test
    fun `kdoc-without-property-setter-tag`() {
        // given
        val sut =
            getSnippetFile("kdoc-without-property-setter-tag")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.propertySetterTag shouldBeEqualTo null
            it?.hasPropertySetterTag shouldBeEqualTo false
        }
    }

    @Test
    fun `property-with-property-setter-tag`() {
        // given
        val sut =
            getSnippetFile("property-with-property-setter-tag")
                .properties(includeNested = true)
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.propertySetterTag?.name shouldBeEqualTo PROPERTY_SETTER
            it?.propertySetterTag?.description shouldBeEqualTo "Sets the value of the [name] property."
            it?.hasPropertySetterTag shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdoc/snippet/forkokdocpropertysettertagprovider/", fileName)
}
