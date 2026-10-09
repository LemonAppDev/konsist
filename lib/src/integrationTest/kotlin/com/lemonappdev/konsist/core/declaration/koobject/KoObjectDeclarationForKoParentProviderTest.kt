package com.lemonappdev.konsist.core.declaration.koobject

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
    fun `object-has-direct-parent-class-interfaces-and-external-parent`() {
        // given
        val sut =
            getSnippetFile("object-has-direct-parent-class-interfaces-and-external-parent")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            parents().map { it.name } shouldBeEqualTo
                listOf(
                    "FixtureParentClass",
                    "FixtureParentInterface1",
                    "FixtureParentInterface2",
                    "FixtureExternalInterface",
                )
            numParents() shouldBeEqualTo 4
            countParents { it.name == "FixtureParentClass" } shouldBeEqualTo 1
            countParents { it.hasNameStartingWith("FixtureParentInterface") } shouldBeEqualTo 2
            hasParents() shouldBeEqualTo true
            hasParentWithName(emptyList()) shouldBeEqualTo true
            hasParentWithName(emptySet()) shouldBeEqualTo true
            hasParentsWithAllNames(emptyList()) shouldBeEqualTo true
            hasParentsWithAllNames(emptySet()) shouldBeEqualTo true
            hasParentWithName("FixtureParentClass") shouldBeEqualTo true
            hasParentWithName("OtherInterface") shouldBeEqualTo false
            hasParentWithName("FixtureParentClass", "OtherInterface") shouldBeEqualTo true
            hasParentWithName(listOf("FixtureParentClass")) shouldBeEqualTo true
            hasParentWithName(listOf("OtherInterface")) shouldBeEqualTo false
            hasParentWithName(listOf("FixtureParentClass", "OtherInterface")) shouldBeEqualTo true
            hasParentWithName(setOf("FixtureParentClass")) shouldBeEqualTo true
            hasParentWithName(setOf("OtherInterface")) shouldBeEqualTo false
            hasParentWithName(setOf("FixtureParentClass", "OtherInterface")) shouldBeEqualTo true
            hasParentsWithAllNames("FixtureParentClass") shouldBeEqualTo true
            hasParentsWithAllNames("OtherInterface") shouldBeEqualTo false
            hasParentsWithAllNames("FixtureParentClass", "FixtureParentInterface1") shouldBeEqualTo true
            hasParentsWithAllNames("FixtureParentClass", "OtherInterface") shouldBeEqualTo false
            hasParentsWithAllNames(listOf("FixtureParentClass")) shouldBeEqualTo true
            hasParentsWithAllNames(listOf("OtherInterface")) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("FixtureParentClass", "FixtureParentInterface1")) shouldBeEqualTo true
            hasParentsWithAllNames(listOf("FixtureParentClass", "OtherInterface")) shouldBeEqualTo false
            hasParentsWithAllNames(setOf("FixtureParentClass")) shouldBeEqualTo true
            hasParentsWithAllNames(setOf("OtherInterface")) shouldBeEqualTo false
            hasParentsWithAllNames(setOf("FixtureParentClass", "FixtureParentInterface1")) shouldBeEqualTo true
            hasParentsWithAllNames(setOf("FixtureParentClass", "OtherInterface")) shouldBeEqualTo false
            hasParent { it.name == "FixtureParentClass" } shouldBeEqualTo true
            hasParent { it.name == "OtherClass" } shouldBeEqualTo false
            hasAllParents { it.name == "FixtureParentClass" } shouldBeEqualTo false
            hasAllParents { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllParents { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParentOf(FixtureParentClass::class) shouldBeEqualTo true
            hasParentOf(FixtureParentClass::class, FixtureInterface::class) shouldBeEqualTo true
            hasParentOf(listOf(FixtureParentClass::class)) shouldBeEqualTo true
            hasParentOf(listOf(FixtureParentClass::class, FixtureInterface::class)) shouldBeEqualTo true
            hasParentOf(setOf(FixtureParentClass::class)) shouldBeEqualTo true
            hasParentOf(setOf(FixtureParentClass::class, FixtureInterface::class)) shouldBeEqualTo true
            hasAllParentsOf(FixtureParentClass::class) shouldBeEqualTo true
            hasAllParentsOf(FixtureParentClass::class, FixtureInterface::class) shouldBeEqualTo false
            hasAllParentsOf(FixtureParentClass::class, FixtureParentInterface1::class) shouldBeEqualTo true
            hasAllParentsOf(listOf(FixtureParentClass::class)) shouldBeEqualTo true
            hasAllParentsOf(listOf(FixtureParentClass::class, FixtureInterface::class)) shouldBeEqualTo false
            hasAllParentsOf(listOf(FixtureParentClass::class, FixtureParentInterface1::class)) shouldBeEqualTo true
            hasAllParentsOf(setOf(FixtureParentClass::class)) shouldBeEqualTo true
            hasAllParentsOf(setOf(FixtureParentClass::class, FixtureInterface::class)) shouldBeEqualTo false
            hasAllParentsOf(setOf(FixtureParentClass::class, FixtureParentInterface1::class)) shouldBeEqualTo true
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
                    "FixtureParentClass",
                    "FixtureParentInterface1",
                    "FixtureExternalInterface",
                )
            numParents(indirectParents = false) shouldBeEqualTo 3
            parents(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "FixtureParentClass",
                    "FixtureParentInterface1",
                    "FixtureExternalInterface",
                    "FixtureParentInterface2",
                )
            numParents(indirectParents = true) shouldBeEqualTo 4
            countParents(indirectParents = true) { it.name == "FixtureParentInterface2" } shouldBeEqualTo 1
            countParents(indirectParents = true) { it.hasNameStartingWith("FixtureParentInterface") } shouldBeEqualTo 2
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
            hasAllParentsOf(listOf(FixtureParentInterface2::class), indirectParents = true) shouldBeEqualTo true
            hasAllParentsOf(
                listOf(
                    FixtureParentInterface2::class,
                    FixtureInterface::class,
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllParentsOf(setOf(FixtureParentInterface2::class), indirectParents = true) shouldBeEqualTo true
            hasAllParentsOf(
                setOf(
                    FixtureParentInterface2::class,
                    FixtureInterface::class,
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
                    "FixtureParentClass",
                    "FixtureParentInterface1",
                    "FixtureExternalInterface",
                )
            parents(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "FixtureParentClass",
                    "FixtureParentInterface1",
                    "FixtureExternalInterface",
                    "FixtureParentInterface2",
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
        assertSoftly(sut.parents().first()) {
            name shouldBeEqualTo "AliasParent"
            sourceDeclaration?.isImportAlias shouldBeEqualTo true
        }
    }

    @Test
    fun `object-has-kotlin-parent`() {
        // given
        val sut =
            getSnippetFile("object-has-kotlin-parent")
                .objects()
                .first()

        // then
        assertSoftly(sut.parents().first()) {
            name shouldBeEqualTo "Throwable"
            sourceDeclaration?.asKotlinTypeDeclaration()?.fullyQualifiedName shouldBeEqualTo "kotlin.Throwable"
            sourceDeclaration?.isKotlinType shouldBeEqualTo true
        }
    }

    @Test
    fun `object-has-no-parents-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-no-parents-ignore-case")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasParentWithName("fixtureparentclass") shouldBeEqualTo false
            hasParentWithName("fixtureparentclass", ignoreCase = true) shouldBeEqualTo false
            hasParentWithName(listOf("fixtureparentclass")) shouldBeEqualTo false
            hasParentWithName(listOf("fixtureparentclass"), ignoreCase = true) shouldBeEqualTo false
            hasParentWithName(setOf("fixtureparentclass")) shouldBeEqualTo false
            hasParentWithName(setOf("fixtureparentclass"), ignoreCase = true) shouldBeEqualTo false
            hasParentsWithAllNames("fixtureparentclass", "fixtureparentinterface1") shouldBeEqualTo false
            hasParentsWithAllNames("fixtureparentclass", "fixtureparentinterface1", ignoreCase = true) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("fixtureparentclass", "fixtureparentinterface1")) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("fixtureparentclass", "fixtureparentinterface1"), ignoreCase = true) shouldBeEqualTo false
            hasParentsWithAllNames(setOf("fixtureparentclass", "fixtureparentinterface1")) shouldBeEqualTo false
            hasParentsWithAllNames(setOf("fixtureparentclass", "fixtureparentinterface1"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-direct-parents-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-direct-parents-ignore-case")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasParentWithName("fixtureparentclass") shouldBeEqualTo false
            hasParentWithName("fixtureparentclass", ignoreCase = true) shouldBeEqualTo true
            hasParentWithName("otherparentclass") shouldBeEqualTo false
            hasParentWithName("otherparentclass", ignoreCase = true) shouldBeEqualTo false
            hasParentWithName("fixtureparentclass", "otherName") shouldBeEqualTo false
            hasParentWithName("fixtureparentclass", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasParentWithName(listOf("fixtureparentclass")) shouldBeEqualTo false
            hasParentWithName(listOf("fixtureparentclass"), ignoreCase = true) shouldBeEqualTo true
            hasParentWithName(listOf("otherparentclass")) shouldBeEqualTo false
            hasParentWithName(listOf("otherparentclass"), ignoreCase = true) shouldBeEqualTo false
            hasParentWithName(listOf("fixtureparentclass", "otherName")) shouldBeEqualTo false
            hasParentWithName(listOf("fixtureparentclass", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasParentsWithAllNames("fixtureparentclass") shouldBeEqualTo false
            hasParentsWithAllNames("fixtureparentclass", ignoreCase = true) shouldBeEqualTo true
            hasParentsWithAllNames("fixtureparentclass", "fixtureparentinterface1") shouldBeEqualTo false
            hasParentsWithAllNames("fixtureparentclass", "fixtureparentinterface1", ignoreCase = true) shouldBeEqualTo true
            hasParentsWithAllNames("fixtureparentclass", "otherparentclass") shouldBeEqualTo false
            hasParentsWithAllNames("fixtureparentclass", "otherparentclass", ignoreCase = true) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("fixtureparentclass")) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("fixtureparentclass"), ignoreCase = true) shouldBeEqualTo true
            hasParentsWithAllNames(listOf("fixtureparentclass", "fixtureparentinterface1")) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("fixtureparentclass", "fixtureparentinterface1"), ignoreCase = true) shouldBeEqualTo true
            hasParentsWithAllNames(listOf("fixtureparentclass", "otherparentclass")) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("fixtureparentclass", "otherparentclass"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkoparentprovider/", fileName)
}
