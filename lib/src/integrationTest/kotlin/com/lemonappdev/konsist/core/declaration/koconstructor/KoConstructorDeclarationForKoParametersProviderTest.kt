package com.lemonappdev.konsist.core.declaration.koconstructor

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorDeclarationForKoParametersProviderTest {
    @Test
    fun `constructor-contains-no-parameters`() {
        // given
        val sut =
            getSnippetFile("constructor-contains-no-parameters")
                .classes()
                .first()
                .constructors
                .first()

        // then
        assertSoftly(sut) {
            parameters shouldBeEqualTo emptyList()
            numParameters shouldBeEqualTo 0
            countParameters { it.hasNameStartingWith("fixture") } shouldBeEqualTo 0
            hasParameters() shouldBeEqualTo false
            hasParameterWithName(emptyList()) shouldBeEqualTo false
            hasParameterWithName(emptySet()) shouldBeEqualTo false
            hasParametersWithAllNames(emptyList()) shouldBeEqualTo false
            hasParametersWithAllNames(emptySet()) shouldBeEqualTo false
            hasParameterWithName("fixtureParameter") shouldBeEqualTo false
            hasParameterWithName(listOf("fixtureParameter")) shouldBeEqualTo false
            hasParameterWithName(setOf("fixtureParameter")) shouldBeEqualTo false
            hasParametersWithAllNames("fixtureParameter1", "fixtureParameter2") shouldBeEqualTo false
            hasParametersWithAllNames(listOf("fixtureParameter1", "fixtureParameter2")) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("fixtureParameter1", "fixtureParameter2")) shouldBeEqualTo false
            hasParameter { it.hasNameStartingWith("other") } shouldBeEqualTo false
            hasAllParameters { it.hasNameStartingWith("fixture") } shouldBeEqualTo true
        }
    }

    @Test
    fun `constructor-contains-one-parameter`() {
        // given
        val sut =
            getSnippetFile("constructor-contains-one-parameter")
                .classes()
                .first()
                .constructors
                .first()

        // then
        assertSoftly(sut) {
            parameters.size shouldBeEqualTo 1
            numParameters shouldBeEqualTo 1
            countParameters { it.hasNameStartingWith("fixture") } shouldBeEqualTo 1
            hasParameters() shouldBeEqualTo true
            hasParameterWithName(emptyList()) shouldBeEqualTo true
            hasParameterWithName(emptySet()) shouldBeEqualTo true
            hasParametersWithAllNames(emptyList()) shouldBeEqualTo true
            hasParametersWithAllNames(emptySet()) shouldBeEqualTo true
            hasParameterWithName("fixtureParameter") shouldBeEqualTo true
            hasParameterWithName("otherParameter") shouldBeEqualTo false
            hasParameterWithName("fixtureParameter", "otherParameter") shouldBeEqualTo true
            hasParameterWithName(listOf("fixtureParameter")) shouldBeEqualTo true
            hasParameterWithName(listOf("otherParameter")) shouldBeEqualTo false
            hasParameterWithName(listOf("fixtureParameter", "otherParameter")) shouldBeEqualTo true
            hasParameterWithName(setOf("fixtureParameter")) shouldBeEqualTo true
            hasParameterWithName(setOf("otherParameter")) shouldBeEqualTo false
            hasParameterWithName(setOf("fixtureParameter", "otherParameter")) shouldBeEqualTo true
            hasParametersWithAllNames("fixtureParameter") shouldBeEqualTo true
            hasParametersWithAllNames("fixtureParameter", "otherParameter") shouldBeEqualTo false
            hasParametersWithAllNames(listOf("fixtureParameter")) shouldBeEqualTo true
            hasParametersWithAllNames(listOf("fixtureParameter", "otherParameter")) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("fixtureParameter")) shouldBeEqualTo true
            hasParametersWithAllNames(setOf("fixtureParameter", "otherParameter")) shouldBeEqualTo false
            hasParameter { it.hasNameStartingWith("fixture") } shouldBeEqualTo true
            hasParameter { it.hasNameStartingWith("other") } shouldBeEqualTo false
            hasAllParameters { it.hasNameStartingWith("fixture") } shouldBeEqualTo true
        }
    }

    @Test
    fun `constructor-contains-two-parameters`() {
        // given
        val sut =
            getSnippetFile("constructor-contains-two-parameters")
                .classes()
                .first()
                .constructors
                .first()

        // then
        assertSoftly(sut) {
            parameters.size shouldBeEqualTo 2
            numParameters shouldBeEqualTo 2
            countParameters { it.hasNameStartingWith("fixture") } shouldBeEqualTo 2
            countParameters { param -> param.hasType { it.name == "Int" } } shouldBeEqualTo 1
            hasParameters() shouldBeEqualTo true
            hasParameterWithName(emptyList()) shouldBeEqualTo true
            hasParameterWithName(emptySet()) shouldBeEqualTo true
            hasParametersWithAllNames(emptyList()) shouldBeEqualTo true
            hasParametersWithAllNames(emptySet()) shouldBeEqualTo true
            hasParameterWithName("fixtureParameter1") shouldBeEqualTo true
            hasParameterWithName("otherParameter") shouldBeEqualTo false
            hasParameterWithName("fixtureParameter1", "otherName") shouldBeEqualTo true
            hasParameterWithName(listOf("fixtureParameter1")) shouldBeEqualTo true
            hasParameterWithName(listOf("otherParameter")) shouldBeEqualTo false
            hasParameterWithName(listOf("fixtureParameter1", "otherName")) shouldBeEqualTo true
            hasParameterWithName(setOf("fixtureParameter1")) shouldBeEqualTo true
            hasParameterWithName(setOf("otherParameter")) shouldBeEqualTo false
            hasParameterWithName(setOf("fixtureParameter1", "otherName")) shouldBeEqualTo true
            hasParametersWithAllNames("fixtureParameter1") shouldBeEqualTo true
            hasParametersWithAllNames("fixtureParameter1", "fixtureParameter2") shouldBeEqualTo true
            hasParametersWithAllNames("fixtureParameter1", "otherParameter") shouldBeEqualTo false
            hasParametersWithAllNames(listOf("fixtureParameter1")) shouldBeEqualTo true
            hasParametersWithAllNames(listOf("fixtureParameter1", "fixtureParameter2")) shouldBeEqualTo true
            hasParametersWithAllNames(listOf("fixtureParameter1", "otherParameter")) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("fixtureParameter1")) shouldBeEqualTo true
            hasParametersWithAllNames(setOf("fixtureParameter1", "fixtureParameter2")) shouldBeEqualTo true
            hasParametersWithAllNames(setOf("fixtureParameter1", "otherParameter")) shouldBeEqualTo false
            hasParameter { it.hasNameStartingWith("fixture") } shouldBeEqualTo true
            hasParameter { param -> param.hasType { it.name == "Int" } } shouldBeEqualTo true
            hasAllParameters { it.hasNameStartingWith("fixture") } shouldBeEqualTo true
            hasAllParameters { param -> param.hasType { it.name == "Int" } } shouldBeEqualTo false
        }
    }

    @Test
    fun `constructor-contains-no-parameters-ignore-case`() {
        // given
        val sut =
            getSnippetFile("constructor-contains-no-parameters-ignore-case")
                .classes()
                .first()
                .constructors
                .first()

        // then
        assertSoftly(sut) {
            hasParameterWithName("fixtureparameter") shouldBeEqualTo false
            hasParameterWithName("fixtureparameter", ignoreCase = true) shouldBeEqualTo false
            hasParameterWithName(listOf("fixtureparameter")) shouldBeEqualTo false
            hasParameterWithName(listOf("fixtureparameter"), ignoreCase = true) shouldBeEqualTo false
            hasParameterWithName(setOf("fixtureparameter")) shouldBeEqualTo false
            hasParameterWithName(setOf("fixtureparameter"), ignoreCase = true) shouldBeEqualTo false
            hasParametersWithAllNames("fixtureparameter1", "fixtureparameter2") shouldBeEqualTo false
            hasParametersWithAllNames("fixtureparameter1", "fixtureparameter2", ignoreCase = true) shouldBeEqualTo false
            hasParametersWithAllNames(listOf("fixtureparameter1", "fixtureparameter2")) shouldBeEqualTo false
            hasParametersWithAllNames(listOf("fixtureparameter1", "fixtureparameter2"), ignoreCase = true) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("fixtureparameter1", "fixtureparameter2")) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("fixtureparameter1", "fixtureparameter2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `constructor-contains-parameters-ignore-case`() {
        // given
        val sut =
            getSnippetFile("constructor-contains-parameters-ignore-case")
                .classes()
                .first()
                .constructors
                .first()

        // then
        assertSoftly(sut) {
            hasParameterWithName("fixtureparameter1") shouldBeEqualTo false
            hasParameterWithName("fixtureparameter1", ignoreCase = true) shouldBeEqualTo true
            hasParameterWithName("otherparameter") shouldBeEqualTo false
            hasParameterWithName("otherparameter", ignoreCase = true) shouldBeEqualTo false
            hasParameterWithName("fixtureparameter1", "otherName") shouldBeEqualTo false
            hasParameterWithName("fixtureparameter1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasParameterWithName(listOf("fixtureparameter1")) shouldBeEqualTo false
            hasParameterWithName(listOf("fixtureparameter1"), ignoreCase = true) shouldBeEqualTo true
            hasParameterWithName(listOf("otherparameter")) shouldBeEqualTo false
            hasParameterWithName(listOf("otherparameter"), ignoreCase = true) shouldBeEqualTo false
            hasParameterWithName(listOf("fixtureparameter1", "otherName")) shouldBeEqualTo false
            hasParameterWithName(listOf("fixtureparameter1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasParameterWithName(setOf("fixtureparameter1")) shouldBeEqualTo false
            hasParameterWithName(setOf("fixtureparameter1"), ignoreCase = true) shouldBeEqualTo true
            hasParameterWithName(setOf("otherparameter")) shouldBeEqualTo false
            hasParameterWithName(setOf("otherparameter"), ignoreCase = true) shouldBeEqualTo false
            hasParameterWithName(setOf("fixtureparameter1", "otherName")) shouldBeEqualTo false
            hasParameterWithName(setOf("fixtureparameter1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasParametersWithAllNames("fixtureparameter1") shouldBeEqualTo false
            hasParametersWithAllNames("fixtureparameter1", ignoreCase = true) shouldBeEqualTo true
            hasParametersWithAllNames("fixtureparameter1", "fixtureparameter2") shouldBeEqualTo false
            hasParametersWithAllNames("fixtureparameter1", "fixtureparameter2", ignoreCase = true) shouldBeEqualTo true
            hasParametersWithAllNames("fixtureparameter1", "otherparameter") shouldBeEqualTo false
            hasParametersWithAllNames("fixtureparameter1", "otherparameter", ignoreCase = true) shouldBeEqualTo false
            hasParametersWithAllNames(listOf("fixtureparameter1")) shouldBeEqualTo false
            hasParametersWithAllNames(listOf("fixtureparameter1"), ignoreCase = true) shouldBeEqualTo true
            hasParametersWithAllNames(listOf("fixtureparameter1", "fixtureparameter2")) shouldBeEqualTo false
            hasParametersWithAllNames(listOf("fixtureparameter1", "fixtureparameter2"), ignoreCase = true) shouldBeEqualTo true
            hasParametersWithAllNames(listOf("fixtureparameter1", "otherparameter")) shouldBeEqualTo false
            hasParametersWithAllNames(listOf("fixtureparameter1", "otherparameter"), ignoreCase = true) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("fixtureparameter1")) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("fixtureparameter1"), ignoreCase = true) shouldBeEqualTo true
            hasParametersWithAllNames(setOf("fixtureparameter1", "fixtureparameter2")) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("fixtureparameter1", "fixtureparameter2"), ignoreCase = true) shouldBeEqualTo true
            hasParametersWithAllNames(setOf("fixtureparameter1", "otherparameter")) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("fixtureparameter1", "otherparameter"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koconstructor/snippet/forkoparametersprovider/", fileName)
}
