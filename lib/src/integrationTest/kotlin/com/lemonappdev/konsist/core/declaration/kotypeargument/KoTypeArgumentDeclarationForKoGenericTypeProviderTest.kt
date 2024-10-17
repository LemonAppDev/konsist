package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class KoTypeArgumentDeclarationForKoGenericTypeProviderTest {
    @Test
    fun `not-generic-type-argument-generic-type`() {
        // given
        val sut =
            getSnippetFile("not-generic-type-argument-generic-type")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.genericType shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.genericType?.name shouldBeEqualTo "String"
            it?.hasGenericType { type -> type.name == "String" } shouldBeEqualTo true
            it?.hasGenericType { type -> type.name == "Int" } shouldBeEqualTo false
            it?.hasGenericTypeOf(String::class) shouldBeEqualTo true
            it?.hasGenericTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-type-argument-generic-type`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-generic-type")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.genericType shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.genericType?.name shouldBeEqualTo "Set"
            it?.hasGenericType { type -> type.name == "Set" } shouldBeEqualTo true
            it?.hasGenericType { type -> type.name == "Map" } shouldBeEqualTo false
            it?.hasGenericTypeOf(Set::class) shouldBeEqualTo true
            it?.hasGenericTypeOf(Map::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-complex-type-argument-generic-type`() {
        // given
        val sut =
            getSnippetFile("generic-complex-type-argument-generic-type")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.genericType shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.genericType?.name shouldBeEqualTo "Map"
            it?.hasGenericType { type -> type.name == "Map" } shouldBeEqualTo true
            it?.hasGenericType { type -> type.name == "Set" } shouldBeEqualTo false
            it?.hasGenericTypeOf(Map::class) shouldBeEqualTo true
            it?.hasGenericTypeOf(Set::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `star-projection-type-argument-generic-type`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-argument-generic-type")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.genericType shouldBeInstanceOf KoStarProjectionDeclaration::class
            it?.genericType?.name shouldBeEqualTo "*"
            it?.hasGenericType { type -> type.name == "*" } shouldBeEqualTo true
            it?.hasGenericType { type -> type.name == "Set" } shouldBeEqualTo false
            it?.hasGenericTypeOf(Set::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `out-projection-type-argument-generic-type`() {
        // given
        val sut =
            getSnippetFile("out-projection-type-argument-generic-type")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.genericType shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.genericType?.name shouldBeEqualTo "String"
            it?.hasGenericType { type -> type.name == "String" } shouldBeEqualTo true
            it?.hasGenericType { type -> type.name == "Int" } shouldBeEqualTo false
            it?.hasGenericTypeOf(String::class) shouldBeEqualTo true
            it?.hasGenericTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `in-projection-type-argument-generic-type`() {
        // given
        val sut =
            getSnippetFile("in-projection-type-argument-generic-type")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.genericType shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.genericType?.name shouldBeEqualTo "String"
            it?.hasGenericType { type -> type.name == "String" } shouldBeEqualTo true
            it?.hasGenericType { type -> type.name == "Int" } shouldBeEqualTo false
            it?.hasGenericTypeOf(String::class) shouldBeEqualTo true
            it?.hasGenericTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypeargument/snippet/forkogenerictypeprovider/",
            fileName,
        )
}
