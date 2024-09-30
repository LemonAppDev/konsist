package com.lemonappdev.konsist.core.declaration.koimportalias

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.ext.list.importAliases
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.externalsample.SampleExternalClass
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleObject
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldNotBeInstanceOf
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.reflect.KClass

class KoImportAliasDeclarationForKoSourceDeclarationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `source declaration`(
        fileName: String,
        instanceOf: KClass<*>,
        notInstanceOf: KClass<*>,
        kClass: KClass<*>?,
        fullyQualifiedName: String?,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .imports
                .importAliases
                .first()

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf instanceOf
            sourceDeclaration shouldNotBeInstanceOf notInstanceOf
            hasSourceDeclaration {
                (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName == fullyQualifiedName
            }.shouldBeEqualTo(true)
            hasSourceDeclaration {
                (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName == "com.samplepackage.other"
            }.shouldBeEqualTo(false)
            kClass
                ?.let { value -> hasSourceDeclarationOf(value) }
                ?.shouldBeEqualTo(true)
            hasSourceDeclarationOf(Char::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koimportalias/snippet/forkosourcedeclarationprovider/",
            fileName,
        )

    companion object {
        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments(
                    "kotlin-source-declaration",
                    KoKotlinTypeDeclaration::class,
                    KoClassDeclaration::class,
                    String::class,
                    "kotlin.String",
                ),
                arguments(
                    "class-source-declaration",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleClass::class,
                    "com.lemonappdev.konsist.testdata.SampleClass",
                ),
                arguments(
                    "interface-source-declaration",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    SampleInterface::class,
                    "com.lemonappdev.konsist.testdata.SampleInterface",
                ),
                arguments(
                    "object-source-declaration",
                    KoObjectDeclaration::class,
                    KoClassDeclaration::class,
                    SampleObject::class,
                    "com.lemonappdev.konsist.testdata.SampleObject",
                ),
                arguments(
                    "function-source-declaration",
                    KoFunctionDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.lemonappdev.konsist.testdata.sampleFunction",
                ),
                arguments(
                    "property-source-declaration",
                    KoPropertyDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.lemonappdev.konsist.testdata.SAMPLE_PROPERTY",
                ),
                arguments(
                    "typealias-source-declaration",
                    KoTypeAliasDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.lemonappdev.konsist.testdata.SampleBasicTypeAlias",
                ),
                arguments(
                    "external-source-declaration",
                    KoExternalDeclaration::class,
                    KoClassDeclaration::class,
                    SampleExternalClass::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalClass",
                ),
            )
    }
}
