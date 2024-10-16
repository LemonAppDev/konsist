package com.lemonappdev.konsist.core.declaration.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.ext.list.primaryConstructors
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.returnTypes
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
        sut?.location shouldBeEqualTo "${sut?.path}:1:6"
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
        sut?.location shouldBeEqualTo "${sut?.path}:1:19"
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
        sut?.location shouldBeEqualTo "${sut?.path}:1:27"
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
        sut?.location shouldBeEqualTo "${sut?.path}:1:6"
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
        sut?.location shouldBeEqualTo "${sut?.path}:1:27"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypeparameter/snippet/forkolocationprovider/",
            fileName,
        )
}
