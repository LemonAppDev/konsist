package com.lemonappdev.konsist.core.declaration.type.kogenerictype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGenericTypeDeclarationTest {
    @Test
    fun `type-to-string`() {
        // given
        val sut =
            getSnippetFile("type-to-string")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        sut.toString() shouldBeEqualTo "List<Set<String>>"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kogenerictype/snippet/forgeneral/", fileName)
}
