package com.lemonappdev.konsist.core.declaration.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.ext.list.primaryConstructors
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.returnTypes
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterDeclarationForKoContainingFileProviderTest {
    @Test
    fun `function-type-parameter-containing-file`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-containing-file")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        sut?.containingFile?.nameWithExtension shouldBeEqualTo "function-type-parameter-containing-file.kt"
    }

    @Test
    fun `class-type-parameter-containing-file`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-containing-file")
                .classes()
                .primaryConstructors
                .parameters
                .first()
                .type
                .asTypeParameterDeclaration()

        // then
        (sut?.containingDeclaration as? KoNameProvider)?.name shouldBeEqualTo "class-type-parameter-containing-file"
    }

    @Test
    fun `interface-type-parameter-containing-file`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-containing-file")
                .interfaces()
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        (sut?.containingDeclaration as? KoNameProvider)?.name shouldBeEqualTo "interface-type-parameter-containing-file"
    }

    @Test
    fun `property-type-parameter-containing-file`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-containing-file")
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        (sut?.containingDeclaration as? KoNameProvider)?.name shouldBeEqualTo "property-type-parameter-containing-file"
    }

    @Test
    fun `typealias-type-parameter-containing-file`() {
        // given
        val sut =
            getSnippetFile("typealias-type-parameter-containing-file")
                .typeAliases
                .first()
                .type
                .typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration
                ?.asTypeParameterDeclaration()

        // then
        (sut?.containingDeclaration as? KoNameProvider)?.name shouldBeEqualTo "typealias-type-parameter-containing-file"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypeparameter/snippet/forkocontainingfileprovider/", fileName)
}
