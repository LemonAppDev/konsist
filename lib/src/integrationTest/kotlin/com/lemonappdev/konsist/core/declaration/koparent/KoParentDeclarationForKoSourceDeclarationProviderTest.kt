package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.ext.list.parents
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.externalfixture.FixtureExternalClass
import com.lemonappdev.konsist.externalfixture.FixtureExternalClassWithParameter
import com.lemonappdev.konsist.externalfixture.FixtureExternalGenericClass
import com.lemonappdev.konsist.externalfixture.FixtureExternalGenericClassWithParameter
import com.lemonappdev.konsist.externalfixture.FixtureExternalGenericInterface
import com.lemonappdev.konsist.externalfixture.FixtureExternalInterface
import com.lemonappdev.konsist.testdata.FixtureClassWithParameter
import com.lemonappdev.konsist.testdata.FixtureCollection1
import com.lemonappdev.konsist.testdata.FixtureGenericClassWithParameter
import com.lemonappdev.konsist.testdata.FixtureGenericSuperInterface
import com.lemonappdev.konsist.testdata.FixtureInterface
import com.lemonappdev.konsist.testdata.FixtureParentClass
import com.lemonappdev.konsist.testdata.FixtureParentInterface
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
                (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName == "com.fixturepackage.other"
            }.shouldBeEqualTo(false)
            kClass
                ?.let { value -> hasSourceDeclarationOf(value) }
                ?.shouldBeEqualTo(true)
            hasSourceDeclarationOf(Char::class) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideInterfacesForSourceDeclaration")
    fun `interface-parent-has-source-de claration`(
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
                (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName == "com.fixturepackage.other"
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
                (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName == "com.fixturepackage.other"
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
                    "FixtureSuperClass",
                ),
                arguments(
                    "class-with-generic-parent-class-from-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "FixtureGenericSuperClass",
                ),
                arguments(
                    "class-with-parametrized-parent-class-from-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "FixtureParametrizedSuperClass",
                ),
                arguments(
                    "class-with-parametrized-and-generic-parent-class-from-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "FixtureParametrizedSuperClass",
                ),
                arguments(
                    "class-with-parent-interface-from-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "FixtureSuperInterface",
                ),
                arguments(
                    "class-with-generic-parent-interface-from-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "FixtureGenericSuperInterface",
                ),
                arguments(
                    "class-with-parent-by-delegation-from-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "FixtureSuperInterface",
                ),
                arguments(
                    "class-with-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureParentClass::class,
                    "com.lemonappdev.konsist.testdata.FixtureParentClass",
                ),
                arguments(
                    "class-with-generic-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureCollection1::class,
                    "com.lemonappdev.konsist.testdata.FixtureCollection1",
                ),
                arguments(
                    "class-with-parametrized-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureClassWithParameter::class,
                    "com.lemonappdev.konsist.testdata.FixtureClassWithParameter",
                ),
                arguments(
                    "class-with-parametrized-and-generic-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureGenericClassWithParameter::class,
                    "com.lemonappdev.konsist.testdata.FixtureGenericClassWithParameter",
                ),
                arguments(
                    "class-with-parent-interface-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    FixtureInterface::class,
                    "com.lemonappdev.konsist.testdata.FixtureInterface",
                ),
                arguments(
                    "class-with-generic-parent-interface-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    FixtureGenericSuperInterface::class,
                    "com.lemonappdev.konsist.testdata.FixtureGenericSuperInterface",
                ),
                arguments(
                    "class-with-parent-by-delegation-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    FixtureInterface::class,
                    "com.lemonappdev.konsist.testdata.FixtureInterface",
                ),
                arguments(
                    "class-with-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureExternalClass::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalClass",
                ),
                arguments(
                    "class-with-generic-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureExternalGenericClass::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalGenericClass",
                ),
                arguments(
                    "class-with-parametrized-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureExternalClassWithParameter::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalClassWithParameter",
                ),
                arguments(
                    "class-with-parametrized-and-generic-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureExternalGenericClassWithParameter::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalGenericClassWithParameter",
                ),
                arguments(
                    "class-with-external-parent-interface",
                    KoExternalDeclaration::class,
                    KoClassDeclaration::class,
                    FixtureExternalInterface::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalInterface",
                ),
                arguments(
                    "class-with-generic-external-parent-interface",
                    KoExternalDeclaration::class,
                    KoClassDeclaration::class,
                    FixtureExternalGenericInterface::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalGenericInterface",
                ),
                arguments(
                    "class-with-external-parent-by-delegation",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureExternalInterface::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalInterface",
                ),
                arguments(
                    "class-with-typealias-parent",
                    KoTypeAliasDeclaration::class,
                    KoImportAliasDeclaration::class,
                    null,
                    "FixtureTypeAlias",
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
                    "com.fixturepackage.FixtureInterface.FixtureName",
                ),
                arguments(
                    "class-with-parent-class-with-the-same-name",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "com.fixturepackage.FixtureInterface.FixtureName",
                ),
                arguments(
                    "class-with-parent-interface-with-two-part-name-from-the-same-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.fixturepackage.FixtureInterface.FixtureNestedInterface",
                ),
                arguments(
                    "class-with-parent-class-with-two-part-name-from-the-same-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "com.fixturepackage.FixtureInterface.FixtureNestedClass",
                ),
                arguments(
                    "class-with-parent-interface-with-two-part-name-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.lemonappdev.konsist.testdata.FixtureParentInterfaceWithNestedDeclarations.FixtureNestedInterface",
                ),
                arguments(
                    "class-with-parent-class-with-two-part-name-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "com.lemonappdev.konsist.testdata.FixtureParentInterfaceWithNestedDeclarations.FixtureNestedClass",
                ),
                arguments(
                    "class-with-kotlin-parent-class",
                    KoKotlinTypeDeclaration::class,
                    KoInterfaceDeclaration::class,
                    Throwable::class,
                    "kotlin.Throwable",
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
                    "FixtureSuperInterface",
                ),
                arguments(
                    "interface-with-generic-parent-interface-from-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "FixtureGenericSuperInterface",
                ),
                arguments(
                    "interface-with-parent-interface-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    FixtureParentInterface::class,
                    "com.lemonappdev.konsist.testdata.FixtureParentInterface",
                ),
                arguments(
                    "interface-with-generic-parent-interface-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    FixtureGenericSuperInterface::class,
                    "com.lemonappdev.konsist.testdata.FixtureGenericSuperInterface",
                ),
                arguments(
                    "interface-with-external-parent-interface",
                    KoExternalDeclaration::class,
                    KoClassDeclaration::class,
                    FixtureExternalInterface::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalInterface",
                ),
                arguments(
                    "interface-with-generic-external-parent-interface",
                    KoExternalDeclaration::class,
                    KoClassDeclaration::class,
                    FixtureExternalGenericInterface::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalGenericInterface",
                ),
                arguments(
                    "interface-with-typealias-parent",
                    KoTypeAliasDeclaration::class,
                    KoImportAliasDeclaration::class,
                    null,
                    "FixtureTypeAlias",
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
                    "com.fixturepackage.FixtureInterface.FixtureName",
                ),
                arguments(
                    "interface-with-parent-interface-with-two-part-name-from-the-same-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.fixturepackage.FixtureInterface.FixtureNestedInterface",
                ),
                arguments(
                    "interface-with-parent-interface-with-two-part-name-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.lemonappdev.konsist.testdata.FixtureParentInterfaceWithNestedDeclarations.FixtureNestedInterface",
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
                    "FixtureSuperClass",
                ),
                arguments(
                    "object-with-generic-parent-class-from-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "FixtureGenericSuperClass",
                ),
                arguments(
                    "object-with-parametrized-parent-class-from-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "FixtureParametrizedSuperClass",
                ),
                arguments(
                    "object-with-parametrized-and-generic-parent-class-from-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "FixtureParametrizedSuperClass",
                ),
                arguments(
                    "object-with-parent-interface-from-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "FixtureSuperInterface",
                ),
                arguments(
                    "object-with-generic-parent-interface-from-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "FixtureGenericSuperInterface",
                ),
                arguments(
                    "object-with-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureParentClass::class,
                    "com.lemonappdev.konsist.testdata.FixtureParentClass",
                ),
                arguments(
                    "object-with-generic-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureCollection1::class,
                    "com.lemonappdev.konsist.testdata.FixtureCollection1",
                ),
                arguments(
                    "object-with-parametrized-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureClassWithParameter::class,
                    "com.lemonappdev.konsist.testdata.FixtureClassWithParameter",
                ),
                arguments(
                    "object-with-parametrized-and-generic-parent-class-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureGenericClassWithParameter::class,
                    "com.lemonappdev.konsist.testdata.FixtureGenericClassWithParameter",
                ),
                arguments(
                    "object-with-parent-interface-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    FixtureInterface::class,
                    "com.lemonappdev.konsist.testdata.FixtureInterface",
                ),
                arguments(
                    "object-with-generic-parent-interface-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    FixtureGenericSuperInterface::class,
                    "com.lemonappdev.konsist.testdata.FixtureGenericSuperInterface",
                ),
                arguments(
                    "object-with-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureExternalClass::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalClass",
                ),
                arguments(
                    "object-with-generic-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureExternalGenericClass::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalGenericClass",
                ),
                arguments(
                    "object-with-parametrized-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureExternalClassWithParameter::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalClassWithParameter",
                ),
                arguments(
                    "object-with-parametrized-and-generic-external-parent-class",
                    KoExternalDeclaration::class,
                    KoInterfaceDeclaration::class,
                    FixtureExternalGenericClassWithParameter::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalGenericClassWithParameter",
                ),
                arguments(
                    "object-with-external-parent-interface",
                    KoExternalDeclaration::class,
                    KoClassDeclaration::class,
                    FixtureExternalInterface::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalInterface",
                ),
                arguments(
                    "object-with-generic-external-parent-interface",
                    KoExternalDeclaration::class,
                    KoClassDeclaration::class,
                    FixtureExternalGenericInterface::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalGenericInterface",
                ),
                arguments(
                    "object-with-typealias-parent",
                    KoTypeAliasDeclaration::class,
                    KoImportAliasDeclaration::class,
                    null,
                    "FixtureTypeAlias",
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
                    "com.fixturepackage.FixtureInterface.FixtureName",
                ),
                arguments(
                    "object-with-parent-class-with-the-same-name",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "com.fixturepackage.FixtureInterface.FixtureName",
                ),
                arguments(
                    "object-with-parent-interface-with-two-part-name-from-the-same-file",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.fixturepackage.FixtureInterface.FixtureNestedInterface",
                ),
                arguments(
                    "object-with-parent-class-with-two-part-name-from-the-same-file",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "com.fixturepackage.FixtureInterface.FixtureNestedClass",
                ),
                arguments(
                    "object-with-parent-interface-with-two-part-name-from-import",
                    KoInterfaceDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    "com.lemonappdev.konsist.testdata.FixtureParentInterfaceWithNestedDeclarations.FixtureNestedInterface",
                ),
                arguments(
                    "object-with-parent-class-with-two-part-name-from-import",
                    KoClassDeclaration::class,
                    KoInterfaceDeclaration::class,
                    null,
                    "com.lemonappdev.konsist.testdata.FixtureParentInterfaceWithNestedDeclarations.FixtureNestedClass",
                ),
                arguments(
                    "object-with-kotlin-parent-class",
                    KoKotlinTypeDeclaration::class,
                    KoInterfaceDeclaration::class,
                    Throwable::class,
                    "kotlin.Throwable",
                ),
            )
    }
}
