package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoIsVarProviderTest {
    @Test
    fun `variable-has-no-var-keyword`() {
        // given
        val sut =
            getSnippetFile("variable-has-no-var-keyword")
                .functions()
                .variables
                .first()

        // then
        sut.isVar shouldBeEqualTo false
    }

    @Test
    fun `variable-has-var-keyword`() {
        // given
        val sut =
            getSnippetFile("variable-has-var-keyword")
                .functions()
                .variables
                .first()

        // then
        sut.isVar shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kovariable/snippet/forkoisvarprovider/",
            fileName,
        )
}
