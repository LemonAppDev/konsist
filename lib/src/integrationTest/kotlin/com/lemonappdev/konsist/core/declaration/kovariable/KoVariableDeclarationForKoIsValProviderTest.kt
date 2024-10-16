package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoIsValProviderTest {
    @Test
    fun `variable-has-no-val-keyword`() {
        // given
        val sut =
            getSnippetFile("variable-has-no-val-keyword")
                .functions()
                .variables
                .first()

        // then
        sut.isVal shouldBeEqualTo false
    }

    @Test
    fun `variable-has-val-keyword`() {
        // given
        val sut =
            getSnippetFile("variable-has-val-keyword")
                .functions()
                .variables
                .first()

        // then
        sut.isVal shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kovariable/snippet/forkoisvalprovider/",
            fileName,
        )
}
