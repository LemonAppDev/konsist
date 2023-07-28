package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoTopLevelProviderTest {
    @Test
    fun `class-is-not-top-level`() {
        // given
        val sut = getSnippetFile("class-is-not-top-level")
            .classes()
            .flatMap { it.classes() }
            .first()

        // then
        sut.isTopLevel() shouldBeEqualTo false
    }

    @Test
    fun `class-is-top-level`() {
        // given
        val sut = getSnippetFile("class-is-top-level")
            .classes()
            .first()

        // then
        sut.isTopLevel() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forkotoplevelprovider/", fileName)
}
