package com.lemonappdev.konsist.core.declaration.type.kogenerictype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
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
            it?.typeArgument?.declaration shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.typeArgument?.name shouldBeEqualTo "String"
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
            it?.typeArgument?.declaration shouldBeInstanceOf KoClassDeclaration::class
            it?.typeArgument?.name shouldBeEqualTo "SampleClass"
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
            it?.typeArgument?.declaration shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.typeArgument?.name shouldBeEqualTo "SampleInterface"
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
            it?.typeArgument?.declaration shouldBeInstanceOf KoObjectDeclaration::class
            it?.typeArgument?.name shouldBeEqualTo "SampleObject"
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
            it?.typeArgument?.declaration shouldBeInstanceOf KoGenericTypeDeclaration::class
            it?.typeArgument?.name shouldBeEqualTo "Set<String>"
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
            it?.typeArgument?.asGenericTypeDeclaration()?.typeArgument?.declaration shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.typeArgument?.asGenericTypeDeclaration()?.typeArgument?.name shouldBeEqualTo "String"
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
            it?.typeArgument?.declaration shouldBeInstanceOf KoFunctionTypeDeclaration::class
            it?.typeArgument?.name shouldBeEqualTo "() -> Unit"
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
            it?.typeArgument?.declaration shouldBeInstanceOf KoImportAliasDeclaration::class
            it?.typeArgument?.name shouldBeEqualTo "ImportAlias"
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
            it?.typeArgument?.declaration shouldBeInstanceOf KoTypeAliasDeclaration::class
            it?.typeArgument?.name shouldBeEqualTo "SampleTypeAlias"
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
            it?.typeArgument?.declaration shouldBeInstanceOf KoExternalDeclaration::class
            it?.typeArgument?.name shouldBeEqualTo "SampleExternalClass"
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kogenerictype/snippet/forgeneral/", fileName)
}
