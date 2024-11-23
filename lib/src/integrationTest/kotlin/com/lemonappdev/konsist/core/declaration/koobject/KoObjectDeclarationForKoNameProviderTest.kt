package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoNameProviderTest {
    @Test
    fun `object`() {
        // given
        val sut =
            getSnippetFile("object")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleObject"
            hasNameStartingWith("Sample") shouldBeEqualTo true
            hasNameStartingWith("Other") shouldBeEqualTo false
            hasNameStartingWith("sample", ignoreCase = false) shouldBeEqualTo false
            hasNameStartingWith("sample", ignoreCase = true) shouldBeEqualTo true
            hasNameEndingWith("ject") shouldBeEqualTo true
            hasNameEndingWith("other") shouldBeEqualTo false
            hasNameEndingWith("JECT", ignoreCase = false) shouldBeEqualTo false
            hasNameEndingWith("JECT", ignoreCase = true) shouldBeEqualTo true
            hasNameContaining("leObj") shouldBeEqualTo true
            hasNameContaining("other") shouldBeEqualTo false
            hasNameContaining("leobj", ignoreCase = false) shouldBeEqualTo false
            hasNameContaining("leobj", ignoreCase = true) shouldBeEqualTo true
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `companion-object-with-name`() {
        // given
        val sut =
            getSnippetFile("companion-object-with-name")
                .objects(includeNested = true)
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleObject"
            hasNameStartingWith("Sample") shouldBeEqualTo true
            hasNameStartingWith("Other") shouldBeEqualTo false
            hasNameStartingWith("sample", ignoreCase = false) shouldBeEqualTo false
            hasNameStartingWith("sample", ignoreCase = true) shouldBeEqualTo true
            hasNameEndingWith("ject") shouldBeEqualTo true
            hasNameEndingWith("other") shouldBeEqualTo false
            hasNameEndingWith("JECT", ignoreCase = false) shouldBeEqualTo false
            hasNameEndingWith("JECT", ignoreCase = true) shouldBeEqualTo true
            hasNameContaining("leObj") shouldBeEqualTo true
            hasNameContaining("other") shouldBeEqualTo false
            hasNameContaining("leobj", ignoreCase = false) shouldBeEqualTo false
            hasNameContaining("leobj", ignoreCase = true) shouldBeEqualTo true
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `companion-object-without-name`() {
        // given
        val sut =
            getSnippetFile("companion-object-without-name")
                .objects(includeNested = true)
                .first()

        // then
        sut.name shouldBeEqualTo "Companion"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkonameprovider/", fileName)
}
