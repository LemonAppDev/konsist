package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface
import com.lemonappdev.konsist.testdata.SampleParentInterface1
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
            hasParentWithName("SampleParentClass") shouldBeEqualTo false
            hasParentsWithAllNames("SampleParentClass", "SampleParentInterface") shouldBeEqualTo false
            hasParent { it.name == "SampleParentClass" } shouldBeEqualTo false
            hasAllParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasParentOf(SampleParentClass::class) shouldBeEqualTo false
            hasAllParentsOf(SampleParentClass::class, SampleParentInterface::class) shouldBeEqualTo false
            hasParents("SampleParentClass") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-parent-class-interfaces-and-external-parent`() {
        // given
        val sut = getSnippetFile("object-has-parent-class-interfaces-and-external-parent")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            parents.map { it.name } shouldBeEqualTo listOf(
                "SampleParentClass",
                "SampleParentInterface1",
                "SampleParentInterface2",
                "SampleExternalInterface",
            )
            numParents shouldBeEqualTo 4
            countParents { it.name == "SampleParentClass" } shouldBeEqualTo 1
            countParents { it.hasNameStartingWith("SampleParentInterface") } shouldBeEqualTo 2
            hasParents() shouldBeEqualTo true
            hasParentWithName("SampleParentClass") shouldBeEqualTo true
            hasParentWithName("OtherInterface") shouldBeEqualTo false
            hasParentWithName("SampleParentClass", "OtherInterface") shouldBeEqualTo true
            hasParentsWithAllNames("SampleParentClass") shouldBeEqualTo true
            hasParentsWithAllNames("OtherInterface") shouldBeEqualTo false
            hasParentsWithAllNames("SampleParentClass", "SampleParentInterface1") shouldBeEqualTo true
            hasParentsWithAllNames("SampleParentClass", "OtherInterface") shouldBeEqualTo false
            hasParent { it.name == "SampleParentClass" } shouldBeEqualTo true
            hasParent { it.name == "OtherClass" } shouldBeEqualTo false
            hasAllParents { it.name == "SampleParentClass" } shouldBeEqualTo false
            hasAllParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllParents { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParentOf(SampleParentClass::class) shouldBeEqualTo true
            hasParentOf(SampleParentClass::class, SampleInterface::class) shouldBeEqualTo true
            hasAllParentsOf(SampleParentClass::class) shouldBeEqualTo true
            hasAllParentsOf(SampleParentClass::class, SampleInterface::class) shouldBeEqualTo false
            hasAllParentsOf(SampleParentClass::class, SampleParentInterface1::class) shouldBeEqualTo true
            hasParents("SampleParentClass") shouldBeEqualTo true
            hasParents("OtherInterface") shouldBeEqualTo false
            hasParents("SampleParentClass", "SampleParentInterface1") shouldBeEqualTo true
            hasParents("SampleParentClass", "SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-parent-defined-by-import-alias`() {
        // given
        val sut = getSnippetFile("object-has-parent-defined-by-import-alias")
            .objects()
            .first()

        // then
        assertSoftly(sut.parents.first()) {
            name shouldBeEqualTo "SampleParentInterface"
            fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentInterface"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkoparentprovider/", fileName)
}
