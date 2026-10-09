package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoTypeAliasProviderTest {
    @Test
    fun `file-has-no-typealias`() {
        // given
        val sut =
            getSnippetFile("file-has-no-typealias")
                .files
                .first()

        // then
        assertSoftly(sut) {
            typeAliases shouldBeEqualTo emptyList()
            numTypeAliases shouldBeEqualTo 0
            countTypeAliases { it.hasPrivateModifier } shouldBeEqualTo 0
            hasTypeAliases() shouldBeEqualTo false
            hasTypeAliasWithName(emptyList()) shouldBeEqualTo false
            hasTypeAliasWithName(emptySet()) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(emptyList()) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(emptySet()) shouldBeEqualTo false
            hasTypeAliasWithName("FixtureTypeAlias") shouldBeEqualTo false
            hasTypeAliasWithName(listOf("FixtureTypeAlias")) shouldBeEqualTo false
            hasTypeAliasWithName(setOf("FixtureTypeAlias")) shouldBeEqualTo false
            hasTypeAliasesWithAllNames("FixtureTypeAlias1", "FixtureTypeAlias2") shouldBeEqualTo false
            hasTypeAliasesWithAllNames(listOf("FixtureTypeAlias1", "FixtureTypeAlias2")) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(setOf("FixtureTypeAlias1", "FixtureTypeAlias2")) shouldBeEqualTo false
            hasTypeAlias { it.hasPublicModifier } shouldBeEqualTo false
            hasAllTypeAliases { it.hasPublicOrDefaultModifier } shouldBeEqualTo true
        }
    }

    @Test
    fun `file-has-one-typealias`() {
        // given
        val sut =
            getSnippetFile("file-has-one-typealias")
                .files
                .first()

        // then
        assertSoftly(sut) {
            typeAliases.size shouldBeEqualTo 1
            numTypeAliases shouldBeEqualTo 1
            countTypeAliases { it.hasPublicOrDefaultModifier } shouldBeEqualTo 1
            hasTypeAliases() shouldBeEqualTo true
            hasTypeAliasWithName(emptyList()) shouldBeEqualTo true
            hasTypeAliasWithName(emptySet()) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(emptyList()) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(emptySet()) shouldBeEqualTo true
            hasTypeAliasWithName("FixtureTypeAlias") shouldBeEqualTo true
            hasTypeAliasWithName("otherTypeAlias") shouldBeEqualTo false
            hasTypeAliasWithName("FixtureTypeAlias", "otherTypeAlias") shouldBeEqualTo true
            hasTypeAliasWithName(listOf("FixtureTypeAlias")) shouldBeEqualTo true
            hasTypeAliasWithName(listOf("otherTypeAlias")) shouldBeEqualTo false
            hasTypeAliasWithName(listOf("FixtureTypeAlias", "otherTypeAlias")) shouldBeEqualTo true
            hasTypeAliasWithName(setOf("FixtureTypeAlias")) shouldBeEqualTo true
            hasTypeAliasWithName(setOf("otherTypeAlias")) shouldBeEqualTo false
            hasTypeAliasWithName(setOf("FixtureTypeAlias", "otherTypeAlias")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames("FixtureTypeAlias") shouldBeEqualTo true
            hasTypeAliasesWithAllNames("FixtureTypeAlias", "otherTypeAlias") shouldBeEqualTo false
            hasTypeAliasesWithAllNames(listOf("FixtureTypeAlias")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(listOf("FixtureTypeAlias", "otherTypeAlias")) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(setOf("FixtureTypeAlias")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(setOf("FixtureTypeAlias", "otherTypeAlias")) shouldBeEqualTo false
            hasTypeAlias { it.hasPublicOrDefaultModifier } shouldBeEqualTo true
            hasTypeAlias { it.hasPublicModifier } shouldBeEqualTo false
            hasAllTypeAliases { it.hasPublicOrDefaultModifier } shouldBeEqualTo true
        }
    }

    @Test
    fun `file-has-two-typealiases`() {
        // given
        val sut =
            getSnippetFile("file-has-two-typealiases")
                .files
                .first()

        // then
        assertSoftly(sut) {
            numTypeAliases shouldBeEqualTo 2
            countTypeAliases { it.hasNameStartingWith("Fixture") } shouldBeEqualTo 2
            countTypeAliases { it.name == "FixtureTypeAlias1" } shouldBeEqualTo 1
            hasTypeAliases() shouldBeEqualTo true
            hasTypeAliasWithName(emptyList()) shouldBeEqualTo true
            hasTypeAliasWithName(emptySet()) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(emptyList()) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(emptySet()) shouldBeEqualTo true
            hasTypeAliasWithName("FixtureTypeAlias1") shouldBeEqualTo true
            hasTypeAliasWithName("otherTypeAlias") shouldBeEqualTo false
            hasTypeAliasWithName("FixtureTypeAlias1", "otherName") shouldBeEqualTo true
            hasTypeAliasWithName(listOf("FixtureTypeAlias1")) shouldBeEqualTo true
            hasTypeAliasWithName(listOf("otherTypeAlias")) shouldBeEqualTo false
            hasTypeAliasWithName(listOf("FixtureTypeAlias1", "otherName")) shouldBeEqualTo true
            hasTypeAliasWithName(setOf("FixtureTypeAlias1")) shouldBeEqualTo true
            hasTypeAliasWithName(setOf("otherTypeAlias")) shouldBeEqualTo false
            hasTypeAliasWithName(setOf("FixtureTypeAlias1", "otherName")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames("FixtureTypeAlias1") shouldBeEqualTo true
            hasTypeAliasesWithAllNames("FixtureTypeAlias1", "FixtureTypeAlias2") shouldBeEqualTo true
            hasTypeAliasesWithAllNames("FixtureTypeAlias1", "otherTypeAlias") shouldBeEqualTo false
            hasTypeAliasesWithAllNames(listOf("FixtureTypeAlias1")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(listOf("FixtureTypeAlias1", "FixtureTypeAlias2")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(listOf("FixtureTypeAlias1", "otherTypeAlias")) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(setOf("FixtureTypeAlias1")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(setOf("FixtureTypeAlias1", "FixtureTypeAlias2")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(setOf("FixtureTypeAlias1", "otherTypeAlias")) shouldBeEqualTo false
            hasTypeAlias { it.hasPublicOrDefaultModifier } shouldBeEqualTo true
            hasTypeAlias { it.hasPublicModifier } shouldBeEqualTo true
            hasAllTypeAliases { it.hasPublicOrDefaultModifier } shouldBeEqualTo true
            hasAllTypeAliases { it.hasPublicModifier } shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-no-typealias-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-no-typealias-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasTypeAliasWithName("fixturetypealias") shouldBeEqualTo false
            hasTypeAliasWithName("fixturetypealias", ignoreCase = true) shouldBeEqualTo false
            hasTypeAliasWithName(listOf("fixturetypealias")) shouldBeEqualTo false
            hasTypeAliasWithName(listOf("fixturetypealias"), ignoreCase = true) shouldBeEqualTo false
            hasTypeAliasWithName(setOf("fixturetypealias")) shouldBeEqualTo false
            hasTypeAliasWithName(setOf("fixturetypealias"), ignoreCase = true) shouldBeEqualTo false
            hasTypeAliasesWithAllNames("fixturetypealias1", "fixturetypealias2") shouldBeEqualTo false
            hasTypeAliasesWithAllNames("fixturetypealias1", "fixturetypealias2", ignoreCase = true) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(listOf("fixturetypealias1", "fixturetypealias2")) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(listOf("fixturetypealias1", "fixturetypealias2"), ignoreCase = true) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(setOf("fixturetypealias1", "fixturetypealias2")) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(setOf("fixturetypealias1", "fixturetypealias2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-typealiases-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-typealiases-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasTypeAliasWithName("fixturetypealias1") shouldBeEqualTo false
            hasTypeAliasWithName("fixturetypealias1", ignoreCase = true) shouldBeEqualTo true
            hasTypeAliasWithName("othertypealias") shouldBeEqualTo false
            hasTypeAliasWithName("othertypealias", ignoreCase = true) shouldBeEqualTo false
            hasTypeAliasWithName("fixturetypealias1", "otherName") shouldBeEqualTo false
            hasTypeAliasWithName("fixturetypealias1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasTypeAliasWithName(listOf("fixturetypealias1")) shouldBeEqualTo false
            hasTypeAliasWithName(listOf("fixturetypealias1"), ignoreCase = true) shouldBeEqualTo true
            hasTypeAliasWithName(listOf("othertypealias")) shouldBeEqualTo false
            hasTypeAliasWithName(listOf("othertypealias"), ignoreCase = true) shouldBeEqualTo false
            hasTypeAliasWithName(listOf("fixturetypealias1", "otherName")) shouldBeEqualTo false
            hasTypeAliasWithName(listOf("fixturetypealias1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasTypeAliasesWithAllNames("fixturetypealias1") shouldBeEqualTo false
            hasTypeAliasesWithAllNames("fixturetypealias1", ignoreCase = true) shouldBeEqualTo true
            hasTypeAliasesWithAllNames("fixturetypealias1", "fixturetypealias2") shouldBeEqualTo false
            hasTypeAliasesWithAllNames("fixturetypealias1", "fixturetypealias2", ignoreCase = true) shouldBeEqualTo true
            hasTypeAliasesWithAllNames("fixturetypealias1", "othertypealias") shouldBeEqualTo false
            hasTypeAliasesWithAllNames("fixturetypealias1", "othertypealias", ignoreCase = true) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(listOf("fixturetypealias1")) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(listOf("fixturetypealias1"), ignoreCase = true) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(listOf("fixturetypealias1", "fixturetypealias2")) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(listOf("fixturetypealias1", "fixturetypealias2"), ignoreCase = true) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(listOf("fixturetypealias1", "othertypealias")) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(listOf("fixturetypealias1", "othertypealias"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/kofile/snippet/forkotypealiasprovider/",
            fileName,
        )
}
