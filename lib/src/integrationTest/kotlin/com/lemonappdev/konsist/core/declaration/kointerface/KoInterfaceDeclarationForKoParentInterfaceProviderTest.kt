package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoParentInterfaceProviderTest {
    @Test
    fun `interface-has-no-parent-interface`() {
        // given
        val sut = getSnippetFile("interface-has-no-parent-interface")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            parentInterfaces shouldBeEqualTo emptyList()
            numParentInterfaces shouldBeEqualTo 0
            hasParentInterfaces() shouldBeEqualTo false
            hasParentInterfaces("SampleInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-parent-interfaces`() {
        // given
        val sut = getSnippetFile("interface-has-parent-interfaces")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            parentInterfaces.map { it.name } shouldBeEqualTo listOf("SampleParentInterface1", "SampleParentInterface2")
            numParentInterfaces shouldBeEqualTo 2
            hasParentInterfaces() shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1") shouldBeEqualTo true
            hasParentInterfaces("OtherInterface") shouldBeEqualTo false
            hasParentInterfaces("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerfacedeclaration/snippet/forkoparentinterfaceprovider/", fileName)
}
