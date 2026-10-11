package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.testdata.FixtureClass
import com.lemonappdev.konsist.testdata.FixtureClassWithParameter
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoTacitTypeProviderTest {
    @Test
    fun `property-has-no-tacit-type`() {
        // given
        val sut =
            getSnippetFile("property-has-no-tacit-type")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            tacitType shouldBeEqualTo null
            hasTacitType("String") shouldBeEqualTo false
            hasTacitTypeOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-explicit-simple-type`() {
        // given
        val sut =
            getSnippetFile("property-has-explicit-simple-type")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            tacitType shouldBeEqualTo type
            hasTacitType("String") shouldBeEqualTo true
            hasTacitType("Int") shouldBeEqualTo false
            hasTacitTypeOf(String::class) shouldBeEqualTo true
            hasTacitTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-explicit-complex-type`() {
        // given
        val sut =
            getSnippetFile("property-has-explicit-complex-type")
                .properties()
                .first { it.name == "fixtureProperty" }

        // then
        assertSoftly(sut) {
            tacitType shouldBeEqualTo type
            hasTacitType("FixtureClassWithParameter") shouldBeEqualTo true
            hasTacitType("FixtureClass") shouldBeEqualTo false
            hasTacitTypeOf(FixtureClassWithParameter::class) shouldBeEqualTo true
            hasTacitTypeOf(FixtureClass::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-implicit-complex-type`() {
        // given
        val sut =
            getSnippetFile("property-has-implicit-complex-type")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            tacitType?.name shouldBeEqualTo "FixtureClassWithParameter"
            (tacitType?.sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo
                "com.lemonappdev.konsist.testdata.FixtureClassWithParameter"
            hasTacitType("FixtureClassWithParameter") shouldBeEqualTo true
            hasTacitType("FixtureClass") shouldBeEqualTo false
            hasTacitTypeOf(FixtureClassWithParameter::class) shouldBeEqualTo true
            hasTacitTypeOf(FixtureClass::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-implicit-type-with-name-suffix`() {
        // given
        val sut =
            getSnippetFile("property-has-implicit-type-with-name-suffix")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            tacitType?.name shouldBeEqualTo "SampleType"
            hasTacitType("SampleType") shouldBeEqualTo true
            hasTacitType("Type") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-implicit-generic-type`() {
        // given
        val sut =
            getSnippetFile("property-has-implicit-generic-type")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            tacitType?.name shouldBeEqualTo "SampleGenericType<String>"
            tacitType?.isGeneric shouldBeEqualTo true
            tacitType?.typeArguments?.map { it.name } shouldBeEqualTo listOf("String")
            tacitType?.sourceDeclaration?.isClass shouldBeEqualTo true
            hasTacitType("SampleGenericType") shouldBeEqualTo true
            hasTacitType("SampleGenericType<String>") shouldBeEqualTo true
            hasTacitType("SampleGenericType<Int>") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-implicit-fully-qualified-type`() {
        // given
        val sut =
            getSnippetFile("property-has-implicit-fully-qualified-type")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            tacitType?.name shouldBeEqualTo "com.lemonappdev.konsist.testdata.FixtureClassWithParameter"
            hasTacitType("com.lemonappdev.konsist.testdata.FixtureClassWithParameter") shouldBeEqualTo true
            hasTacitTypeOf(FixtureClassWithParameter::class) shouldBeEqualTo true
            hasTacitTypeOf(FixtureClass::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-implicit-type-wrapped-in-apply`() {
        // given
        val sut =
            getSnippetFile("property-has-implicit-type-wrapped-in-apply")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            tacitType?.name shouldBeEqualTo "FixtureClassWithParameter"
            hasTacitType("FixtureClassWithParameter") shouldBeEqualTo true
            hasTacitTypeOf(FixtureClassWithParameter::class) shouldBeEqualTo true
        }
    }

    @Test
    fun `property-has-type-call-in-function-argument`() {
        // given
        val sut =
            getSnippetFile("property-has-type-call-in-function-argument")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            tacitType shouldBeEqualTo null
            hasTacitType("FixtureClassWithParameter") shouldBeEqualTo false
            hasTacitTypeOf(FixtureClassWithParameter::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-type-call-in-string`() {
        // given
        val sut =
            getSnippetFile("property-has-type-call-in-string")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            tacitType shouldBeEqualTo null
            hasTacitType("FixtureClassWithParameter") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-function-call`() {
        // given
        val sut =
            getSnippetFile("property-has-function-call")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            tacitType shouldBeEqualTo null
            hasTacitType("sampleFunction") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-explicit-type-and-different-constructor-call`() {
        // given
        val sut =
            getSnippetFile("property-has-explicit-type-and-different-constructor-call")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            tacitType shouldBeEqualTo type
            hasTacitType("Any") shouldBeEqualTo true
            hasTacitType("FixtureClassWithParameter") shouldBeEqualTo false
            hasTacitTypeOf(Any::class) shouldBeEqualTo true
            hasTacitTypeOf(FixtureClassWithParameter::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-explicit-generic-type`() {
        // given
        val sut =
            getSnippetFile("property-has-explicit-generic-type")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            hasTacitType("List") shouldBeEqualTo true
            hasTacitType("List<String>") shouldBeEqualTo true
            hasTacitTypeOf(List::class) shouldBeEqualTo true
        }
    }

    @Test
    fun `property-has-type-with-same-name-from-other-package`() {
        // given
        val sut =
            getSnippetFile("property-has-type-with-same-name-from-other-package")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            hasTacitType("FixtureClass") shouldBeEqualTo true
            hasTacitTypeOf(FixtureClass::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koproperty/snippet/forkotacittypeprovider/", fileName)
}
