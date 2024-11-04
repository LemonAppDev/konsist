package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoIsGenericProviderTest {
    @Test
    fun `property-is-not-generic`() {
        // given
        val sut =
            getSnippetFile("property-is-not-generic")
                .properties()
                .first()

        // then
        sut.isGeneric shouldBeEqualTo false
    }

    @Test
    fun `property-is-generic`() {
        // given
        val sut =
            getSnippetFile("property-is-generic")
                .properties()
                .first()

        // then
        sut.isGeneric shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koproperty/snippet/forkoisgenericprovider/", fileName)
}
