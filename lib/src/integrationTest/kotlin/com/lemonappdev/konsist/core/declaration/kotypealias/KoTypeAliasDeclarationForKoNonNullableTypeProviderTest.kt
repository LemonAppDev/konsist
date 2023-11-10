package com.lemonappdev.konsist.core.declaration.kotypealias

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.testdata.SampleType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoNonNullableTypeProviderTest {
    @Test
    fun `typealias-has-simple-type`() {
        // given
        val sut = getSnippetFile("typealias-has-simple-type")
            .files
            .first()
            .typeAliases
            .first()

        // then
        assertSoftly(sut) {
            it.type.name shouldBeEqualTo "String"
            it.hasType { type -> type.name == "String" } shouldBeEqualTo true
            it.hasType { type -> type.name == "Int" } shouldBeEqualTo false
            it.hasTypeOf(String::class) shouldBeEqualTo true
            it.hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-complex-type`() {
        // given
        val sut = getSnippetFile("typealias-has-complex-type")
            .files
            .first()
            .typeAliases
            .first()

        // then
        assertSoftly(sut) {
            it.type.name shouldBeEqualTo "SampleType"
            it.hasType { type -> type.name == "SampleType" } shouldBeEqualTo true
            it.hasType { type -> type.name == "Int" } shouldBeEqualTo false
            it.hasTypeOf(SampleType::class) shouldBeEqualTo true
            it.hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-lambda-type`() {
        // given
        val sut = getSnippetFile("typealias-has-lambda-type")
            .files
            .first()
            .typeAliases
            .first()

        // then
        assertSoftly(sut) {
            it.type.name shouldBeEqualTo "() -> Int"
            it.hasType { type -> type.name == "() -> Int" } shouldBeEqualTo true
            it.hasType { type -> type.name == "Int" } shouldBeEqualTo false
            it.hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypealias/snippet/forkononnullabletypeprovider/", fileName)
}
