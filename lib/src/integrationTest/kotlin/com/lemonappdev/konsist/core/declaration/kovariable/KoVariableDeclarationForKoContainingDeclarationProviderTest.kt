package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.variables
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `variable-parent-declaration`() {
        // given
        val sut = getSnippetFile("variable-parent-declaration")
            .functions()
            .variables
            .first()

        // then
        (sut.containingDeclaration as KoNameProvider).name shouldBeEqualTo "sampleFunction"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kovariable/snippet/forkocontainingdeclarationprovider/", fileName)
}
