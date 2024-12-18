package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoNameProviderTest {
    @Test
    fun `interface-name`() {
        // given
        val sut =
            getSnippetFile("interface-name")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleInterface"
            hasNameStartingWith("Sample") shouldBeEqualTo true
            hasNameStartingWith("Other") shouldBeEqualTo false
            hasNameStartingWith("sample", ignoreCase = false) shouldBeEqualTo false
            hasNameStartingWith("sample", ignoreCase = true) shouldBeEqualTo true
            hasNameEndingWith("face") shouldBeEqualTo true
            hasNameEndingWith("other") shouldBeEqualTo false
            hasNameEndingWith("FACE", ignoreCase = false) shouldBeEqualTo false
            hasNameEndingWith("FACE", ignoreCase = true) shouldBeEqualTo true
            hasNameContaining("leInt") shouldBeEqualTo true
            hasNameContaining("other") shouldBeEqualTo false
            hasNameContaining("leint", ignoreCase = false) shouldBeEqualTo false
            hasNameContaining("leint", ignoreCase = true) shouldBeEqualTo true
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kointerface/snippet/forkonameprovider/", fileName)
}
