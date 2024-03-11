package com.lemonappdev.konsist.core.declaration.koproperty.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoLateinitModifierProviderTest {
    @Test
    fun `property-has-no-lateinit-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-no-lateinit-modifier")
                .properties()
                .first()

        // then
        sut.hasLateinitModifier shouldBeEqualTo false
    }

    @Test
    fun `property-has-lateinit-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-lateinit-modifier")
                .properties()
                .first()

        // then
        sut.hasLateinitModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koproperty/forkomodifier/snippet/forkolateinitmodifierprovider/",
            fileName,
        )
}
