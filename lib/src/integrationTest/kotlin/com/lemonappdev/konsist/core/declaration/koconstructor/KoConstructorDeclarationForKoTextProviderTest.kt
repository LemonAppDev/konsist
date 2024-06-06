package com.lemonappdev.konsist.core.declaration.koconstructor

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.awt.SystemColor.text

class KoConstructorDeclarationForKoTextProviderTest {
    @Test
    fun `constructor-text`() {
        // given
        val sut =
            getSnippetFile("constructor-text")
                .classes()
                .first()
                .constructors
                .first()

        // then
        assertSoftly(sut) {
            text shouldBeEqualTo "(val sampleParameter: Int)"
            hasTextStartingWith("(val ") shouldBeEqualTo true
            hasTextStartingWith("Other") shouldBeEqualTo false
            hasTextEndingWith(": Int)") shouldBeEqualTo true
            hasTextEndingWith("other") shouldBeEqualTo false
            hasTextContaining("sampleParameter: ") shouldBeEqualTo true
            hasTextContaining("anno") shouldBeEqualTo false
            hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koconstructor/snippet/forkotextprovider/", fileName)
}
