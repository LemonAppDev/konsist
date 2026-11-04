package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.testdata.FixtureType
import com.lemonappdev.konsist.testdata.FixtureType1
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.reflect.typeOf

class KoPropertyDeclarationForKoNullableTypeProviderTest {
    @Test
    fun `property-without-type`() {
        // given
        val sut =
            getSnippetFile("property-without-type")
                .properties(includeNested = true)
                .first()

        // then
        assertSoftly(sut) {
            type shouldBeEqualTo null
            hasType() shouldBeEqualTo false
            hasType { it.name == "String" } shouldBeEqualTo false
            hasTypeOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-with-type`() {
        // given
        val sut =
            getSnippetFile("property-with-type")
                .properties(includeNested = true)
                .first()

        // then
        assertSoftly(sut) {
            type?.name shouldBeEqualTo "String"
            hasType() shouldBeEqualTo true
            hasType { it.name == "String" } shouldBeEqualTo true
            hasType { it.name == "Int" } shouldBeEqualTo false
            hasTypeOf(String::class) shouldBeEqualTo true
            hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-with-annotated-type`() {
        // given
        val sut =
            getSnippetFile("property-with-annotated-type")
                .properties(includeNested = true)
                .first()

        // then
        assertSoftly(sut) {
            type?.name shouldBeEqualTo "String"
            hasType() shouldBeEqualTo true
            hasType { it.name == "String" } shouldBeEqualTo true
            hasType { it.name == "Int" } shouldBeEqualTo false
            hasTypeOf(String::class) shouldBeEqualTo true
            hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-with-import-alias-type`() {
        // given
        val sut =
            getSnippetFile("property-with-import-alias-type")
                .properties(includeNested = true)

        // then
        assertSoftly(sut.first { it.name == "sampleProperty1" }) {
            hasTypeOf(FixtureType::class) shouldBeEqualTo true
            hasTypeOf(FixtureType1::class) shouldBeEqualTo false
            hasTypeOf(typeOf<FixtureType?>()) shouldBeEqualTo true
            hasTypeOf(typeOf<FixtureType1?>()) shouldBeEqualTo false
        }

        assertSoftly(sut.first { it.name == "sampleProperty2" }) {
            hasTypeOf(List::class) shouldBeEqualTo true
            hasTypeOf(typeOf<List<FixtureType>>()) shouldBeEqualTo true
            hasTypeOf(typeOf<List<FixtureType1>>()) shouldBeEqualTo false
            type?.hasTypeArgumentOf(FixtureType::class) shouldBeEqualTo true
            type?.hasTypeArgumentOf(FixtureType1::class) shouldBeEqualTo false
            type?.hasAllTypeArgumentsOf(FixtureType::class) shouldBeEqualTo true
            type?.hasAllTypeArgumentsOf(listOf(FixtureType::class)) shouldBeEqualTo true
        }

        assertSoftly(sut.first { it.name == "sampleProperty3" }) {
            hasTypeOf(File::class) shouldBeEqualTo true
            hasTypeOf(typeOf<File>()) shouldBeEqualTo true
            hasTypeOf(FixtureType::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `extension-property-with-type`() {
        // given
        val sut =
            getSnippetFile("extension-property-with-type")
                .properties(includeNested = true)
                .first()

        // then
        assertSoftly(sut) {
            type?.name shouldBeEqualTo "String"
            hasType() shouldBeEqualTo true
            hasType { it.name == "String" } shouldBeEqualTo true
            hasType { it.name == "Int" } shouldBeEqualTo false
            hasTypeOf(String::class) shouldBeEqualTo true
            hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `extension-property-without-type`() {
        // given
        val sut =
            getSnippetFile("extension-property-without-type")
                .properties(includeNested = true)
                .first()

        // then
        assertSoftly(sut) {
            type shouldBeEqualTo null
            hasType() shouldBeEqualTo false
            hasType { it.name == "Int" } shouldBeEqualTo false
            hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koproperty/snippet/forkonullabletypeprovider/", fileName)
}
