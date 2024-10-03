package com.lemonappdev.konsist.core.declaration.koproperty.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.provider.properties
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoValModifierProviderTest {
    @Test
    fun `property-has-no-val-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-no-val-modifier")
                .properties()
                .first()

        // then
        sut.hasValModifier shouldBeEqualTo false
    }

    @Test
    fun `property-has-val-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-val-modifier")
                .properties()
                .first()

        // then
        sut.hasValModifier shouldBeEqualTo true
    }

    @Test
    fun `property-defined-in-constructor-has-no-val-modifier`() {
        // given
        val sut =
            getSnippetFile("property-defined-in-constructor-has-no-val-modifier")
                .classes()
                .properties()
                .first()

        // then
        sut.hasValModifier shouldBeEqualTo false
    }

    @Test
    fun `property-defined-in-constructor-has-val-modifier`() {
        // given
        val sut =
            getSnippetFile("property-defined-in-constructor-has-val-modifier")
                .classes()
                .properties()
                .first()

        // then
        sut.hasValModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koproperty/forkomodifier/snippet/forkovalmodifierprovider/",
            fileName,
        )
}
