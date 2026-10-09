package com.lemonappdev.konsist.core.declaration.kosetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterDeclarationForKoLocalFunctionProviderTest {
    @Test
    fun `setter-contains-no-local-function`() {
        // given
        val sut =
            getSnippetFile("setter-contains-no-local-function")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.localFunctions shouldBeEqualTo emptyList()
            it?.numLocalFunctions shouldBeEqualTo 0
            it?.countLocalFunctions { it.name == "fixtureLocalFunction" } shouldBeEqualTo 0
            it?.hasLocalFunctions() shouldBeEqualTo false
            it?.hasLocalFunctionWithName(emptyList()) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(emptySet()) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(emptyList()) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(emptySet()) shouldBeEqualTo false
            it?.hasLocalFunctionWithName("fixtureLocalFunction") shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("fixtureLocalFunction")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(setOf("fixtureLocalFunction")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames("fixtureLocalFunction1", "fixtureLocalFunction2") shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("fixtureLocalFunction1", "fixtureLocalFunction2")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(setOf("fixtureLocalFunction1", "fixtureLocalFunction2")) shouldBeEqualTo false
            it?.hasLocalFunction { it.name == "fixtureLocalFunction" } shouldBeEqualTo false
            it?.hasAllLocalFunctions { it.name == "fixtureLocalFunction" } shouldBeEqualTo true
        }
    }

    @Test
    fun `setter-contains-local-function`() {
        // given
        val sut =
            getSnippetFile("setter-contains-local-function")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.numLocalFunctions shouldBeEqualTo 2
            it?.countLocalFunctions { it.name == "fixtureLocalFunction1" } shouldBeEqualTo 1
            it?.hasLocalFunctions() shouldBeEqualTo true
            it?.hasLocalFunctionWithName(emptyList()) shouldBeEqualTo true
            it?.hasLocalFunctionWithName(emptySet()) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames(emptyList()) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames(emptySet()) shouldBeEqualTo true
            it?.hasLocalFunctionWithName("fixtureLocalFunction1") shouldBeEqualTo true
            it?.hasLocalFunctionWithName("otherLocalFunction") shouldBeEqualTo false
            it?.hasLocalFunctionWithName("fixtureLocalFunction1", "otherLocalFunction") shouldBeEqualTo true
            it?.hasLocalFunctionWithName(listOf("fixtureLocalFunction1")) shouldBeEqualTo true
            it?.hasLocalFunctionWithName(listOf("otherLocalFunction")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("fixtureLocalFunction1", "otherLocalFunction")) shouldBeEqualTo true
            it?.hasLocalFunctionWithName(setOf("fixtureLocalFunction1")) shouldBeEqualTo true
            it?.hasLocalFunctionWithName(setOf("otherLocalFunction")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(setOf("fixtureLocalFunction1", "otherLocalFunction")) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames("fixtureLocalFunction1") shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames("fixtureLocalFunction1", "fixtureLocalFunction2") shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames("fixtureLocalFunction1", "otherLocalFunction") shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("fixtureLocalFunction1")) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames(listOf("fixtureLocalFunction1", "fixtureLocalFunction2")) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames(listOf("fixtureLocalFunction1", "otherLocalFunction")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(setOf("fixtureLocalFunction1")) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames(setOf("fixtureLocalFunction1", "fixtureLocalFunction2")) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames(setOf("fixtureLocalFunction1", "otherLocalFunction")) shouldBeEqualTo false
            it?.hasLocalFunction { it.name == "fixtureLocalFunction1" } shouldBeEqualTo true
            it?.hasLocalFunction { it.name == "otherLocalFunction" } shouldBeEqualTo false
            it?.hasAllLocalFunctions { it.name.endsWith("2") || it.name == "fixtureLocalFunction1" } shouldBeEqualTo true
            it?.hasAllLocalFunctions { it.name.endsWith("2") } shouldBeEqualTo false
            it
                ?.localFunctions
                ?.map { it.name }
                .shouldBeEqualTo(listOf("fixtureLocalFunction1", "fixtureLocalFunction2"))
        }
    }

    @Test
    fun `setter-contains-no-local-function-ignore-case`() {
        // given
        val sut =
            getSnippetFile("setter-contains-no-local-function-ignore-case")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.hasLocalFunctionWithName("fixturelocalfunction") shouldBeEqualTo false
            it?.hasLocalFunctionWithName("fixturelocalfunction", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("fixturelocalfunction")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("fixturelocalfunction"), ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(setOf("fixturelocalfunction")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(setOf("fixturelocalfunction"), ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames("fixturelocalfunction1", "fixturelocalfunction2") shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames("fixturelocalfunction1", "fixturelocalfunction2", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1", "fixturelocalfunction2")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1", "fixturelocalfunction2"), ignoreCase = true) shouldBeEqualTo
                false
            it?.hasLocalFunctionsWithAllNames(setOf("fixturelocalfunction1", "fixturelocalfunction2")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(setOf("fixturelocalfunction1", "fixturelocalfunction2"), ignoreCase = true) shouldBeEqualTo
                false
        }
    }

    @Test
    fun `setter-contains-local-function-ignore-case`() {
        // given
        val sut =
            getSnippetFile("setter-contains-local-function-ignore-case")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.hasLocalFunctionWithName("fixturelocalfunction1") shouldBeEqualTo false
            it?.hasLocalFunctionWithName("fixturelocalfunction1", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalFunctionWithName("otherlocalfunction") shouldBeEqualTo false
            it?.hasLocalFunctionWithName("otherlocalfunction", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalFunctionWithName("fixturelocalfunction1", "otherName") shouldBeEqualTo false
            it?.hasLocalFunctionWithName("fixturelocalfunction1", "otherName", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalFunctionWithName(listOf("fixturelocalfunction1")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("fixturelocalfunction1"), ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalFunctionWithName(listOf("otherlocalfunction")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("otherlocalfunction"), ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("fixturelocalfunction1", "otherName")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("fixturelocalfunction1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames("fixturelocalfunction1") shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames("fixturelocalfunction1", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames("fixturelocalfunction1", "fixturelocalfunction2") shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames("fixturelocalfunction1", "fixturelocalfunction2", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames("fixturelocalfunction1", "otherlocalfunction") shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames("fixturelocalfunction1", "otherlocalfunction", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1"), ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1", "fixturelocalfunction2")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1", "fixturelocalfunction2"), ignoreCase = true) shouldBeEqualTo
                true
            it?.hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1", "otherlocalfunction")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("fixturelocalfunction1", "otherlocalfunction"), ignoreCase = true) shouldBeEqualTo
                false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kosetter/snippet/forkolocalfunctionprovider/", fileName)
}
