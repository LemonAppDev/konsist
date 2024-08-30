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
            hasTypeAliasWithName("SampleTypeAlias") shouldBeEqualTo false
            hasTypeAliasWithName(listOf("SampleTypeAlias")) shouldBeEqualTo false
            hasTypeAliasWithName(setOf("SampleTypeAlias")) shouldBeEqualTo false
            hasTypeAliasesWithAllNames("SampleTypeAlias1", "SampleTypeAlias2") shouldBeEqualTo false
            hasTypeAliasesWithAllNames(listOf("SampleTypeAlias1", "SampleTypeAlias2")) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(setOf("SampleTypeAlias1", "SampleTypeAlias2")) shouldBeEqualTo false
            hasTypeAlias { it.hasPublicModifier } shouldBeEqualTo false
            hasAllTypeAliases { it.hasPublicOrDefaultModifier } shouldBeEqualTo true
            hasTypeAliases("SampleTypeAlias") shouldBeEqualTo false
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
            hasTypeAliasWithName("SampleTypeAlias") shouldBeEqualTo true
            hasTypeAliasWithName("otherTypeAlias") shouldBeEqualTo false
            hasTypeAliasWithName("SampleTypeAlias", "otherTypeAlias") shouldBeEqualTo true
            hasTypeAliasWithName(listOf("SampleTypeAlias")) shouldBeEqualTo true
            hasTypeAliasWithName(listOf("otherTypeAlias")) shouldBeEqualTo false
            hasTypeAliasWithName(listOf("SampleTypeAlias", "otherTypeAlias")) shouldBeEqualTo true
            hasTypeAliasWithName(setOf("SampleTypeAlias")) shouldBeEqualTo true
            hasTypeAliasWithName(setOf("otherTypeAlias")) shouldBeEqualTo false
            hasTypeAliasWithName(setOf("SampleTypeAlias", "otherTypeAlias")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames("SampleTypeAlias") shouldBeEqualTo true
            hasTypeAliasesWithAllNames("SampleTypeAlias", "otherTypeAlias") shouldBeEqualTo false
            hasTypeAliasesWithAllNames(listOf("SampleTypeAlias")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(listOf("SampleTypeAlias", "otherTypeAlias")) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(setOf("SampleTypeAlias")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(setOf("SampleTypeAlias", "otherTypeAlias")) shouldBeEqualTo false
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
            countTypeAliases { it.hasNameStartingWith("Sample") } shouldBeEqualTo 2
            countTypeAliases { it.name == "SampleTypeAlias1" } shouldBeEqualTo 1
            hasTypeAliases() shouldBeEqualTo true
            hasTypeAliasWithName(emptyList()) shouldBeEqualTo true
            hasTypeAliasWithName(emptySet()) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(emptyList()) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(emptySet()) shouldBeEqualTo true
            hasTypeAliasWithName("SampleTypeAlias1") shouldBeEqualTo true
            hasTypeAliasWithName("otherTypeAlias") shouldBeEqualTo false
            hasTypeAliasWithName("SampleTypeAlias1", "otherName") shouldBeEqualTo true
            hasTypeAliasWithName(listOf("SampleTypeAlias1")) shouldBeEqualTo true
            hasTypeAliasWithName(listOf("otherTypeAlias")) shouldBeEqualTo false
            hasTypeAliasWithName(listOf("SampleTypeAlias1", "otherName")) shouldBeEqualTo true
            hasTypeAliasWithName(setOf("SampleTypeAlias1")) shouldBeEqualTo true
            hasTypeAliasWithName(setOf("otherTypeAlias")) shouldBeEqualTo false
            hasTypeAliasWithName(setOf("SampleTypeAlias1", "otherName")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames("SampleTypeAlias1") shouldBeEqualTo true
            hasTypeAliasesWithAllNames("SampleTypeAlias1", "SampleTypeAlias2") shouldBeEqualTo true
            hasTypeAliasesWithAllNames("SampleTypeAlias1", "otherTypeAlias") shouldBeEqualTo false
            hasTypeAliasesWithAllNames(listOf("SampleTypeAlias1")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(listOf("SampleTypeAlias1", "SampleTypeAlias2")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(listOf("SampleTypeAlias1", "otherTypeAlias")) shouldBeEqualTo false
            hasTypeAliasesWithAllNames(setOf("SampleTypeAlias1")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(setOf("SampleTypeAlias1", "SampleTypeAlias2")) shouldBeEqualTo true
            hasTypeAliasesWithAllNames(setOf("SampleTypeAlias1", "otherTypeAlias")) shouldBeEqualTo false
            hasTypeAlias { it.hasPublicOrDefaultModifier } shouldBeEqualTo true
            hasTypeAlias { it.hasPublicModifier } shouldBeEqualTo true
            hasAllTypeAliases { it.hasPublicOrDefaultModifier } shouldBeEqualTo true
            hasAllTypeAliases { it.hasPublicModifier } shouldBeEqualTo false
            hasTypeAliases("SampleTypeAlias1") shouldBeEqualTo true
            hasTypeAliases("SampleTypeAlias1", "SampleTypeAlias2") shouldBeEqualTo true
            hasTypeAliases("OtherTypeAlias") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/kofile/snippet/forkotypealiasprovider/",
            fileName,
        )
}
