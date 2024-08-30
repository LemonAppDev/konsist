package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoTextProviderTest {
    @Test
    fun `enum-const-text`() {
        // given
        val sut =
            getSnippetFile("enum-const-text")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        sut.text shouldBeEqualTo "SAMPLE_CONSTANT_1(sampleArgument = 0)"
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koenumconstant/snippet/forkotextprovider/", fileName)
}
