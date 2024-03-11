package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.provider.hasParentOf
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface
import com.lemonappdev.konsist.testdata.SampleParentInterface1
import com.lemonappdev.konsist.testdata.SampleParentInterface2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoParentProviderTest {
    @Test
    fun `class-has-no-parents`() {
        // given
        val sut =
            getSnippetFile("class-has-no-parents")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            parents shouldBeEqualTo emptyList()
            numParents shouldBeEqualTo 0
            parents() shouldBeEqualTo emptyList()
            numParents() shouldBeEqualTo 0
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
    fun `class-has-direct-parent-class-interfaces-and-external-parent`() {
        // given
        val sut =
            getSnippetFile("class-has-direct-parent-class-interfaces-and-external-parent")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            parents.map { it.name } shouldBeEqualTo
                listOf(
                    "SampleParentClass",
                    "SampleParentInterface1",
                    "SampleParentInterface2",
                    "SampleExternalInterface",
                )
            numParents shouldBeEqualTo 4
            parents().map { it.name } shouldBeEqualTo
                listOf(
                    "SampleParentClass",
                    "SampleParentInterface1",
                    "SampleParentInterface2",
                    "SampleExternalInterface",
                )
            numParents() shouldBeEqualTo 4
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
    fun `class-has-indirect-parents`() {
        // given
        val sut =
            getSnippetFile("class-has-indirect-parents")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            parents().map { it.name } shouldBeEqualTo
                listOf(
                    "SampleParentClass",
                    "SampleParentInterface1",
                    "SampleExternalInterface",
                )
            numParents(indirectParents = false) shouldBeEqualTo 3
            parents(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "SampleParentClass",
                    "SampleParentInterface1",
                    "SampleExternalInterface",
                    "SampleParentInterface2",
                )
            numParents(indirectParents = true) shouldBeEqualTo 4
            countParents(indirectParents = true) { it.name == "SampleParentInterface2" } shouldBeEqualTo 1
            countParents(indirectParents = true) { it.hasNameStartingWith("SampleParentInterface") } shouldBeEqualTo 2
            hasParents(indirectParents = true) shouldBeEqualTo true
            hasParentWithName("SampleParentInterface2", indirectParents = true) shouldBeEqualTo true
            hasParentWithName("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasParentWithName("SampleParentInterface2", "OtherInterface", indirectParents = true) shouldBeEqualTo true
            hasParentsWithAllNames("SampleParentInterface2", indirectParents = true) shouldBeEqualTo true
            hasParentsWithAllNames("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasParentsWithAllNames(
                "SampleParentInterface2",
                "SampleParentInterface1",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentsWithAllNames("SampleParentInterface2", "OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasParent(indirectParents = true) { it.name == "SampleParentInterface2" } shouldBeEqualTo true
            hasParent(indirectParents = true) { it.name == "OtherClass" } shouldBeEqualTo false
            hasAllParents(indirectParents = true) { it.name == "SampleParentInterface2" } shouldBeEqualTo false
            hasAllParents(indirectParents = true) { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllParents(indirectParents = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParentOf(SampleParentInterface2::class, indirectParents = true) shouldBeEqualTo true
            hasParentOf(SampleParentInterface2::class, SampleInterface::class, indirectParents = true) shouldBeEqualTo true
            hasAllParentsOf(SampleParentInterface2::class, indirectParents = true) shouldBeEqualTo true
            hasAllParentsOf(
                SampleParentInterface2::class,
                SampleInterface::class,
                indirectParents = true,
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-repeated-indirect-parents`() {
        // given
        val sut =
            getSnippetFile("class-has-repeated-indirect-parents")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            parents().map { it.name } shouldBeEqualTo
                listOf(
                    "SampleParentClass",
                    "SampleParentInterface1",
                    "SampleExternalInterface",
                )
            parents(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "SampleParentClass",
                    "SampleParentInterface1",
                    "SampleExternalInterface",
                    "SampleParentInterface2",
                )
            numParents(indirectParents = false) shouldBeEqualTo 3
            numParents(indirectParents = true) shouldBeEqualTo 4
        }
    }

    @Test
    fun `class-has-parent-defined-by-import-alias`() {
        // given
        val sut =
            getSnippetFile("class-has-parent-defined-by-import-alias")
                .classes()
                .first()

        // then
        assertSoftly(sut.parents().first()) {
            name shouldBeEqualTo "SampleParentInterface"
            fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentInterface"
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkoparentprovider/", fileName)
}
