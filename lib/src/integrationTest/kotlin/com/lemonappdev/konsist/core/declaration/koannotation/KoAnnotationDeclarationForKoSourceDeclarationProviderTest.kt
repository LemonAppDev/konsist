package com.lemonappdev.konsist.core.declaration.koannotation

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.ext.list.annotations
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.externalsample.SampleExternalClass
import com.lemonappdev.konsist.testdata.SampleAnnotation
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

class KoAnnotationDeclarationForKoSourceDeclarationProviderTest {
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
                .classes()
                .annotations
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
        getSnippetKoScope("core/declaration/koannotation/snippet/forkosourcedeclarationprovider/", fileName)

    companion object {
        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments(
                    "annotation-with-kotlin-source-declaration-with-default-import",
                    KoKotlinTypeDeclaration::class,
                    KoClassDeclaration::class,
                    Deprecated::class,
                    "kotlin.Deprecated",
                ),
                arguments(
                    "annotation-with-kotlin-source-declaration-without-default-import",
                    KoKotlinTypeDeclaration::class,
                    KoClassDeclaration::class,
                    Deprecated::class,
                    "kotlin.Deprecated",
                ),
                arguments(
                    "annotation-with-source-declaration-defined-in-the-file-with-package",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "com.samplepackage.SampleAnnotationFromFile",
                ),
                arguments(
                    "annotation-with-source-declaration-defined-in-the-file-without-package",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "SampleAnnotationFromFile",
                ),
                arguments(
                    "annotation-with-imported-source-declaration",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleAnnotation::class,
                    "com.lemonappdev.konsist.testdata.SampleAnnotation",
                ),
                arguments(
                    "annotation-with-source-declaration-defined-using-import-alias",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleAnnotation::class,
                    "com.lemonappdev.konsist.testdata.SampleAnnotation",
                ),
            )
    }
}
