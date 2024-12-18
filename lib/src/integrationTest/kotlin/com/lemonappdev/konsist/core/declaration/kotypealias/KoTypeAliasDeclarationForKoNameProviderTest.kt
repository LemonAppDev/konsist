package com.lemonappdev.konsist.core.declaration.kotypealias

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoNameProviderTest {
    @Test
    fun `typealias-name`() {
        // given
        val sut =
            getSnippetFile("typealias-name")
                .typeAliases
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleTypeAlias"
            hasNameStartingWith("Sample") shouldBeEqualTo true
            hasNameStartingWith("Other") shouldBeEqualTo false
            hasNameStartingWith("sample", ignoreCase = false) shouldBeEqualTo false
            hasNameStartingWith("sample", ignoreCase = true) shouldBeEqualTo true
            hasNameEndingWith("lias") shouldBeEqualTo true
            hasNameEndingWith("other") shouldBeEqualTo false
            hasNameEndingWith("LIAS", ignoreCase = false) shouldBeEqualTo false
            hasNameEndingWith("LIAS", ignoreCase = true) shouldBeEqualTo true
            hasNameContaining("Type") shouldBeEqualTo true
            hasNameContaining("other") shouldBeEqualTo false
            hasNameContaining("type", ignoreCase = false) shouldBeEqualTo false
            hasNameContaining("type", ignoreCase = true) shouldBeEqualTo true
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kotypealias/snippet/forkonameprovider/", fileName)
}
