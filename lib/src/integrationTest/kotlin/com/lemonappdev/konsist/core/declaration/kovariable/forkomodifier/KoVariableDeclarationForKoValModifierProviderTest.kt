package com.lemonappdev.konsist.core.declaration.kovariable.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoValModifierProviderTest {
    @Test
    fun `variable-has-no-val-modifier`() {
        // given
        val sut = getSnippetFile("variable-has-no-val-modifier")
            .functions()
            .variables
            .first()

        // then
        sut.hasValModifier shouldBeEqualTo false
    }

    @Test
    fun `variable-has-val-modifier`() {
        // given
        val sut = getSnippetFile("variable-has-val-modifier")
            .functions()
            .variables
            .first()

        // then
        sut.hasValModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kovariable/forkomodifier/snippet/forkovalmodifierprovider/",
            fileName,
        )
}
