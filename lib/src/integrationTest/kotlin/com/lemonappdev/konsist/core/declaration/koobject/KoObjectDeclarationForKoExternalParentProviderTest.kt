package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.externalfixture.FixtureExternalClass
import com.lemonappdev.konsist.externalfixture.FixtureExternalGenericInterface
import com.lemonappdev.konsist.externalfixture.FixtureExternalInterface
import com.lemonappdev.konsist.testdata.FixtureParentClass
import com.lemonappdev.konsist.testdata.FixtureParentInterface1
import com.lemonappdev.konsist.testdata.FixtureParentInterface2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
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
            countExternalParents { it.name == "FixtureExternalParent" } shouldBeEqualTo 0
            hasExternalParents() shouldBeEqualTo false
            hasExternalParentWithName(emptyList()) shouldBeEqualTo false
            hasExternalParentWithName(emptySet()) shouldBeEqualTo false
            hasExternalParentsWithAllNames(emptyList()) shouldBeEqualTo false
            hasExternalParentsWithAllNames(emptySet()) shouldBeEqualTo false
            hasExternalParentWithName("FixtureExternalClass", "FixtureExternalInterface") shouldBeEqualTo false
            hasExternalParentWithName(listOf("FixtureExternalClass", "FixtureExternalInterface")) shouldBeEqualTo false
            hasExternalParentWithName(setOf("FixtureExternalClass", "FixtureExternalInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames("FixtureExternalClass", "FixtureExternalInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("FixtureExternalClass", "FixtureExternalInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(setOf("FixtureExternalClass", "FixtureExternalInterface")) shouldBeEqualTo false
            hasExternalParent { it.name == "FixtureExternalParent" } shouldBeEqualTo false
            hasAllExternalParents { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasExternalParentOf(FixtureParentInterface1::class) shouldBeEqualTo false
            hasExternalParentOf(listOf(FixtureParentInterface1::class)) shouldBeEqualTo false
            hasExternalParentOf(setOf(FixtureParentInterface1::class)) shouldBeEqualTo false
            hasAllExternalParentsOf(FixtureParentInterface1::class, FixtureParentInterface2::class) shouldBeEqualTo false
            hasAllExternalParentsOf(listOf(FixtureParentInterface1::class, FixtureParentInterface2::class)) shouldBeEqualTo false
            hasAllExternalParentsOf(setOf(FixtureParentInterface1::class, FixtureParentInterface2::class)) shouldBeEqualTo false
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
            externalParents().map { it.name } shouldBeEqualTo listOf("FixtureExternalClass", "FixtureExternalInterface")
            numExternalParents() shouldBeEqualTo 2
            countExternalParents { it.name == "FixtureExternalClass" } shouldBeEqualTo 1
            countExternalParents { it.hasNameStartingWith("FixtureExternal") } shouldBeEqualTo 2
            hasExternalParents() shouldBeEqualTo true
            hasExternalParentWithName(emptyList()) shouldBeEqualTo true
            hasExternalParentWithName(emptySet()) shouldBeEqualTo true
            hasExternalParentsWithAllNames(emptyList()) shouldBeEqualTo true
            hasExternalParentsWithAllNames(emptySet()) shouldBeEqualTo true
            hasExternalParentWithName("FixtureExternalClass") shouldBeEqualTo true
            hasExternalParentWithName("OtherInterface") shouldBeEqualTo false
            hasExternalParentWithName("FixtureExternalClass", "FixtureExternalInterface") shouldBeEqualTo true
            hasExternalParentWithName("FixtureExternalClass", "OtherInterface") shouldBeEqualTo true
            hasExternalParentWithName(listOf("FixtureExternalClass")) shouldBeEqualTo true
            hasExternalParentWithName(listOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentWithName(listOf("FixtureExternalClass", "FixtureExternalInterface")) shouldBeEqualTo true
            hasExternalParentWithName(listOf("FixtureExternalClass", "OtherInterface")) shouldBeEqualTo true
            hasExternalParentWithName(setOf("FixtureExternalClass")) shouldBeEqualTo true
            hasExternalParentWithName(setOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentWithName(setOf("FixtureExternalClass", "FixtureExternalInterface")) shouldBeEqualTo true
            hasExternalParentWithName(setOf("FixtureExternalClass", "OtherInterface")) shouldBeEqualTo true
            hasExternalParentsWithAllNames("FixtureExternalClass") shouldBeEqualTo true
            hasExternalParentsWithAllNames("OtherInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames("FixtureExternalClass", "FixtureExternalInterface") shouldBeEqualTo true
            hasExternalParentsWithAllNames("FixtureExternalClass", "OtherInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("FixtureExternalClass")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(listOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("FixtureExternalClass", "FixtureExternalInterface")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(listOf("FixtureExternalClass", "OtherInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(setOf("FixtureExternalClass")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(setOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(setOf("FixtureExternalClass", "FixtureExternalInterface")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(setOf("FixtureExternalClass", "OtherInterface")) shouldBeEqualTo false
            hasExternalParent { it.name == "FixtureExternalClass" } shouldBeEqualTo true
            hasExternalParent { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllExternalParents { it.name == "FixtureExternalClass" } shouldBeEqualTo false
            hasAllExternalParents { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllExternalParents { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasExternalParentOf(FixtureExternalClass::class) shouldBeEqualTo true
            hasExternalParentOf(FixtureExternalClass::class, FixtureParentClass::class) shouldBeEqualTo true
            hasExternalParentOf(listOf(FixtureExternalClass::class)) shouldBeEqualTo true
            hasExternalParentOf(listOf(FixtureExternalClass::class, FixtureParentClass::class)) shouldBeEqualTo true
            hasExternalParentOf(setOf(FixtureExternalClass::class)) shouldBeEqualTo true
            hasExternalParentOf(setOf(FixtureExternalClass::class, FixtureParentClass::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(FixtureExternalClass::class) shouldBeEqualTo true
            hasAllExternalParentsOf(FixtureExternalClass::class, FixtureParentClass::class) shouldBeEqualTo false
            hasAllExternalParentsOf(FixtureExternalClass::class, FixtureExternalInterface::class) shouldBeEqualTo true
            hasAllExternalParentsOf(listOf(FixtureExternalClass::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(listOf(FixtureExternalClass::class, FixtureParentClass::class)) shouldBeEqualTo false
            hasAllExternalParentsOf(listOf(FixtureExternalClass::class, FixtureExternalInterface::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(setOf(FixtureExternalClass::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(setOf(FixtureExternalClass::class, FixtureParentClass::class)) shouldBeEqualTo false
            hasAllExternalParentsOf(setOf(FixtureExternalClass::class, FixtureExternalInterface::class)) shouldBeEqualTo true
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
            externalParents().map { it.name } shouldBeEqualTo listOf("FixtureExternalInterface", "FixtureExternalGenericInterface<Int>")
            numExternalParents() shouldBeEqualTo 2
            countExternalParents { it.name == "FixtureExternalInterface" } shouldBeEqualTo 1
            countExternalParents { it.hasNameStartingWith("FixtureExternal") } shouldBeEqualTo 2
            hasExternalParents() shouldBeEqualTo true
            hasExternalParentWithName(emptyList()) shouldBeEqualTo true
            hasExternalParentWithName(emptySet()) shouldBeEqualTo true
            hasExternalParentsWithAllNames(emptyList()) shouldBeEqualTo true
            hasExternalParentsWithAllNames(emptySet()) shouldBeEqualTo true
            hasExternalParentWithName("FixtureExternalInterface") shouldBeEqualTo true
            hasExternalParentWithName("OtherInterface") shouldBeEqualTo false
            hasExternalParentWithName("FixtureExternalInterface", "FixtureExternalGenericInterface<Int>") shouldBeEqualTo true
            hasExternalParentWithName("FixtureExternalInterface", "OtherInterface") shouldBeEqualTo true
            hasExternalParentWithName(listOf("FixtureExternalInterface")) shouldBeEqualTo true
            hasExternalParentWithName(listOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentWithName(listOf("FixtureExternalInterface", "FixtureExternalGenericInterface<Int>")) shouldBeEqualTo true
            hasExternalParentWithName(listOf("FixtureExternalInterface", "OtherInterface")) shouldBeEqualTo true
            hasExternalParentWithName(setOf("FixtureExternalInterface")) shouldBeEqualTo true
            hasExternalParentWithName(setOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentWithName(setOf("FixtureExternalInterface", "FixtureExternalGenericInterface<Int>")) shouldBeEqualTo true
            hasExternalParentWithName(setOf("FixtureExternalInterface", "OtherInterface")) shouldBeEqualTo true
            hasExternalParentsWithAllNames("FixtureExternalInterface") shouldBeEqualTo true
            hasExternalParentsWithAllNames("OtherInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames("FixtureExternalInterface", "FixtureExternalGenericInterface<Int>") shouldBeEqualTo true
            hasExternalParentsWithAllNames("FixtureExternalInterface", "OtherInterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("FixtureExternalInterface")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(listOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("FixtureExternalInterface", "FixtureExternalGenericInterface<Int>")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(listOf("FixtureExternalInterface", "OtherInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(setOf("FixtureExternalInterface")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(setOf("OtherInterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(setOf("FixtureExternalInterface", "FixtureExternalGenericInterface<Int>")) shouldBeEqualTo true
            hasExternalParentsWithAllNames(setOf("FixtureExternalInterface", "OtherInterface")) shouldBeEqualTo false
            hasExternalParent { it.name == "FixtureExternalInterface" } shouldBeEqualTo true
            hasExternalParent { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllExternalParents { it.name == "FixtureExternalInterface" } shouldBeEqualTo false
            hasAllExternalParents { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllExternalParents { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasExternalParentOf(FixtureExternalInterface::class) shouldBeEqualTo true
            hasExternalParentOf(FixtureExternalInterface::class, FixtureParentClass::class) shouldBeEqualTo true
            hasExternalParentOf(listOf(FixtureExternalInterface::class)) shouldBeEqualTo true
            hasExternalParentOf(listOf(FixtureExternalInterface::class, FixtureParentClass::class)) shouldBeEqualTo true
            hasExternalParentOf(setOf(FixtureExternalInterface::class)) shouldBeEqualTo true
            hasExternalParentOf(setOf(FixtureExternalInterface::class, FixtureParentClass::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(FixtureExternalInterface::class) shouldBeEqualTo true
            hasAllExternalParentsOf(FixtureExternalInterface::class, FixtureParentClass::class) shouldBeEqualTo false
            hasAllExternalParentsOf(FixtureExternalInterface::class, FixtureExternalGenericInterface::class) shouldBeEqualTo true
            hasAllExternalParentsOf(listOf(FixtureExternalInterface::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(listOf(FixtureExternalInterface::class, FixtureParentClass::class)) shouldBeEqualTo false
            hasAllExternalParentsOf(listOf(FixtureExternalInterface::class, FixtureExternalGenericInterface::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(setOf(FixtureExternalInterface::class)) shouldBeEqualTo true
            hasAllExternalParentsOf(setOf(FixtureExternalInterface::class, FixtureParentClass::class)) shouldBeEqualTo false
            hasAllExternalParentsOf(setOf(FixtureExternalInterface::class, FixtureExternalGenericInterface::class)) shouldBeEqualTo true
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
                    "FixtureExternalClass",
                    "FixtureExternalInterface",
                )
            numExternalParents(indirectParents = false) shouldBeEqualTo 0
            numExternalParents(indirectParents = true) shouldBeEqualTo 2
            countExternalParents(indirectParents = false) { it.name == "FixtureExternalInterface" } shouldBeEqualTo 0
            countExternalParents(indirectParents = true) { it.name == "FixtureExternalInterface" } shouldBeEqualTo 1
            countExternalParents(indirectParents = false) { it.hasNameStartingWith("FixtureExternal") } shouldBeEqualTo 0
            countExternalParents(indirectParents = true) { it.hasNameStartingWith("FixtureExternal") } shouldBeEqualTo 2
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
            hasExternalParentWithName("FixtureExternalInterface", indirectParents = true) shouldBeEqualTo true
            hasExternalParentWithName("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasExternalParentWithName(
                "FixtureExternalInterface",
                "FixtureExternalClass",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentWithName(
                "FixtureExternalInterface",
                "OtherInterface",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentWithName(listOf("FixtureExternalInterface"), indirectParents = true) shouldBeEqualTo true
            hasExternalParentWithName(listOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasExternalParentWithName(
                listOf(
                    "FixtureExternalInterface",
                    "FixtureExternalClass",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentWithName(
                listOf(
                    "FixtureExternalInterface",
                    "OtherInterface",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentsWithAllNames("FixtureExternalInterface", indirectParents = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasExternalParentsWithAllNames(
                "FixtureExternalInterface",
                "FixtureExternalClass",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentsWithAllNames(
                "FixtureExternalInterface",
                "OtherInterface",
                indirectParents = true,
            ) shouldBeEqualTo false

            hasExternalParentsWithAllNames(listOf("FixtureExternalInterface"), indirectParents = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames(listOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasExternalParentsWithAllNames(
                listOf(
                    "FixtureExternalInterface",
                    "FixtureExternalClass",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentsWithAllNames(
                listOf(
                    "FixtureExternalInterface",
                    "OtherInterface",
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasExternalParent(indirectParents = true) { it.name == "FixtureExternalInterface" } shouldBeEqualTo true
            hasExternalParent(indirectParents = true) { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllExternalParents(indirectParents = true) { it.name == "FixtureExternalInterface" } shouldBeEqualTo false
            hasAllExternalParents(indirectParents = true) { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllExternalParents(indirectParents = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasExternalParentOf(FixtureExternalInterface::class, indirectParents = true) shouldBeEqualTo true
            hasExternalParentOf(
                FixtureExternalInterface::class,
                FixtureParentClass::class,
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentOf(listOf(FixtureExternalInterface::class), indirectParents = true) shouldBeEqualTo true
            hasExternalParentOf(
                listOf(
                    FixtureExternalInterface::class,
                    FixtureParentClass::class,
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasAllExternalParentsOf(FixtureExternalInterface::class, indirectParents = true) shouldBeEqualTo true
            hasAllExternalParentsOf(
                FixtureExternalInterface::class,
                FixtureParentClass::class,
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllExternalParentsOf(
                FixtureExternalInterface::class,
                FixtureExternalClass::class,
                indirectParents = true,
            ) shouldBeEqualTo true

            hasAllExternalParentsOf(listOf(FixtureExternalInterface::class), indirectParents = true) shouldBeEqualTo true
            hasAllExternalParentsOf(
                listOf(
                    FixtureExternalInterface::class,
                    FixtureParentClass::class,
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllExternalParentsOf(
                listOf(
                    FixtureExternalInterface::class,
                    FixtureExternalClass::class,
                ),
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
            externalParents(indirectParents = false).map { it.name } shouldBeEqualTo listOf("FixtureExternalInterface")
            externalParents(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "FixtureExternalInterface",
                    "FixtureExternalClass",
                )
            numExternalParents(indirectParents = false) shouldBeEqualTo 1
            numExternalParents(indirectParents = true) shouldBeEqualTo 2
        }
    }

    @Test
    fun `object-has-no-external-parent-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-no-external-parent-ignore-case")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasExternalParentWithName("fixtureexternalclass") shouldBeEqualTo false
            hasExternalParentWithName("fixtureexternalclass", ignoreCase = true) shouldBeEqualTo false
            hasExternalParentWithName(listOf("fixtureexternalclass")) shouldBeEqualTo false
            hasExternalParentWithName(listOf("fixtureexternalclass"), ignoreCase = true) shouldBeEqualTo false
            hasExternalParentWithName(setOf("fixtureexternalclass")) shouldBeEqualTo false
            hasExternalParentWithName(setOf("fixtureexternalclass"), ignoreCase = true) shouldBeEqualTo false
            hasExternalParentsWithAllNames("fixtureexternalclass", "fixtureexternalinterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames("fixtureexternalclass", "fixtureexternalinterface", ignoreCase = true) shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("fixtureexternalclass", "fixtureexternalinterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("fixtureexternalclass", "fixtureexternalinterface"), ignoreCase = true) shouldBeEqualTo
                false
            hasExternalParentsWithAllNames(setOf("fixtureexternalclass", "fixtureexternalinterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(setOf("fixtureexternalclass", "fixtureexternalinterface"), ignoreCase = true) shouldBeEqualTo
                false
        }
    }

    @Test
    fun `object-has-external-parents-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-external-parents-ignore-case")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasExternalParentWithName("fixtureexternalclass") shouldBeEqualTo false
            hasExternalParentWithName("fixtureexternalclass", ignoreCase = true) shouldBeEqualTo true
            hasExternalParentWithName("otherexternalinterface") shouldBeEqualTo false
            hasExternalParentWithName("otherexternalinterface", ignoreCase = true) shouldBeEqualTo false
            hasExternalParentWithName("fixtureexternalclass", "otherName") shouldBeEqualTo false
            hasExternalParentWithName("fixtureexternalclass", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasExternalParentWithName(listOf("fixtureexternalclass")) shouldBeEqualTo false
            hasExternalParentWithName(listOf("fixtureexternalclass"), ignoreCase = true) shouldBeEqualTo true
            hasExternalParentWithName(listOf("otherexternalinterface")) shouldBeEqualTo false
            hasExternalParentWithName(listOf("otherexternalinterface"), ignoreCase = true) shouldBeEqualTo false
            hasExternalParentWithName(listOf("fixtureexternalclass", "otherName")) shouldBeEqualTo false
            hasExternalParentWithName(listOf("fixtureexternalclass", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames("fixtureexternalclass") shouldBeEqualTo false
            hasExternalParentsWithAllNames("fixtureexternalclass", ignoreCase = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames("fixtureexternalclass", "fixtureexternalinterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames("fixtureexternalclass", "fixtureexternalinterface", ignoreCase = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames("fixtureexternalclass", "otherexternalinterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames("fixtureexternalclass", "otherexternalinterface", ignoreCase = true) shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("fixtureexternalclass")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("fixtureexternalclass"), ignoreCase = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames(listOf("fixtureexternalclass", "fixtureexternalinterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("fixtureexternalclass", "fixtureexternalinterface"), ignoreCase = true) shouldBeEqualTo
                true
            hasExternalParentsWithAllNames(listOf("fixtureexternalclass", "otherexternalinterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("fixtureexternalclass", "otherexternalinterface"), ignoreCase = true) shouldBeEqualTo
                false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkoexternalparentprovider/", fileName)
}
