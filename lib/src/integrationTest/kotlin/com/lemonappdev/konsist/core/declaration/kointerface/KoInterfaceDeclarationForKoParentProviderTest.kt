package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.FixtureInterface
import com.lemonappdev.konsist.testdata.FixtureParentClass
import com.lemonappdev.konsist.testdata.FixtureParentInterface
import com.lemonappdev.konsist.testdata.FixtureParentInterface1
import com.lemonappdev.konsist.testdata.FixtureParentInterface2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
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
            parents() shouldBeEqualTo emptyList()
            numParents() shouldBeEqualTo 0
            countParents { it.name == "FixtureParentClass" } shouldBeEqualTo 0
            hasParents() shouldBeEqualTo false
            hasParentWithName(emptyList()) shouldBeEqualTo false
            hasParentWithName(emptySet()) shouldBeEqualTo false
            hasParentsWithAllNames(emptyList()) shouldBeEqualTo false
            hasParentsWithAllNames(emptySet()) shouldBeEqualTo false
            hasParentWithName("FixtureParentClass") shouldBeEqualTo false
            hasParentWithName(listOf("FixtureParentClass")) shouldBeEqualTo false
            hasParentWithName(setOf("FixtureParentClass")) shouldBeEqualTo false
            hasParentsWithAllNames("FixtureParentClass", "FixtureParentInterface") shouldBeEqualTo false
            hasParentsWithAllNames(listOf("FixtureParentClass", "FixtureParentInterface")) shouldBeEqualTo false
            hasParentsWithAllNames(setOf("FixtureParentClass", "FixtureParentInterface")) shouldBeEqualTo false
            hasParent { it.name == "FixtureParentClass" } shouldBeEqualTo false
            hasAllParents { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasParentOf(FixtureParentClass::class) shouldBeEqualTo false
            hasParentOf(listOf(FixtureParentClass::class)) shouldBeEqualTo false
            hasParentOf(setOf(FixtureParentClass::class)) shouldBeEqualTo false
            hasAllParentsOf(FixtureParentClass::class, FixtureParentInterface::class) shouldBeEqualTo false
            hasAllParentsOf(listOf(FixtureParentClass::class, FixtureParentInterface::class)) shouldBeEqualTo false
            hasAllParentsOf(setOf(FixtureParentClass::class, FixtureParentInterface::class)) shouldBeEqualTo false
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
            parents().map { it.name } shouldBeEqualTo
                listOf(
                    "FixtureParentInterface1",
                    "FixtureParentInterface2",
                    "FixtureExternalInterface",
                    "FixtureExternalGenericInterface<Int>",
                )
            numParents() shouldBeEqualTo 4
            countParents { it.name == "FixtureParentInterface1" } shouldBeEqualTo 1
            countParents { it.hasNameStartingWith("FixtureExternal") } shouldBeEqualTo 2
            hasParents() shouldBeEqualTo true
            hasParentWithName(emptyList()) shouldBeEqualTo true
            hasParentWithName(emptySet()) shouldBeEqualTo true
            hasParentsWithAllNames(emptyList()) shouldBeEqualTo true
            hasParentsWithAllNames(emptySet()) shouldBeEqualTo true
            hasParentWithName("FixtureParentInterface1") shouldBeEqualTo true
            hasParentWithName("OtherInterface") shouldBeEqualTo false
            hasParentWithName("FixtureParentInterface1", "OtherInterface") shouldBeEqualTo true
            hasParentWithName(listOf("FixtureParentInterface1")) shouldBeEqualTo true
            hasParentWithName(listOf("OtherInterface")) shouldBeEqualTo false
            hasParentWithName(listOf("FixtureParentInterface1", "OtherInterface")) shouldBeEqualTo true
            hasParentWithName(setOf("FixtureParentInterface1")) shouldBeEqualTo true
            hasParentWithName(setOf("OtherInterface")) shouldBeEqualTo false
            hasParentWithName(setOf("FixtureParentInterface1", "OtherInterface")) shouldBeEqualTo true
            hasParentsWithAllNames("FixtureParentInterface1") shouldBeEqualTo true
            hasParentsWithAllNames("OtherInterface") shouldBeEqualTo false
            hasParentsWithAllNames("FixtureParentInterface1", "FixtureExternalInterface") shouldBeEqualTo true
            hasParentsWithAllNames("FixtureParentInterface1", "OtherInterface") shouldBeEqualTo false
            hasParentsWithAllNames(listOf("FixtureParentInterface1")) shouldBeEqualTo true
            hasParentsWithAllNames(listOf("OtherInterface")) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("FixtureParentInterface1", "FixtureExternalInterface")) shouldBeEqualTo true
            hasParentsWithAllNames(listOf("FixtureParentInterface1", "OtherInterface")) shouldBeEqualTo false
            hasParentsWithAllNames(setOf("FixtureParentInterface1")) shouldBeEqualTo true
            hasParentsWithAllNames(setOf("OtherInterface")) shouldBeEqualTo false
            hasParentsWithAllNames(setOf("FixtureParentInterface1", "FixtureExternalInterface")) shouldBeEqualTo true
            hasParentsWithAllNames(setOf("FixtureParentInterface1", "OtherInterface")) shouldBeEqualTo false
            hasParent { it.name == "FixtureParentInterface1" } shouldBeEqualTo true
            hasParent { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllParents { it.name == "FixtureParentInterface1" } shouldBeEqualTo false
            hasAllParents { it.hasNameContaining("Parent") || it.hasNameContaining("External") } shouldBeEqualTo true
            hasAllParents { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParentOf(FixtureParentInterface1::class) shouldBeEqualTo true
            hasParentOf(FixtureParentInterface1::class, FixtureInterface::class) shouldBeEqualTo true
            hasParentOf(listOf(FixtureParentInterface1::class)) shouldBeEqualTo true
            hasParentOf(listOf(FixtureParentInterface1::class, FixtureInterface::class)) shouldBeEqualTo true
            hasParentOf(setOf(FixtureParentInterface1::class)) shouldBeEqualTo true
            hasParentOf(setOf(FixtureParentInterface1::class, FixtureInterface::class)) shouldBeEqualTo true
            hasAllParentsOf(FixtureParentInterface1::class) shouldBeEqualTo true
            hasAllParentsOf(FixtureParentInterface1::class, FixtureInterface::class) shouldBeEqualTo false
            hasAllParentsOf(FixtureParentInterface1::class, FixtureParentInterface2::class) shouldBeEqualTo true
            hasAllParentsOf(listOf(FixtureParentInterface1::class)) shouldBeEqualTo true
            hasAllParentsOf(listOf(FixtureParentInterface1::class, FixtureInterface::class)) shouldBeEqualTo false
            hasAllParentsOf(listOf(FixtureParentInterface1::class, FixtureParentInterface2::class)) shouldBeEqualTo true
            hasAllParentsOf(setOf(FixtureParentInterface1::class)) shouldBeEqualTo true
            hasAllParentsOf(setOf(FixtureParentInterface1::class, FixtureInterface::class)) shouldBeEqualTo false
            hasAllParentsOf(setOf(FixtureParentInterface1::class, FixtureParentInterface2::class)) shouldBeEqualTo true
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
                    "FixtureParentInterface",
                    "FixtureExternalInterface",
                )
            numParents(indirectParents = false) shouldBeEqualTo 2
            parents(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "FixtureParentInterface",
                    "FixtureExternalInterface",
                    "FixtureParentInterface1",
                    "FixtureParentInterface2",
                )
            numParents(indirectParents = true) shouldBeEqualTo 4
            countParents(indirectParents = true) { it.name == "FixtureParentInterface2" } shouldBeEqualTo 1
            countParents(indirectParents = true) { it.hasNameStartingWith("FixtureParentInterface") } shouldBeEqualTo 3
            hasParents(indirectParents = true) shouldBeEqualTo true
            hasParents(indirectParents = true) shouldBeEqualTo true
            hasParentWithName(emptyList(), indirectParents = true) shouldBeEqualTo true
            hasParentWithName(emptySet(), indirectParents = true) shouldBeEqualTo true
            hasParentsWithAllNames(emptyList(), indirectParents = true) shouldBeEqualTo true
            hasParentsWithAllNames(emptySet(), indirectParents = true) shouldBeEqualTo true
            hasParentWithName("FixtureParentInterface2", indirectParents = true) shouldBeEqualTo true
            hasParentWithName("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasParentWithName("FixtureParentInterface2", "OtherInterface", indirectParents = true) shouldBeEqualTo true
            hasParentWithName(listOf("FixtureParentInterface2"), indirectParents = true) shouldBeEqualTo true
            hasParentWithName(listOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasParentWithName(listOf("FixtureParentInterface2", "OtherInterface"), indirectParents = true) shouldBeEqualTo true
            hasParentWithName(setOf("FixtureParentInterface2"), indirectParents = true) shouldBeEqualTo true
            hasParentWithName(setOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasParentWithName(setOf("FixtureParentInterface2", "OtherInterface"), indirectParents = true) shouldBeEqualTo true
            hasParentsWithAllNames("FixtureParentInterface2", indirectParents = true) shouldBeEqualTo true
            hasParentsWithAllNames("OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasParentsWithAllNames(
                "FixtureParentInterface2",
                "FixtureParentInterface1",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentsWithAllNames("FixtureParentInterface2", "OtherInterface", indirectParents = true) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("FixtureParentInterface2"), indirectParents = true) shouldBeEqualTo true
            hasParentsWithAllNames(listOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasParentsWithAllNames(
                listOf(
                    "FixtureParentInterface2",
                    "FixtureParentInterface1",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentsWithAllNames(listOf("FixtureParentInterface2", "OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasParentsWithAllNames(setOf("FixtureParentInterface2"), indirectParents = true) shouldBeEqualTo true
            hasParentsWithAllNames(setOf("OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasParentsWithAllNames(
                setOf(
                    "FixtureParentInterface2",
                    "FixtureParentInterface1",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentsWithAllNames(setOf("FixtureParentInterface2", "OtherInterface"), indirectParents = true) shouldBeEqualTo false
            hasParent(indirectParents = true) { it.name == "FixtureParentInterface2" } shouldBeEqualTo true
            hasParent(indirectParents = true) { it.name == "OtherClass" } shouldBeEqualTo false
            hasAllParents(indirectParents = true) { it.name == "FixtureParentInterface2" } shouldBeEqualTo false
            hasAllParents(indirectParents = true) { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllParents(indirectParents = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParentOf(FixtureParentInterface2::class, indirectParents = true) shouldBeEqualTo true
            hasParentOf(FixtureParentInterface2::class, FixtureInterface::class, indirectParents = true) shouldBeEqualTo true
            hasParentOf(listOf(FixtureParentInterface2::class), indirectParents = true) shouldBeEqualTo true
            hasParentOf(listOf(FixtureParentInterface2::class, FixtureInterface::class), indirectParents = true) shouldBeEqualTo true
            hasParentOf(setOf(FixtureParentInterface2::class), indirectParents = true) shouldBeEqualTo true
            hasParentOf(setOf(FixtureParentInterface2::class, FixtureInterface::class), indirectParents = true) shouldBeEqualTo true
            hasAllParentsOf(FixtureParentInterface2::class, indirectParents = true) shouldBeEqualTo true
            hasAllParentsOf(
                FixtureParentInterface2::class,
                FixtureInterface::class,
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllParentsOf(
                FixtureParentInterface2::class,
                FixtureParentInterface1::class,
                indirectParents = true,
            ) shouldBeEqualTo true
            hasAllParentsOf(listOf(FixtureParentInterface2::class), indirectParents = true) shouldBeEqualTo true
            hasAllParentsOf(
                listOf(
                    FixtureParentInterface2::class,
                    FixtureInterface::class,
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllParentsOf(
                listOf(
                    FixtureParentInterface2::class,
                    FixtureParentInterface1::class,
                ),
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
                    "FixtureParentInterface1",
                    "FixtureExternalInterface",
                )
            numParents(indirectParents = false) shouldBeEqualTo 2
            parents(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "FixtureParentInterface1",
                    "FixtureExternalInterface",
                    "FixtureParentInterface2",
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
        assertSoftly(sut.parents().first()) {
            name shouldBeEqualTo "AliasParent"
            sourceDeclaration?.isImportAlias shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-no-parents-ignore-case`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-parents-ignore-case")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasParentWithName("fixtureparentinterface1") shouldBeEqualTo false
            hasParentWithName("fixtureparentinterface1", ignoreCase = true) shouldBeEqualTo false
            hasParentWithName(listOf("fixtureparentinterface1")) shouldBeEqualTo false
            hasParentWithName(listOf("fixtureparentinterface1"), ignoreCase = true) shouldBeEqualTo false
            hasParentWithName(setOf("fixtureparentinterface1")) shouldBeEqualTo false
            hasParentWithName(setOf("fixtureparentinterface1"), ignoreCase = true) shouldBeEqualTo false
            hasParentsWithAllNames("fixtureparentinterface1", "fixtureparentinterface2") shouldBeEqualTo false
            hasParentsWithAllNames("fixtureparentinterface1", "fixtureparentinterface2", ignoreCase = true) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("fixtureparentinterface1", "fixtureparentinterface2")) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("fixtureparentinterface1", "fixtureparentinterface2"), ignoreCase = true) shouldBeEqualTo false
            hasParentsWithAllNames(setOf("fixtureparentinterface1", "fixtureparentinterface2")) shouldBeEqualTo false
            hasParentsWithAllNames(setOf("fixtureparentinterface1", "fixtureparentinterface2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-direct-parents-ignore-case`() {
        // given
        val sut =
            getSnippetFile("interface-has-direct-parents-ignore-case")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasParentWithName("fixtureparentinterface1") shouldBeEqualTo false
            hasParentWithName("fixtureparentinterface1", ignoreCase = true) shouldBeEqualTo true
            hasParentWithName("otherparentclass") shouldBeEqualTo false
            hasParentWithName("otherparentclass", ignoreCase = true) shouldBeEqualTo false
            hasParentWithName("fixtureparentinterface1", "otherName") shouldBeEqualTo false
            hasParentWithName("fixtureparentinterface1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasParentWithName(listOf("fixtureparentinterface1")) shouldBeEqualTo false
            hasParentWithName(listOf("fixtureparentinterface1"), ignoreCase = true) shouldBeEqualTo true
            hasParentWithName(listOf("otherparentclass")) shouldBeEqualTo false
            hasParentWithName(listOf("otherparentclass"), ignoreCase = true) shouldBeEqualTo false
            hasParentWithName(listOf("fixtureparentinterface1", "otherName")) shouldBeEqualTo false
            hasParentWithName(listOf("fixtureparentinterface1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasParentsWithAllNames("fixtureparentinterface1") shouldBeEqualTo false
            hasParentsWithAllNames("fixtureparentinterface1", ignoreCase = true) shouldBeEqualTo true
            hasParentsWithAllNames("fixtureparentinterface1", "fixtureparentinterface2") shouldBeEqualTo false
            hasParentsWithAllNames("fixtureparentinterface1", "fixtureparentinterface2", ignoreCase = true) shouldBeEqualTo true
            hasParentsWithAllNames("fixtureparentinterface1", "otherparentclass") shouldBeEqualTo false
            hasParentsWithAllNames("fixtureparentinterface1", "otherparentclass", ignoreCase = true) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("fixtureparentinterface1")) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("fixtureparentinterface1"), ignoreCase = true) shouldBeEqualTo true
            hasParentsWithAllNames(listOf("fixtureparentinterface1", "fixtureparentinterface2")) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("fixtureparentinterface1", "fixtureparentinterface2"), ignoreCase = true) shouldBeEqualTo true
            hasParentsWithAllNames(listOf("fixtureparentinterface1", "otherparentclass")) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("fixtureparentinterface1", "otherparentclass"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kointerface/snippet/forkoparentprovider/", fileName)
}
