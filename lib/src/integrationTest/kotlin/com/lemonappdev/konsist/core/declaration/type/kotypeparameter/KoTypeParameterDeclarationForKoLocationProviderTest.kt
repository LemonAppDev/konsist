package com.lemonappdev.konsist.core.declaration.type.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.ext.list.primaryConstructors
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.returnTypes
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeParameterDeclarationForKoLocationProviderTest {
    @Test
    fun `function-type-parameter-location`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-location")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:1:43"
    }

    @Test
    fun `class-type-parameter-location`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-location")
                .classes()
                .primaryConstructors
                .parameters
                .first()
                .type
                .asTypeParameterDeclaration()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:1:50"
    }

    @Test
    fun `interface-type-parameter-location`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-location")
                .interfaces()
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:2:25"
    }

    @Test
    fun `property-type-parameter-location`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-location")
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:1:16"
    }

    @Test
    fun `typealias-type-parameter-location`() {
        // given
        val sut =
            getSnippetFile("typealias-type-parameter-location")
                .typeAliases
                .first()
                .type
                .asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotypeparameter/snippet/forkolocationprovider/",
            fileName,
        )
}
