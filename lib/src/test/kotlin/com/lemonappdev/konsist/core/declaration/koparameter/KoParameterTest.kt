package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
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
                importAliasName shouldBeEqualTo ""
                name shouldBeEqualTo "SampleType"
                isImportAlias() shouldBeEqualTo false
                fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
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
                importAliasName shouldBeEqualTo "ImportAlias"
                name shouldBeEqualTo "ImportAlias"
                isImportAlias() shouldBeEqualTo true
                fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
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
            hasDefaultValue("6") shouldBeEqualTo true
            hasDefaultValue("10") shouldBeEqualTo false
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
        sut.run {
            defaultValue shouldBeEqualTo "2"
            hasDefaultValue() shouldBeEqualTo true
            hasDefaultValue("2") shouldBeEqualTo true
            hasDefaultValue("10") shouldBeEqualTo false
        }
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
        sut.run {
            defaultValue shouldBeEqualTo "SampleType()"
            hasDefaultValue() shouldBeEqualTo true
            hasDefaultValue("SampleType()") shouldBeEqualTo true
            hasDefaultValue("OtherType()") shouldBeEqualTo false
        }
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
        sut.run {
            defaultValue shouldBeEqualTo "null"
            hasDefaultValue() shouldBeEqualTo true
            hasDefaultValue("SampleType()") shouldBeEqualTo false
        }
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
        sut.run {
            defaultValue shouldBeEqualTo null
            hasDefaultValue() shouldBeEqualTo false
            hasDefaultValue("SampleType()") shouldBeEqualTo false
        }
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
