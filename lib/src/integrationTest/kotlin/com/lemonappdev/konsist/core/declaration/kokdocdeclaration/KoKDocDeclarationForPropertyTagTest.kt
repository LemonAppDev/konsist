package com.lemonappdev.konsist.core.declaration.kokdocdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoKDocTag.AUTHOR
import com.lemonappdev.konsist.api.KoKDocTag.CONSTRUCTOR
import com.lemonappdev.konsist.api.KoKDocTag.EXCEPTION
import com.lemonappdev.konsist.api.KoKDocTag.PARAM
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY_GETTER
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY_SETTER
import com.lemonappdev.konsist.api.KoKDocTag.RECEIVER
import com.lemonappdev.konsist.api.KoKDocTag.RETURN
import com.lemonappdev.konsist.api.KoKDocTag.SAMPLE
import com.lemonappdev.konsist.api.KoKDocTag.SEE
import com.lemonappdev.konsist.api.KoKDocTag.SINCE
import com.lemonappdev.konsist.api.KoKDocTag.SUPPRESS
import com.lemonappdev.konsist.api.KoKDocTag.THROWS
import com.lemonappdev.konsist.api.KoKDocTag.VERSION
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

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
        TestSnippetProvider.getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/forpropertytag/", fileName)
}
