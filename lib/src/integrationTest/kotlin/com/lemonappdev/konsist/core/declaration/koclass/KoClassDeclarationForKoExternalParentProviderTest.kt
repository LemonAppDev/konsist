package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.externalsample.SampleExternalClass
import com.lemonappdev.konsist.externalsample.SampleExternalGenericInterface
import com.lemonappdev.konsist.externalsample.SampleExternalInterface
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface1
import com.lemonappdev.konsist.testdata.SampleParentInterface2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoExternalParentProviderTest {
    @Test
    fun `class-has-no-external-parent`() {
        // given
        val sut =
            getSnippetFile("class-has-no-external-parent")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            externalParents() shouldBeEqualTo emptyList()
            numExternalParents() shouldBeEqualTo 0
            countExternalParents { it.name == "SampleExternalParent" } shouldBeEqualTo 0
            hasExternalParents() shouldBeEqualTo false
            hasExternalParentWithName("SampleExternalClass", "SampleExternalInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames("SampleExternalClass", "SampleExternalInterface") shouldBeEqualTo false
            hasExternalParent { it.name == "SampleExternalParent" } shouldBeEqualTo false
            hasAllExternalParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasExternalParentOf(SampleParentInterface1::class) shouldBeEqualTo false
            hasAllExternalParentsOf(SampleParentInterface1::class, SampleParentInterface2::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-only-direct-external-parents`() {
        // given
        val sut =
            getSnippetFile("class-has-only-direct-external-parents")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            externalParents().map { it.name } shouldBeEqualTo listOf("SampleExternalClass", "SampleExternalInterface")
            numExternalParents() shouldBeEqualTo 2
            countExternalParents { it.name == "SampleExternalClass" } shouldBeEqualTo 1
            countExternalParents { it.hasNameStartingWith("SampleExternal") } shouldBeEqualTo 2
            hasExternalParents() shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalClass") shouldBeEqualTo true
            hasExternalParentWithName("OtherInterface") shouldBeEqualTo false
            hasExternalParentWithName("SampleExternalClass", "SampleExternalInterface") shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalClass", "OtherInterface") shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalClass") shouldBeEqualTo true
            hasExternalParentsWithAllNames("OtherInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames("SampleExternalClass", "SampleExternalInterface") shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalClass", "OtherInterface") shouldBeEqualTo false
            hasExternalParent { it.name == "SampleExternalClass" } shouldBeEqualTo true
            hasExternalParent { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllExternalParents { it.name == "SampleExternalClass" } shouldBeEqualTo false
            hasAllExternalParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllExternalParents { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasExternalParentOf(SampleExternalClass::class) shouldBeEqualTo true
            hasExternalParentOf(SampleExternalClass::class, SampleParentClass::class) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleExternalClass::class) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleExternalClass::class, SampleParentClass::class) shouldBeEqualTo false
            hasAllExternalParentsOf(SampleExternalClass::class, SampleExternalInterface::class) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-internal-and-external-parents`() {
        // given
        val sut =
            getSnippetFile("class-has-internal-and-external-parents")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            externalParents().map { it.name } shouldBeEqualTo
                listOf(
                    "SampleExternalInterface",
                    "SampleExternalGenericInterface",
                )
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
            hasExternalParentsWithAllNames(
                "SampleExternalInterface",
                "SampleExternalGenericInterface",
            ) shouldBeEqualTo true
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
            hasAllExternalParentsOf(
                SampleExternalInterface::class,
                SampleExternalGenericInterface::class,
            ) shouldBeEqualTo true
        }
    }

    @Suppress("detekt.LongMethod")
    @Test
    fun `class-has-indirect-external-parents`() {
        // given
        val sut =
            getSnippetFile("class-has-indirect-external-parents")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            externalParents(indirectParents = false) shouldBeEqualTo emptyList()
            externalParents(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "SampleExternalClass",
                    "SampleExternalInterface",
                )
            numExternalParents(indirectParents = false) shouldBeEqualTo 0
            numExternalParents(indirectParents = true) shouldBeEqualTo 2
            countExternalParents(indirectParents = false) { it.name == "SampleExternalClass" } shouldBeEqualTo 0
            countExternalParents(indirectParents = true) { it.name == "SampleExternalClass" } shouldBeEqualTo 1
            countExternalParents(indirectParents = false) { it.hasNameStartingWith("SampleExternal") } shouldBeEqualTo 0
            countExternalParents(indirectParents = true) { it.hasNameStartingWith("SampleExternal") } shouldBeEqualTo 2
            hasExternalParents(indirectParents = false) shouldBeEqualTo false
            hasExternalParents(indirectParents = true) shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalClass", indirectParents = true) shouldBeEqualTo true
            hasExternalParentWithName("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasExternalParentWithName(
                "SampleExternalClass",
                "SampleExternalInterface",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentWithName(
                "SampleExternalClass",
                "OtherInterface",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalClass", indirectParents = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasExternalParentsWithAllNames(
                "SampleExternalClass",
                "SampleExternalInterface",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentsWithAllNames(
                "SampleExternalClass",
                "OtherInterface",
                indirectParents = true,
            ) shouldBeEqualTo false
            hasExternalParent(indirectParents = true) { it.name == "SampleExternalClass" } shouldBeEqualTo true
            hasExternalParent(indirectParents = true) { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllExternalParents(indirectParents = true) { it.name == "SampleExternalClass" } shouldBeEqualTo false
            hasAllExternalParents(indirectParents = true) { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllExternalParents(indirectParents = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasExternalParentOf(SampleExternalClass::class, indirectParents = true) shouldBeEqualTo true
            hasExternalParentOf(
                SampleExternalClass::class,
                SampleParentClass::class,
                indirectParents = true,
            ) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleExternalClass::class, indirectParents = true) shouldBeEqualTo true
            hasAllExternalParentsOf(
                SampleExternalClass::class,
                SampleParentClass::class,
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllExternalParentsOf(
                SampleExternalClass::class,
                SampleExternalInterface::class,
                indirectParents = true,
            ) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-indirect-repeated-external-parents`() {
        // given
        val sut =
            getSnippetFile("class-has-indirect-repeated-external-parents")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            externalParents(indirectParents = false).map { it.name } shouldBeEqualTo listOf("SampleExternalInterface")
            externalParents(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "SampleExternalInterface",
                    "SampleExternalClass",
                )
            numExternalParents(indirectParents = false) shouldBeEqualTo 1
            numExternalParents(indirectParents = true) shouldBeEqualTo 2
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkoexternalparentprovider/", fileName)
}
