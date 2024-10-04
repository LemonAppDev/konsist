package com.lemonappdev.konsist.core.declaration.koproperty.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.properties
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoVarModifierProviderTest {
    @Test
    fun `property-has-no-var-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-no-var-modifier")
                .properties()
                .first()

        // then
        sut.hasVarModifier shouldBeEqualTo false
    }

    @Test
    fun `property-has-var-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-var-modifier")
                .properties()
                .first()

        // then
        sut.hasVarModifier shouldBeEqualTo true
    }

    @Test
    fun `property-defined-in-constructor-has-no-var-modifier`() {
        // given
        val sut =
            getSnippetFile("property-defined-in-constructor-has-no-var-modifier")
                .classes()
                .properties()
                .first()

        // then
        sut.hasVarModifier shouldBeEqualTo false
    }

    @Test
    fun `property-defined-in-constructor-has-var-modifier`() {
        // given
        val sut =
            getSnippetFile("property-defined-in-constructor-has-var-modifier")
                .classes()
                .properties()
                .first()

        // then
        sut.hasVarModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koproperty/forkomodifier/snippet/forkovarmodifierprovider/",
            fileName,
        )
}
