package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoVariableProviderTest {
    @Test
    fun `function-contains-no-variable`() {
        // given
        val sut =
            getSnippetFile("function-contains-no-variable")
                .functions()
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
    fun `function-contains-variable`() {
        // given
        val sut =
            getSnippetFile("function-contains-variable")
                .functions()
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

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kofunction/snippet/forkovariableprovider/", fileName)
}
