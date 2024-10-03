package com.lemonappdev.konsist.core.declaration.type.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.provider.parameters
import com.lemonappdev.konsist.api.ext.list.provider.primaryConstructors
import com.lemonappdev.konsist.api.ext.list.provider.properties
import com.lemonappdev.konsist.api.ext.list.provider.returnTypes
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterDeclarationForKoPackageProviderTest {
    @Test
    fun `function-type-parameter-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-is-not-in-package")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        sut?.packagee shouldBeEqualTo null
    }

    @Test
    fun `function-type-parameter-is-in-package`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-is-in-package")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        sut?.packagee?.name shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `class-type-parameter-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-is-not-in-package")
                .classes()
                .primaryConstructors
                .parameters
                .first()
                .type
                .asTypeParameterDeclaration()

        // then
        sut?.packagee shouldBeEqualTo null
    }

    @Test
    fun `class-type-parameter-is-in-package`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-is-in-package")
                .classes()
                .primaryConstructors
                .parameters
                .first()
                .type
                .asTypeParameterDeclaration()

        // then
        sut?.packagee?.name shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `interface-type-parameter-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-is-not-in-package")
                .interfaces()
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        sut?.packagee shouldBeEqualTo null
    }

    @Test
    fun `interface-type-parameter-is-in-package`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-is-in-package")
                .interfaces()
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        sut?.packagee?.name shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `property-type-parameter-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-is-not-in-package")
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        sut?.packagee shouldBeEqualTo null
    }

    @Test
    fun `property-type-parameter-is-in-package`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-is-in-package")
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        sut?.packagee?.name shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `typealias-type-parameter-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("typealias-type-parameter-is-not-in-package")
                .typeAliases
                .first()
                .type
                .asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration
                ?.asTypeParameterDeclaration()

        // then
        sut?.packagee shouldBeEqualTo null
    }

    @Test
    fun `typealias-type-parameter-is-in-package`() {
        // given
        val sut =
            getSnippetFile("typealias-type-parameter-is-in-package")
                .typeAliases
                .first()
                .type
                .asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration
                ?.asTypeParameterDeclaration()

        // then
        sut?.packagee?.name shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotypeparameter/snippet/forkopackageprovider/",
            fileName,
        )
}
