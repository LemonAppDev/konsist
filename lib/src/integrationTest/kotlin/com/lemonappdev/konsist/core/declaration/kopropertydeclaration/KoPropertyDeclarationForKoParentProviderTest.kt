package com.lemonappdev.konsist.core.declaration.kopropertydeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoParentProviderTest {
    @Test
    fun `property-without-parent`() {
        // given
        val sut = getSnippetFile("property-without-parent")
            .properties()
            .first()

        // then
        sut.parent shouldBeEqualTo null
    }

    @Test
    fun `property-with-parent`() {
        // given
        val sut = getSnippetFile("property-with-parent")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            parent shouldNotBeEqualTo null
            (parent as KoNameProvider).name shouldBeEqualTo "SampleClass"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/forkoparentprovider/", fileName)
}
