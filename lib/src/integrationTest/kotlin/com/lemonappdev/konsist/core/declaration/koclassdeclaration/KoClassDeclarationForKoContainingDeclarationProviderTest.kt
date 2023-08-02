package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `class-without-parent`() {
        // given
        val sut = getSnippetFile("class-without-parent")
            .classes()
            .first()

        // then
        sut.containingDeclaration shouldBeEqualTo null
    }

    @Test
    fun `class-with-parent`() {
        // given
        val sut = getSnippetFile("class-with-parent")
            .classes(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            containingDeclaration shouldNotBeEqualTo null
            (containingDeclaration as KoNameProvider).name shouldBeEqualTo "SampleInterface"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forkocontainingdeclarationprovider/", fileName)
}
