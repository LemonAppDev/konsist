package com.lemonappdev.konsist.core.declaration.type.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.provider.parameters
import com.lemonappdev.konsist.api.ext.list.provider.primaryConstructors
import com.lemonappdev.konsist.api.ext.list.provider.properties
import com.lemonappdev.konsist.api.ext.list.provider.returnTypes
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

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
                ?.sourceDeclaration
                ?.asTypeParameterDeclaration()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:1:37"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotypeparameter/snippet/forkolocationprovider/",
            fileName,
        )
}
