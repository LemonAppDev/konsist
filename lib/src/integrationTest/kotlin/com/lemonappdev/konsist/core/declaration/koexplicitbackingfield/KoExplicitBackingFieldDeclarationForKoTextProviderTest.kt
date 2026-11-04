package com.lemonappdev.konsist.core.declaration.koexplicitbackingfield

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExplicitBackingFieldDeclarationForKoTextProviderTest {
    @Test
    fun `explicit-backing-field-text`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-text")
                .properties()
                .first()
                .explicitBackingField

        // then
        assertSoftly(sut) {
            it?.text shouldBeEqualTo "field = mutableListOf(0)"
            it?.hasTextStartingWith("field =") shouldBeEqualTo true
            it?.hasTextStartingWith("Other") shouldBeEqualTo false
            it?.hasTextEndingWith("(0)") shouldBeEqualTo true
            it?.hasTextEndingWith("other") shouldBeEqualTo false
            it?.hasTextContaining("mutableListOf") shouldBeEqualTo true
            it?.hasTextContaining("anno") shouldBeEqualTo false
            it?.hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            it?.hasTextMatching(Regex("[1-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koexplicitbackingfield/snippet/forkotextprovider/", fileName)
}
