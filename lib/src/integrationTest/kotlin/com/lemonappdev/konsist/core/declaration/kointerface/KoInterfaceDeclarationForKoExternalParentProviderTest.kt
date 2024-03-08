package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.externalsample.SampleExternalGenericInterface
import com.lemonappdev.konsist.externalsample.SampleExternalInterface
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface1
import com.lemonappdev.konsist.testdata.SampleParentInterface2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoExternalParentProviderTest {
    @Test
    fun `interface-has-no-external-parent`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-external-parent")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            externalParents() shouldBeEqualTo emptyList()
            numExternalParents() shouldBeEqualTo 0
            countExternalParents { it.name == "SampleExternalParent" } shouldBeEqualTo 0
            hasExternalParents() shouldBeEqualTo false
            hasExternalParentWithName("SampleExternalParent1", "SampleExternalParent2") shouldBeEqualTo false
            hasExternalParentsWithAllNames("SampleExternalParent1", "SampleExternalParent2") shouldBeEqualTo false
            hasExternalParent { it.name == "SampleExternalParent" } shouldBeEqualTo false
            hasAllExternalParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasExternalParentOf(SampleParentInterface1::class) shouldBeEqualTo false
            hasAllExternalParentsOf(SampleParentInterface1::class, SampleParentInterface2::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-only-direct-external-parents`() {
        // given
        val sut =
            getSnippetFile("interface-has-only-direct-external-parents")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            externalParents().map { it.name } shouldBeEqualTo listOf("SampleExternalInterface", "SampleExternalGenericInterface")
            numExternalParents() shouldBeEqualTo 2
            countExternalParents { it.name == "SampleExternalInterface" } shouldBeEqualTo 1
            countExternalParents { it.hasNameStartingWith("SampleExternal") } shouldBeEqualTo 2
            hasExternalParents() shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalInterface") shouldBeEqualTo true
            hasExternalParentWithName("OtherInterface") shouldBeEqualTo false
            hasExternalParentWithName("SampleExternalInterface", "SampleExternalGenericInterface") shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalInterface", "OtherInterface") shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalInterface") shouldBeEqualTo true
            hasExternalParentsWithAllNames("OtherInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames("SampleExternalInterface", "SampleExternalGenericInterface") shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalInterface", "OtherInterface") shouldBeEqualTo false
            hasExternalParent { it.name == "SampleExternalInterface" } shouldBeEqualTo true
            hasExternalParent { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllExternalParents { it.name == "SampleExternalInterface" } shouldBeEqualTo false
            hasAllExternalParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllExternalParents { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasExternalParentOf(SampleExternalInterface::class) shouldBeEqualTo true
            hasExternalParentOf(SampleExternalInterface::class, SampleParentClass::class) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleExternalInterface::class) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleExternalInterface::class, SampleParentClass::class) shouldBeEqualTo false
            hasAllExternalParentsOf(SampleExternalInterface::class, SampleExternalGenericInterface::class) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-internal-and-external-parents`() {
        // given
        val sut =
            getSnippetFile("interface-has-internal-and-external-parents")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            externalParents().map { it.name } shouldBeEqualTo listOf("SampleExternalInterface", "SampleExternalGenericInterface")
            numExternalParents() shouldBeEqualTo 2
            countExternalParents { it.name == "SampleExternalInterface" } shouldBeEqualTo 1
            countExternalParents { it.hasNameStartingWith("SampleExternal") } shouldBeEqualTo 2
            hasExternalParents() shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalInterface") shouldBeEqualTo true
            hasExternalParentWithName("OtherInterface") shouldBeEqualTo false
            hasExternalParentWithName("SampleExternalInterface", "SampleExternalGenericInterface") shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalInterface", "OtherInterface") shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalInterface") shouldBeEqualTo true
            hasExternalParentsWithAllNames("OtherInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames("SampleExternalInterface", "SampleExternalGenericInterface") shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalInterface", "OtherInterface") shouldBeEqualTo false
            hasExternalParent { it.name == "SampleExternalInterface" } shouldBeEqualTo true
            hasExternalParent { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllExternalParents { it.name == "SampleExternalInterface" } shouldBeEqualTo false
            hasAllExternalParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllExternalParents { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasExternalParentOf(SampleExternalInterface::class) shouldBeEqualTo true
            hasExternalParentOf(SampleExternalInterface::class, SampleParentClass::class) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleExternalInterface::class) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleExternalInterface::class, SampleParentClass::class) shouldBeEqualTo false
            hasAllExternalParentsOf(SampleExternalInterface::class, SampleExternalGenericInterface::class) shouldBeEqualTo true
        }
    }

    @Suppress("detekt.LongMethod")
    @Test
    fun `interface-has-indirect-external-parents`() {
        // given
        val sut =
            getSnippetFile("interface-has-indirect-external-parents")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            externalParents(indirectParents = false) shouldBeEqualTo emptyList()
            externalParents(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "SampleExternalInterface",
                    "SampleExternalGenericInterface",
                )
            numExternalParents(indirectParents = false) shouldBeEqualTo 0
            numExternalParents(indirectParents = true) shouldBeEqualTo 2
            countExternalParents(indirectParents = false) { it.name == "SampleExternalGenericInterface" } shouldBeEqualTo 0
            countExternalParents(indirectParents = true) { it.name == "SampleExternalGenericInterface" } shouldBeEqualTo 1
            countExternalParents(indirectParents = false) { it.hasNameStartingWith("SampleExternal") } shouldBeEqualTo 0
            countExternalParents(indirectParents = true) { it.hasNameStartingWith("SampleExternal") } shouldBeEqualTo 2
            hasExternalParents(indirectParents = false) shouldBeEqualTo false
            hasExternalParents(indirectParents = true) shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalGenericInterface", indirectParents = true) shouldBeEqualTo true
            hasExternalParentWithName("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasExternalParentWithName(
                "SampleExternalGenericInterface",
                "SampleExternalInterface",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentWithName(
                "SampleExternalGenericInterface",
                "OtherInterface",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalGenericInterface", indirectParents = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasExternalParentsWithAllNames(
                "SampleExternalGenericInterface",
                "SampleExternalInterface",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentsWithAllNames(
                "SampleExternalGenericInterface",
                "OtherInterface",
                indirectParents = true,
            ) shouldBeEqualTo false
            hasExternalParent(indirectParents = true) { it.name == "SampleExternalGenericInterface" } shouldBeEqualTo true
            hasExternalParent(indirectParents = true) { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllExternalParents(indirectParents = true) { it.name == "SampleExternalGenericInterface" } shouldBeEqualTo false
            hasAllExternalParents(indirectParents = true) { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllExternalParents(indirectParents = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasExternalParentOf(SampleExternalGenericInterface::class, indirectParents = true) shouldBeEqualTo true
            hasExternalParentOf(
                SampleExternalGenericInterface::class,
                SampleParentClass::class,
                indirectParents = true,
            ) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleExternalGenericInterface::class, indirectParents = true) shouldBeEqualTo true
            hasAllExternalParentsOf(
                SampleExternalGenericInterface::class,
                SampleParentClass::class,
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllExternalParentsOf(
                SampleExternalGenericInterface::class,
                SampleExternalInterface::class,
                indirectParents = true,
            ) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-indirect-repeated-external-parents`() {
        // given
        val sut =
            getSnippetFile("interface-has-indirect-repeated-external-parents")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            externalParents(indirectParents = false).map { it.name } shouldBeEqualTo listOf("SampleExternalInterface")
            externalParents(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "SampleExternalInterface",
                    "SampleExternalGenericInterface",
                )
            numExternalParents(indirectParents = false) shouldBeEqualTo 1
            numExternalParents(indirectParents = true) shouldBeEqualTo 2
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkoexternalparentprovider/", fileName)
}
