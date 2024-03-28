package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface
import com.lemonappdev.konsist.testdata.SampleParentInterface1
import com.lemonappdev.konsist.testdata.SampleParentInterface2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoObjectDeclarationForKoParentProviderTest {
    @Test
    fun `object-has-no-parents`() {
        // given
        val sut =
            getSnippetFile("object-has-no-parents")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            parents shouldBeEqualTo emptyList()
            numParents shouldBeEqualTo 0
            countParents { it.name == "SampleParentClass" } shouldBeEqualTo 0
            hasParents() shouldBeEqualTo false
            hasParentWithName(emptyList()) shouldBeEqualTo false
            hasParentWithName(emptySet()) shouldBeEqualTo false
            hasParentsWithAllNames(emptyList()) shouldBeEqualTo false
            hasParentsWithAllNames(emptySet()) shouldBeEqualTo false
            hasParentWithName("SampleParentClass") shouldBeEqualTo false
            hasParentWithName(listOf("SampleParentClass")) shouldBeEqualTo false
            hasParentWithName(setOf("SampleParentClass")) shouldBeEqualTo false
            hasParentsWithAllNames("SampleParentClass", "SampleParentInterface") shouldBeEqualTo false
            hasParentsWithAllNames(listOf("SampleParentClass", "SampleParentInterface")) shouldBeEqualTo false
            hasParentsWithAllNames(setOf("SampleParentClass", "SampleParentInterface")) shouldBeEqualTo false
            hasParent { it.name == "SampleParentClass" } shouldBeEqualTo false
            hasAllParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasParentOf(SampleParentClass::class) shouldBeEqualTo false
            hasParentOf(listOf(SampleParentClass::class)) shouldBeEqualTo false
            hasParentOf(setOf(SampleParentClass::class)) shouldBeEqualTo false
            hasAllParentsOf(SampleParentClass::class, SampleParentInterface::class) shouldBeEqualTo false
            hasAllParentsOf(listOf(SampleParentClass::class, SampleParentInterface::class)) shouldBeEqualTo false
            hasAllParentsOf(setOf(SampleParentClass::class, SampleParentInterface::class)) shouldBeEqualTo false
            hasParents("SampleParentClass") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-direct-parent-class-interfaces-and-external-parent`() {
        // given
        val sut =
            getSnippetFile("object-has-direct-parent-class-interfaces-and-external-parent")
                .objects()
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
            countParents { it.name == "SampleParentClass" } shouldBeEqualTo 1
            countParents { it.hasNameStartingWith("SampleParentInterface") } shouldBeEqualTo 2
            hasParents() shouldBeEqualTo true
            hasParentWithName(emptyList()) shouldBeEqualTo true
            hasParentWithName(emptySet()) shouldBeEqualTo true
            hasParentsWithAllNames(emptyList()) shouldBeEqualTo true
            hasParentsWithAllNames(emptySet()) shouldBeEqualTo true
            hasParentWithName("SampleParentClass") shouldBeEqualTo true
            hasParentWithName("OtherInterface") shouldBeEqualTo false
            hasParentWithName("SampleParentClass", "OtherInterface") shouldBeEqualTo true
            hasParentWithName(listOf("SampleParentClass")) shouldBeEqualTo true
            hasParentWithName(listOf("OtherInterface")) shouldBeEqualTo false
            hasParentWithName(listOf("SampleParentClass", "OtherInterface")) shouldBeEqualTo true
            hasParentWithName(setOf("SampleParentClass")) shouldBeEqualTo true
            hasParentWithName(setOf("OtherInterface")) shouldBeEqualTo false
            hasParentWithName(setOf("SampleParentClass", "OtherInterface")) shouldBeEqualTo true
            hasParentsWithAllNames("SampleParentClass") shouldBeEqualTo true
            hasParentsWithAllNames("OtherInterface") shouldBeEqualTo false
            hasParentsWithAllNames("SampleParentClass", "SampleParentInterface1") shouldBeEqualTo true
            hasParentsWithAllNames("SampleParentClass", "OtherInterface") shouldBeEqualTo false
            hasParentsWithAllNames(listOf("SampleParentClass")) shouldBeEqualTo true
            hasParentsWithAllNames(listOf("OtherInterface")) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("SampleParentClass", "SampleParentInterface1")) shouldBeEqualTo true
            hasParentsWithAllNames(listOf("SampleParentClass", "OtherInterface")) shouldBeEqualTo false
            hasParentsWithAllNames(setOf("SampleParentClass")) shouldBeEqualTo true
            hasParentsWithAllNames(setOf("OtherInterface")) shouldBeEqualTo false
            hasParentsWithAllNames(setOf("SampleParentClass", "SampleParentInterface1")) shouldBeEqualTo true
            hasParentsWithAllNames(setOf("SampleParentClass", "OtherInterface")) shouldBeEqualTo false
            hasParent { it.name == "SampleParentClass" } shouldBeEqualTo true
            hasParent { it.name == "OtherClass" } shouldBeEqualTo false
            hasAllParents { it.name == "SampleParentClass" } shouldBeEqualTo false
            hasAllParents { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllParents { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParentOf(SampleParentClass::class) shouldBeEqualTo true
            hasParentOf(SampleParentClass::class, SampleInterface::class) shouldBeEqualTo true
            hasParentOf(listOf(SampleParentClass::class)) shouldBeEqualTo true
            hasParentOf(listOf(SampleParentClass::class, SampleInterface::class)) shouldBeEqualTo true
            hasParentOf(setOf(SampleParentClass::class)) shouldBeEqualTo true
            hasParentOf(setOf(SampleParentClass::class, SampleInterface::class)) shouldBeEqualTo true
            hasAllParentsOf(SampleParentClass::class) shouldBeEqualTo true
            hasAllParentsOf(SampleParentClass::class, SampleInterface::class) shouldBeEqualTo false
            hasAllParentsOf(SampleParentClass::class, SampleParentInterface1::class) shouldBeEqualTo true
            hasAllParentsOf(listOf(SampleParentClass::class)) shouldBeEqualTo true
            hasAllParentsOf(listOf(SampleParentClass::class, SampleInterface::class)) shouldBeEqualTo false
            hasAllParentsOf(listOf(SampleParentClass::class, SampleParentInterface1::class)) shouldBeEqualTo true
            hasAllParentsOf(setOf(SampleParentClass::class)) shouldBeEqualTo true
            hasAllParentsOf(setOf(SampleParentClass::class, SampleInterface::class)) shouldBeEqualTo false
            hasAllParentsOf(setOf(SampleParentClass::class, SampleParentInterface1::class)) shouldBeEqualTo true
            hasParents("SampleParentClass") shouldBeEqualTo true
            hasParents("OtherInterface") shouldBeEqualTo false
            hasParents("SampleParentClass", "SampleParentInterface1") shouldBeEqualTo true
            hasParents("SampleParentClass", "SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-indirect-parents`() {
        // given
        val sut =
            getSnippetFile("object-has-indirect-parents")
                .objects()
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
            hasParentWithName(emptyList(), indirectParents = true) shouldBeEqualTo true
            hasParentWithName(emptySet(), indirectParents = true) shouldBeEqualTo true
            hasParentsWithAllNames(emptyList(), indirectParents = true) shouldBeEqualTo true
            hasParentsWithAllNames(emptySet(), indirectParents = true) shouldBeEqualTo true
            hasParentWithName("SampleParentInterface2", indirectParents = true) shouldBeEqualTo true
            hasParentWithName("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasParentWithName("SampleParentInterface2", "OtherInterface", indirectParents = true) shouldBeEqualTo true
            hasParentWithName(listOf("SampleParentInterface2"), indirectParents = true) shouldBeEqualTo true
            hasParentWithName(listOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasParentWithName(listOf("SampleParentInterface2", "OtherInterface"), indirectParents = true) shouldBeEqualTo true
            hasParentWithName(setOf("SampleParentInterface2"), indirectParents = true) shouldBeEqualTo true
            hasParentWithName(setOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasParentWithName(setOf("SampleParentInterface2", "OtherInterface"), indirectParents = true) shouldBeEqualTo true
            hasParentsWithAllNames("SampleParentInterface2", indirectParents = true) shouldBeEqualTo true
            hasParentsWithAllNames("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasParentsWithAllNames(
                "SampleParentInterface2",
                "SampleParentInterface1",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentsWithAllNames("SampleParentInterface2", "OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("SampleParentInterface2"), indirectParents = true) shouldBeEqualTo true
            hasParentsWithAllNames(listOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasParentsWithAllNames(
                listOf(
                    "SampleParentInterface2",
                    "SampleParentInterface1",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentsWithAllNames(listOf("SampleParentInterface2", "OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasParentsWithAllNames(setOf("SampleParentInterface2"), indirectParents = true) shouldBeEqualTo true
            hasParentsWithAllNames(setOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasParentsWithAllNames(
                setOf(
                    "SampleParentInterface2",
                    "SampleParentInterface1",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentsWithAllNames(setOf("SampleParentInterface2", "OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasParent(indirectParents = true) { it.name == "SampleParentInterface2" } shouldBeEqualTo true
            hasParent(indirectParents = true) { it.name == "OtherClass" } shouldBeEqualTo false
            hasAllParents(indirectParents = true) { it.name == "SampleParentInterface2" } shouldBeEqualTo false
            hasAllParents(indirectParents = true) { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllParents(indirectParents = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParentOf(SampleParentInterface2::class, indirectParents = true) shouldBeEqualTo true
            hasParentOf(SampleParentInterface2::class, SampleInterface::class, indirectParents = true) shouldBeEqualTo true
            hasParentOf(listOf(SampleParentInterface2::class), indirectParents = true) shouldBeEqualTo true
            hasParentOf(listOf(SampleParentInterface2::class, SampleInterface::class), indirectParents = true) shouldBeEqualTo true
            hasParentOf(setOf(SampleParentInterface2::class), indirectParents = true) shouldBeEqualTo true
            hasParentOf(setOf(SampleParentInterface2::class, SampleInterface::class), indirectParents = true) shouldBeEqualTo true
            hasAllParentsOf(SampleParentInterface2::class, indirectParents = true) shouldBeEqualTo true
            hasAllParentsOf(
                SampleParentInterface2::class,
                SampleInterface::class,
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllParentsOf(listOf(SampleParentInterface2::class), indirectParents = true) shouldBeEqualTo true
            hasAllParentsOf(
                listOf(
                    SampleParentInterface2::class,
                    SampleInterface::class,
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllParentsOf(setOf(SampleParentInterface2::class), indirectParents = true) shouldBeEqualTo true
            hasAllParentsOf(
                setOf(
                    SampleParentInterface2::class,
                    SampleInterface::class,
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-repeated-indirect-parents`() {
        // given
        val sut =
            getSnippetFile("object-has-repeated-indirect-parents")
                .objects()
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
    fun `object-has-parent-defined-by-import-alias`() {
        // given
        val sut =
            getSnippetFile("object-has-parent-defined-by-import-alias")
                .objects()
                .first()

        // then
        assertSoftly(sut.parents.first()) {
            name shouldBeEqualTo "SampleParentInterface"
            fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentInterface"
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkoparentprovider/", fileName)
}
