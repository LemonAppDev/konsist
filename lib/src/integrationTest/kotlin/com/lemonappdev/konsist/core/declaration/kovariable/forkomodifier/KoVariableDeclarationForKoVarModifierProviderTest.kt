package com.lemonappdev.konsist.core.declaration.kovariable.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoVarModifierProviderTest {
    @Test
    fun `variable-has-no-var-modifier`() {
        // given
        val sut = getSnippetFile("variable-has-no-var-modifier")
            .functions()
            .variables
            .first()

        // then
        sut.hasVarModifier shouldBeEqualTo false
    }

    @Test
    fun `variable-has-var-modifier`() {
        // given
        val sut = getSnippetFile("variable-has-var-modifier")
            .functions()
            .variables
            .first()

        // then
        sut.hasVarModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kovariable/forkomodifier/snippet/forkovarmodifierprovider/",
            fileName,
        )
}
