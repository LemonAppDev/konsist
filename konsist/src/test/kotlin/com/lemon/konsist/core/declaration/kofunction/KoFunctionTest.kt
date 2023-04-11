package com.lemon.konsist.core.declaration.kofunction

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionTest {
    @Test
    fun `function-has-operator-modifier`() {
        // given
        val sut = getSut("function-has-operator-modifier")
            .functions()
            .first()

        // then
        sut.isOperator shouldBeEqualTo true
    }

    @Test
    fun `function-has-inline-modifier`() {
        // given
        val sut = getSut("function-has-inline-modifier")
            .functions()
            .first()

        // then
        sut.isInline shouldBeEqualTo true
    }

    @Test
    fun `function-has-tailrec-modifier`() {
        // given
        val sut = getSut("function-has-tailrec-modifier")
            .functions()
            .first()

        // then
        sut.isTailrec shouldBeEqualTo true
    }

    @Test
    fun `function-has-infix-modifier`() {
        // given
        val sut = getSut("function-has-infix-modifier")
            .functions()
            .first()

        // then
        sut.isInfix shouldBeEqualTo true
    }

    @Test
    fun `function-has-external-modifier`() {
        // given
        val sut = getSut("function-has-external-modifier")
            .functions()
            .first()

        // then
        sut.isExternal shouldBeEqualTo true
    }

    @Test
    fun `function-has-suspend-modifier`() {
        // given
        val sut = getSut("function-has-suspend-modifier")
            .functions()
            .first()

        // then
        sut.isSuspend shouldBeEqualTo true
    }

    @Test
    fun `function-has-open-modifier`() {
        // given
        val sut = getSut("function-has-open-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.isOpen shouldBeEqualTo true
    }

    @Test
    fun `function-has-override-modifier`() {
        // given
        val sut = getSut("function-has-override-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.isOverride shouldBeEqualTo true
    }

    @Test
    fun `function-has-final-modifier`() {
        // given
        val sut = getSut("function-has-final-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.isFinal shouldBeEqualTo true
    }

    @Test
    fun `function-has-abstract-modifier`() {
        // given
        val sut = getSut("function-has-abstract-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.isAbstract shouldBeEqualTo true
    }

    @Test
    fun `function-has-no-modifiers`() {
        // given
        val sut = getSut("function-has-no-modifiers")
            .functions()
            .first()

        // then
        sut.run {
            isOperator shouldBeEqualTo false
            isInline shouldBeEqualTo false
            isTailrec shouldBeEqualTo false
            isInfix shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            isSuspend shouldBeEqualTo false
            isOpen shouldBeEqualTo false
            isOverride shouldBeEqualTo false
            isFinal shouldBeEqualTo false
            isAbstract shouldBeEqualTo false
        }
    }

    @Test
    fun `function-is-extension`() {
        // given
        val sut = getSut("function-is-extension")
            .functions()
            .first()

        // then
        sut.isExtension shouldBeEqualTo true
    }

    @Test
    fun `function-is-not-extension`() {
        // given
        val sut = getSut("function-is-not-extension")
            .functions()
            .first()

        // then
        sut.isExtension shouldBeEqualTo false
    }

    @Test
    fun `function-return-type`() {
        // given
        val sut = getSut("function-return-type")
            .functions()
            .first()

        // then
        sut.run {
            hasExplicitReturnType shouldBeEqualTo true
            explicitReturnType?.sourceType shouldBeEqualTo "SampleType"
            explicitReturnType?.name shouldBeEqualTo ""
            explicitReturnType?.isImportAlias shouldBeEqualTo false
            explicitReturnType?.fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
        }
    }

    @Test
    fun `function-return-import-alias`() {
        // given
        val sut = getSut("function-return-import-alias")
            .functions()
            .first()

        // then
        sut.run {
            hasExplicitReturnType shouldBeEqualTo true
            explicitReturnType?.sourceType shouldBeEqualTo "SampleType"
            explicitReturnType?.name shouldBeEqualTo "ImportAlias"
            explicitReturnType?.isImportAlias shouldBeEqualTo true
        }
    }

    @Test
    fun `function-not-return-type`() {
        // given
        val sut = getSut("function-not-return-type")
            .functions()
            .first()

        // then
        sut.run {
            hasExplicitReturnType shouldBeEqualTo false
            explicitReturnType?.sourceType shouldBeEqualTo null
            explicitReturnType?.name shouldBeEqualTo null
            explicitReturnType?.isImportAlias shouldBeEqualTo null
        }
    }

    @Test
    fun `function-contains-local-property`() {
        // given
        val sut = getSut("function-contains-local-property")
            .functions()
            .first()

        // then
        sut.apply {
            containsLocalProperty("sampleLocalProperty") shouldBeEqualTo true
            localProperties().map { it.name } shouldBeEqualTo listOf("sampleLocalProperty")
        }
    }

    @Test
    fun `function-contains-local-function`() {
        // given
        val sut = getSut("function-contains-local-function")
            .functions()
            .first()

        // then
        sut.apply {
            containsLocalFunction("sampleLocalFunction") shouldBeEqualTo true
            localFunctions().map { it.name } shouldBeEqualTo listOf("sampleLocalFunction")
        }
    }

    @Test
    fun `function-contains-local-class`() {
        // given
        val sut = getSut("function-contains-local-class")
            .functions()
            .first()

        // then
        sut.apply {
            containsLocalClass("SampleClass") shouldBeEqualTo true
            localClasses().map { it.name } shouldBeEqualTo listOf("SampleClass")
        }
    }

    @Test
    fun `function-contains-local-declarations`() {
        // given
        val sut = getSut("function-contains-local-declarations")
            .functions()
            .first()

        // then
        sut
            .localDeclarations()
            .map { it.name } shouldBeEqualTo listOf(
            "sampleLocalProperty",
            "sampleLocalFunction",
            "SampleNestedClass",
        )
    }

    private fun getSut(fileName: String) = getSnippetKoScope("kofunction/snippet/", fileName)
}
