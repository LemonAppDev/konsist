package com.lemon.konsist.core.declaration.kointerface

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceTest {
    @Test
    fun `interface-has-actual-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-actual-modifier")
            .interfaces()
            .first()

        // then
        sut.hasActualModifier() shouldBeEqualTo true
    }

    @Test
    fun `interface-has-expect-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-expect-modifier")
            .interfaces()
            .first()

        // then
        sut.hasExpectModifier() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kointerface/snippet/", fileName)
}
