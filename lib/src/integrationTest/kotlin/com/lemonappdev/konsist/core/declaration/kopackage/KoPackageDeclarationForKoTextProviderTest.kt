package com.lemonappdev.konsist.core.declaration.kopackage

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageDeclarationForKoTextProviderTest {
    @Test
    fun `package-text`() {
        // given
        val sut =
            getSnippetFile("package-text")
                .packages
                .first()

        // then
        assertSoftly(sut) {
            text shouldBeEqualTo "package com.samplepackage"
            hasTextStartingWith("package ") shouldBeEqualTo true
            hasTextStartingWith("Other") shouldBeEqualTo false
            hasTextEndingWith(".samplepackage") shouldBeEqualTo true
            hasTextEndingWith("other") shouldBeEqualTo false
            hasTextContaining("com.sample") shouldBeEqualTo true
            hasTextContaining("anno") shouldBeEqualTo false
            hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kopackage/snippet/forkotextprovider/", fileName)
}
