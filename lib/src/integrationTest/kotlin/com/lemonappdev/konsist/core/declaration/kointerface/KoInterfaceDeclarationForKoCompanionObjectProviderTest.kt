package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoCompanionObjectProviderTest {
    @Test
    fun `interface-has-no-companion-object`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-companion-object")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            companionObject shouldBeEqualTo null
            hasCompanionObject() shouldBeEqualTo false
            hasCompanionObject { it.name == "SampleCompanionObject" } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-companion-object-with-default-name`() {
        // given
        val sut =
            getSnippetFile("interface-has-companion-object-with-default-name")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObject() shouldBeEqualTo true
            hasCompanionObject { it.name == "Companion" } shouldBeEqualTo true
            hasCompanionObject { it.hasNameEndingWith("nion") } shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-companion-object-with-given-name`() {
        // given
        val sut =
            getSnippetFile("interface-has-companion-object-with-given-name")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObject() shouldBeEqualTo true
            hasCompanionObject { it.name == "SampleCompanionObject" } shouldBeEqualTo true
            hasCompanionObject { it.hasNameEndingWith("CompanionObject") } shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kointerface/snippet/forkocompanionobjectprovider/", fileName)
}
