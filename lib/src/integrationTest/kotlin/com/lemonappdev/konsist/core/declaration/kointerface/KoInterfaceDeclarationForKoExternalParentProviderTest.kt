package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.externalfixture.FixtureExternalGenericInterface
import com.lemonappdev.konsist.externalfixture.FixtureExternalInterface
import com.lemonappdev.konsist.testdata.FixtureParentClass
import com.lemonappdev.konsist.testdata.FixtureParentInterface1
import com.lemonappdev.konsist.testdata.FixtureParentInterface2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
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
            countExternalParents { it.name == "FixtureExternalParent" } shouldBeEqualTo 0
            hasExternalParents() shouldBeEqualTo false
            hasExternalParentWithName(emptyList()) shouldBeEqualTo false
            hasExternalParentWithName(emptySet()) shouldBeEqualTo false
            hasExternalParentsWithAllNames(emptyList()) shouldBeEqualTo false
            hasExternalParentsWithAllNames(emptySet()) shouldBeEqualTo false
            hasExternalParentWithName("FixtureExternalParent1", "FixtureExternalParent2") shouldBeEqualTo false
            hasExternalParentWithName(listOf("FixtureExternalParent1", "FixtureExternalParent2")) shouldBeEqualTo false
            hasExternalParentWithName(setOf("FixtureExternalParent1", "FixtureExternalParent2")) shouldBeEqualTo false
            hasExternalParentsWithAllNames("FixtureExternalParent1", "FixtureExternalParent2") shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("FixtureExternalParent1", "FixtureExternalParent2")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(setOf("FixtureExternalParent1", "FixtureExternalParent2")) shouldBeEqualTo false
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
    fun `interface-has-only-direct-external-parents`() {
        // given
        val sut =
            getSnippetFile("interface-has-only-direct-external-parents")
                .interfaces()
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

    @Test
    fun `interface-has-internal-and-external-parents`() {
        // given
        val sut =
            getSnippetFile("interface-has-internal-and-external-parents")
                .interfaces()
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
                    "FixtureExternalInterface",
                    "FixtureExternalGenericInterface<Int>",
                )
            numExternalParents(indirectParents = false) shouldBeEqualTo 0
            numExternalParents(indirectParents = true) shouldBeEqualTo 2
            countExternalParents(indirectParents = false) { it.name == "FixtureExternalGenericInterface<Int>" } shouldBeEqualTo 0
            countExternalParents(indirectParents = true) { it.name == "FixtureExternalGenericInterface<Int>" } shouldBeEqualTo 1
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
            hasExternalParentWithName("FixtureExternalGenericInterface<Int>", indirectParents = true) shouldBeEqualTo true
            hasExternalParentWithName("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasExternalParentWithName(
                "FixtureExternalGenericInterface<Int>",
                "FixtureExternalInterface",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentWithName(
                "FixtureExternalGenericInterface<Int>",
                "OtherInterface",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentWithName(listOf("FixtureExternalGenericInterface<Int>"), indirectParents = true) shouldBeEqualTo true
            hasExternalParentWithName(listOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasExternalParentWithName(
                listOf(
                    "FixtureExternalGenericInterface<Int>",
                    "FixtureExternalInterface",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentWithName(
                listOf(
                    "FixtureExternalGenericInterface<Int>",
                    "OtherInterface",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentsWithAllNames("FixtureExternalGenericInterface<Int>", indirectParents = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasExternalParentsWithAllNames(
                "FixtureExternalGenericInterface<Int>",
                "FixtureExternalInterface",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentsWithAllNames(
                "FixtureExternalGenericInterface<Int>",
                "OtherInterface",
                indirectParents = true,
            ) shouldBeEqualTo false

            hasExternalParentsWithAllNames(listOf("FixtureExternalGenericInterface<Int>"), indirectParents = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames(listOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasExternalParentsWithAllNames(
                listOf(
                    "FixtureExternalGenericInterface<Int>",
                    "FixtureExternalInterface",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentsWithAllNames(
                listOf(
                    "FixtureExternalGenericInterface<Int>",
                    "OtherInterface",
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasExternalParent(indirectParents = true) { it.name == "FixtureExternalGenericInterface<Int>" } shouldBeEqualTo true
            hasExternalParent(indirectParents = true) { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllExternalParents(indirectParents = true) { it.name == "FixtureExternalGenericInterface<Int>" } shouldBeEqualTo false
            hasAllExternalParents(indirectParents = true) { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllExternalParents(indirectParents = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasExternalParentOf(FixtureExternalGenericInterface::class, indirectParents = true) shouldBeEqualTo true
            hasExternalParentOf(
                FixtureExternalGenericInterface::class,
                FixtureParentClass::class,
                indirectParents = true,
            ) shouldBeEqualTo true
            hasExternalParentOf(listOf(FixtureExternalGenericInterface::class), indirectParents = true) shouldBeEqualTo true
            hasExternalParentOf(
                listOf(
                    FixtureExternalGenericInterface::class,
                    FixtureParentClass::class,
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasAllExternalParentsOf(FixtureExternalGenericInterface::class, indirectParents = true) shouldBeEqualTo true
            hasAllExternalParentsOf(
                FixtureExternalGenericInterface::class,
                FixtureParentClass::class,
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllExternalParentsOf(
                FixtureExternalGenericInterface::class,
                FixtureExternalInterface::class,
                indirectParents = true,
            ) shouldBeEqualTo true

            hasAllExternalParentsOf(listOf(FixtureExternalGenericInterface::class), indirectParents = true) shouldBeEqualTo true
            hasAllExternalParentsOf(
                listOf(
                    FixtureExternalGenericInterface::class,
                    FixtureParentClass::class,
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllExternalParentsOf(
                listOf(
                    FixtureExternalGenericInterface::class,
                    FixtureExternalInterface::class,
                ),
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
            externalParents(indirectParents = false).map { it.name } shouldBeEqualTo listOf("FixtureExternalInterface")
            externalParents(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "FixtureExternalInterface",
                    "FixtureExternalGenericInterface<Int>",
                )
            numExternalParents(indirectParents = false) shouldBeEqualTo 1
            numExternalParents(indirectParents = true) shouldBeEqualTo 2
        }
    }

    @Test
    fun `interface-has-no-external-parent-ignore-case`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-external-parent-ignore-case")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasExternalParentWithName("fixtureexternalinterface") shouldBeEqualTo false
            hasExternalParentWithName("fixtureexternalinterface", ignoreCase = true) shouldBeEqualTo false
            hasExternalParentWithName(listOf("fixtureexternalinterface")) shouldBeEqualTo false
            hasExternalParentWithName(listOf("fixtureexternalinterface"), ignoreCase = true) shouldBeEqualTo false
            hasExternalParentWithName(setOf("fixtureexternalinterface")) shouldBeEqualTo false
            hasExternalParentWithName(setOf("fixtureexternalinterface"), ignoreCase = true) shouldBeEqualTo false
            hasExternalParentsWithAllNames("fixtureexternalinterface", "fixtureexternalinterface2") shouldBeEqualTo false
            hasExternalParentsWithAllNames("fixtureexternalinterface", "fixtureexternalinterface2", ignoreCase = true) shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("fixtureexternalinterface", "fixtureexternalinterface2")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(
                listOf("fixtureexternalinterface", "fixtureexternalinterface2"),
                ignoreCase = true,
            ) shouldBeEqualTo
                false
            hasExternalParentsWithAllNames(setOf("fixtureexternalinterface", "fixtureexternalinterface2")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(
                setOf("fixtureexternalinterface", "fixtureexternalinterface2"),
                ignoreCase = true,
            ) shouldBeEqualTo
                false
        }
    }

    @Test
    fun `interface-has-external-parents-ignore-case`() {
        // given
        val sut =
            getSnippetFile("interface-has-external-parents-ignore-case")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasExternalParentWithName("fixtureexternalinterface") shouldBeEqualTo false
            hasExternalParentWithName("fixtureexternalinterface", ignoreCase = true) shouldBeEqualTo true
            hasExternalParentWithName("otherexternalinterface") shouldBeEqualTo false
            hasExternalParentWithName("otherexternalinterface", ignoreCase = true) shouldBeEqualTo false
            hasExternalParentWithName("fixtureexternalinterface", "otherName") shouldBeEqualTo false
            hasExternalParentWithName("fixtureexternalinterface", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasExternalParentWithName(listOf("fixtureexternalinterface")) shouldBeEqualTo false
            hasExternalParentWithName(listOf("fixtureexternalinterface"), ignoreCase = true) shouldBeEqualTo true
            hasExternalParentWithName(listOf("otherexternalinterface")) shouldBeEqualTo false
            hasExternalParentWithName(listOf("otherexternalinterface"), ignoreCase = true) shouldBeEqualTo false
            hasExternalParentWithName(listOf("fixtureexternalinterface", "otherName")) shouldBeEqualTo false
            hasExternalParentWithName(listOf("fixtureexternalinterface", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames("fixtureexternalinterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames("fixtureexternalinterface", ignoreCase = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames("fixtureexternalinterface", "fixtureexternalgenericinterface<int>") shouldBeEqualTo false
            hasExternalParentsWithAllNames(
                "fixtureexternalinterface",
                "fixtureexternalgenericinterface<int>",
                ignoreCase = true,
            ) shouldBeEqualTo
                true
            hasExternalParentsWithAllNames("fixtureexternalinterface", "otherexternalinterface") shouldBeEqualTo false
            hasExternalParentsWithAllNames("fixtureexternalinterface", "otherexternalinterface", ignoreCase = true) shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("fixtureexternalinterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("fixtureexternalinterface"), ignoreCase = true) shouldBeEqualTo true
            hasExternalParentsWithAllNames(listOf("fixtureexternalinterface", "fixtureexternalgenericinterface<int>")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(
                listOf("fixtureexternalinterface", "fixtureexternalgenericinterface<int>"),
                ignoreCase = true,
            ) shouldBeEqualTo
                true
            hasExternalParentsWithAllNames(listOf("fixtureexternalinterface", "otherexternalinterface")) shouldBeEqualTo false
            hasExternalParentsWithAllNames(listOf("fixtureexternalinterface", "otherexternalinterface"), ignoreCase = true) shouldBeEqualTo
                false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkoexternalparentprovider/", fileName)
}
