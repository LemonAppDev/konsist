package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoParentProviderTest {
    @Test
    fun `object-has-no-parents`() {
        // given
        val sut = getSnippetFile("object-has-no-parents")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            parents shouldBeEqualTo emptyList()
            numParents shouldBeEqualTo 0
            countParents { it.name == "SampleParentClass" } shouldBeEqualTo 0
            hasParents() shouldBeEqualTo false
            hasParents("SampleClass") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-parent-class-and-interfaces`() {
        // given
        val sut = getSnippetFile("object-has-parent-class-and-interfaces")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            parents.map { it.name } shouldBeEqualTo listOf(
                "SampleParentClass",
                "SampleParentInterface1",
                "SampleParentInterface2",
            )
            numParents shouldBeEqualTo 3
            countParents { it.name == "SampleParentClass" } shouldBeEqualTo 1
            countParents { it.hasNameStartingWith("SampleParentInterface") } shouldBeEqualTo 2
            hasParents() shouldBeEqualTo true
            hasParents("SampleParentClass") shouldBeEqualTo true
            hasParents("OtherInterface") shouldBeEqualTo false
            hasParents("SampleParentClass", "SampleParentInterface1") shouldBeEqualTo true
            hasParents("SampleParentClass", "SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkoparentprovider/", fileName)
}
