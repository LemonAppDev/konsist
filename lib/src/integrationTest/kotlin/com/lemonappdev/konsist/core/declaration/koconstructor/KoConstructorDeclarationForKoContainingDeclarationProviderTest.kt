package com.lemonappdev.konsist.core.declaration.koconstructor

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `constructor-parent`() {
        // given
        val sut = getSnippetFile("constructor-parent")
            .classes()
            .first()
            .constructors
            .first()

        // then
        (sut.containingDeclaration as KoNameProvider).name shouldBeEqualTo "SampleClass"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koconstructor/snippet/forkocontainingdeclarationprovider/", fileName)
}
