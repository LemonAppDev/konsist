package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withoutModifiers
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.externalfixture.FixtureExternalClass
import com.lemonappdev.konsist.testdata.FixtureClass
import com.lemonappdev.konsist.testdata.FixtureInterface
import com.lemonappdev.konsist.testdata.FixtureObject
import com.lemonappdev.konsist.testdata.FixtureObjectWithInitializer
import com.lemonappdev.konsist.testdata.FixtureParentClassWithNestedDeclarations
import com.lemonappdev.konsist.testdata.FixtureType
import com.lemonappdev.konsist.testdata.testpackage.Type
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldNotBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.Date
import kotlin.reflect.KClass

@Suppress("detekt.LargeClass")
class KoTypeDeclarationForKoSourceDeclarationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    @Suppress("detekt.LongParameterList")
    fun `source declaration`(
        fileName: String,
        instanceOf: KClass<*>,
        notInstanceOf: KClass<*>,
        kClassOf: KClass<*>?,
        notKClassOf: KClass<*>,
        fullyQualifiedName: String?,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.sourceDeclaration shouldBeInstanceOf instanceOf
            it?.sourceDeclaration shouldNotBeInstanceOf notInstanceOf
            (it?.sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo fullyQualifiedName

            it
                ?.hasSourceDeclaration { declaration ->
                    (declaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName == fullyQualifiedName
                }?.shouldBeEqualTo(true)

            it?.hasSourceDeclaration { declaration -> declaration.hasNameEndingWith("Suffix") } shouldBeEqualTo false
            kClassOf?.let { kClass -> it?.hasSourceDeclarationOf(kClass) }?.shouldBeEqualTo(true)
            it?.hasSourceDeclarationOf(notKClassOf) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideNestedDeclarationsWithParentsWithoutFullyQualifiedName")
    fun `source declaration when nested declaration names are the same and parent has no fullyQualifiedName`(
        fileName: String,
        instanceOf: KClass<*>,
        notInstanceOf: KClass<*>,
        fullyQualifiedName: String?,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .functions()
                .last()
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf instanceOf
            sourceDeclaration shouldNotBeInstanceOf notInstanceOf
            (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo fullyQualifiedName

            it
                .hasSourceDeclaration { declaration ->
                    (declaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName == fullyQualifiedName
                }.shouldBeEqualTo(true)

            it.hasSourceDeclaration { declaration -> declaration.hasNameEndingWith("Suffix") } shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideNestedDeclarationsWithParentsWithFullyQualifiedName")
    fun `source declaration when nested declaration names are the same and parent has fullyQualifiedName`(
        fileName: String,
        instanceOf: KClass<*>,
        notInstanceOf: KClass<*>,
        fullyQualifiedName: String?,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .withoutModifiers()
                .last()
                .constructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf instanceOf
            sourceDeclaration shouldNotBeInstanceOf notInstanceOf
            (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo fullyQualifiedName

            it
                .hasSourceDeclaration { declaration ->
                    (declaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName == fullyQualifiedName
                }.shouldBeEqualTo(true)

            it.hasSourceDeclaration { declaration -> declaration.hasNameEndingWith("Suffix") } shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideNestedDeclarationsWithParentsWithoutFullyQualifiedNameDifferentCombinations")
    fun `source declaration when nested declaration names are the same and parent has no fullyQualifiedName - different combinations`(
        fileName: String,
        instanceOf: KClass<*>,
        notInstanceOf: KClass<*>,
        fullyQualifiedName: String?,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .functions()
                .last()
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf instanceOf
            sourceDeclaration shouldNotBeInstanceOf notInstanceOf
            (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo fullyQualifiedName

            it
                .hasSourceDeclaration { declaration ->
                    (declaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName == fullyQualifiedName
                }.shouldBeEqualTo(true)

            it.hasSourceDeclaration { declaration -> declaration.hasNameEndingWith("Suffix") } shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideNestedDeclarationsWithParentsWithFullyQualifiedNameDifferentCombinations")
    fun `source declaration when nested declaration names are the same and parent has fullyQualifiedName - different combinations`(
        fileName: String,
        instanceOf: KClass<*>,
        notInstanceOf: KClass<*>,
        fullyQualifiedName: String?,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .withoutModifiers()
                .last()
                .constructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf instanceOf
            sourceDeclaration shouldNotBeInstanceOf notInstanceOf
            (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo fullyQualifiedName

            it
                .hasSourceDeclaration { declaration ->
                    (declaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName == fullyQualifiedName
                }.shouldBeEqualTo(true)

            it.hasSourceDeclaration { declaration -> declaration.hasNameEndingWith("Suffix") } shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-type-parameter`() {
        // given
        val sut =
            getSnippetFile("nullable-type-parameter")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "TestType" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-type-parameter`() {
        // given
        val sut =
            getSnippetFile("not-nullable-type-parameter")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "TestType" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-function-type`() {
        // given
        val sut =
            getSnippetFile("nullable-function-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "(FixtureObject) -> Unit" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-function-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-function-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "(FixtureObject) -> Unit" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `star-projection-type`() {
        // given
        val sut =
            getSnippetFile("star-projection-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "*" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `type-with-name-being-suffix-of-other-declaration-name`() {
        // given
        val sut =
            getSnippetFile("type-with-name-being-suffix-of-other-declaration-name")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut?.sourceDeclaration) {
            it shouldBeInstanceOf KoExternalDeclaration::class
            (it as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo
                "com.lemonappdev.konsist.externalfixture.FixtureExternalClass"
        }
    }

    @Test
    fun `external types with the same name imported from different packages`() {
        // given
        val first =
            getSnippetFile("external-type-with-the-same-name-imported-from-first-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        val second =
            getSnippetFile("external-type-with-the-same-name-imported-from-second-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly {
            (first?.sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo "java.util.Timer"
            (second?.sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo "javax.swing.Timer"
        }
    }

    @Test
    fun `external-type-with-static-initializer-from-wildcard-import`() {
        // given
        val sut =
            getSnippetFile("external-type-with-static-initializer-from-wildcard-import")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly {
            (sut?.sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo
                "com.lemonappdev.konsist.testdata.FixtureObjectWithInitializer"
            System.getProperty(FixtureObjectWithInitializer.INITIALIZED_PROPERTY) shouldBeEqualTo null
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotype/snippet/forkosourcedeclarationprovider/",
            fileName,
        )

    companion object {
        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments(
                    "class-type-from-wildcard-import",
                    KoClassDeclaration::class,
                    KoExternalDeclaration::class,
                    FixtureClass::class,
                    String::class,
                    "com.lemonappdev.konsist.testdata.FixtureClass",
                ),
                arguments(
                    "nested-class-type-with-qualified-name-from-import",
                    KoClassDeclaration::class,
                    KoExternalDeclaration::class,
                    FixtureParentClassWithNestedDeclarations.FixtureNestedClass::class,
                    String::class,
                    "com.lemonappdev.konsist.testdata.FixtureParentClassWithNestedDeclarations.FixtureNestedClass",
                ),
                arguments(
                    "external-nested-type-from-default-import",
                    KoExternalDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    Thread.State::class,
                    String::class,
                    "java.lang.Thread.State",
                ),
                arguments(
                    "class-type-with-kotlin-type-name-from-the-same-file",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    null,
                    Result::class,
                    "com.fixturepackage.Result",
                ),
                arguments(
                    "class-type-with-kotlin-type-name-from-the-same-package",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    null,
                    Result::class,
                    "com.lemonappdev.konsist.testdata.Result",
                ),
                arguments(
                    "class-type-with-kotlin-type-name-from-wildcard-import",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    null,
                    Pair::class,
                    "com.lemonappdev.konsist.testdata.Pair",
                ),
                arguments(
                    "class-type-with-java-lang-type-name-from-the-same-package",
                    KoClassDeclaration::class,
                    KoExternalDeclaration::class,
                    null,
                    Module::class,
                    "com.lemonappdev.konsist.testdata.Module",
                ),
                arguments(
                    "class-type-from-the-same-package-and-wildcard-import",
                    KoClassDeclaration::class,
                    KoExternalDeclaration::class,
                    null,
                    Type::class,
                    "com.lemonappdev.konsist.testdata.Type",
                ),
                arguments(
                    "kotlin-collection-type-with-java-util-wildcard-import",
                    KoKotlinTypeDeclaration::class,
                    KoExternalDeclaration::class,
                    List::class,
                    String::class,
                    "kotlin.collections.List",
                ),
                arguments(
                    "kotlin-type-with-import-of-kotlin-function-with-the-same-name",
                    KoKotlinTypeDeclaration::class,
                    KoExternalDeclaration::class,
                    String::class,
                    Int::class,
                    "kotlin.String",
                ),
                arguments(
                    "external-type-with-kotlin-type-name-from-import",
                    KoExternalDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    Date::class,
                    String::class,
                    "java.util.Date",
                ),
                arguments(
                    "nullable-kotlin-type",
                    KoKotlinTypeDeclaration::class,
                    KoClassDeclaration::class,
                    String::class,
                    Int::class,
                    "kotlin.String",
                ),
                arguments(
                    "not-nullable-kotlin-type",
                    KoKotlinTypeDeclaration::class,
                    KoClassDeclaration::class,
                    String::class,
                    Int::class,
                    "kotlin.String",
                ),
                arguments(
                    "nullable-generic-type",
                    KoKotlinTypeDeclaration::class,
                    KoClassDeclaration::class,
                    List::class,
                    String::class,
                    "kotlin.collections.List",
                ),
                arguments(
                    "not-nullable-generic-type",
                    KoKotlinTypeDeclaration::class,
                    KoClassDeclaration::class,
                    List::class,
                    String::class,
                    "kotlin.collections.List",
                ),
                arguments(
                    "nullable-class-type",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    FixtureType::class,
                    String::class,
                    "com.lemonappdev.konsist.testdata.FixtureType",
                ),
                arguments(
                    "not-nullable-class-type",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    FixtureType::class,
                    String::class,
                    "com.lemonappdev.konsist.testdata.FixtureType",
                ),
                arguments(
                    "nullable-interface-type",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    FixtureInterface::class,
                    String::class,
                    "com.lemonappdev.konsist.testdata.FixtureInterface",
                ),
                arguments(
                    "not-nullable-interface-type",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    FixtureInterface::class,
                    String::class,
                    "com.lemonappdev.konsist.testdata.FixtureInterface",
                ),
                arguments(
                    "nullable-object-type",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    FixtureObject::class,
                    String::class,
                    "com.lemonappdev.konsist.testdata.FixtureObject",
                ),
                arguments(
                    "not-nullable-object-type",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    FixtureObject::class,
                    String::class,
                    "com.lemonappdev.konsist.testdata.FixtureObject",
                ),
                arguments(
                    "nullable-import-alias-type",
                    KoImportAliasDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    null,
                    String::class,
                    null,
                ),
                arguments(
                    "not-nullable-import-alias-type",
                    KoImportAliasDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    null,
                    String::class,
                    null,
                ),
                arguments(
                    "nullable-typealias-type",
                    KoTypeAliasDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    null,
                    String::class,
                    "com.lemonappdev.konsist.testdata.FixtureTypeAlias",
                ),
                arguments(
                    "not-nullable-typealias-type",
                    KoTypeAliasDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    null,
                    String::class,
                    "FixtureTypeAlias",
                ),
                arguments(
                    "nullable-external-type",
                    KoExternalDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    FixtureExternalClass::class,
                    String::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalClass",
                ),
                arguments(
                    "not-nullable-external-type",
                    KoExternalDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    FixtureExternalClass::class,
                    String::class,
                    "com.lemonappdev.konsist.externalfixture.FixtureExternalClass",
                ),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideNestedDeclarationsWithParentsWithoutFullyQualifiedName() =
            listOf(
                arguments(
                    "nullable-nested-class-type-with-the-same-name-and-parent-without-fqn",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.FixtureNestedClassWithTheSameName",
                ),
                arguments(
                    "not-nullable-nested-class-type-with-the-same-name-and-parent-without-fqn",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.FixtureNestedClassWithTheSameName",
                ),
                arguments(
                    "nullable-nested-interface-type-with-the-same-name-and-parent-without-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.FixtureNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "not-nullable-nested-interface-type-with-the-same-name-and-parent-without-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.FixtureNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nullable-nested-object-type-with-the-same-name-and-parent-without-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.FixtureNestedObjectWithTheSameName",
                ),
                arguments(
                    "not-nullable-nested-object-type-with-the-same-name-and-parent-without-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.FixtureNestedObjectWithTheSameName",
                ),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideNestedDeclarationsWithParentsWithFullyQualifiedName() =
            listOf(
                arguments(
                    "nullable-nested-class-type-with-the-same-name-and-parent-with-fqn",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.FixtureNestedClassWithTheSameName",
                ),
                arguments(
                    "not-nullable-nested-class-type-with-the-same-name-and-parent-with-fqn",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.FixtureNestedClassWithTheSameName",
                ),
                arguments(
                    "nullable-nested-interface-type-with-the-same-name-and-parent-with-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.FixtureNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "not-nullable-nested-interface-type-with-the-same-name-and-parent-with-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.FixtureNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nullable-nested-object-type-with-the-same-name-and-parent-with-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.FixtureNestedObjectWithTheSameName",
                ),
                arguments(
                    "not-nullable-nested-object-type-with-the-same-name-and-parent-with-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.FixtureNestedObjectWithTheSameName",
                ),
            )

        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideNestedDeclarationsWithParentsWithoutFullyQualifiedNameDifferentCombinations() =
            listOf(
                arguments(
                    "nested-class-type-with-the-same-name-and-parent-without-fqn-using-all-fqn",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.SecondInterface.FixtureNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-class-type-with-the-same-name-and-parent-without-fqn-using-part-of-fqn",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.SecondInterface.FixtureNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-class-type-with-the-same-name-and-parent-without-fqn-using-all-fqn-of-other-declaration",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.FirstInterface.FixtureNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-class-type-with-the-same-name-and-parent-without-fqn-using-part-of-fqn-of-other-declaration",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.FirstInterface.FixtureNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-without-fqn-using-all-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.SecondInterface.FixtureNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-without-fqn-using-part-of-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.SecondInterface.FixtureNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-without-fqn-using-all-fqn-of-other-declaration",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.FirstInterface.FixtureNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-without-fqn-using-part-of-fqn-of-other-declaration",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.FirstInterface.FixtureNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-without-fqn-using-all-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.SecondInterface.FixtureNestedObjectWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-without-fqn-using-part-of-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.SecondInterface.FixtureNestedObjectWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-without-fqn-using-all-fqn-of-other-declaration",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.FirstInterface.FixtureNestedObjectWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-without-fqn-using-part-of-fqn-of-other-declaration",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.FirstInterface.FixtureNestedObjectWithTheSameName",
                ),
            )

        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideNestedDeclarationsWithParentsWithFullyQualifiedNameDifferentCombinations() =
            listOf(
                arguments(
                    "nested-class-type-with-the-same-name-and-parent-with-fqn-using-all-fqn",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.SecondInterface.FixtureNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-class-type-with-the-same-name-and-parent-with-fqn-using-part-of-fqn",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.SecondInterface.FixtureNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-class-type-with-the-same-name-and-parent-with-fqn-using-all-fqn-of-other-declaration",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.FirstInterface.FixtureNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-class-type-with-the-same-name-and-parent-with-fqn-using-part-of-fqn-of-other-declaration",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.FirstInterface.FixtureNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-with-fqn-using-all-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.SecondInterface.FixtureNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-with-fqn-using-part-of-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.SecondInterface.FixtureNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-with-fqn-using-all-fqn-of-other-declaration",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.FirstInterface.FixtureNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-with-fqn-using-part-of-fqn-of-other-declaration",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.FirstInterface.FixtureNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-with-fqn-using-all-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.SecondInterface.FixtureNestedObjectWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-with-fqn-using-part-of-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.SecondInterface.FixtureNestedObjectWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-with-fqn-using-all-fqn-of-other-declaration",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.FirstInterface.FixtureNestedObjectWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-with-fqn-using-part-of-fqn-of-other-declaration",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.fixturepackage.FirstInterface.FixtureNestedObjectWithTheSameName",
                ),
            )
    }
}
