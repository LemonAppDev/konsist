package com.lemonappdev.konsist.core.declaration.koparentinterface

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentInterfaceDeclarationForKoDelegateProviderTest {
    @Test
    fun `class-has-parent-interface-without-delegate`() {
        // given
        val sut = getSnippetFile("class-has-parent-interface-without-delegate")
            .classes()
            .first()
            .parentInterfaces
            .first()

        // then
        assertSoftly(sut) {
            delegateName shouldBeEqualTo null
            it.hasDelegate() shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-parent-by-delegation`() {
        // given
        val sut = getSnippetFile("class-has-parent-by-delegation")
            .classes()
            .first()
            .parentInterfaces
            .first()

        // then
        assertSoftly(sut) {
            delegateName shouldBeEqualTo "sampleProperty"
            hasDelegate() shouldBeEqualTo true
            hasDelegate("sampleProperty") shouldBeEqualTo true
            hasDelegate("sampleOtherProperty") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparentinterfacedeclaration/snippet/forkodelegateprovider/", fileName)
}
