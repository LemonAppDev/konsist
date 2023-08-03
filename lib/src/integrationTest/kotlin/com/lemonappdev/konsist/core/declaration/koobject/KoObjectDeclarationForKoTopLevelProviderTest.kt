package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoTopLevelProviderTest {
    @Test
    fun `object-is-not-top-level`() {
        // given
        val sut = getSnippetFile("object-is-not-top-level")
            .classes()
            .flatMap { it.objects() }
            .first()

        // then
        sut.isTopLevel shouldBeEqualTo false
    }

    @Test
    fun `object-is-top-level`() {
        // given
        val sut = getSnippetFile("object-is-top-level")
            .objects()
            .first()

        // then
        sut.isTopLevel shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkotoplevelprovider/", fileName)
}
