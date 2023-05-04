package com.lemonappdev.konsist.core.declaration.koparameterdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationTest {
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
        assertSoftly(sut?.type) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.importAliasName shouldBeEqualTo ""
            it?.name shouldBeEqualTo "SampleType"
            it?.isImportAlias() shouldBeEqualTo false
            it?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
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
        assertSoftly(sut?.type) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.importAliasName shouldBeEqualTo "ImportAlias"
            it?.name shouldBeEqualTo "ImportAlias"
            it?.isImportAlias() shouldBeEqualTo true
            it?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
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
        assertSoftly(sut) {
            it?.hasDefaultValue() shouldBeEqualTo true
            it?.hasDefaultValue("6") shouldBeEqualTo true
            it?.hasDefaultValue("10") shouldBeEqualTo false
            it?.name shouldBeEqualTo "sampleParameter"
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
        assertSoftly(sut) {
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
        assertSoftly(sut) {
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
        assertSoftly(sut) {
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
        assertSoftly(sut) {
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
        assertSoftly(sut) {
            it?.hasVarargModifier() shouldBeEqualTo false
            it?.hasNoInlineModifier() shouldBeEqualTo false
            it?.hasCrossInlineModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-represents-type`() {
        // given
        val sut = getSnippetFile("parameter-represents-type")
            .functions()
            .first()
            .parameters
            .first()

        // then
        assertSoftly(sut) {
            it.representsType("SampleType") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koparameterdeclaration/snippet/", fileName)
}
