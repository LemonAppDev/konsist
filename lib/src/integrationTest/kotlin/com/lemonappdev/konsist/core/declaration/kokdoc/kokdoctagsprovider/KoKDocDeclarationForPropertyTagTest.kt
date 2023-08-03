package com.lemonappdev.konsist.core.declaration.kokdoc.kokdoctagsprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoKDocDeclarationForPropertyTagTest {
    @Test
    fun `class-with-property-tag`() {
        // given
        val sut = getSnippetFile("class-with-property-tag")
            .classes()
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.propertyTags?.shouldHaveSize(2)
            it?.propertyTags?.get(0)?.name shouldBeEqualTo PROPERTY
            it?.propertyTags?.get(0)?.value shouldBeEqualTo "sampleProperty1"
            it?.propertyTags?.get(0)?.description shouldBeEqualTo "The first property of the class."
            it?.propertyTags?.get(1)?.name shouldBeEqualTo PROPERTY
            it?.propertyTags?.get(1)?.value shouldBeEqualTo "sampleProperty2"
            it?.propertyTags?.get(1)?.description shouldBeEqualTo "The second property of the class."
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdocdeclaration/kokdoctagsprovider/snippet/forpropertytag/", fileName)
}
