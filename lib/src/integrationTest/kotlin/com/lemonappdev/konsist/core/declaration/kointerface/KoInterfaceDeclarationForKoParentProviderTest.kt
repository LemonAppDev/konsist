package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoParentProviderTest {
    @Test
    fun `interface-has-no-parent-interface`() {
        // given
        val sut = getSnippetFile("interface-has-no-parent-interface")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            parents shouldBeEqualTo emptyList()
            numParents shouldBeEqualTo 0
            hasParents() shouldBeEqualTo false
            hasParents("SampleInterface") shouldBeEqualTo false
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
            parents.map { it.name } shouldBeEqualTo listOf("SampleParentInterface1", "SampleParentInterface2")
            numParents shouldBeEqualTo 2
            hasParents() shouldBeEqualTo true
            hasParents("SampleParentInterface1") shouldBeEqualTo true
            hasParents("OtherInterface") shouldBeEqualTo false
            hasParents("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParents("SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkoparentprovider/", fileName)
}
