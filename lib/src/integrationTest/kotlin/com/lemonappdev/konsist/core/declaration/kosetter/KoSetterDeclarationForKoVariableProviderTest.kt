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
        val sut = getSnippetFile("setter-contains-no-variable")
            .properties()
            .setters
            .first()

        // then
        assertSoftly(sut) {
            variables shouldBeEqualTo emptyList()
            numVariables shouldBeEqualTo 0
            countVariables { it.name == "sampleVariable" } shouldBeEqualTo 0
            hasVariables() shouldBeEqualTo false
            hasVariableWithName("sampleVariable") shouldBeEqualTo false
            hasVariablesWithAllNames("sampleVariable1", "sampleVariable2") shouldBeEqualTo false
            hasVariable { it.name == "sampleVariable" } shouldBeEqualTo false
            hasAllVariables { it.name == "sampleVariable" } shouldBeEqualTo true
        }
    }

    @Test
    fun `setter-contains-variable`() {
        // given
        val sut = getSnippetFile("setter-contains-variable")
            .properties()
            .setters
            .first()

        // then
        assertSoftly(sut) {
            numVariables shouldBeEqualTo 2
            countVariables { it.name == "sampleVariable1" } shouldBeEqualTo 1
            hasVariables() shouldBeEqualTo true
            hasVariableWithName("sampleVariable1") shouldBeEqualTo true
            hasVariableWithName("otherVariable") shouldBeEqualTo false
            hasVariableWithName("sampleVariable1", "otherVariable") shouldBeEqualTo true
            hasVariablesWithAllNames("sampleVariable1") shouldBeEqualTo true
            hasVariablesWithAllNames("sampleVariable1", "sampleVariable2") shouldBeEqualTo true
            hasVariablesWithAllNames("sampleVariable1", "otherVariable") shouldBeEqualTo false
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
        TestSnippetProvider.getSnippetKoScope("core/declaration/kosetter/snippet/forkovariableprovider/", fileName)
}
