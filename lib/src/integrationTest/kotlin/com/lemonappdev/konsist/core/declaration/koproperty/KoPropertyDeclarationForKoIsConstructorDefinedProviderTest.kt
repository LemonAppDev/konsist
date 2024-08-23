package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.properties
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoIsConstructorDefinedProviderTest {
    @Test
    fun `property-is-defined-in-constructor`() {
        // given
        val sut =
            getSnippetFile("property-is-defined-in-constructor")
                .classes()
                .properties()
                .first()

        // then
        sut.isConstructorDefined shouldBeEqualTo true
    }

    @Test
    fun `property-is-defined-in-body`() {
        // given
        val sut =
            getSnippetFile("property-is-defined-in-body")
                .classes()
                .properties()
                .first()

        // then
        sut.isConstructorDefined shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koproperty/snippet/forkoisconstructordefinedprovider/", fileName)
}
