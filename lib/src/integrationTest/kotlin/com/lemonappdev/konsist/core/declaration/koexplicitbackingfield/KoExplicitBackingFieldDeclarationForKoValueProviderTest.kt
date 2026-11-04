package com.lemonappdev.konsist.core.declaration.koexplicitbackingfield

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExplicitBackingFieldDeclarationForKoValueProviderTest {
    @Test
    fun `explicit-backing-field-has-value`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-has-value")
                .properties()
                .first()
                .explicitBackingField

        // then
        assertSoftly(sut) {
            it?.value shouldBeEqualTo "mutableListOf(0)"
            it?.hasValue() shouldBeEqualTo true
            it?.hasValue("mutableListOf(0)") shouldBeEqualTo true
            it?.hasValue("mutableListOf(1)") shouldBeEqualTo false
        }
    }

    @Test
    fun `explicit-backing-field-has-string-value`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-has-string-value")
                .properties()
                .first()
                .explicitBackingField

        // then
        sut?.value shouldBeEqualTo "text"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koexplicitbackingfield/snippet/forkovalueprovider/", fileName)
}
