package com.lemonappdev.konsist.core.declaration.kocompanionobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoCompanionObjectDeclarationForKoHasDefaultNameProviderTest {
    @Test
    fun `companion-object-has-default-name`() {
        // given
        val sut =
            getSnippetFile("companion-object-has-default-name")
                .classes()
                .first()
                .companionObject

        // then
        sut?.hasDefaultName shouldBeEqualTo true
    }

    @Test
    fun `companion-object-has-given-name`() {
        // given
        val sut =
            getSnippetFile("companion-object-has-given-name")
                .classes()
                .first()
                .companionObject

        // then
        sut?.hasDefaultName shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kocompanionobject/snippet/forkohasdefaultnameprovider/", fileName)
}
