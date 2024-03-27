package com.lemonappdev.konsist.core.declaration.koobject

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

class KoObjectDeclarationForKoExternalParentProviderTest {
    @Test
    fun `object-has-no-external-parent`() {
        // given
        val sut =
            getSnippetFile("object-has-no-external-parent")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            externalParents() shouldBeEqualTo emptyList()
            numExternalParents() shouldBeEqualTo 0
            countExternalParents { it.name == "SampleExternalParent" } shouldBeEqualTo 0
            hasExternalParents() shouldBeEqualTo false
            hasExternalParentWithName(emptyList()) shouldBeEqualTo false
            hasExternalParentWithName(emptySet()) shouldBeEqualTo false
            hasExternalParentsWithAllNames(emptyList()) shouldBeEqualTo false
            hasExternalParentsWithAllNames(emptySet()) shouldBeEqualTo false
            hasExternalParentWithName("SampleExternalClass", "SampleExternalInterface") shouldBeEqualTo false
            hasExternalParentWithName(listOf("SampleExternalClass", "SampleExternalInterface")) shouldBeEqualTo false
            hasExternalParentWithName(setOf("SampleExternalClass", "SampleExternalInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames("SampleExternalClass", "SampleExternalInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("SampleExternalClass", "SampleExternalInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(setOf("SampleExternalClass", "SampleExternalInterface")) shouldBeEqualTo false
            hasExternalParent { it.name == "SampleExternalParent" } shouldBeEqualTo false
            hasAllExternalParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasExternalParentOf(SampleParentInterface1::class) shouldBeEqualTo false
            hasExternalParentOf(listOf(SampleParentInterface1::class)) shouldBeEqualTo false
            hasExternalParentOf(setOf(SampleParentInterface1::class)) shouldBeEqualTo false
            hasAllExternalParentsOf(SampleParentInterface1::class, SampleParentInterface2::class) shouldBeEqualTo false
            hasAllExternalParentsOf(listOf(SampleParentInterface1::class, SampleParentInterface2::class)) shouldBeEqualTo false
            hasAllExternalParentsOf(setOf(SampleParentInterface1::class, SampleParentInterface2::class)) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-only-direct-external-parents`() {
        // given
        val sut =
            getSnippetFile("object-has-only-direct-external-parents")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            externalParents().map { it.name } shouldBeEqualTo listOf("SampleExternalClass", "SampleExternalInterface")
            numExternalParents() shouldBeEqualTo 2
            countExternalParents { it.name == "SampleExternalClass" } shouldBeEqualTo 1
            countExternalParents { it.hasNameStartingWith("SampleExternal") } shouldBeEqualTo 2
            hasExternalParents() shouldBeEqualTo true
            hasExternalParentWithName(emptyList()) shouldBeEqualTo true
            hasExternalParentWithName(emptySet()) shouldBeEqualTo true
            hasExternalParentsWithAllNames(emptyList()) shouldBeEqualTo true
            hasExternalParentsWithAllNames(emptySet()) shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalClass") shouldBeEqualTo true
            hasExternalParentWithName("OtherInterface") shouldBeEqualTo false
            hasExternalParentWithName("SampleExternalClass", "SampleExternalInterface") shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalClass", "OtherInterface") shouldBeEqualTo true
            hasExternalParentWithName(listOf("SampleExternalClass")) shouldBeEqualTo true
            hasExternalParentWithName(listOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentWithName(listOf("SampleExternalClass", "SampleExternalInterface")) shouldBeEqualTo true
            hasExternalParentWithName(listOf("SampleExternalClass", "OtherInterface")) shouldBeEqualTo true
            hasExternalParentWithName(setOf("SampleExternalClass")) shouldBeEqualTo true
            hasExternalParentWithName(setOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentWithName(setOf("SampleExternalClass", "SampleExternalInterface")) shouldBeEqualTo true
            hasExternalParentWithName(setOf("SampleExternalClass", "OtherInterface")) shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalClass") shouldBeEqualTo true
            hasExternalParentsWithAllNames("OtherInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames("SampleExternalClass", "SampleExternalInterface") shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalClass", "OtherInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("SampleExternalClass")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(listOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("SampleExternalClass", "SampleExternalInterface")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(listOf("SampleExternalClass", "OtherInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(setOf("SampleExternalClass")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(setOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(setOf("SampleExternalClass", "SampleExternalInterface")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(setOf("SampleExternalClass", "OtherInterface")) shouldBeEqualTo false
            hasExternalParent { it.name == "SampleExternalClass" } shouldBeEqualTo true
            hasExternalParent { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllExternalParents { it.name == "SampleExternalClass" } shouldBeEqualTo false
            hasAllExternalParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllExternalParents { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasExternalParentOf(SampleExternalClass::class) shouldBeEqualTo true
            hasExternalParentOf(SampleExternalClass::class, SampleParentClass::class) shouldBeEqualTo true
            hasExternalParentOf(listOf(SampleExternalClass::class)) shouldBeEqualTo true
            hasExternalParentOf(listOf(SampleExternalClass::class, SampleParentClass::class)) shouldBeEqualTo true
            hasExternalParentOf(setOf(SampleExternalClass::class)) shouldBeEqualTo true
            hasExternalParentOf(setOf(SampleExternalClass::class, SampleParentClass::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleExternalClass::class) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleExternalClass::class, SampleParentClass::class) shouldBeEqualTo false
            hasAllExternalParentsOf(SampleExternalClass::class, SampleExternalInterface::class) shouldBeEqualTo true
            hasAllExternalParentsOf(listOf(SampleExternalClass::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(listOf(SampleExternalClass::class, SampleParentClass::class)) shouldBeEqualTo false
            hasAllExternalParentsOf(listOf(SampleExternalClass::class, SampleExternalInterface::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(setOf(SampleExternalClass::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(setOf(SampleExternalClass::class, SampleParentClass::class)) shouldBeEqualTo false
            hasAllExternalParentsOf(setOf(SampleExternalClass::class, SampleExternalInterface::class)) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-has-internal-and-external-parents`() {
        // given
        val sut =
            getSnippetFile("object-has-internal-and-external-parents")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            externalParents().map { it.name } shouldBeEqualTo listOf("SampleExternalInterface", "SampleExternalGenericInterface")
            numExternalParents() shouldBeEqualTo 2
            countExternalParents { it.name == "SampleExternalInterface" } shouldBeEqualTo 1
            countExternalParents { it.hasNameStartingWith("SampleExternal") } shouldBeEqualTo 2
            hasExternalParents() shouldBeEqualTo true
            hasExternalParentWithName(emptyList()) shouldBeEqualTo true
            hasExternalParentWithName(emptySet()) shouldBeEqualTo true
            hasExternalParentsWithAllNames(emptyList()) shouldBeEqualTo true
            hasExternalParentsWithAllNames(emptySet()) shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalInterface") shouldBeEqualTo true
            hasExternalParentWithName("OtherInterface") shouldBeEqualTo false
            hasExternalParentWithName("SampleExternalInterface", "SampleExternalGenericInterface") shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalInterface", "OtherInterface") shouldBeEqualTo true
            hasExternalParentWithName(listOf("SampleExternalInterface")) shouldBeEqualTo true
            hasExternalParentWithName(listOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentWithName(listOf("SampleExternalInterface", "SampleExternalGenericInterface")) shouldBeEqualTo true
            hasExternalParentWithName(listOf("SampleExternalInterface", "OtherInterface")) shouldBeEqualTo true
            hasExternalParentWithName(setOf("SampleExternalInterface")) shouldBeEqualTo true
            hasExternalParentWithName(setOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentWithName(setOf("SampleExternalInterface", "SampleExternalGenericInterface")) shouldBeEqualTo true
            hasExternalParentWithName(setOf("SampleExternalInterface", "OtherInterface")) shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalInterface") shouldBeEqualTo true
            hasExternalParentsWithAllNames("OtherInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames("SampleExternalInterface", "SampleExternalGenericInterface") shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalInterface", "OtherInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("SampleExternalInterface")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(listOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("SampleExternalInterface", "SampleExternalGenericInterface")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(listOf("SampleExternalInterface", "OtherInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(setOf("SampleExternalInterface")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(setOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(setOf("SampleExternalInterface", "SampleExternalGenericInterface")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(setOf("SampleExternalInterface", "OtherInterface")) shouldBeEqualTo false
            hasExternalParent { it.name == "SampleExternalInterface" } shouldBeEqualTo true
            hasExternalParent { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllExternalParents { it.name == "SampleExternalInterface" } shouldBeEqualTo false
            hasAllExternalParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllExternalParents { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasExternalParentOf(SampleExternalInterface::class) shouldBeEqualTo true
            hasExternalParentOf(SampleExternalInterface::class, SampleParentClass::class) shouldBeEqualTo true
            hasExternalParentOf(listOf(SampleExternalInterface::class)) shouldBeEqualTo true
            hasExternalParentOf(listOf(SampleExternalInterface::class, SampleParentClass::class)) shouldBeEqualTo true
            hasExternalParentOf(setOf(SampleExternalInterface::class)) shouldBeEqualTo true
            hasExternalParentOf(setOf(SampleExternalInterface::class, SampleParentClass::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleExternalInterface::class) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleExternalInterface::class, SampleParentClass::class) shouldBeEqualTo false
            hasAllExternalParentsOf(SampleExternalInterface::class, SampleExternalGenericInterface::class) shouldBeEqualTo true
            hasAllExternalParentsOf(listOf(SampleExternalInterface::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(listOf(SampleExternalInterface::class, SampleParentClass::class)) shouldBeEqualTo false
            hasAllExternalParentsOf(listOf(SampleExternalInterface::class, SampleExternalGenericInterface::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(setOf(SampleExternalInterface::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(setOf(SampleExternalInterface::class, SampleParentClass::class)) shouldBeEqualTo false
            hasAllExternalParentsOf(setOf(SampleExternalInterface::class, SampleExternalGenericInterface::class)) shouldBeEqualTo true
        }
    }

    @Suppress("detekt.LongMethod")
    @Test
    fun `object-has-indirect-external-parents`() {
        // given
        val sut =
            getSnippetFile("object-has-indirect-external-parents")
                .objects()
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
            countExternalParents(indirectParents = false) { it.name == "SampleExternalInterface" } shouldBeEqualTo 0
            countExternalParents(indirectParents = true) { it.name == "SampleExternalInterface" } shouldBeEqualTo 1
            countExternalParents(indirectParents = false) { it.hasNameStartingWith("SampleExternal") } shouldBeEqualTo 0
            countExternalParents(indirectParents = true) { it.hasNameStartingWith("SampleExternal") } shouldBeEqualTo 2
            hasExternalParents(indirectParents = false) shouldBeEqualTo false
            hasExternalParents(indirectParents = true) shouldBeEqualTo true
            hasExternalParentWithName(emptyList(), indirectParents = false) shouldBeEqualTo false
            hasExternalParentWithName(emptySet(), indirectParents = false) shouldBeEqualTo false
            hasExternalParentsWithAllNames(emptyList(), indirectParents = false) shouldBeEqualTo false
            hasExternalParentsWithAllNames(emptySet(), indirectParents = false) shouldBeEqualTo false
            hasExternalParentWithName(emptyList(), indirectParents = true) shouldBeEqualTo true
            hasExternalParentWithName(emptySet(), indirectParents = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames(emptyList(), indirectParents = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames(emptySet(), indirectParents = true) shouldBeEqualTo true
            hasExternalParentWithName("SampleExternalInterface", indirectParents = true) shouldBeEqualTo true
            hasExternalParentWithName("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasExternalParentWithName(
                "SampleExternalInterface",
                "SampleExternalClass",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentWithName(
                "SampleExternalInterface",
                "OtherInterface",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentWithName(listOf("SampleExternalInterface"), indirectParents = true) shouldBeEqualTo true
            hasExternalParentWithName(listOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasExternalParentWithName(listOf(
                "SampleExternalInterface",
                "SampleExternalClass"),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentWithName(listOf(
                "SampleExternalInterface",
                "OtherInterface"),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentsWithAllNames("SampleExternalInterface", indirectParents = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasExternalParentsWithAllNames(
                "SampleExternalInterface",
                "SampleExternalClass",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentsWithAllNames(
                "SampleExternalInterface",
                "OtherInterface",
                indirectParents = true,
            ) shouldBeEqualTo false

            hasExternalParentsWithAllNames(listOf("SampleExternalInterface"), indirectParents = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames(listOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf(
                "SampleExternalInterface",
                "SampleExternalClass"),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentsWithAllNames(listOf(
                "SampleExternalInterface",
                "OtherInterface"),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasExternalParent(indirectParents = true) { it.name == "SampleExternalInterface" } shouldBeEqualTo true
            hasExternalParent(indirectParents = true) { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllExternalParents(indirectParents = true) { it.name == "SampleExternalInterface" } shouldBeEqualTo false
            hasAllExternalParents(indirectParents = true) { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllExternalParents(indirectParents = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasExternalParentOf(SampleExternalInterface::class, indirectParents = true) shouldBeEqualTo true
            hasExternalParentOf(
                SampleExternalInterface::class,
                SampleParentClass::class,
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentOf(listOf(SampleExternalInterface::class), indirectParents = true) shouldBeEqualTo true
            hasExternalParentOf(listOf(
                SampleExternalInterface::class,
                SampleParentClass::class),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasAllExternalParentsOf(SampleExternalInterface::class, indirectParents = true) shouldBeEqualTo true
            hasAllExternalParentsOf(
                SampleExternalInterface::class,
                SampleParentClass::class,
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllExternalParentsOf(
                SampleExternalInterface::class,
                SampleExternalClass::class,
                indirectParents = true,
            ) shouldBeEqualTo true

            hasAllExternalParentsOf(listOf(SampleExternalInterface::class), indirectParents = true) shouldBeEqualTo true
            hasAllExternalParentsOf(listOf(
                SampleExternalInterface::class,
                SampleParentClass::class),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllExternalParentsOf(listOf(
                SampleExternalInterface::class,
                SampleExternalClass::class),
                indirectParents = true,
            ) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-has-indirect-repeated-external-parents`() {
        // given
        val sut =
            getSnippetFile("object-has-indirect-repeated-external-parents")
                .objects()
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
        getSnippetKoScope("core/declaration/koobject/snippet/forkoexternalparentprovider/", fileName)
}
