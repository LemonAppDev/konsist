package com.lemonappdev.konsist.core.declaration.kosetter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.setters
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterDeclarationForKoVariableProviderTest {
    @Test
    fun `setter-contains-no-variable`() {
        // given
        val sut =
            getSnippetFile("setter-contains-no-variable")
                .properties()
                .setters
                .first()

        // then
        assertSoftly(sut) {
            variables shouldBeEqualTo emptyList()
            numVariables shouldBeEqualTo 0
            countVariables { it.name == "fixtureVariable" } shouldBeEqualTo 0
            hasVariables() shouldBeEqualTo false
            hasVariableWithName(emptyList()) shouldBeEqualTo false
            hasVariableWithName(emptySet()) shouldBeEqualTo false
            hasVariablesWithAllNames(emptyList()) shouldBeEqualTo false
            hasVariablesWithAllNames(emptySet()) shouldBeEqualTo false
            hasVariableWithName("fixtureVariable") shouldBeEqualTo false
            hasVariableWithName(listOf("fixtureVariable")) shouldBeEqualTo false
            hasVariableWithName(setOf("fixtureVariable")) shouldBeEqualTo false
            hasVariablesWithAllNames("fixtureVariable1", "fixtureVariable2") shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("fixtureVariable1", "fixtureVariable2")) shouldBeEqualTo false
            hasVariablesWithAllNames(setOf("fixtureVariable1", "fixtureVariable2")) shouldBeEqualTo false
            hasVariable { it.name == "fixtureVariable" } shouldBeEqualTo false
            hasAllVariables { it.name == "fixtureVariable" } shouldBeEqualTo true
        }
    }

    @Test
    fun `setter-contains-variable`() {
        // given
        val sut =
            getSnippetFile("setter-contains-variable")
                .properties()
                .setters
                .first()

        // then
        assertSoftly(sut) {
            numVariables shouldBeEqualTo 2
            countVariables { it.name == "fixtureVariable1" } shouldBeEqualTo 1
            hasVariables() shouldBeEqualTo true
            hasVariableWithName(emptyList()) shouldBeEqualTo true
            hasVariableWithName(emptySet()) shouldBeEqualTo true
            hasVariablesWithAllNames(emptyList()) shouldBeEqualTo true
            hasVariablesWithAllNames(emptySet()) shouldBeEqualTo true
            hasVariableWithName("fixtureVariable1") shouldBeEqualTo true
            hasVariableWithName("otherVariable") shouldBeEqualTo false
            hasVariableWithName("fixtureVariable1", "otherVariable") shouldBeEqualTo true
            hasVariableWithName(listOf("fixtureVariable1")) shouldBeEqualTo true
            hasVariableWithName(listOf("otherVariable")) shouldBeEqualTo false
            hasVariableWithName(listOf("fixtureVariable1", "otherVariable")) shouldBeEqualTo true
            hasVariableWithName(setOf("fixtureVariable1")) shouldBeEqualTo true
            hasVariableWithName(setOf("otherVariable")) shouldBeEqualTo false
            hasVariableWithName(setOf("fixtureVariable1", "otherVariable")) shouldBeEqualTo true
            hasVariablesWithAllNames("fixtureVariable1") shouldBeEqualTo true
            hasVariablesWithAllNames("fixtureVariable1", "fixtureVariable2") shouldBeEqualTo true
            hasVariablesWithAllNames("fixtureVariable1", "otherVariable") shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("fixtureVariable1")) shouldBeEqualTo true
            hasVariablesWithAllNames(listOf("fixtureVariable1", "fixtureVariable2")) shouldBeEqualTo true
            hasVariablesWithAllNames(listOf("fixtureVariable1", "otherVariable")) shouldBeEqualTo false
            hasVariablesWithAllNames(setOf("fixtureVariable1")) shouldBeEqualTo true
            hasVariablesWithAllNames(setOf("fixtureVariable1", "fixtureVariable2")) shouldBeEqualTo true
            hasVariablesWithAllNames(setOf("fixtureVariable1", "otherVariable")) shouldBeEqualTo false
            hasVariable { it.name == "fixtureVariable1" } shouldBeEqualTo true
            hasVariable { it.name == "otherVariable" } shouldBeEqualTo false
            hasAllVariables { it.name.endsWith("2") || it.name == "fixtureVariable1" } shouldBeEqualTo true
            hasAllVariables { it.name.endsWith("2") } shouldBeEqualTo false
            variables
                .map { it.name }
                .shouldBeEqualTo(listOf("fixtureVariable1", "fixtureVariable2"))
        }
    }

    @Test
    fun `setter-contains-no-variable-ignore-case`() {
        // given
        val sut =
            getSnippetFile("setter-contains-no-variable-ignore-case")
                .properties()
                .setters
                .first()

        // then
        assertSoftly(sut) {
            hasVariableWithName("fixturevariable") shouldBeEqualTo false
            hasVariableWithName("fixturevariable", ignoreCase = true) shouldBeEqualTo false
            hasVariableWithName(listOf("fixturevariable")) shouldBeEqualTo false
            hasVariableWithName(listOf("fixturevariable"), ignoreCase = true) shouldBeEqualTo false
            hasVariableWithName(setOf("fixturevariable")) shouldBeEqualTo false
            hasVariableWithName(setOf("fixturevariable"), ignoreCase = true) shouldBeEqualTo false
            hasVariablesWithAllNames("fixturevariable1", "fixturevariable2") shouldBeEqualTo false
            hasVariablesWithAllNames("fixturevariable1", "fixturevariable2", ignoreCase = true) shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("fixturevariable1", "fixturevariable2")) shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("fixturevariable1", "fixturevariable2"), ignoreCase = true) shouldBeEqualTo false
            hasVariablesWithAllNames(setOf("fixturevariable1", "fixturevariable2")) shouldBeEqualTo false
            hasVariablesWithAllNames(setOf("fixturevariable1", "fixturevariable2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `setter-contains-variable-ignore-case`() {
        // given
        val sut =
            getSnippetFile("setter-contains-variable-ignore-case")
                .properties()
                .setters
                .first()

        // then
        assertSoftly(sut) {
            hasVariableWithName("fixturevariable1") shouldBeEqualTo false
            hasVariableWithName("fixturevariable1", ignoreCase = true) shouldBeEqualTo true
            hasVariableWithName("othervariable") shouldBeEqualTo false
            hasVariableWithName("othervariable", ignoreCase = true) shouldBeEqualTo false
            hasVariableWithName("fixturevariable1", "otherName") shouldBeEqualTo false
            hasVariableWithName("fixturevariable1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasVariableWithName(listOf("fixturevariable1")) shouldBeEqualTo false
            hasVariableWithName(listOf("fixturevariable1"), ignoreCase = true) shouldBeEqualTo true
            hasVariableWithName(listOf("othervariable")) shouldBeEqualTo false
            hasVariableWithName(listOf("othervariable"), ignoreCase = true) shouldBeEqualTo false
            hasVariableWithName(listOf("fixturevariable1", "otherName")) shouldBeEqualTo false
            hasVariableWithName(listOf("fixturevariable1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasVariablesWithAllNames("fixturevariable1") shouldBeEqualTo false
            hasVariablesWithAllNames("fixturevariable1", ignoreCase = true) shouldBeEqualTo true
            hasVariablesWithAllNames("fixturevariable1", "fixturevariable2") shouldBeEqualTo false
            hasVariablesWithAllNames("fixturevariable1", "fixturevariable2", ignoreCase = true) shouldBeEqualTo true
            hasVariablesWithAllNames("fixturevariable1", "othervariable") shouldBeEqualTo false
            hasVariablesWithAllNames("fixturevariable1", "othervariable", ignoreCase = true) shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("fixturevariable1")) shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("fixturevariable1"), ignoreCase = true) shouldBeEqualTo true
            hasVariablesWithAllNames(listOf("fixturevariable1", "fixturevariable2")) shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("fixturevariable1", "fixturevariable2"), ignoreCase = true) shouldBeEqualTo true
            hasVariablesWithAllNames(listOf("fixturevariable1", "othervariable")) shouldBeEqualTo false
            hasVariablesWithAllNames(listOf("fixturevariable1", "othervariable"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kosetter/snippet/forkovariableprovider/", fileName)
}
