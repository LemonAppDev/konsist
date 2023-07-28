package com.lemonappdev.konsist.core.declaration.koparameterdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoParentProviderTest {
    @Test
    fun `parameter-parent`() {
        // given
        val sut = getSnippetFile("parameter-parent")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.parent shouldNotBeEqualTo null
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameterdeclaration/snippet/forkoparentprovider/", fileName)
}
