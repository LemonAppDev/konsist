package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoDefaultValueProviderTest {
    @Test
    fun `class-has-primitive-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("class-has-primitive-default-parameter-value")
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
        }
    }

    @Test
    fun `function-has-primitive-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("function-has-primitive-default-parameter-value")
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
    fun `class-has-class-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("class-has-class-default-parameter-value")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.defaultValue shouldBeEqualTo "SampleType()"
            it?.hasDefaultValue() shouldBeEqualTo true
            it?.hasDefaultValue("SampleType()") shouldBeEqualTo true
            it?.hasDefaultValue("OtherType()") shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-class-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("function-has-class-default-parameter-value")
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
    fun `class-has-object-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("class-has-object-default-parameter-value")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.defaultValue shouldBeEqualTo "SampleObject"
            it?.hasDefaultValue() shouldBeEqualTo true
            it?.hasDefaultValue("SampleObject") shouldBeEqualTo true
            it?.hasDefaultValue("SampleObject()") shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-object-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("function-has-object-default-parameter-value")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            defaultValue shouldBeEqualTo "SampleObject"
            hasDefaultValue() shouldBeEqualTo true
            hasDefaultValue("SampleObject") shouldBeEqualTo true
            hasDefaultValue("SampleObject()") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-lambda-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("class-has-lambda-default-parameter-value")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.defaultValue shouldBeEqualTo "{ }"
            it?.hasDefaultValue() shouldBeEqualTo true
            it?.hasDefaultValue("{ }") shouldBeEqualTo true
            it?.hasDefaultValue("SampleObject()") shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-lambda-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("function-has-lambda-default-parameter-value")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            defaultValue shouldBeEqualTo "{ }"
            hasDefaultValue() shouldBeEqualTo true
            hasDefaultValue("{ }") shouldBeEqualTo true
            hasDefaultValue("SampleObject()") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-generic-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("class-has-generic-default-parameter-value")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.defaultValue shouldBeEqualTo "SampleGenericClassWithParameter(\"\")"
            it?.hasDefaultValue() shouldBeEqualTo true
            it?.hasDefaultValue("SampleGenericClassWithParameter(\"\")") shouldBeEqualTo true
            it?.hasDefaultValue("SampleGenericClassWithParameter") shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-generic-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("function-has-generic-default-parameter-value")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            defaultValue shouldBeEqualTo "SampleGenericClassWithParameter(\"\")"
            hasDefaultValue() shouldBeEqualTo true
            hasDefaultValue("SampleGenericClassWithParameter(\"\")") shouldBeEqualTo true
            hasDefaultValue("SampleGenericClassWithParameter") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-null-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("class-has-null-default-parameter-value")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.defaultValue shouldBeEqualTo "null"
            it?.hasDefaultValue() shouldBeEqualTo true
            it?.hasDefaultValue("SampleType()") shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-null-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("function-has-null-default-parameter-value")
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
    fun `class-has-no-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("class-has-no-default-parameter-value")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.defaultValue shouldBeEqualTo null
            it?.hasDefaultValue() shouldBeEqualTo false
            it?.hasDefaultValue("SampleType()") shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-no-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("function-has-no-default-parameter-value")
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

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/snippet/forkodefaultvalueprovider/", fileName)
}
