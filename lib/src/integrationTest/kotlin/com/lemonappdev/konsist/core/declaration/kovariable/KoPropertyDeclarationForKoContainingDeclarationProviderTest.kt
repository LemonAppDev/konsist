package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `property-with-file-parent-declaration`() {
        // given
        val sut = getSnippetFile("property-with-file-parent-declaration")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            containingDeclaration shouldNotBeEqualTo null
            (containingDeclaration as KoNameProvider).name shouldBeEqualTo "property-with-file-parent-declaration"
        }
    }

    @Test
    fun `property-with-parent-declaration`() {
        // given
        val sut = getSnippetFile("property-with-parent-declaration")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            containingDeclaration shouldNotBeEqualTo null
            (containingDeclaration as KoNameProvider).name shouldBeEqualTo "SampleClass"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koproperty/snippet/forkocontainingdeclarationprovider/", fileName)
}
