package com.lemonappdev.konsist.core.declaration.kopropertydeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForExtensionTest {
    @Test
    fun `property-is-extension`() {
        // given
        val sut = getSnippetFile("property-is-extension")
            .properties()
            .first()

        // then
        sut.isExtension() shouldBeEqualTo true
    }

    @Test
    fun `property-is-not-extension`() {
        // given
        val sut = getSnippetFile("property-is-not-extension")
            .properties()
            .first()

        // then
        sut.isExtension() shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/forextension/", fileName)
}
