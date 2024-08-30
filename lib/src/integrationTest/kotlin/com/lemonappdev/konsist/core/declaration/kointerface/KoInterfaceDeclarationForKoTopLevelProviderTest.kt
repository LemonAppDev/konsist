package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.interfaces
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoTopLevelProviderTest {
    @Test
    fun `interface-is-not-top-level`() {
        // given
        val sut =
            getSnippetFile("interface-is-not-top-level")
                .classes()
                .interfaces()
                .first()

        // then
        sut.isTopLevel shouldBeEqualTo false
    }

    @Test
    fun `interface-is-top-level`() {
        // given
        val sut =
            getSnippetFile("interface-is-top-level")
                .interfaces()
                .first()

        // then
        sut.isTopLevel shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkotoplevelprovider/", fileName)
}
