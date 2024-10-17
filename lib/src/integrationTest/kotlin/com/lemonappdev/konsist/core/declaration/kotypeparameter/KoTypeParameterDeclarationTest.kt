package com.lemonappdev.konsist.core.declaration.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.ext.list.primaryConstructors
import com.lemonappdev.konsist.api.ext.list.properties
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterDeclarationTest {
    @Test
    fun `function-parameter-type-to-string`() {
        // given
        val sut =
            getSnippetFile("function-parameter-type-to-string")
                .functions()
                .first()
                .returnType
                ?.asTypeParameterDeclaration()

        // then
        sut.toString() shouldBeEqualTo "TestType"
    }

    @Test
    fun `class-parameter-type-to-string`() {
        // given
        val sut =
            getSnippetFile("class-parameter-type-to-string")
                .classes()
                .primaryConstructors
                .parameters
                .first()
                .type
                .asTypeParameterDeclaration()

        // then
        sut.toString() shouldBeEqualTo "TestType"
    }

    @Test
    fun `interface-parameter-type-to-string`() {
        // given
        val sut =
            getSnippetFile("interface-parameter-type-to-string")
                .interfaces()
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        sut.toString() shouldBeEqualTo "TestType"
    }

    @Test
    fun `property-parameter-type-to-string`() {
        // given
        val sut =
            getSnippetFile("property-parameter-type-to-string")
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        sut.toString() shouldBeEqualTo "TestType"
    }

    @Test
    fun `typealias-parameter-type-to-string`() {
        // given
        val sut =
            getSnippetFile("typealias-parameter-type-to-string")
                .typeAliases
                .first()
                .type
                .typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration
                ?.asTypeParameterDeclaration()

        // then
        sut.toString() shouldBeEqualTo "TestType"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypeparameter/snippet/forgeneral/", fileName)
}
