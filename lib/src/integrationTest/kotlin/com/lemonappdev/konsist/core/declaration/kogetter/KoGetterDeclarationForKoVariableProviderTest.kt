package com.lemonappdev.konsist.core.declaration.kogetter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.getters
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterDeclarationForKoVariableProviderTest {
    @Test
    fun `getter-contains-no-variable`() {
        // given
        val sut =
            getSnippetFile("getter-contains-no-variable")
                .properties()
                .getters
                .first()

        // then
        assertSoftly(sut) {
            variables shouldBeEqualTo emptyList()
            numVariables shouldBeEqualTo 0
            countVariables { it.name == "sampleVariable" } shouldBeEqualTo 0
            hasVariables() shouldBeEqualTo false
            hasVariableWithName(emptyList()) shouldBeEqualTo false
            hasVariableWithName(emptySet()) shouldBeEqualTo false
            hasVariablesWithAllNames(emptyList()) shouldBeEqualTo false
            hasVariablesWithAllNames(emptySet()) shouldBeEqualTo false
            hasVariableWithName("sampleVariable") shouldBeEqualTo false
            hasVariableWithName(listOf("sampleVariable")) shouldBeEqualTo false
            hasVariableWithName(setOf("sampleVariable")) shouldBeEqualTo false
            hasVariablesWithAllNames("sampleVariable1", "sampleVariable2") shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("sampleVariable1", "sampleVariable2")) shouldBeEqualTo false
            hasVariablesWithAllNames(setOf("sampleVariable1", "sampleVariable2")) shouldBeEqualTo false
            hasVariable { it.name == "sampleVariable" } shouldBeEqualTo false
            hasAllVariables { it.name == "sampleVariable" } shouldBeEqualTo true
        }
    }

    @Test
    fun `getter-contains-variable`() {
        // given
        val sut =
            getSnippetFile("getter-contains-variable")
                .properties()
                .getters
                .first()

        // then
        assertSoftly(sut) {
            numVariables shouldBeEqualTo 2
            countVariables { it.name == "sampleVariable1" } shouldBeEqualTo 1
            hasVariables() shouldBeEqualTo true
            hasVariableWithName(emptyList()) shouldBeEqualTo true
            hasVariableWithName(emptySet()) shouldBeEqualTo true
            hasVariablesWithAllNames(emptyList()) shouldBeEqualTo true
            hasVariablesWithAllNames(emptySet()) shouldBeEqualTo true
            hasVariableWithName("sampleVariable1") shouldBeEqualTo true
            hasVariableWithName("otherVariable") shouldBeEqualTo false
            hasVariableWithName("sampleVariable1", "otherVariable") shouldBeEqualTo true
            hasVariableWithName(listOf("sampleVariable1")) shouldBeEqualTo true
            hasVariableWithName(listOf("otherVariable")) shouldBeEqualTo false
            hasVariableWithName(listOf("sampleVariable1", "otherVariable")) shouldBeEqualTo true
            hasVariableWithName(setOf("sampleVariable1")) shouldBeEqualTo true
            hasVariableWithName(setOf("otherVariable")) shouldBeEqualTo false
            hasVariableWithName(setOf("sampleVariable1", "otherVariable")) shouldBeEqualTo true
            hasVariablesWithAllNames("sampleVariable1") shouldBeEqualTo true
            hasVariablesWithAllNames("sampleVariable1", "sampleVariable2") shouldBeEqualTo true
            hasVariablesWithAllNames("sampleVariable1", "otherVariable") shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("sampleVariable1")) shouldBeEqualTo true
            hasVariablesWithAllNames(listOf("sampleVariable1", "sampleVariable2")) shouldBeEqualTo true
            hasVariablesWithAllNames(listOf("sampleVariable1", "otherVariable")) shouldBeEqualTo false
            hasVariablesWithAllNames(setOf("sampleVariable1")) shouldBeEqualTo true
            hasVariablesWithAllNames(setOf("sampleVariable1", "sampleVariable2")) shouldBeEqualTo true
            hasVariablesWithAllNames(setOf("sampleVariable1", "otherVariable")) shouldBeEqualTo false
            hasVariable { it.name == "sampleVariable1" } shouldBeEqualTo true
            hasVariable { it.name == "otherVariable" } shouldBeEqualTo false
            hasAllVariables { it.name.endsWith("2") || it.name == "sampleVariable1" } shouldBeEqualTo true
            hasAllVariables { it.name.endsWith("2") } shouldBeEqualTo false
            variables
                .map { it.name }
                .shouldBeEqualTo(listOf("sampleVariable1", "sampleVariable2"))
        }
    }

    @Test
    fun `getter-contains-no-variable-ignore-case`() {
        // given
        val sut =
            getSnippetFile("getter-contains-no-variable-ignore-case")
                .properties()
                .getters
                .first()

        // then
        assertSoftly(sut) {
            hasVariableWithName("samplevariable") shouldBeEqualTo false
            hasVariableWithName("samplevariable", ignoreCase = true) shouldBeEqualTo false
            hasVariableWithName(listOf("samplevariable")) shouldBeEqualTo false
            hasVariableWithName(listOf("samplevariable"), ignoreCase = true) shouldBeEqualTo false
            hasVariableWithName(setOf("samplevariable")) shouldBeEqualTo false
            hasVariableWithName(setOf("samplevariable"), ignoreCase = true) shouldBeEqualTo false
            hasVariablesWithAllNames("samplevariable1", "samplevariable2") shouldBeEqualTo false
            hasVariablesWithAllNames("samplevariable1", "samplevariable2", ignoreCase = true) shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("samplevariable1", "samplevariable2")) shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("samplevariable1", "samplevariable2"), ignoreCase = true) shouldBeEqualTo false
            hasVariablesWithAllNames(setOf("samplevariable1", "samplevariable2")) shouldBeEqualTo false
            hasVariablesWithAllNames(setOf("samplevariable1", "samplevariable2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `getter-contains-variable-ignore-case`() {
        // given
        val sut =
            getSnippetFile("getter-contains-variable-ignore-case")
                .properties()
                .getters
                .first()

        // then
        assertSoftly(sut) {
            hasVariableWithName("samplevariable1") shouldBeEqualTo false
            hasVariableWithName("samplevariable1", ignoreCase = true) shouldBeEqualTo true
            hasVariableWithName("othervariable") shouldBeEqualTo false
            hasVariableWithName("othervariable", ignoreCase = true) shouldBeEqualTo false
            hasVariableWithName("samplevariable1", "otherName") shouldBeEqualTo false
            hasVariableWithName("samplevariable1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasVariableWithName(listOf("samplevariable1")) shouldBeEqualTo false
            hasVariableWithName(listOf("samplevariable1"), ignoreCase = true) shouldBeEqualTo true
            hasVariableWithName(listOf("othervariable")) shouldBeEqualTo false
            hasVariableWithName(listOf("othervariable"), ignoreCase = true) shouldBeEqualTo false
            hasVariableWithName(listOf("samplevariable1", "otherName")) shouldBeEqualTo false
            hasVariableWithName(listOf("samplevariable1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasVariablesWithAllNames("samplevariable1") shouldBeEqualTo false
            hasVariablesWithAllNames("samplevariable1", ignoreCase = true) shouldBeEqualTo true
            hasVariablesWithAllNames("samplevariable1", "samplevariable2") shouldBeEqualTo false
            hasVariablesWithAllNames("samplevariable1", "samplevariable2", ignoreCase = true) shouldBeEqualTo true
            hasVariablesWithAllNames("samplevariable1", "othervariable") shouldBeEqualTo false
            hasVariablesWithAllNames("samplevariable1", "othervariable", ignoreCase = true) shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("samplevariable1")) shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("samplevariable1"), ignoreCase = true) shouldBeEqualTo true
            hasVariablesWithAllNames(listOf("samplevariable1", "samplevariable2")) shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("samplevariable1", "samplevariable2"), ignoreCase = true) shouldBeEqualTo true
            hasVariablesWithAllNames(listOf("samplevariable1", "othervariable")) shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("samplevariable1", "othervariable"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kogetter/snippet/forkovariableprovider/", fileName)
}
