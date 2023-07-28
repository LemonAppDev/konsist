package com.lemonappdev.konsist.core.declaration.kokdocdeclaration.kokdoctagsprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY_SETTER
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocDeclarationForPropertySetterTagTest {
    @Test
    fun `property-with-property-setter-tag`() {
        // given
        val sut = getSnippetFile("property-with-property-setter-tag")
            .properties(includeNested = true)
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.propertySetterTag?.name shouldBeEqualTo PROPERTY_SETTER
            it?.propertySetterTag?.description shouldBeEqualTo "Sets the value of the [name] property."
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdocdeclaration/kokdoctagsprovider/snippet/forpropertysettertag/", fileName)
}
