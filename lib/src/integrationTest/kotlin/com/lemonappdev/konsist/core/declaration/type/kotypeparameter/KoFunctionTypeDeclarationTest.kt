package com.lemonappdev.konsist.core.declaration.type.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterDeclarationTest {
    @Test
    fun `type-to-string`() {
        // given
        val sut =
            getSnippetFile("type-to-string")
                .functions()
                .first()
                .returnType
                ?.asTypeParameterDeclaration()

        // then
        sut.toString() shouldBeEqualTo "TestType"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotypeparameter/snippet/forgeneral/", fileName)
}
