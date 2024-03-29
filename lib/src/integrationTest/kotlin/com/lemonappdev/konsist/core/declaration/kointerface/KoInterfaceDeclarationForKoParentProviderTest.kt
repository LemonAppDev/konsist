package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface
import com.lemonappdev.konsist.testdata.SampleParentInterface1
import com.lemonappdev.konsist.testdata.SampleParentInterface2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoParentProviderTest {
    @Test
    fun `interface-has-no-parents`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-parents")
                .interfaces()
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
    fun `interface-has-direct-internal-and-external-parents`() {
        // given
        val sut =
            getSnippetFile("interface-has-direct-internal-and-external-parents")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            parents.map { it.name } shouldBeEqualTo
                listOf(
                    "SampleParentInterface1",
                    "SampleParentInterface2",
                    "SampleExternalInterface",
                    "SampleExternalGenericInterface",
                )
            numParents shouldBeEqualTo 4
            parents().map { it.name } shouldBeEqualTo
                listOf(
                    "SampleParentInterface1",
                    "SampleParentInterface2",
                    "SampleExternalInterface",
                    "SampleExternalGenericInterface",
                )
            numParents() shouldBeEqualTo 4
            countParents { it.name == "SampleParentInterface1" } shouldBeEqualTo 1
            countParents { it.hasNameStartingWith("SampleExternal") } shouldBeEqualTo 2
            hasParents() shouldBeEqualTo true
            hasParentWithName("SampleParentInterface1") shouldBeEqualTo true
            hasParentWithName("OtherInterface") shouldBeEqualTo false
            hasParentWithName("SampleParentInterface1", "OtherInterface") shouldBeEqualTo true
            hasParentsWithAllNames("SampleParentInterface1") shouldBeEqualTo true
            hasParentsWithAllNames("OtherInterface") shouldBeEqualTo false
            hasParentsWithAllNames("SampleParentInterface1", "SampleExternalInterface") shouldBeEqualTo true
            hasParentsWithAllNames("SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
            hasParent { it.name == "SampleParentInterface1" } shouldBeEqualTo true
            hasParent { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllParents { it.name == "SampleParentInterface1" } shouldBeEqualTo false
            hasAllParents { it.hasNameContaining("Parent") || it.hasNameContaining("External") } shouldBeEqualTo true
            hasAllParents { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParentOf(SampleParentInterface1::class) shouldBeEqualTo true
            hasParentOf(SampleParentInterface1::class, SampleInterface::class) shouldBeEqualTo true
            hasAllParentsOf(SampleParentInterface1::class) shouldBeEqualTo true
            hasAllParentsOf(SampleParentInterface1::class, SampleInterface::class) shouldBeEqualTo false
            hasAllParentsOf(SampleParentInterface1::class, SampleParentInterface2::class) shouldBeEqualTo true
            hasParents("SampleParentInterface1") shouldBeEqualTo true
            hasParents("OtherInterface") shouldBeEqualTo false
            hasParents("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParents("SampleParentInterface1", "SampleParentInterface2", "OtherInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-indirect-parents`() {
        // given
        val sut =
            getSnippetFile("interface-has-indirect-parents")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            parents().map { it.name } shouldBeEqualTo
                listOf(
                    "SampleParentInterface",
                    "SampleExternalInterface",
                )
            numParents(indirectParents = false) shouldBeEqualTo 2
            parents(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "SampleParentInterface",
                    "SampleExternalInterface",
                    "SampleParentInterface1",
                    "SampleParentInterface2",
                )
            numParents(indirectParents = true) shouldBeEqualTo 4
            countParents(indirectParents = true) { it.name == "SampleParentInterface2" } shouldBeEqualTo 1
            countParents(indirectParents = true) { it.hasNameStartingWith("SampleParentInterface") } shouldBeEqualTo 3
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
            hasAllParentsOf(
                SampleParentInterface2::class,
                SampleParentInterface1::class,
                indirectParents = true,
            ) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-repeated-indirect-parents`() {
        // given
        val sut =
            getSnippetFile("interface-has-repeated-indirect-parents")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            parents().map { it.name } shouldBeEqualTo
                listOf(
                    "SampleParentInterface1",
                    "SampleExternalInterface",
                )
            numParents(indirectParents = false) shouldBeEqualTo 2
            parents(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "SampleParentInterface1",
                    "SampleExternalInterface",
                    "SampleParentInterface2",
                )
            numParents(indirectParents = true) shouldBeEqualTo 3
        }
    }

    @Test
    fun `interface-has-parent-defined-by-import-alias`() {
        // given
        val sut =
            getSnippetFile("interface-has-parent-defined-by-import-alias")
                .interfaces()
                .first()

        // then
        assertSoftly(sut.parents.first()) {
            name shouldBeEqualTo "SampleParentInterface"
            fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentInterface"
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kointerface/snippet/forkoparentprovider/", fileName)
}
