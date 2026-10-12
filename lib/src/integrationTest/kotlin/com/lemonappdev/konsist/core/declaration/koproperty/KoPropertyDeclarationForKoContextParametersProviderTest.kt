package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoContextParametersProviderTest {
    @Test
    fun `property-has-no-context-parameters`() {
        // given
        val sut =
            getSnippetFile("property-has-no-context-parameters")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            contextParameters shouldBeEqualTo emptyList()
            numContextParameters shouldBeEqualTo 0
            hasContextParameters() shouldBeEqualTo false
            hasContextParameterWithName("fixtureParameter") shouldBeEqualTo false
            hasContextParameter { it.hasNameStartingWith("fixture") } shouldBeEqualTo false
            hasAllContextParameters { it.hasNameStartingWith("fixture") } shouldBeEqualTo true
        }
    }

    @Test
    fun `property-has-one-context-parameter`() {
        // given
        val sut =
            getSnippetFile("property-has-one-context-parameter")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            contextParameters.map { it.name } shouldBeEqualTo listOf("fixtureParameter")
            numContextParameters shouldBeEqualTo 1
            hasContextParameters() shouldBeEqualTo true
            hasContextParameterWithName("fixtureParameter") shouldBeEqualTo true
            hasContextParameter { it.hasType { type -> type.name == "Logger" } } shouldBeEqualTo true
            hasAllContextParameters { it.hasNameStartingWith("fixture") } shouldBeEqualTo true
        }
    }

    @Test
    fun `property-has-two-context-parameters`() {
        // given
        val sut =
            getSnippetFile("property-has-two-context-parameters")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            contextParameters.map { it.name } shouldBeEqualTo listOf("fixtureParameter1", "fixtureParameter2")
            numContextParameters shouldBeEqualTo 2
            hasContextParametersWithAllNames("fixtureParameter1", "fixtureParameter2") shouldBeEqualTo true
            hasAllContextParameters { it.hasType { type -> type.name == "Logger" } } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koproperty/snippet/forkocontextparametersprovider/", fileName)
}
