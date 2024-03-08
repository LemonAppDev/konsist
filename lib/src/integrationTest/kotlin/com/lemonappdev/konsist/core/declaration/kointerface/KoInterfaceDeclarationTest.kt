package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationTest {
    @Test
    fun `interface-to-string`() {
        // given
        val sut =
            getSnippetFile("interface-to-string")
                .interfaces()
                .first()

        // then
        sut.toString() shouldBeEqualTo "SampleInterface"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kointerface/snippet/forgeneral/", fileName)
}
