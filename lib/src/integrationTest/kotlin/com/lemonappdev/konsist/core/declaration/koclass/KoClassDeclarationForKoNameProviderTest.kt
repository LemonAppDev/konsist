package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoNameProviderTest {
    @Test
    fun `class`() {
        // given
        val sut =
            getSnippetFile("class")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleClass"
            hasNameStartingWith("Sample") shouldBeEqualTo true
            hasNameStartingWith("Other") shouldBeEqualTo false
            hasNameStartingWith("sample", ignoreCase = false) shouldBeEqualTo false
            hasNameStartingWith("sample", ignoreCase = true) shouldBeEqualTo true
            hasNameEndingWith("ass") shouldBeEqualTo true
            hasNameEndingWith("other") shouldBeEqualTo false
            hasNameEndingWith("ASS", ignoreCase = false) shouldBeEqualTo false
            hasNameEndingWith("ASS", ignoreCase = true) shouldBeEqualTo true
            hasNameContaining("leCl") shouldBeEqualTo true
            hasNameContaining("other") shouldBeEqualTo false
            hasNameContaining("lecl", ignoreCase = false) shouldBeEqualTo false
            hasNameContaining("lecl", ignoreCase = true) shouldBeEqualTo true
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkonameprovider/", fileName)
}
