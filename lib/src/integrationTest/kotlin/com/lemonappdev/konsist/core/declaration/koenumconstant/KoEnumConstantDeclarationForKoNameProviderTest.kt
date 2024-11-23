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
            hasNameStartingWith("sample", ignoreCase = false) shouldBeEqualTo false
            hasNameStartingWith("sample", ignoreCase = true) shouldBeEqualTo true
            hasNameEndingWith("T_1") shouldBeEqualTo true
            hasNameEndingWith("OTHER") shouldBeEqualTo false
            hasNameEndingWith("t_1", ignoreCase = false) shouldBeEqualTo false
            hasNameEndingWith("t_1", ignoreCase = true) shouldBeEqualTo true
            hasNameContaining("LE_CO") shouldBeEqualTo true
            hasNameContaining("LECO") shouldBeEqualTo false
            hasNameContaining("le_co", ignoreCase = false) shouldBeEqualTo false
            hasNameContaining("le_co", ignoreCase = true) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koenumconstant/snippet/forkonameprovider/", fileName)
}
