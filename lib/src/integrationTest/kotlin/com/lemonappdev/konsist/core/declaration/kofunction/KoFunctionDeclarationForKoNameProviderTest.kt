package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoNameProviderTest {
    @Test
    fun `function-name`() {
        // given
        val sut =
            getSnippetFile("function-name")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "sampleFunction"
            hasNameStartingWith("sample") shouldBeEqualTo true
            hasNameStartingWith("Other") shouldBeEqualTo false
            hasNameStartingWith("SAMPLE", ignoreCase = false) shouldBeEqualTo false
            hasNameStartingWith("SAMPLE", ignoreCase = true) shouldBeEqualTo true
            hasNameEndingWith("tion") shouldBeEqualTo true
            hasNameEndingWith("other") shouldBeEqualTo false
            hasNameEndingWith("TION", ignoreCase = false) shouldBeEqualTo false
            hasNameEndingWith("TION", ignoreCase = true) shouldBeEqualTo true
            hasNameContaining("leFun") shouldBeEqualTo true
            hasNameContaining("other") shouldBeEqualTo false
            hasNameContaining("lefun", ignoreCase = false) shouldBeEqualTo false
            hasNameContaining("lefun", ignoreCase = true) shouldBeEqualTo true
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofunction/snippet/forkonameprovider/", fileName)
}
