package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.FixtureParentClass
import com.lemonappdev.konsist.testdata.FixtureParentInterface
import com.lemonappdev.konsist.testdata.FixtureParentInterface1
import com.lemonappdev.konsist.testdata.FixtureParentInterface2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoObjectDeclarationForKoParentInterfaceProviderTest {
    @Test
    fun `object-has-no-parent-interface`() {
        // given
        val sut =
            getSnippetFile("object-has-no-parent-interface")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            parentInterfaces() shouldBeEqualTo emptyList()
            numParentInterfaces() shouldBeEqualTo 0
            countParentInterfaces { it.name == "FixtureParentInterface" } shouldBeEqualTo 0
            hasParentInterfaces() shouldBeEqualTo false
            hasParentInterfaceWithName(emptyList()) shouldBeEqualTo false
            hasParentInterfaceWithName(emptySet()) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(emptyList()) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(emptySet()) shouldBeEqualTo false
            hasParentInterfaceWithName("FixtureParentInterface1", "FixtureParentInterface2") shouldBeEqualTo false
            hasParentInterfaceWithName(listOf("FixtureParentInterface1", "FixtureParentInterface2")) shouldBeEqualTo false
            hasParentInterfaceWithName(setOf("FixtureParentInterface1", "FixtureParentInterface2")) shouldBeEqualTo false
            hasParentInterfacesWithAllNames("FixtureParentInterface1", "FixtureParentInterface2") shouldBeEqualTo false
            hasParentInterfacesWithAllNames(listOf("FixtureParentInterface1", "FixtureParentInterface2")) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(setOf("FixtureParentInterface1", "FixtureParentInterface2")) shouldBeEqualTo false
            hasParentInterface { it.name == "FixtureParentInterface" } shouldBeEqualTo false
            hasAllParentInterfaces { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasParentInterfaceOf(FixtureParentInterface1::class) shouldBeEqualTo false
            hasParentInterfaceOf(listOf(FixtureParentInterface1::class)) shouldBeEqualTo false
            hasParentInterfaceOf(setOf(FixtureParentInterface1::class)) shouldBeEqualTo false
            hasAllParentInterfacesOf(FixtureParentInterface1::class, FixtureParentInterface2::class) shouldBeEqualTo false
            hasAllParentInterfacesOf(listOf(FixtureParentInterface1::class, FixtureParentInterface2::class)) shouldBeEqualTo false
            hasAllParentInterfacesOf(setOf(FixtureParentInterface1::class, FixtureParentInterface2::class)) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-only-direct-parent-interfaces`() {
        // given
        val sut =
            getSnippetFile("object-has-only-direct-parent-interfaces")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            parentInterfaces().map { it.name } shouldBeEqualTo listOf("FixtureParentInterface1", "FixtureParentInterface2")
            numParentInterfaces() shouldBeEqualTo 2
            countParentInterfaces { it.name == "FixtureParentInterface1" } shouldBeEqualTo 1
            countParentInterfaces { it.hasNameStartingWith("FixtureParentInterface") } shouldBeEqualTo 2
            hasParentInterfaces() shouldBeEqualTo true
            hasParentInterfaceWithName(emptyList()) shouldBeEqualTo true
            hasParentInterfaceWithName(emptySet()) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(emptyList()) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(emptySet()) shouldBeEqualTo true
            hasParentInterfaceWithName("FixtureParentInterface1") shouldBeEqualTo true
            hasParentInterfaceWithName("OtherInterface") shouldBeEqualTo false
            hasParentInterfaceWithName("FixtureParentInterface1", "FixtureParentInterface2") shouldBeEqualTo true
            hasParentInterfaceWithName("FixtureParentInterface1", "OtherInterface") shouldBeEqualTo true
            hasParentInterfaceWithName(listOf("FixtureParentInterface1")) shouldBeEqualTo true
            hasParentInterfaceWithName(listOf("OtherInterface")) shouldBeEqualTo false
            hasParentInterfaceWithName(listOf("FixtureParentInterface1", "FixtureParentInterface2")) shouldBeEqualTo true
            hasParentInterfaceWithName(listOf("FixtureParentInterface1", "OtherInterface")) shouldBeEqualTo true
            hasParentInterfaceWithName(setOf("FixtureParentInterface1")) shouldBeEqualTo true
            hasParentInterfaceWithName(setOf("OtherInterface")) shouldBeEqualTo false
            hasParentInterfaceWithName(setOf("FixtureParentInterface1", "FixtureParentInterface2")) shouldBeEqualTo true
            hasParentInterfaceWithName(setOf("FixtureParentInterface1", "OtherInterface")) shouldBeEqualTo true
            hasParentInterfacesWithAllNames("FixtureParentInterface1") shouldBeEqualTo true
            hasParentInterfacesWithAllNames("OtherInterface") shouldBeEqualTo false
            hasParentInterfacesWithAllNames("FixtureParentInterface1", "FixtureParentInterface2") shouldBeEqualTo true
            hasParentInterfacesWithAllNames("FixtureParentInterface1", "OtherInterface") shouldBeEqualTo false
            hasParentInterfacesWithAllNames(listOf("FixtureParentInterface1")) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(listOf("OtherInterface")) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(listOf("FixtureParentInterface1", "FixtureParentInterface2")) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(listOf("FixtureParentInterface1", "OtherInterface")) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(setOf("FixtureParentInterface1")) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(setOf("OtherInterface")) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(setOf("FixtureParentInterface1", "FixtureParentInterface2")) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(setOf("FixtureParentInterface1", "OtherInterface")) shouldBeEqualTo false
            hasParentInterface { it.name == "FixtureParentInterface1" } shouldBeEqualTo true
            hasParentInterface { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllParentInterfaces { it.name == "FixtureParentInterface1" } shouldBeEqualTo false
            hasAllParentInterfaces { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllParentInterfaces { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParentInterfaceOf(FixtureParentInterface1::class) shouldBeEqualTo true
            hasParentInterfaceOf(FixtureParentInterface1::class, FixtureParentClass::class) shouldBeEqualTo true
            hasParentInterfaceOf(listOf(FixtureParentInterface1::class)) shouldBeEqualTo true
            hasParentInterfaceOf(listOf(FixtureParentInterface1::class, FixtureParentClass::class)) shouldBeEqualTo true
            hasParentInterfaceOf(setOf(FixtureParentInterface1::class)) shouldBeEqualTo true
            hasParentInterfaceOf(setOf(FixtureParentInterface1::class, FixtureParentClass::class)) shouldBeEqualTo true
            hasAllParentInterfacesOf(FixtureParentInterface1::class) shouldBeEqualTo true
            hasAllParentInterfacesOf(FixtureParentInterface1::class, FixtureParentClass::class) shouldBeEqualTo false
            hasAllParentInterfacesOf(FixtureParentInterface1::class, FixtureParentInterface2::class) shouldBeEqualTo true
            hasAllParentInterfacesOf(listOf(FixtureParentInterface1::class)) shouldBeEqualTo true
            hasAllParentInterfacesOf(listOf(FixtureParentInterface1::class, FixtureParentClass::class)) shouldBeEqualTo false
            hasAllParentInterfacesOf(listOf(FixtureParentInterface1::class, FixtureParentInterface2::class)) shouldBeEqualTo true
            hasAllParentInterfacesOf(setOf(FixtureParentInterface1::class)) shouldBeEqualTo true
            hasAllParentInterfacesOf(setOf(FixtureParentInterface1::class, FixtureParentClass::class)) shouldBeEqualTo false
            hasAllParentInterfacesOf(setOf(FixtureParentInterface1::class, FixtureParentInterface2::class)) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-has-parent-class-interfaces-and-external-parent`() {
        // given
        val sut =
            getSnippetFile("object-has-parent-class-interfaces-and-external-parent")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            parentInterfaces().map { it.name } shouldBeEqualTo listOf("FixtureParentInterface1", "FixtureParentInterface2")
            numParentInterfaces() shouldBeEqualTo 2
            countParentInterfaces { it.name == "FixtureParentInterface1" } shouldBeEqualTo 1
            countParentInterfaces { it.hasNameStartingWith("FixtureParentInterface") } shouldBeEqualTo 2
            hasParentInterfaces() shouldBeEqualTo true
            hasParentInterfaceWithName(emptyList()) shouldBeEqualTo true
            hasParentInterfaceWithName(emptySet()) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(emptyList()) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(emptySet()) shouldBeEqualTo true
            hasParentInterfaceWithName("FixtureParentInterface1") shouldBeEqualTo true
            hasParentInterfaceWithName("OtherInterface") shouldBeEqualTo false
            hasParentInterfaceWithName("FixtureParentInterface1", "FixtureParentInterface2") shouldBeEqualTo true
            hasParentInterfaceWithName("FixtureParentInterface1", "OtherInterface") shouldBeEqualTo true
            hasParentInterfaceWithName(listOf("FixtureParentInterface1")) shouldBeEqualTo true
            hasParentInterfaceWithName(listOf("OtherInterface")) shouldBeEqualTo false
            hasParentInterfaceWithName(listOf("FixtureParentInterface1", "FixtureParentInterface2")) shouldBeEqualTo true
            hasParentInterfaceWithName(listOf("FixtureParentInterface1", "OtherInterface")) shouldBeEqualTo true
            hasParentInterfaceWithName(setOf("FixtureParentInterface1")) shouldBeEqualTo true
            hasParentInterfaceWithName(setOf("OtherInterface")) shouldBeEqualTo false
            hasParentInterfaceWithName(setOf("FixtureParentInterface1", "FixtureParentInterface2")) shouldBeEqualTo true
            hasParentInterfaceWithName(setOf("FixtureParentInterface1", "OtherInterface")) shouldBeEqualTo true
            hasParentInterfacesWithAllNames("FixtureParentInterface1") shouldBeEqualTo true
            hasParentInterfacesWithAllNames("OtherInterface") shouldBeEqualTo false
            hasParentInterfacesWithAllNames("FixtureParentInterface1", "FixtureParentInterface2") shouldBeEqualTo true
            hasParentInterfacesWithAllNames("FixtureParentInterface1", "OtherInterface") shouldBeEqualTo false
            hasParentInterfacesWithAllNames(listOf("FixtureParentInterface1")) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(listOf("OtherInterface")) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(listOf("FixtureParentInterface1", "FixtureParentInterface2")) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(listOf("FixtureParentInterface1", "OtherInterface")) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(setOf("FixtureParentInterface1")) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(setOf("OtherInterface")) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(setOf("FixtureParentInterface1", "FixtureParentInterface2")) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(setOf("FixtureParentInterface1", "OtherInterface")) shouldBeEqualTo false
            hasParentInterface { it.name == "FixtureParentInterface1" } shouldBeEqualTo true
            hasParentInterface { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllParentInterfaces { it.name == "FixtureParentInterface1" } shouldBeEqualTo false
            hasAllParentInterfaces { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllParentInterfaces { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParentInterfaceOf(FixtureParentInterface1::class) shouldBeEqualTo true
            hasParentInterfaceOf(FixtureParentInterface1::class, FixtureParentClass::class) shouldBeEqualTo true
            hasParentInterfaceOf(listOf(FixtureParentInterface1::class)) shouldBeEqualTo true
            hasParentInterfaceOf(listOf(FixtureParentInterface1::class, FixtureParentClass::class)) shouldBeEqualTo true
            hasParentInterfaceOf(setOf(FixtureParentInterface1::class)) shouldBeEqualTo true
            hasParentInterfaceOf(setOf(FixtureParentInterface1::class, FixtureParentClass::class)) shouldBeEqualTo true
            hasAllParentInterfacesOf(FixtureParentInterface1::class) shouldBeEqualTo true
            hasAllParentInterfacesOf(FixtureParentInterface1::class, FixtureParentClass::class) shouldBeEqualTo false
            hasAllParentInterfacesOf(FixtureParentInterface1::class, FixtureParentInterface2::class) shouldBeEqualTo true
            hasAllParentInterfacesOf(listOf(FixtureParentInterface1::class)) shouldBeEqualTo true
            hasAllParentInterfacesOf(listOf(FixtureParentInterface1::class, FixtureParentClass::class)) shouldBeEqualTo false
            hasAllParentInterfacesOf(listOf(FixtureParentInterface1::class, FixtureParentInterface2::class)) shouldBeEqualTo true
            hasAllParentInterfacesOf(setOf(FixtureParentInterface1::class)) shouldBeEqualTo true
            hasAllParentInterfacesOf(setOf(FixtureParentInterface1::class, FixtureParentClass::class)) shouldBeEqualTo false
            hasAllParentInterfacesOf(setOf(FixtureParentInterface1::class, FixtureParentInterface2::class)) shouldBeEqualTo true
        }
    }

    @Suppress("detekt.LongMethod")
    @Test
    fun `object-has-indirect-parent-interfaces`() {
        // given
        val sut =
            getSnippetFile("object-has-indirect-parent-interfaces")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            parentInterfaces(indirectParents = false) shouldBeEqualTo emptyList()
            parentInterfaces(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "FixtureParentInterface1",
                    "FixtureParentInterface2",
                )
            numParentInterfaces(indirectParents = false) shouldBeEqualTo 0
            numParentInterfaces(indirectParents = true) shouldBeEqualTo 2
            countParentInterfaces(indirectParents = false) { it.name == "FixtureParentInterface1" } shouldBeEqualTo 0
            countParentInterfaces(indirectParents = true) { it.name == "FixtureParentInterface1" } shouldBeEqualTo 1
            countParentInterfaces(indirectParents = false) { it.hasNameStartingWith("FixtureParent") } shouldBeEqualTo 0
            countParentInterfaces(indirectParents = true) { it.hasNameStartingWith("FixtureParent") } shouldBeEqualTo 2
            hasParentInterfaces(indirectParents = false) shouldBeEqualTo false
            hasParentInterfaces(indirectParents = true) shouldBeEqualTo true
            hasParentInterfaceWithName(emptyList(), indirectParents = false) shouldBeEqualTo false
            hasParentInterfaceWithName(emptySet(), indirectParents = false) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(emptyList(), indirectParents = false) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(emptySet(), indirectParents = false) shouldBeEqualTo false
            hasParentInterfaceWithName(emptyList(), indirectParents = true) shouldBeEqualTo true
            hasParentInterfaceWithName(emptySet(), indirectParents = true) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(emptyList(), indirectParents = true) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(emptySet(), indirectParents = true) shouldBeEqualTo true
            hasParentInterfaceWithName("FixtureParentInterface1", indirectParents = true) shouldBeEqualTo true
            hasParentInterfaceWithName("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasParentInterfaceWithName(
                "FixtureParentInterface1",
                "FixtureParentInterface2",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentInterfaceWithName(
                "FixtureParentInterface1",
                "OtherInterface",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentInterfaceWithName(listOf("FixtureParentInterface1"), indirectParents = true) shouldBeEqualTo true
            hasParentInterfaceWithName(listOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasParentInterfaceWithName(
                listOf(
                    "FixtureParentInterface1",
                    "FixtureParentInterface2",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentInterfaceWithName(
                listOf(
                    "FixtureParentInterface1",
                    "OtherInterface",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentInterfacesWithAllNames("FixtureParentInterface1", indirectParents = true) shouldBeEqualTo true
            hasParentInterfacesWithAllNames("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(
                "FixtureParentInterface1",
                "FixtureParentInterface2",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(
                "FixtureParentInterface1",
                "OtherInterface",
                indirectParents = true,
            ) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(listOf("FixtureParentInterface1"), indirectParents = true) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(listOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(
                listOf(
                    "FixtureParentInterface1",
                    "FixtureParentInterface2",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(
                listOf(
                    "FixtureParentInterface1",
                    "OtherInterface",
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasParentInterface(indirectParents = true) { it.name == "FixtureParentInterface1" } shouldBeEqualTo true
            hasParentInterface(indirectParents = true) { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllParentInterfaces(indirectParents = true) { it.name == "FixtureParentInterface1" } shouldBeEqualTo false
            hasAllParentInterfaces(indirectParents = true) { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllParentInterfaces(indirectParents = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParentInterfaceOf(FixtureParentInterface2::class, indirectParents = true) shouldBeEqualTo true
            hasParentInterfaceOf(listOf(FixtureParentInterface2::class), indirectParents = true) shouldBeEqualTo true
            hasAllParentInterfacesOf(FixtureParentInterface2::class, indirectParents = true) shouldBeEqualTo true
            hasAllParentInterfacesOf(
                FixtureParentInterface2::class,
                FixtureParentInterface::class,
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllParentInterfacesOf(listOf(FixtureParentInterface2::class), indirectParents = true) shouldBeEqualTo true
            hasAllParentInterfacesOf(
                listOf(
                    FixtureParentInterface2::class,
                    FixtureParentInterface::class,
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-indirect-repeated-parent-interfaces`() {
        // given
        val sut =
            getSnippetFile("object-has-indirect-repeated-parent-interfaces")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            parentInterfaces(indirectParents = false).map { it.name } shouldBeEqualTo listOf("FixtureParentInterface2")
            parentInterfaces(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "FixtureParentInterface2",
                    "FixtureParentInterface1",
                )
            numParentInterfaces(indirectParents = false) shouldBeEqualTo 1
            numParentInterfaces(indirectParents = true) shouldBeEqualTo 2
        }
    }

    @Test
    fun `object-has-no-parent-interface-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-no-parent-interface-ignore-case")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasParentInterfaceWithName("fixtureparentinterface1") shouldBeEqualTo false
            hasParentInterfaceWithName("fixtureparentinterface1", ignoreCase = true) shouldBeEqualTo false
            hasParentInterfaceWithName(listOf("fixtureparentinterface1")) shouldBeEqualTo false
            hasParentInterfaceWithName(listOf("fixtureparentinterface1"), ignoreCase = true) shouldBeEqualTo false
            hasParentInterfaceWithName(setOf("fixtureparentinterface1")) shouldBeEqualTo false
            hasParentInterfaceWithName(setOf("fixtureparentinterface1"), ignoreCase = true) shouldBeEqualTo false
            hasParentInterfacesWithAllNames("fixtureparentinterface1", "fixtureparentinterface2") shouldBeEqualTo false
            hasParentInterfacesWithAllNames("fixtureparentinterface1", "fixtureparentinterface2", ignoreCase = true) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(listOf("fixtureparentinterface1", "fixtureparentinterface2")) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(listOf("fixtureparentinterface1", "fixtureparentinterface2"), ignoreCase = true) shouldBeEqualTo
                false
            hasParentInterfacesWithAllNames(setOf("fixtureparentinterface1", "fixtureparentinterface2")) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(setOf("fixtureparentinterface1", "fixtureparentinterface2"), ignoreCase = true) shouldBeEqualTo
                false
        }
    }

    @Test
    fun `object-has-parent-interfaces-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-parent-interfaces-ignore-case")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasParentInterfaceWithName("fixtureparentinterface1") shouldBeEqualTo false
            hasParentInterfaceWithName("fixtureparentinterface1", ignoreCase = true) shouldBeEqualTo true
            hasParentInterfaceWithName("otherparentinterface") shouldBeEqualTo false
            hasParentInterfaceWithName("otherparentinterface", ignoreCase = true) shouldBeEqualTo false
            hasParentInterfaceWithName("fixtureparentinterface1", "otherName") shouldBeEqualTo false
            hasParentInterfaceWithName("fixtureparentinterface1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasParentInterfaceWithName(listOf("fixtureparentinterface1")) shouldBeEqualTo false
            hasParentInterfaceWithName(listOf("fixtureparentinterface1"), ignoreCase = true) shouldBeEqualTo true
            hasParentInterfaceWithName(listOf("otherparentinterface")) shouldBeEqualTo false
            hasParentInterfaceWithName(listOf("otherparentinterface"), ignoreCase = true) shouldBeEqualTo false
            hasParentInterfaceWithName(listOf("fixtureparentinterface1", "otherName")) shouldBeEqualTo false
            hasParentInterfaceWithName(listOf("fixtureparentinterface1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasParentInterfacesWithAllNames("fixtureparentinterface1") shouldBeEqualTo false
            hasParentInterfacesWithAllNames("fixtureparentinterface1", ignoreCase = true) shouldBeEqualTo true
            hasParentInterfacesWithAllNames("fixtureparentinterface1", "fixtureparentinterface2") shouldBeEqualTo false
            hasParentInterfacesWithAllNames("fixtureparentinterface1", "fixtureparentinterface2", ignoreCase = true) shouldBeEqualTo true
            hasParentInterfacesWithAllNames("fixtureparentinterface1", "otherparentinterface") shouldBeEqualTo false
            hasParentInterfacesWithAllNames("fixtureparentinterface1", "otherparentinterface", ignoreCase = true) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(listOf("fixtureparentinterface1")) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(listOf("fixtureparentinterface1"), ignoreCase = true) shouldBeEqualTo true
            hasParentInterfacesWithAllNames(listOf("fixtureparentinterface1", "fixtureparentinterface2")) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(listOf("fixtureparentinterface1", "fixtureparentinterface2"), ignoreCase = true) shouldBeEqualTo
                true
            hasParentInterfacesWithAllNames(listOf("fixtureparentinterface1", "otherparentinterface")) shouldBeEqualTo false
            hasParentInterfacesWithAllNames(listOf("fixtureparentinterface1", "otherparentinterface"), ignoreCase = true) shouldBeEqualTo
                false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkoparentinterfaceprovider/", fileName)
}
