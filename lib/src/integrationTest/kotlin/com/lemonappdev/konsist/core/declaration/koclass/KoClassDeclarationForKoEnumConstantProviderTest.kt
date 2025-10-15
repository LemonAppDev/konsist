package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoEnumConstantProviderTest {
    @Test
    fun `class-has-no-constant`() {
        // given
        val sut =
            getSnippetFile("class-has-no-constant")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            enumConstants shouldBeEqualTo emptyList()
            numEnumConstants shouldBeEqualTo 0
            countEnumConstants { it.hasNameStartingWith("SAMPLE") } shouldBeEqualTo 0
            hasEnumConstants() shouldBeEqualTo false
            hasEnumConstantWithName(emptyList()) shouldBeEqualTo false
            hasEnumConstantWithName(emptySet()) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(emptyList()) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(emptySet()) shouldBeEqualTo false
            hasEnumConstantWithName("SAMPLE_CONSTANT") shouldBeEqualTo false
            hasEnumConstantWithName(listOf("SAMPLE_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantWithName(setOf("SAMPLE_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames("SAMPLE_CONSTANT1", "SAMPLE_CONSTANT2") shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("SAMPLE_CONSTANT1", "SAMPLE_CONSTANT2")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(setOf("SAMPLE_CONSTANT1", "SAMPLE_CONSTANT2")) shouldBeEqualTo false
            hasEnumConstant { it.hasNameStartingWith("SAMPLE") } shouldBeEqualTo false
            hasAllEnumConstants { it.hasNameStartingWith("SAMPLE") } shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-one-constant`() {
        // given
        val sut =
            getSnippetFile("class-has-one-constant")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            enumConstants.size shouldBeEqualTo 1
            numEnumConstants shouldBeEqualTo 1
            countEnumConstants { it.hasNameStartingWith("SAMPLE") } shouldBeEqualTo 1
            hasEnumConstants() shouldBeEqualTo true
            hasEnumConstantWithName(emptyList()) shouldBeEqualTo true
            hasEnumConstantWithName(emptySet()) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(emptyList()) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(emptySet()) shouldBeEqualTo true
            hasEnumConstantWithName("SAMPLE_CONSTANT") shouldBeEqualTo true
            hasEnumConstantWithName("OTHER_CONSTANT") shouldBeEqualTo false
            hasEnumConstantWithName("SAMPLE_CONSTANT", "OTHER_CONSTANT") shouldBeEqualTo true
            hasEnumConstantWithName(listOf("SAMPLE_CONSTANT")) shouldBeEqualTo true
            hasEnumConstantWithName(listOf("OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("SAMPLE_CONSTANT", "OTHER_CONSTANT")) shouldBeEqualTo true
            hasEnumConstantWithName(setOf("SAMPLE_CONSTANT")) shouldBeEqualTo true
            hasEnumConstantWithName(setOf("OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantWithName(setOf("SAMPLE_CONSTANT", "OTHER_CONSTANT")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames("SAMPLE_CONSTANT") shouldBeEqualTo true
            hasEnumConstantsWithAllNames("SAMPLE_CONSTANT", "OTHER_CONSTANT") shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("SAMPLE_CONSTANT")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(listOf("SAMPLE_CONSTANT", "OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(setOf("SAMPLE_CONSTANT")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(setOf("SAMPLE_CONSTANT", "OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstant { it.hasNameStartingWith("SAMPLE") } shouldBeEqualTo true
            hasEnumConstant { it.name == "OTHER_CONSTANT" } shouldBeEqualTo false
            hasAllEnumConstants { it.hasNameStartingWith("SAMPLE") } shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-two-constants`() {
        // given
        val sut =
            getSnippetFile("class-has-two-constants")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            numEnumConstants shouldBeEqualTo 2
            countEnumConstants { it.hasNameStartingWith("SAMPLE") } shouldBeEqualTo 2
            countEnumConstants { it.name == "SAMPLE_CONSTANT_1" } shouldBeEqualTo 1
            hasEnumConstants() shouldBeEqualTo true
            hasEnumConstantWithName(emptyList()) shouldBeEqualTo true
            hasEnumConstantWithName(emptySet()) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(emptyList()) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(emptySet()) shouldBeEqualTo true
            hasEnumConstantWithName("SAMPLE_CONSTANT_1") shouldBeEqualTo true
            hasEnumConstantWithName("OTHER_CONSTANT") shouldBeEqualTo false
            hasEnumConstantWithName("SAMPLE_CONSTANT_1", "otherName") shouldBeEqualTo true
            hasEnumConstantWithName(listOf("SAMPLE_CONSTANT_1")) shouldBeEqualTo true
            hasEnumConstantWithName(listOf("OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("SAMPLE_CONSTANT_1", "otherName")) shouldBeEqualTo true
            hasEnumConstantWithName(setOf("SAMPLE_CONSTANT_1")) shouldBeEqualTo true
            hasEnumConstantWithName(setOf("OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantWithName(setOf("SAMPLE_CONSTANT_1", "otherName")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames("SAMPLE_CONSTANT_1") shouldBeEqualTo true
            hasEnumConstantsWithAllNames("SAMPLE_CONSTANT_1", "SAMPLE_CONSTANT_2") shouldBeEqualTo true
            hasEnumConstantsWithAllNames("SAMPLE_CONSTANT_1", "OTHER_CONSTANT") shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("SAMPLE_CONSTANT_1")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(listOf("SAMPLE_CONSTANT_1", "SAMPLE_CONSTANT_2")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(listOf("SAMPLE_CONSTANT_1", "OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(setOf("SAMPLE_CONSTANT_1")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(setOf("SAMPLE_CONSTANT_1", "SAMPLE_CONSTANT_2")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(setOf("SAMPLE_CONSTANT_1", "OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstant { it.name == "SAMPLE_CONSTANT_1" } shouldBeEqualTo true
            hasEnumConstant { it.name == "OTHER_CONSTANT_1" } shouldBeEqualTo false
            hasAllEnumConstants { it.name == "SAMPLE_CONSTANT_1" } shouldBeEqualTo false
            hasAllEnumConstants { it.hasNameStartingWith("SAMPLE") } shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-no-constant-ignore-case`() {
        // given
        val sut =
            getSnippetFile("class-has-no-constant-ignore-case")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasEnumConstantWithName("sample_constant") shouldBeEqualTo false
            hasEnumConstantWithName("sample_constant", ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("sample_constant")) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("sample_constant"), ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantWithName(setOf("sample_constant")) shouldBeEqualTo false
            hasEnumConstantWithName(setOf("sample_constant"), ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantsWithAllNames("sample_constant_1", "sample_constant_2") shouldBeEqualTo false
            hasEnumConstantsWithAllNames("sample_constant_1", "sample_constant_2", ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("sample_constant_1", "sample_constant_2")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("sample_constant_1", "sample_constant_2"), ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(setOf("sample_constant_1", "sample_constant_2")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(setOf("sample_constant_1", "sample_constant_2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-constants-ignore-case`() {
        // given
        val sut =
            getSnippetFile("class-has-constants-ignore-case")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasEnumConstantWithName("sample_constant_1") shouldBeEqualTo false
            hasEnumConstantWithName("sample_constant_1", ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantWithName("other_constant") shouldBeEqualTo false
            hasEnumConstantWithName("other_constant", ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantWithName("sample_constant_1", "otherName") shouldBeEqualTo false
            hasEnumConstantWithName("sample_constant_1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantWithName(listOf("sample_constant_1")) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("sample_constant_1"), ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantWithName(listOf("other_constant")) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("other_constant"), ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("sample_constant_1", "otherName")) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("sample_constant_1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantsWithAllNames("sample_constant_1") shouldBeEqualTo false
            hasEnumConstantsWithAllNames("sample_constant_1", ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantsWithAllNames("sample_constant_1", "sample_constant_2") shouldBeEqualTo false
            hasEnumConstantsWithAllNames("sample_constant_1", "sample_constant_2", ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantsWithAllNames("sample_constant_1", "other_constant") shouldBeEqualTo false
            hasEnumConstantsWithAllNames("sample_constant_1", "other_constant", ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("sample_constant_1")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("sample_constant_1"), ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(listOf("sample_constant_1", "sample_constant_2")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("sample_constant_1", "sample_constant_2"), ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(listOf("sample_constant_1", "other_constant")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("sample_constant_1", "other_constant"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkoenumconstantprovider/", fileName)
}
