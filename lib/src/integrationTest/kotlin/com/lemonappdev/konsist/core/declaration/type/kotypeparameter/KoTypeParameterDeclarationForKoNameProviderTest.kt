package com.lemonappdev.konsist.core.declaration.type.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.ext.list.primaryConstructors
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.returnTypes
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterDeclarationForKoNameProviderTest {
    @Test
    fun `function-type-parameter-name`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-name")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        sut?.name shouldBeEqualTo "TestType"
    }

    @Test
    fun `class-type-parameter-name`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-name")
                .classes()
                .primaryConstructors
                .parameters
                .first()
                .type
                .asTypeParameterDeclaration()

        // then
        sut?.name shouldBeEqualTo "TestType"
    }

    @Test
    fun `interface-type-parameter-name`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-name")
                .interfaces()
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        sut?.name shouldBeEqualTo "TestType"
    }

    @Test
    fun `property-type-parameter-name`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-name")
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        sut?.name shouldBeEqualTo "TestType"
    }

    @Test
    fun `typealias-type-parameter-name`() {
        // given
        val sut =
            getSnippetFile("typealias-type-parameter-name")
                .typeAliases
                .first()
                .type
                .asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        sut?.name shouldBeEqualTo "TestType"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotypeparameter/snippet/forkonameprovider/",
            fileName,
        )
}
