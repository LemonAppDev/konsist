package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.ext.list.parents
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.externalsample.SampleExternalClass
import com.lemonappdev.konsist.externalsample.SampleExternalClassWithParameter
import com.lemonappdev.konsist.externalsample.SampleExternalGenericClass
import com.lemonappdev.konsist.externalsample.SampleExternalGenericClassWithParameter
import com.lemonappdev.konsist.externalsample.SampleExternalGenericInterface
import com.lemonappdev.konsist.externalsample.SampleExternalInterface
import com.lemonappdev.konsist.testdata.SampleClassWithParameter
import com.lemonappdev.konsist.testdata.SampleCollection1
import com.lemonappdev.konsist.testdata.SampleGenericClassWithParameter
import com.lemonappdev.konsist.testdata.SampleGenericSuperInterface
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldNotBeInstanceOf
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.reflect.KClass

class KoParentDeclarationForKoSourceDeclarationProviderTest {
    @ParameterizedTest
    @MethodSource("provideClassesForSourceDeclaration")
    fun `class-parent-has-source-declaration`(
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
                .parents()
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

    @ParameterizedTest
    @MethodSource("provideInterfacesForSourceDeclaration")
    fun `interface-parent-has-source-declaration`(
        fileName: String,
        instanceOf: KClass<*>,
        notInstanceOf: KClass<*>,
        kClass: KClass<*>?,
        fullyQualifiedName: String?,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .interfaces()
                .parents()
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

    @ParameterizedTest
    @MethodSource("provideObjectsForSourceDeclaration")
    fun `object-parent-has-source-declaration`(
        fileName: String,
        instanceOf: KClass<*>,
        notInstanceOf: KClass<*>,
        kClass: KClass<*>?,
        fullyQualifiedName: String?,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .objects()
                .parents()
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
            "core/declaration/koparent/snippet/forkosourcedeclarationprovider/",
            fileName,
        )

    companion object {
        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideClassesForSourceDeclaration() =
            listOf(
                arguments(
                    "class-with-parent-class-from-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "SampleSuperClass",
                ),
                arguments(
                    "class-with-generic-parent-class-from-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "SampleGenericSuperClass",
                ),
                arguments(
                    "class-with-parametrized-parent-class-from-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "SampleParametrizedSuperClass",
                ),
                arguments(
                    "class-with-parametrized-and-generic-parent-class-from-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "SampleParametrizedSuperClass",
                ),
                arguments(
                    "class-with-parent-interface-from-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "SampleSuperInterface",
                ),
                arguments(
                    "class-with-generic-parent-interface-from-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "SampleGenericSuperInterface",
                ),
                arguments(
                    "class-with-parent-by-delegation-from-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "SampleSuperInterface",
                ),
                arguments(
                    "class-with-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleParentClass::class,
                    "com.lemonappdev.konsist.testdata.SampleParentClass",
                ),
                arguments(
                    "class-with-generic-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleCollection1::class,
                    "com.lemonappdev.konsist.testdata.SampleCollection1",
                ),
                arguments(
                    "class-with-parametrized-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleClassWithParameter::class,
                    "com.lemonappdev.konsist.testdata.SampleClassWithParameter",
                ),
                arguments(
                    "class-with-parametrized-and-generic-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleGenericClassWithParameter::class,
                    "com.lemonappdev.konsist.testdata.SampleGenericClassWithParameter",
                ),
                arguments(
                    "class-with-parent-interface-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    SampleInterface::class,
                    "com.lemonappdev.konsist.testdata.SampleInterface",
                ),
                arguments(
                    "class-with-generic-parent-interface-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    SampleGenericSuperInterface::class,
                    "com.lemonappdev.konsist.testdata.SampleGenericSuperInterface",
                ),
                arguments(
                    "class-with-parent-by-delegation-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    SampleInterface::class,
                    "com.lemonappdev.konsist.testdata.SampleInterface",
                ),
                arguments(
                    "class-with-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleExternalClass::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalClass",
                ),
                arguments(
                    "class-with-generic-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleExternalGenericClass::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalGenericClass",
                ),
                arguments(
                    "class-with-parametrized-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleExternalClassWithParameter::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalClassWithParameter",
                ),
                arguments(
                    "class-with-parametrized-and-generic-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleExternalGenericClassWithParameter::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalGenericClassWithParameter",
                ),
                arguments(
                    "class-with-external-parent-interface",
                    KoExternalDeclaration::class,
                    KoClassDeclaration::class,
                    SampleExternalInterface::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalInterface",
                ),
                arguments(
                    "class-with-generic-external-parent-interface",
                    KoExternalDeclaration::class,
                    KoClassDeclaration::class,
                    SampleExternalGenericInterface::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalGenericInterface",
                ),
                arguments(
                    "class-with-external-parent-by-delegation",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleExternalInterface::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalInterface",
                ),
                arguments(
                    "class-with-typealias-parent",
                    KoTypeAliasDeclaration::class,
                    KoImportAliasDeclaration::class,
                    null,
                    "SampleTypeAlias",
                ),
                arguments(
                    "class-with-import-alias-parent",
                    KoImportAliasDeclaration::class,
                    KoTypeAliasDeclaration::class,
                    null,
                    null,
                ),
                arguments(
                    "class-with-parent-interface-with-the-same-name",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.samplepackage.SampleInterface.SampleName",
                ),
                arguments(
                    "class-with-parent-class-with-the-same-name",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "com.samplepackage.SampleInterface.SampleName",
                ),
                arguments(
                    "class-with-parent-interface-with-two-part-name-from-the-same-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.samplepackage.SampleInterface.SampleNestedInterface",
                ),
                arguments(
                    "class-with-parent-class-with-two-part-name-from-the-same-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "com.samplepackage.SampleInterface.SampleNestedClass",
                ),
                arguments(
                    "class-with-parent-interface-with-two-part-name-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.lemonappdev.konsist.testdata.SampleParentInterfaceWithNestedDeclarations.SampleNestedInterface",
                ),
                arguments(
                    "class-with-parent-class-with-two-part-name-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "com.lemonappdev.konsist.testdata.SampleParentInterfaceWithNestedDeclarations.SampleNestedClass",
                ),
            )

        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideInterfacesForSourceDeclaration() =
            listOf(
                arguments(
                    "interface-with-parent-interface-from-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "SampleSuperInterface",
                ),
                arguments(
                    "interface-with-generic-parent-interface-from-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "SampleGenericSuperInterface",
                ),
                arguments(
                    "interface-with-parent-interface-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    SampleParentInterface::class,
                    "com.lemonappdev.konsist.testdata.SampleParentInterface",
                ),
                arguments(
                    "interface-with-generic-parent-interface-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    SampleGenericSuperInterface::class,
                    "com.lemonappdev.konsist.testdata.SampleGenericSuperInterface",
                ),
                arguments(
                    "interface-with-external-parent-interface",
                    KoExternalDeclaration::class,
                    KoClassDeclaration::class,
                    SampleExternalInterface::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalInterface",
                ),
                arguments(
                    "interface-with-generic-external-parent-interface",
                    KoExternalDeclaration::class,
                    KoClassDeclaration::class,
                    SampleExternalGenericInterface::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalGenericInterface",
                ),
                arguments(
                    "interface-with-typealias-parent",
                    KoTypeAliasDeclaration::class,
                    KoImportAliasDeclaration::class,
                    null,
                    "SampleTypeAlias",
                ),
                arguments(
                    "interface-with-import-alias-parent",
                    KoImportAliasDeclaration::class,
                    KoTypeAliasDeclaration::class,
                    null,
                    null,
                ),
                arguments(
                    "interface-with-parent-interface-with-the-same-name",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.samplepackage.SampleInterface.SampleName",
                ),
                arguments(
                    "interface-with-parent-interface-with-two-part-name-from-the-same-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.samplepackage.SampleInterface.SampleNestedInterface",
                ),
                arguments(
                    "interface-with-parent-interface-with-two-part-name-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.lemonappdev.konsist.testdata.SampleParentInterfaceWithNestedDeclarations.SampleNestedInterface",
                ),
            )

        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideObjectsForSourceDeclaration() =
            listOf(
                arguments(
                    "object-with-parent-class-from-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "SampleSuperClass",
                ),
                arguments(
                    "object-with-generic-parent-class-from-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "SampleGenericSuperClass",
                ),
                arguments(
                    "object-with-parametrized-parent-class-from-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "SampleParametrizedSuperClass",
                ),
                arguments(
                    "object-with-parametrized-and-generic-parent-class-from-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "SampleParametrizedSuperClass",
                ),
                arguments(
                    "object-with-parent-interface-from-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "SampleSuperInterface",
                ),
                arguments(
                    "object-with-generic-parent-interface-from-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "SampleGenericSuperInterface",
                ),
                arguments(
                    "object-with-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleParentClass::class,
                    "com.lemonappdev.konsist.testdata.SampleParentClass",
                ),
                arguments(
                    "object-with-generic-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleCollection1::class,
                    "com.lemonappdev.konsist.testdata.SampleCollection1",
                ),
                arguments(
                    "object-with-parametrized-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleClassWithParameter::class,
                    "com.lemonappdev.konsist.testdata.SampleClassWithParameter",
                ),
                arguments(
                    "object-with-parametrized-and-generic-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleGenericClassWithParameter::class,
                    "com.lemonappdev.konsist.testdata.SampleGenericClassWithParameter",
                ),
                arguments(
                    "object-with-parent-interface-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    SampleInterface::class,
                    "com.lemonappdev.konsist.testdata.SampleInterface",
                ),
                arguments(
                    "object-with-generic-parent-interface-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    SampleGenericSuperInterface::class,
                    "com.lemonappdev.konsist.testdata.SampleGenericSuperInterface",
                ),
                arguments(
                    "object-with-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleExternalClass::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalClass",
                ),
                arguments(
                    "object-with-generic-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleExternalGenericClass::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalGenericClass",
                ),
                arguments(
                    "object-with-parametrized-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleExternalClassWithParameter::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalClassWithParameter",
                ),
                arguments(
                    "object-with-parametrized-and-generic-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    SampleExternalGenericClassWithParameter::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalGenericClassWithParameter",
                ),
                arguments(
                    "object-with-external-parent-interface",
                    KoExternalDeclaration::class,
                    KoClassDeclaration::class,
                    SampleExternalInterface::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalInterface",
                ),
                arguments(
                    "object-with-generic-external-parent-interface",
                    KoExternalDeclaration::class,
                    KoClassDeclaration::class,
                    SampleExternalGenericInterface::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalGenericInterface",
                ),
                arguments(
                    "object-with-typealias-parent",
                    KoTypeAliasDeclaration::class,
                    KoImportAliasDeclaration::class,
                    null,
                    "SampleTypeAlias",
                ),
                arguments(
                    "object-with-import-alias-parent",
                    KoImportAliasDeclaration::class,
                    KoTypeAliasDeclaration::class,
                    null,
                    null,
                ),
                arguments(
                    "object-with-parent-interface-with-the-same-name",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.samplepackage.SampleInterface.SampleName",
                ),
                arguments(
                    "object-with-parent-class-with-the-same-name",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "com.samplepackage.SampleInterface.SampleName",
                ),
                arguments(
                    "object-with-parent-interface-with-two-part-name-from-the-same-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.samplepackage.SampleInterface.SampleNestedInterface",
                ),
                arguments(
                    "object-with-parent-class-with-two-part-name-from-the-same-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "com.samplepackage.SampleInterface.SampleNestedClass",
                ),
                arguments(
                    "object-with-parent-interface-with-two-part-name-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.lemonappdev.konsist.testdata.SampleParentInterfaceWithNestedDeclarations.SampleNestedInterface",
                ),
                arguments(
                    "object-with-parent-class-with-two-part-name-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "com.lemonappdev.konsist.testdata.SampleParentInterfaceWithNestedDeclarations.SampleNestedClass",
                ),
            )
    }
}
