package com.lemonappdev.konsist.core.declaration.koobjectdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoParentProviderTest {
    @Test
    fun `object-without-parent`() {
        // given
        val sut = getSnippetFile("object-without-parent")
            .objects()
            .first()

        // then
        sut.parent shouldBeEqualTo null
    }

    @Test
    fun `object-with-parent`() {
        // given
        val sut = getSnippetFile("object-with-parent")
            .objects(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            parent shouldNotBeEqualTo null
            (parent as KoNameProvider).name shouldBeEqualTo "SampleClass"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobjectdeclaration/snippet/forkoparentprovider/", fileName)
}
