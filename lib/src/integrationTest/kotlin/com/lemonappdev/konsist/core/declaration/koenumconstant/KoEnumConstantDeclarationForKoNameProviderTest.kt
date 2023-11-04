package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoNameProviderTest {
    @Test
    fun `enum-const`() {
        // given
        val sut =
            getSnippetFile("enum-const")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SAMPLE_CONSTANT_1"
            hasNameStartingWith("SAMPLE") shouldBeEqualTo true
            hasNameStartingWith("OTHER") shouldBeEqualTo false
            hasNameEndingWith("T_1") shouldBeEqualTo true
            hasNameEndingWith("OTHER") shouldBeEqualTo false
            hasNameContaining("LE_CO") shouldBeEqualTo true
            hasNameContaining("LECO") shouldBeEqualTo false
            hasNameMatching(Regex(".*")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koenumconstant/snippet/forkonameprovider/", fileName)
}
