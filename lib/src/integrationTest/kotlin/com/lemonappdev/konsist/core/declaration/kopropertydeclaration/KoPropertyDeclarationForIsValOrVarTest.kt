package com.lemonappdev.konsist.core.declaration.kopropertydeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForIsValOrVarTest {
    @Test
    fun `property-is-val`() {
        // given
        val sut = getSnippetFile("property-is-val")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            isVal shouldBeEqualTo true
            isVar shouldBeEqualTo false
        }
    }

    @Test
    fun `property-is-var`() {
        // given
        val sut = getSnippetFile("property-is-var")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            isVal shouldBeEqualTo false
            isVar shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/forisvalorvar/", fileName)
}
