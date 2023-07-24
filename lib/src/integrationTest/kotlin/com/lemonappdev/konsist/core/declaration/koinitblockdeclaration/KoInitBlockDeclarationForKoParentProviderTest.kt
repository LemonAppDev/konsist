package com.lemonappdev.konsist.core.declaration.koinitblockdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.sequence.withInitBlocks
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoParentProviderTest {
    @Test
    fun `init-block-parent`() {
        // given
        val sut = getSnippetFile("init-block-parent")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        assertSoftly(sut) {
            this?.parent?.shouldNotBeEqualTo(null)
            (this?.parent as KoNameProvider).name shouldBeEqualTo "SampleClass"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblockdeclaration/snippet/forkoparentprovider/", fileName)
}
