package com.lemon.konsist.core.declaration.koparameter

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterTest {
    @Test
    fun `class-has-complex-default-parameter-value`() {
        // given
        val sut = getSnippetFile("class-has-complex-default-parameter-value")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut
            ?.type
            ?.run {
                sourceType shouldBeEqualTo "SampleType"
                name shouldBeEqualTo ""
                isImportAlias() shouldBeEqualTo false
                fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
            }
    }

    @Test
    fun `class-has-one-parameter-with-import-alias`() {
        // given
        val sut = getSnippetFile("class-has-one-parameter-with-import-alias")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut
            ?.type
            ?.run {
                sourceType shouldBeEqualTo "SampleType"
                name shouldBeEqualTo "ImportAlias"
                isImportAlias() shouldBeEqualTo true
                fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
            }
    }

    @Test
    fun `class-has-primitive-default-parameter-value`() {
        // given
        val sut = getSnippetFile("class-has-primitive-default-parameter-value")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.run {
            hasDefaultValue() shouldBeEqualTo true
            name shouldBeEqualTo "sampleParameter"
        }
    }

    @Test
    fun `class-has-no-parameters`() {
        // given
        val sut = getSnippetFile("class-has-no-parameters")
            .classes()
            .first()

        // then
        sut.primaryConstructor shouldBeEqualTo null
    }

    @Test
    fun `class-has-empty-primary-constructor`() {
        // given
        val sut = getSnippetFile("class-has-empty-primary-constructor")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters

        // then
        sut?.isEmpty() shouldBeEqualTo true
    }

    @Test
    fun `function-has-primitive-default-parameter-value`() {
        // given
        val sut = getSnippetFile("function-has-primitive-default-parameter-value")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.defaultValue shouldBeEqualTo "2"
    }

    @Test
    fun `function-has-complex-default-parameter-value`() {
        // given
        val sut = getSnippetFile("function-has-complex-default-parameter-value")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.defaultValue shouldBeEqualTo "SampleType()"
    }

    @Test
    fun `function-has-null-default-parameter-value`() {
        // given
        val sut = getSnippetFile("function-has-null-default-parameter-value")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.defaultValue shouldBeEqualTo "null"
    }

    @Test
    fun `function-has-no-default-parameter-value`() {
        // given
        val sut = getSnippetFile("function-has-no-default-parameter-value")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.defaultValue shouldBeEqualTo null
    }

    @Test
    fun `parameter-has-vararg-modifier`() {
        // given
        val sut = getSnippetFile("parameter-has-vararg-modifier")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.hasVarargModifier() shouldBeEqualTo true
    }

    @Test
    fun `parameter-has-noinline-modifier`() {
        // given
        val sut = getSnippetFile("parameter-has-noinline-modifier")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.hasNoInlineModifier() shouldBeEqualTo true
    }

    @Test
    fun `parameter-has-crossinline-modifier`() {
        // given
        val sut = getSnippetFile("parameter-has-crossinline-modifier")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.hasCrossInlineModifier() shouldBeEqualTo true
    }

    @Test
    fun `parameter-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("parameter-has-no-modifiers")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.run {
            hasVarargModifier() shouldBeEqualTo false
            hasNoInlineModifier() shouldBeEqualTo false
            hasCrossInlineModifier() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koparameter/snippet/", fileName)
}
