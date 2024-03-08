package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.properties
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoGetterProviderTest {
    @Test
    fun `property-has-no-getter`() {
        // given
        val sut =
            getSnippetFile("property-has-no-getter")
                .properties()
                .first()

        // then
        sut.hasGetter shouldBeEqualTo false
    }

    @Test
    fun `property-has-getter`() {
        // given
        val sut =
            getSnippetFile("property-has-getter")
                .properties()
                .first()

        // then
        sut.hasGetter shouldBeEqualTo true
    }

    @Test
    fun `property-has-getter-and-setter`() {
        // given
        val sut =
            getSnippetFile("property-has-getter-and-setter")
                .properties()
                .first()

        // then
        sut.hasGetter shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koproperty/snippet/forkogetterprovider/", fileName)
}
