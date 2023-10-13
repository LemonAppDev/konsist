package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoTextProviderTest {
    @Test
    fun `variable-text`() {
        // given
        val sut = getSnippetFile("variable-text")
            .functions()
            .variables
            .first()

        // then
        sut
            .text
            .shouldBeEqualTo("val sampleProperty = \"\"")
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kovariable/snippet/forkotextprovider/", fileName)
}
