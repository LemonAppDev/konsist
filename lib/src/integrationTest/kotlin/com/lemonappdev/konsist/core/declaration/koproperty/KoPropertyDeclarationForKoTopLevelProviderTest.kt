package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoTopLevelProviderTest {
    @Test
    fun `property-is-not-top-level`() {
        // given
        val sut =
            getSnippetFile("property-is-not-top-level")
                .properties(includeNested = true)
                .first()

        // then
        sut.isTopLevel shouldBeEqualTo false
    }

    @Test
    fun `property-is-top-level`() {
        // given
        val sut =
            getSnippetFile("property-is-top-level")
                .properties()
                .first()

        // then
        sut.isTopLevel shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koproperty/snippet/forkotoplevelprovider/", fileName)
}
