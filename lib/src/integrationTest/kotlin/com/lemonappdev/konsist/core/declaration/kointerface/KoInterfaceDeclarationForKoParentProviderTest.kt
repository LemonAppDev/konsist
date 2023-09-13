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
            countParents { it.name == "SampleParentInterface" } shouldBeEqualTo 0
            hasParents() shouldBeEqualTo false
            hasParentWithName("SampleParentInterface") shouldBeEqualTo false
            hasParentsWithAllNames("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo false
            hasParent { it.name == "SampleParentInterface" } shouldBeEqualTo false
            hasAllParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasParents("SampleParentInterface") shouldBeEqualTo false
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
            countParents { it.name == "SampleParentInterface1" } shouldBeEqualTo 1
            countParents { it.hasNameStartingWith("SampleParentInterface") } shouldBeEqualTo 2
            hasParents() shouldBeEqualTo true
            hasParentWithName("SampleParentInterface1") shouldBeEqualTo true
            hasParentWithName("OtherInterface") shouldBeEqualTo false
            hasParentWithName("SampleParentInterface1", "OtherInterface") shouldBeEqualTo true
            hasParentsWithAllNames("SampleParentInterface1") shouldBeEqualTo true
            hasParentsWithAllNames("OtherInterface") shouldBeEqualTo false
            hasParentsWithAllNames("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParentsWithAllNames("SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
            hasParent { it.name == "SampleParentInterface1" } shouldBeEqualTo true
            hasParent { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllParents { it.name == "SampleParentInterface1" } shouldBeEqualTo false
            hasAllParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllParents { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParents("SampleParentInterface1") shouldBeEqualTo true
            hasParents("OtherInterface") shouldBeEqualTo false
            hasParents("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParents("SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkoparentprovider/", fileName)
}
