package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `argument-containing-declaration`() {
        // given
        val sut = getSnippetFile("argument-containing-declaration")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
            (sut.containingDeclaration as KoNameProvider).name shouldBeEqualTo "SAMPLE_CONSTANT"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koargument/snippet/forkocontainingdeclarationprovider/", fileName)
}
