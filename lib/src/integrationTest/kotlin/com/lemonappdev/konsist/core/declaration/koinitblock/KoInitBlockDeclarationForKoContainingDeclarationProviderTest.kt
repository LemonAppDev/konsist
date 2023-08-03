package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `init-block-parent-declaration`() {
        // given
        val sut = getSnippetFile("init-block-parent-declaration")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        assertSoftly(sut) {
            this?.containingDeclaration?.shouldNotBeEqualTo(null)
            (this?.containingDeclaration as KoNameProvider).name shouldBeEqualTo "SampleClass"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblockdeclaration/snippet/forkocontainingdeclarationprovider/", fileName)
}
