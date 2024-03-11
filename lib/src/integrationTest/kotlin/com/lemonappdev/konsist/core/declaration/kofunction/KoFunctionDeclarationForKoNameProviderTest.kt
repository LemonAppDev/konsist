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
            hasNameStartingWith("other") shouldBeEqualTo false
            hasNameEndingWith("tion") shouldBeEqualTo true
            hasNameEndingWith("other") shouldBeEqualTo false
            hasNameContaining("leFun") shouldBeEqualTo true
            hasNameContaining("lefun") shouldBeEqualTo false
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofunction/snippet/forkonameprovider/", fileName)
}
