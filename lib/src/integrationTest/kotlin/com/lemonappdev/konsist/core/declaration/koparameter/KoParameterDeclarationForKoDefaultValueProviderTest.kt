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
    fun `class-has-complex-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("class-has-complex-default-parameter-value")
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
    fun `function-has-complex-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("function-has-complex-default-parameter-value")
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
