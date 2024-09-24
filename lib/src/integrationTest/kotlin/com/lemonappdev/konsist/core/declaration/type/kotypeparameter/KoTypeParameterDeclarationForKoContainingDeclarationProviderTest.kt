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

class KoTypeParameterDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `function-type-parameter-containing-declaration`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-containing-declaration")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        (sut?.containingDeclaration as? KoNameProvider)?.name shouldBeEqualTo "function-type-parameter-containing-declaration"
    }

    @Test
    fun `class-type-parameter-containing-declaration`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-containing-declaration")
                .classes()
                .primaryConstructors
                .parameters
                .first()
                .type
                .asTypeParameterDeclaration()

        // then
        (sut?.containingDeclaration as? KoNameProvider)?.name shouldBeEqualTo "class-type-parameter-containing-declaration"
    }

    @Test
    fun `interface-type-parameter-containing-declaration`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-containing-declaration")
                .interfaces()
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        (sut?.containingDeclaration as? KoNameProvider)?.name shouldBeEqualTo "interface-type-parameter-containing-declaration"
    }

    @Test
    fun `property-type-parameter-containing-declaration`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-containing-declaration")
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        (sut?.containingDeclaration as? KoNameProvider)?.name shouldBeEqualTo "property-type-parameter-containing-declaration"
    }

    @Test
    fun `typealias-type-parameter-containing-declaration`() {
        // given
        val sut =
            getSnippetFile("typealias-type-parameter-containing-declaration")
                .typeAliases
                .first()
                .type
                .asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        (sut?.containingDeclaration as? KoNameProvider)?.name shouldBeEqualTo "typealias-type-parameter-containing-declaration"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotypeparameter/snippet/forkocontainingdeclarationprovider/",
            fileName,
        )
}
