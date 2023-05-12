package com.lemonappdev.konsist.core.declaration.koparameterdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForNoParameterTest {
    @Test
    fun `class-has-no-parameters`() {
        // given
        val sut = getSnippetFile("class-has-no-parameters")
            .classes()
            .first()

        // then
        sut.primaryConstructor shouldBeEqualTo null
    }

    @Test
    fun `class-has-empty-primary-constructor`() {
        // given
        val sut = getSnippetFile("class-has-empty-primary-constructor")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters

        // then
        sut shouldBeEqualTo null
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koparameterdeclaration/snippet/fornoparameter/", fileName)
}
