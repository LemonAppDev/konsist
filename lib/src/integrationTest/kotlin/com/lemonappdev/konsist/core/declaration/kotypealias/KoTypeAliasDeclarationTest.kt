package com.lemonappdev.konsist.core.declaration.kotypealias

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationTest {
    @Test
    fun `typealias-to-string`() {
        // given
        val sut =
            getSnippetFile("typealias-to-string")
                .typeAliases
                .first()

        // then
        sut.toString() shouldBeEqualTo "SampleTypeAlias"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypealias/snippet/forgeneral/", fileName)
}
