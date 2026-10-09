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
            countEnumConstants { it.hasNameStartingWith("FIXTURE") } shouldBeEqualTo 0
            hasEnumConstants() shouldBeEqualTo false
            hasEnumConstantWithName(emptyList()) shouldBeEqualTo false
            hasEnumConstantWithName(emptySet()) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(emptyList()) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(emptySet()) shouldBeEqualTo false
            hasEnumConstantWithName("FIXTURE_CONSTANT") shouldBeEqualTo false
            hasEnumConstantWithName(listOf("FIXTURE_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantWithName(setOf("FIXTURE_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames("FIXTURE_CONSTANT1", "FIXTURE_CONSTANT2") shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("FIXTURE_CONSTANT1", "FIXTURE_CONSTANT2")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(setOf("FIXTURE_CONSTANT1", "FIXTURE_CONSTANT2")) shouldBeEqualTo false
            hasEnumConstant { it.hasNameStartingWith("FIXTURE") } shouldBeEqualTo false
            hasAllEnumConstants { it.hasNameStartingWith("FIXTURE") } shouldBeEqualTo true
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
            countEnumConstants { it.hasNameStartingWith("FIXTURE") } shouldBeEqualTo 1
            hasEnumConstants() shouldBeEqualTo true
            hasEnumConstantWithName(emptyList()) shouldBeEqualTo true
            hasEnumConstantWithName(emptySet()) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(emptyList()) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(emptySet()) shouldBeEqualTo true
            hasEnumConstantWithName("FIXTURE_CONSTANT") shouldBeEqualTo true
            hasEnumConstantWithName("OTHER_CONSTANT") shouldBeEqualTo false
            hasEnumConstantWithName("FIXTURE_CONSTANT", "OTHER_CONSTANT") shouldBeEqualTo true
            hasEnumConstantWithName(listOf("FIXTURE_CONSTANT")) shouldBeEqualTo true
            hasEnumConstantWithName(listOf("OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("FIXTURE_CONSTANT", "OTHER_CONSTANT")) shouldBeEqualTo true
            hasEnumConstantWithName(setOf("FIXTURE_CONSTANT")) shouldBeEqualTo true
            hasEnumConstantWithName(setOf("OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantWithName(setOf("FIXTURE_CONSTANT", "OTHER_CONSTANT")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames("FIXTURE_CONSTANT") shouldBeEqualTo true
            hasEnumConstantsWithAllNames("FIXTURE_CONSTANT", "OTHER_CONSTANT") shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("FIXTURE_CONSTANT")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(listOf("FIXTURE_CONSTANT", "OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(setOf("FIXTURE_CONSTANT")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(setOf("FIXTURE_CONSTANT", "OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstant { it.hasNameStartingWith("FIXTURE") } shouldBeEqualTo true
            hasEnumConstant { it.name == "OTHER_CONSTANT" } shouldBeEqualTo false
            hasAllEnumConstants { it.hasNameStartingWith("FIXTURE") } shouldBeEqualTo true
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
            countEnumConstants { it.hasNameStartingWith("FIXTURE") } shouldBeEqualTo 2
            countEnumConstants { it.name == "FIXTURE_CONSTANT_1" } shouldBeEqualTo 1
            hasEnumConstants() shouldBeEqualTo true
            hasEnumConstantWithName(emptyList()) shouldBeEqualTo true
            hasEnumConstantWithName(emptySet()) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(emptyList()) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(emptySet()) shouldBeEqualTo true
            hasEnumConstantWithName("FIXTURE_CONSTANT_1") shouldBeEqualTo true
            hasEnumConstantWithName("OTHER_CONSTANT") shouldBeEqualTo false
            hasEnumConstantWithName("FIXTURE_CONSTANT_1", "otherName") shouldBeEqualTo true
            hasEnumConstantWithName(listOf("FIXTURE_CONSTANT_1")) shouldBeEqualTo true
            hasEnumConstantWithName(listOf("OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("FIXTURE_CONSTANT_1", "otherName")) shouldBeEqualTo true
            hasEnumConstantWithName(setOf("FIXTURE_CONSTANT_1")) shouldBeEqualTo true
            hasEnumConstantWithName(setOf("OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantWithName(setOf("FIXTURE_CONSTANT_1", "otherName")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames("FIXTURE_CONSTANT_1") shouldBeEqualTo true
            hasEnumConstantsWithAllNames("FIXTURE_CONSTANT_1", "FIXTURE_CONSTANT_2") shouldBeEqualTo true
            hasEnumConstantsWithAllNames("FIXTURE_CONSTANT_1", "OTHER_CONSTANT") shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("FIXTURE_CONSTANT_1")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(listOf("FIXTURE_CONSTANT_1", "FIXTURE_CONSTANT_2")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(listOf("FIXTURE_CONSTANT_1", "OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(setOf("FIXTURE_CONSTANT_1")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(setOf("FIXTURE_CONSTANT_1", "FIXTURE_CONSTANT_2")) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(setOf("FIXTURE_CONSTANT_1", "OTHER_CONSTANT")) shouldBeEqualTo false
            hasEnumConstant { it.name == "FIXTURE_CONSTANT_1" } shouldBeEqualTo true
            hasEnumConstant { it.name == "OTHER_CONSTANT_1" } shouldBeEqualTo false
            hasAllEnumConstants { it.name == "FIXTURE_CONSTANT_1" } shouldBeEqualTo false
            hasAllEnumConstants { it.hasNameStartingWith("FIXTURE") } shouldBeEqualTo true
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
            hasEnumConstantWithName("fixture_constant") shouldBeEqualTo false
            hasEnumConstantWithName("fixture_constant", ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("fixture_constant")) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("fixture_constant"), ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantWithName(setOf("fixture_constant")) shouldBeEqualTo false
            hasEnumConstantWithName(setOf("fixture_constant"), ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantsWithAllNames("fixture_constant_1", "fixture_constant_2") shouldBeEqualTo false
            hasEnumConstantsWithAllNames("fixture_constant_1", "fixture_constant_2", ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("fixture_constant_1", "fixture_constant_2")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("fixture_constant_1", "fixture_constant_2"), ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(setOf("fixture_constant_1", "fixture_constant_2")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(setOf("fixture_constant_1", "fixture_constant_2"), ignoreCase = true) shouldBeEqualTo false
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
            hasEnumConstantWithName("fixture_constant_1") shouldBeEqualTo false
            hasEnumConstantWithName("fixture_constant_1", ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantWithName("other_constant") shouldBeEqualTo false
            hasEnumConstantWithName("other_constant", ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantWithName("fixture_constant_1", "otherName") shouldBeEqualTo false
            hasEnumConstantWithName("fixture_constant_1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantWithName(listOf("fixture_constant_1")) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("fixture_constant_1"), ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantWithName(listOf("other_constant")) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("other_constant"), ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("fixture_constant_1", "otherName")) shouldBeEqualTo false
            hasEnumConstantWithName(listOf("fixture_constant_1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantsWithAllNames("fixture_constant_1") shouldBeEqualTo false
            hasEnumConstantsWithAllNames("fixture_constant_1", ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantsWithAllNames("fixture_constant_1", "fixture_constant_2") shouldBeEqualTo false
            hasEnumConstantsWithAllNames("fixture_constant_1", "fixture_constant_2", ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantsWithAllNames("fixture_constant_1", "other_constant") shouldBeEqualTo false
            hasEnumConstantsWithAllNames("fixture_constant_1", "other_constant", ignoreCase = true) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("fixture_constant_1")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("fixture_constant_1"), ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(listOf("fixture_constant_1", "fixture_constant_2")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("fixture_constant_1", "fixture_constant_2"), ignoreCase = true) shouldBeEqualTo true
            hasEnumConstantsWithAllNames(listOf("fixture_constant_1", "other_constant")) shouldBeEqualTo false
            hasEnumConstantsWithAllNames(listOf("fixture_constant_1", "other_constant"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkoenumconstantprovider/", fileName)
}
