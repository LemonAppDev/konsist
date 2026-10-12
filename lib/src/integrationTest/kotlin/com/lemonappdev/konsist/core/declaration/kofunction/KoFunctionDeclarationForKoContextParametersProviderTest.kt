package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoContextParametersProviderTest {
    @Test
    fun `function-has-no-context-parameters`() {
        // given
        val sut =
            getSnippetFile("function-has-no-context-parameters")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            contextParameters shouldBeEqualTo emptyList()
            numContextParameters shouldBeEqualTo 0
            countContextParameters { it.hasNameStartingWith("fixture") } shouldBeEqualTo 0
            hasContextParameters() shouldBeEqualTo false
            hasContextParameterWithName(emptyList()) shouldBeEqualTo false
            hasContextParameterWithName(emptySet()) shouldBeEqualTo false
            hasContextParametersWithAllNames(emptyList()) shouldBeEqualTo false
            hasContextParametersWithAllNames(emptySet()) shouldBeEqualTo false
            hasContextParameterWithName("fixtureParameter") shouldBeEqualTo false
            hasContextParameterWithName(listOf("fixtureParameter")) shouldBeEqualTo false
            hasContextParameterWithName(setOf("fixtureParameter")) shouldBeEqualTo false
            hasContextParametersWithAllNames("fixtureParameter1", "fixtureParameter2") shouldBeEqualTo false
            hasContextParametersWithAllNames(listOf("fixtureParameter1", "fixtureParameter2")) shouldBeEqualTo false
            hasContextParametersWithAllNames(setOf("fixtureParameter1", "fixtureParameter2")) shouldBeEqualTo false
            hasContextParameter { it.hasNameStartingWith("other") } shouldBeEqualTo false
            hasAllContextParameters { it.hasNameStartingWith("fixture") } shouldBeEqualTo true
        }
    }

    @Test
    fun `function-has-one-context-parameter`() {
        // given
        val sut =
            getSnippetFile("function-has-one-context-parameter")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            contextParameters.map { it.name } shouldBeEqualTo listOf("fixtureParameter")
            numContextParameters shouldBeEqualTo 1
            countContextParameters { it.hasNameStartingWith("fixture") } shouldBeEqualTo 1
            hasContextParameters() shouldBeEqualTo true
            hasContextParameterWithName(emptyList()) shouldBeEqualTo true
            hasContextParameterWithName(emptySet()) shouldBeEqualTo true
            hasContextParametersWithAllNames(emptyList()) shouldBeEqualTo true
            hasContextParametersWithAllNames(emptySet()) shouldBeEqualTo true
            hasContextParameterWithName("fixtureParameter") shouldBeEqualTo true
            hasContextParameterWithName("otherParameter") shouldBeEqualTo false
            hasContextParameterWithName("fixtureParameter", "otherParameter") shouldBeEqualTo true
            hasContextParameterWithName(listOf("fixtureParameter")) shouldBeEqualTo true
            hasContextParameterWithName(listOf("otherParameter")) shouldBeEqualTo false
            hasContextParameterWithName(setOf("fixtureParameter", "otherParameter")) shouldBeEqualTo true
            hasContextParametersWithAllNames("fixtureParameter") shouldBeEqualTo true
            hasContextParametersWithAllNames("fixtureParameter", "otherParameter") shouldBeEqualTo false
            hasContextParametersWithAllNames(listOf("fixtureParameter")) shouldBeEqualTo true
            hasContextParametersWithAllNames(setOf("fixtureParameter", "otherParameter")) shouldBeEqualTo false
            hasContextParameter { it.hasNameStartingWith("fixture") } shouldBeEqualTo true
            hasContextParameter { it.hasNameStartingWith("other") } shouldBeEqualTo false
            hasContextParameter { it.hasType { type -> type.name == "Logger" } } shouldBeEqualTo true
            hasAllContextParameters { it.hasNameStartingWith("fixture") } shouldBeEqualTo true
        }
    }

    @Test
    fun `function-has-two-context-parameters`() {
        // given
        val sut =
            getSnippetFile("function-has-two-context-parameters")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            contextParameters.map { it.name } shouldBeEqualTo listOf("fixtureParameter1", "fixtureParameter2")
            contextParameters.map { it.type.name } shouldBeEqualTo listOf("Logger", "Tracer")
            numContextParameters shouldBeEqualTo 2
            countContextParameters { it.hasNameStartingWith("fixture") } shouldBeEqualTo 2
            countContextParameters { it.hasType { type -> type.name == "Logger" } } shouldBeEqualTo 1
            hasContextParameters() shouldBeEqualTo true
            hasContextParameterWithName("fixtureParameter1") shouldBeEqualTo true
            hasContextParameterWithName("otherParameter") shouldBeEqualTo false
            hasContextParameterWithName("fixtureParameter1", "otherParameter") shouldBeEqualTo true
            hasContextParametersWithAllNames("fixtureParameter1", "fixtureParameter2") shouldBeEqualTo true
            hasContextParametersWithAllNames("fixtureParameter1", "otherParameter") shouldBeEqualTo false
            hasContextParametersWithAllNames(listOf("fixtureParameter1", "fixtureParameter2")) shouldBeEqualTo true
            hasContextParametersWithAllNames(setOf("fixtureParameter1", "otherParameter")) shouldBeEqualTo false
            hasContextParameter { it.hasType { type -> type.name == "Tracer" } } shouldBeEqualTo true
            hasAllContextParameters { it.hasNameStartingWith("fixture") } shouldBeEqualTo true
            hasAllContextParameters { it.hasType { type -> type.name == "Logger" } } shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-anonymous-context-parameter`() {
        // given
        val sut =
            getSnippetFile("function-has-anonymous-context-parameter")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            contextParameters.map { it.name } shouldBeEqualTo listOf("_")
            contextParameters.map { it.type.name } shouldBeEqualTo listOf("Logger")
            hasContextParameterWithName("_") shouldBeEqualTo true
        }
    }

    @Test
    fun `function-in-interface-has-context-parameter`() {
        // given
        val sut =
            getSnippetFile("function-in-interface-has-context-parameter")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            hasContextParameter { it.hasType { type -> type.name == "Logger" } } shouldBeEqualTo true
            contextParameters.first().containingDeclaration shouldBeEqualTo sut
        }
    }

    @Test
    fun `function-has-context-parameter-and-modifier`() {
        // given
        val sut =
            getSnippetFile("function-has-context-parameter-and-modifier")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            hasContextParameterWithName("fixtureParameter") shouldBeEqualTo true
            modifiers shouldBeEqualTo listOf(KoModifier.SUSPEND)
            hasAnnotations() shouldBeEqualTo false
            name shouldBeEqualTo "fixtureFunction"
        }
    }

    @Test
    fun `function-has-context-parameters-ignore-case`() {
        // given
        val sut =
            getSnippetFile("function-has-context-parameters-ignore-case")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            hasContextParameterWithName("fixtureparameter1") shouldBeEqualTo false
            hasContextParameterWithName("fixtureparameter1", ignoreCase = true) shouldBeEqualTo true
            hasContextParameterWithName(listOf("fixtureparameter1"), ignoreCase = true) shouldBeEqualTo true
            hasContextParameterWithName(setOf("otherparameter"), ignoreCase = true) shouldBeEqualTo false
            hasContextParametersWithAllNames("fixtureparameter1", "fixtureparameter2") shouldBeEqualTo false
            hasContextParametersWithAllNames("fixtureparameter1", "fixtureparameter2", ignoreCase = true) shouldBeEqualTo true
            hasContextParametersWithAllNames(listOf("fixtureparameter1", "otherparameter"), ignoreCase = true) shouldBeEqualTo false
            hasContextParametersWithAllNames(setOf("fixtureparameter1", "fixtureparameter2"), ignoreCase = true) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kofunction/snippet/forkocontextparametersprovider/", fileName)
}
