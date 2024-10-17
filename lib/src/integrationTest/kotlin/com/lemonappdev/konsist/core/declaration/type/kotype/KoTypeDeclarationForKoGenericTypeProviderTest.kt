package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.externalsample.SampleExternalGenericClass
import com.lemonappdev.konsist.testdata.SampleGenericClassWithParameter
import com.lemonappdev.konsist.testdata.SampleGenericSuperInterface
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoTypeDeclarationForKoGenericTypeProviderTest {
    @Test
    fun `kotlin-generic-type`() {
        // given
        val sut =
            getSnippetFile("kotlin-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.genericType shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.genericType?.name shouldBeEqualTo "List"
            it?.hasGenericType { type -> type.isKotlinCollectionType } shouldBeEqualTo true
            it?.hasGenericType { type -> type.isClass } shouldBeEqualTo false
            it?.hasGenericTypeOf(List::class) shouldBeEqualTo true
            it?.hasGenericTypeOf(Map::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-type`() {
        // given
        val sut =
            getSnippetFile("class-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.genericType shouldBeInstanceOf KoClassDeclaration::class
            it?.genericType?.name shouldBeEqualTo "SampleGenericClassWithParameter"
            it?.hasGenericType { type -> type.isClass } shouldBeEqualTo true
            it?.hasGenericType { type -> type.isKotlinType } shouldBeEqualTo false
            it?.hasGenericTypeOf(SampleGenericClassWithParameter::class) shouldBeEqualTo true
            it?.hasGenericTypeOf(Map::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-type`() {
        // given
        val sut =
            getSnippetFile("interface-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.genericType shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.genericType?.name shouldBeEqualTo "SampleGenericSuperInterface"
            it?.hasGenericType { type -> type.isInterface } shouldBeEqualTo true
            it?.hasGenericType { type -> type.isClass } shouldBeEqualTo false
            it?.hasGenericTypeOf(SampleGenericSuperInterface::class) shouldBeEqualTo true
            it?.hasGenericTypeOf(Map::class) shouldBeEqualTo false
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

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.genericType
                ?.shouldBeInstanceOf(KoKotlinTypeDeclaration::class)
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.genericType
                ?.name
                ?.shouldBeEqualTo("Set")
        }
    }

    @Test
    fun `import-alias-type`() {
        // given
        val sut =
            getSnippetFile("import-alias-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
//            it?.genericType shouldBeInstanceOf KoImportAliasDeclaration::class
//            it?.genericType?.name shouldBeEqualTo "SampleImportAlias"
            it?.hasGenericType { type -> type.isImportAlias } shouldBeEqualTo true
//            it?.hasGenericType { type -> type.isClass } shouldBeEqualTo false
//            it?.hasGenericTypeOf(Map::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `external-type`() {
        // given
        val sut =
            getSnippetFile("external-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.genericType shouldBeInstanceOf KoExternalDeclaration::class
            it?.genericType?.name shouldBeEqualTo "SampleExternalGenericClass"
            it?.hasGenericType { type -> type.isExternalType } shouldBeEqualTo true
            it?.hasGenericType { type -> type.isClass } shouldBeEqualTo false
            it?.hasGenericTypeOf(SampleExternalGenericClass::class) shouldBeEqualTo true
            it?.hasGenericTypeOf(Map::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotype/snippet/forkogenerictypeprovider/",
            fileName,
        )
}
