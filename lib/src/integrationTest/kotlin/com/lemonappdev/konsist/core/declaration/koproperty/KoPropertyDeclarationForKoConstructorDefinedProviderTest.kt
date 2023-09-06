package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.properties
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoConstructorDefinedProviderTest {
    @Test
    fun `property-is-constructor-defined`() {
        // given
        val sut = getSnippetFile("property-is-constructor-defined")
            .classes()
            .properties()
            .first()

        // then
        sut.isConstructorDefined shouldBeEqualTo true
    }

    @Test
    fun `property-is-not-constructor-defined`() {
        // given
        val sut = getSnippetFile("property-is-not-constructor-defined")
            .classes()
            .properties()
            .first()

        // then
        sut.isConstructorDefined shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koproperty/snippet/forkoconstructordefinedprovider/", fileName)
}
