package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.provider.objects
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoIsTopLevelProviderTest {
    @Test
    fun `object-is-not-top-level`() {
        // given
        val sut =
            getSnippetFile("object-is-not-top-level")
                .classes()
                .objects()
                .first()

        // then
        sut.isTopLevel shouldBeEqualTo false
    }

    @Test
    fun `object-is-top-level`() {
        // given
        val sut =
            getSnippetFile("object-is-top-level")
                .objects()
                .first()

        // then
        sut.isTopLevel shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkoistoplevelprovider/", fileName)
}
