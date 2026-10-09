package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoLocalFunctionProviderTest {
    @Test
    fun `function-contains-no-local-function`() {
        // given
        val sut =
            getSnippetFile("function-contains-no-local-function")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            localFunctions shouldBeEqualTo emptyList()
            numLocalFunctions shouldBeEqualTo 0
            countLocalFunctions { it.name == "fixtureLocalFunction" } shouldBeEqualTo 0
            hasLocalFunctions() shouldBeEqualTo false
            hasLocalFunctionWithName(emptyList()) shouldBeEqualTo false
            hasLocalFunctionWithName(emptySet()) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(emptyList()) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(emptySet()) shouldBeEqualTo false
            hasLocalFunctionWithName("fixtureLocalFunction") shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("fixtureLocalFunction")) shouldBeEqualTo false
            hasLocalFunctionWithName(setOf("fixtureLocalFunction")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames("fixtureLocalFunction1", "fixtureLocalFunction2") shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("fixtureLocalFunction1", "fixtureLocalFunction2")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(setOf("fixtureLocalFunction1", "fixtureLocalFunction2")) shouldBeEqualTo false
            hasLocalFunction { it.name == "fixtureLocalFunction" } shouldBeEqualTo false
            hasAllLocalFunctions { it.name == "fixtureLocalFunction" } shouldBeEqualTo true
        }
    }

    @Test
    fun `function-contains-local-function`() {
        // given
        val sut =
            getSnippetFile("function-contains-local-function")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            numLocalFunctions shouldBeEqualTo 2
            countLocalFunctions { it.name == "fixtureLocalFunction1" } shouldBeEqualTo 1
            hasLocalFunctions() shouldBeEqualTo true
            hasLocalFunctionWithName(emptyList()) shouldBeEqualTo true
            hasLocalFunctionWithName(emptySet()) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(emptyList()) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(emptySet()) shouldBeEqualTo true
            hasLocalFunctionWithName("fixtureLocalFunction1") shouldBeEqualTo true
            hasLocalFunctionWithName("otherLocalFunction") shouldBeEqualTo false
            hasLocalFunctionWithName("fixtureLocalFunction1", "otherLocalFunction") shouldBeEqualTo true
            hasLocalFunctionWithName(listOf("fixtureLocalFunction1")) shouldBeEqualTo true
            hasLocalFunctionWithName(listOf("otherLocalFunction")) shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("fixtureLocalFunction1", "otherLocalFunction")) shouldBeEqualTo true
            hasLocalFunctionWithName(setOf("fixtureLocalFunction1")) shouldBeEqualTo true
            hasLocalFunctionWithName(setOf("otherLocalFunction")) shouldBeEqualTo false
            hasLocalFunctionWithName(setOf("fixtureLocalFunction1", "otherLocalFunction")) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames("fixtureLocalFunction1") shouldBeEqualTo true
            hasLocalFunctionsWithAllNames("fixtureLocalFunction1", "fixtureLocalFunction2") shouldBeEqualTo true
            hasLocalFunctionsWithAllNames("fixtureLocalFunction1", "otherLocalFunction") shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("fixtureLocalFunction1")) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(listOf("fixtureLocalFunction1", "fixtureLocalFunction2")) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(listOf("fixtureLocalFunction1", "otherLocalFunction")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(setOf("fixtureLocalFunction1")) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(setOf("fixtureLocalFunction1", "fixtureLocalFunction2")) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(setOf("fixtureLocalFunction1", "otherLocalFunction")) shouldBeEqualTo false
            hasLocalFunction { it.name == "fixtureLocalFunction1" } shouldBeEqualTo true
            hasLocalFunction { it.name == "otherLocalFunction" } shouldBeEqualTo false
            hasAllLocalFunctions { it.name.endsWith("2") || it.name == "fixtureLocalFunction1" } shouldBeEqualTo true
            hasAllLocalFunctions { it.name.endsWith("2") } shouldBeEqualTo false
            localFunctions
                .map { it.name }
                .shouldBeEqualTo(listOf("fixtureLocalFunction1", "fixtureLocalFunction2"))
        }
    }

    @Test
    fun `function-contains-no-local-function-ignore-case`() {
        // given
        val sut =
            getSnippetFile("function-contains-no-local-function-ignore-case")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            hasLocalFunctionWithName("fixturelocalfunction") shouldBeEqualTo false
            hasLocalFunctionWithName("fixturelocalfunction", ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("fixturelocalfunction")) shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("fixturelocalfunction"), ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionWithName(setOf("fixturelocalfunction")) shouldBeEqualTo false
            hasLocalFunctionWithName(setOf("fixturelocalfunction"), ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames("fixturelocalfunction1", "fixturelocalfunction2") shouldBeEqualTo false
            hasLocalFunctionsWithAllNames("fixturelocalfunction1", "fixturelocalfunction2", ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1", "fixturelocalfunction2")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1", "fixturelocalfunction2"), ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(setOf("fixturelocalfunction1", "fixturelocalfunction2")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(setOf("fixturelocalfunction1", "fixturelocalfunction2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-contains-local-function-ignore-case`() {
        // given
        val sut =
            getSnippetFile("function-contains-local-function-ignore-case")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            hasLocalFunctionWithName("fixturelocalfunction1") shouldBeEqualTo false
            hasLocalFunctionWithName("fixturelocalfunction1", ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionWithName("otherlocalfunction") shouldBeEqualTo false
            hasLocalFunctionWithName("otherlocalfunction", ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionWithName("fixturelocalfunction1", "otherName") shouldBeEqualTo false
            hasLocalFunctionWithName("fixturelocalfunction1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionWithName(listOf("fixturelocalfunction1")) shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("fixturelocalfunction1"), ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionWithName(listOf("otherlocalfunction")) shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("otherlocalfunction"), ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("fixturelocalfunction1", "otherName")) shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("fixturelocalfunction1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames("fixturelocalfunction1") shouldBeEqualTo false
            hasLocalFunctionsWithAllNames("fixturelocalfunction1", ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames("fixturelocalfunction1", "fixturelocalfunction2") shouldBeEqualTo false
            hasLocalFunctionsWithAllNames("fixturelocalfunction1", "fixturelocalfunction2", ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames("fixturelocalfunction1", "otherlocalfunction") shouldBeEqualTo false
            hasLocalFunctionsWithAllNames("fixturelocalfunction1", "otherlocalfunction", ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1"), ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1", "fixturelocalfunction2")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1", "fixturelocalfunction2"), ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1", "otherlocalfunction")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1", "otherlocalfunction"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/snippet/forkolocalfunctionprovider/", fileName)
}
