package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoCompanionObjectProviderTest {
    @Test
    fun `class-has-no-companion-object`() {
        // given
        val sut =
            getSnippetFile("class-has-no-companion-object")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            companionObject shouldBeEqualTo null
            hasCompanionObject() shouldBeEqualTo false
            hasCompanionObject { it.name == "SampleCompanionObject" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-companion-object-with-default-name`() {
        // given
        val sut =
            getSnippetFile("class-has-companion-object-with-default-name")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObject() shouldBeEqualTo true
            hasCompanionObject { it.name == "Companion" } shouldBeEqualTo true
            hasCompanionObject { it.hasNameEndingWith("nion") } shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-companion-object-with-given-name`() {
        // given
        val sut =
            getSnippetFile("class-has-companion-object-with-given-name")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObject() shouldBeEqualTo true
            hasCompanionObject { it.name == "SampleCompanionObject" } shouldBeEqualTo true
            hasCompanionObject { it.hasNameEndingWith("CompanionObject") } shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkocompanionobjectprovider/", fileName)
}
