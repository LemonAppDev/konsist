package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoNameProviderTest {
    @Test
    fun `property-name`() {
        // given
        val sut =
            getSnippetFile("property-name")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "sampleProperty"
            hasNameStartingWith("sample") shouldBeEqualTo true
            hasNameStartingWith("other") shouldBeEqualTo false
            hasNameEndingWith("erty") shouldBeEqualTo true
            hasNameEndingWith("other") shouldBeEqualTo false
            hasNameContaining("lePro") shouldBeEqualTo true
            hasNameContaining("lepro") shouldBeEqualTo false
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koproperty/snippet/forkonameprovider/", fileName)
}
