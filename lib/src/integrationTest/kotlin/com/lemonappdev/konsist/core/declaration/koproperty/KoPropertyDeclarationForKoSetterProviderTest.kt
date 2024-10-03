package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.provider.properties
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoSetterProviderTest {
    @Test
    fun `property-has-no-setter`() {
        // given
        val sut =
            getSnippetFile("property-has-no-setter")
                .properties()
                .first()

        // then
        sut.hasSetter shouldBeEqualTo false
    }

    @Test
    fun `property-has-setter`() {
        // given
        val sut =
            getSnippetFile("property-has-setter")
                .properties()
                .first()

        // then
        sut.hasSetter shouldBeEqualTo true
    }

    @Test
    fun `property-has-getter-and-setter`() {
        // given
        val sut =
            getSnippetFile("property-has-getter-and-setter")
                .properties()
                .first()

        // then
        sut.hasSetter shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koproperty/snippet/forkosetterprovider/", fileName)
}
