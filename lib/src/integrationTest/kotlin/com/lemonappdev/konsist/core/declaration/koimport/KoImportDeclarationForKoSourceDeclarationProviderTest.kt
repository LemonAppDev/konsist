package com.lemonappdev.konsist.core.declaration.koimport

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldNotBeInstanceOf
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.reflect.KClass

class KoImportDeclarationForKoSourceDeclarationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `source declaration`(
        fileName: String,
        instanceOf: KClass<*>,
        notInstanceOf: KClass<*>,
        fqn: String?,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .imports
                .first()

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf instanceOf
            sourceDeclaration shouldNotBeInstanceOf notInstanceOf
            (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo fqn
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koimport/snippet/forkosourcedeclarationprovider/", fileName)

    companion object {
        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments(
                    "kotlin-source-declaration",
                    KoKotlinTypeDeclaration::class,
                    KoClassDeclaration::class,
                    "kotlin.String",
                ),
                arguments(
                    "class-source-declaration",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    "com.lemonappdev.konsist.testdata.SampleClass",
                ),
                arguments(
                    "interface-source-declaration",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    "com.lemonappdev.konsist.testdata.SampleInterface",
                ),
                arguments(
                    "object-source-declaration",
                    KoObjectDeclaration::class,
                    KoClassDeclaration::class,
                    "com.lemonappdev.konsist.testdata.SampleObject",
                ),
                arguments(
                    "function-source-declaration",
                    KoFunctionDeclaration::class,
                    KoClassDeclaration::class,
                    "com.lemonappdev.konsist.testdata.sampleFunction",
                ),
                arguments(
                    "property-source-declaration",
                    KoPropertyDeclaration::class,
                    KoClassDeclaration::class,
                    "com.lemonappdev.konsist.testdata.SAMPLE_PROPERTY",
                ),
                arguments(
                    "typealias-source-declaration",
                    KoTypeAliasDeclaration::class,
                    KoClassDeclaration::class,
                    "com.lemonappdev.konsist.testdata.SampleTypeAlias",
                ),
                arguments(
                    "external-source-declaration",
                    KoExternalDeclaration::class,
                    KoClassDeclaration::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalClass",
                ),
            )
    }
}
