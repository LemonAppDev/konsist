package com.lemonappdev.konsist.core.declaration.type.kokotlintype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKotlinTypeDeclarationTest {
    @Test
    fun `type-to-string`() {
        // given
        val sut = getSnippetFile("type-to-string")
            .properties()
            .first()
            .type

        // then
        sut.toString() shouldBeEqualTo "String"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kokotlintype/snippet/forgeneral/", fileName)
}
