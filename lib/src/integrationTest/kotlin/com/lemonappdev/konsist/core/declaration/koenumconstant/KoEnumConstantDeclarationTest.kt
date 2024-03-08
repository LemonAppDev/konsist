package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationTest {
    @Test
    fun `enum-const-to-string`() {
        // given
        val sut =
            getSnippetFile("enum-const-to-string")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        sut.toString() shouldBeEqualTo "SAMPLE_CONSTANT_1"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koenumconstant/snippet/forgeneral/", fileName)
}
