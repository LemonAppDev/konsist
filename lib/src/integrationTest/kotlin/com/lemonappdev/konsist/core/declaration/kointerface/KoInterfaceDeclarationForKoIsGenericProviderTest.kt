package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoIsGenericProviderTest {
    @Test
    fun `interface-is-not-generic`() {
        // given
        val sut =
            getSnippetFile("interface-is-not-generic")
                .interfaces()
                .first()

        // then
        sut.isGeneric shouldBeEqualTo false
    }

    @Test
    fun `interface-is-generic`() {
        // given
        val sut =
            getSnippetFile("interface-is-generic")
                .interfaces()
                .first()

        // then
        sut.isGeneric shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kointerface/snippet/forkoisgenericprovider/", fileName)
}
