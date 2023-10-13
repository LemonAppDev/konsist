package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationTest {
    @Test
    fun `variable-to-string`() {
        // given
        val sut = getSnippetFile("variable-to-string")
            .functions()
            .variables
            .first()

        // then
        sut.toString() shouldBeEqualTo "sampleProperty"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kovariable/snippet/forgeneral/", fileName)
}
