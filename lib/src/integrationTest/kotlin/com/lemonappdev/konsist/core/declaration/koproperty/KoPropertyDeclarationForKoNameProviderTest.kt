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
            hasNameStartingWith("Other") shouldBeEqualTo false
            hasNameStartingWith("SAMPLE", ignoreCase = false) shouldBeEqualTo false
            hasNameStartingWith("SAMPLE", ignoreCase = true) shouldBeEqualTo true
            hasNameEndingWith("erty") shouldBeEqualTo true
            hasNameEndingWith("other") shouldBeEqualTo false
            hasNameEndingWith("ERTY", ignoreCase = false) shouldBeEqualTo false
            hasNameEndingWith("ERTY", ignoreCase = true) shouldBeEqualTo true
            hasNameContaining("lePro") shouldBeEqualTo true
            hasNameContaining("other") shouldBeEqualTo false
            hasNameContaining("lepro", ignoreCase = false) shouldBeEqualTo false
            hasNameContaining("lepro", ignoreCase = true) shouldBeEqualTo true
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koproperty/snippet/forkonameprovider/", fileName)
}
