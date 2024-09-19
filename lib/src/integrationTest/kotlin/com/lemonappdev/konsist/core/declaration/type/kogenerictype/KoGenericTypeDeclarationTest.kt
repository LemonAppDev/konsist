package com.lemonappdev.konsist.core.declaration.type.kogenerictype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoGenericTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class KoGenericTypeDeclarationTest {
    @Test
    fun `type-to-string`() {
        // given
        val sut =
            getSnippetFile("type-to-string")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        sut.toString() shouldBeEqualTo "List<Set<String>>"
    }

    @Test
    fun `kotlin-generic-type`() {
        // given
        val sut =
            getSnippetFile("kotlin-generic-type")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.genericType?.declaration shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.genericType?.name shouldBeEqualTo "List"
        }
    }

    @Test
    fun `class-generic-type`() {
        // given
        val sut =
            getSnippetFile("class-generic-type")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.genericType?.declaration shouldBeInstanceOf KoClassDeclaration::class
            it?.genericType?.name shouldBeEqualTo "SampleGenericClassWithParameter"
        }
    }

    @Test
    fun `interface-generic-type`() {
        // given
        val sut =
            getSnippetFile("interface-generic-type")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.genericType?.declaration shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.genericType?.name shouldBeEqualTo "SampleGenericSuperInterface"
        }
    }

    @Test
    fun `nested-generic-type`() {
        // given
        val sut =
            getSnippetFile("nested-generic-type")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.genericType
                ?.declaration
                ?.shouldBeInstanceOf(KoKotlinTypeDeclaration::class)
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.genericType
                ?.name
                ?.shouldBeEqualTo("Set")
        }
    }

    @Test
    fun `import-alias-generic-type`() {
        // given
        val sut =
            getSnippetFile("import-alias-generic-type")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.genericType?.declaration shouldBeInstanceOf KoImportAliasDeclaration::class
            it?.genericType?.name shouldBeEqualTo "SampleImportAlias"
        }
    }

    @Test
    fun `external-generic-type`() {
        // given
        val sut =
            getSnippetFile("external-generic-type")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.genericType?.declaration shouldBeInstanceOf KoExternalDeclaration::class
            it?.genericType?.name shouldBeEqualTo "SampleExternalGenericClass"
        }
    }

    @Test
    fun `kotlin-type-argument`() {
        // given
        val sut =
            getSnippetFile("kotlin-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "String"
        }
    }

    @Test
    fun `class-type-argument`() {
        // given
        val sut =
            getSnippetFile("class-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoClassDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "SampleClass"
        }
    }

    @Test
    fun `interface-type-argument`() {
        // given
        val sut =
            getSnippetFile("interface-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "SampleInterface"
        }
    }

    @Test
    fun `object-type-argument`() {
        // given
        val sut =
            getSnippetFile("object-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoObjectDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "SampleObject"
        }
    }

    @Test
    fun `generic-type-argument`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoGenericTypeDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "Set<String>"
        }
    }

    @Test
    fun `nested-generic-type-argument`() {
        // given
        val sut =
            getSnippetFile("nested-generic-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.declaration
                ?.shouldBeInstanceOf(KoKotlinTypeDeclaration::class)
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.name
                ?.shouldBeEqualTo("String")
        }
    }

    @Test
    fun `function-type-argument`() {
        // given
        val sut =
            getSnippetFile("function-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoFunctionTypeDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "() -> Unit"
        }
    }

    @Test
    fun `import-alias-type-argument`() {
        // given
        val sut =
            getSnippetFile("import-alias-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoImportAliasDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "ImportAlias"
        }
    }

    @Test
    fun `typealias-type-argument`() {
        // given
        val sut =
            getSnippetFile("typealias-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoTypeAliasDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "SampleTypeAlias"
        }
    }

    @Test
    fun `external-type-argument`() {
        // given
        val sut =
            getSnippetFile("external-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoExternalDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "SampleExternalClass"
        }
    }

    @Test
    fun `few-type-arguments`() {
        // given
        val sut =
            getSnippetFile("few-type-arguments")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        sut
            ?.typeArguments
            ?.map { typeArgument -> typeArgument.name }
            .shouldBeEqualTo(listOf("SampleClass", "String"))
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kogenerictype/snippet/forgeneral/", fileName)
}
