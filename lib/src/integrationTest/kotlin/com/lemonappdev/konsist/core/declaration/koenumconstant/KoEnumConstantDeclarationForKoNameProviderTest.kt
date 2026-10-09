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
            name shouldBeEqualTo "FIXTURE_CONSTANT_1"
            hasName("FIXTURE_CONSTANT_1") shouldBeEqualTo true
            hasName("OTHER") shouldBeEqualTo false
            hasName("fixture_constant_1", ignoreCase = false) shouldBeEqualTo false
            hasName("fixture_constant_1", ignoreCase = true) shouldBeEqualTo true
            hasNameStartingWith("FIXTURE") shouldBeEqualTo true
            hasNameStartingWith("OTHER") shouldBeEqualTo false
            hasNameStartingWith("fixture", ignoreCase = false) shouldBeEqualTo false
            hasNameStartingWith("fixture", ignoreCase = true) shouldBeEqualTo true
            hasNameEndingWith("T_1") shouldBeEqualTo true
            hasNameEndingWith("OTHER") shouldBeEqualTo false
            hasNameEndingWith("t_1", ignoreCase = false) shouldBeEqualTo false
            hasNameEndingWith("t_1", ignoreCase = true) shouldBeEqualTo true
            hasNameContaining("RE_CO") shouldBeEqualTo true
            hasNameContaining("LECO") shouldBeEqualTo false
            hasNameContaining("re_co", ignoreCase = false) shouldBeEqualTo false
            hasNameContaining("re_co", ignoreCase = true) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koenumconstant/snippet/forkonameprovider/", fileName)
}
