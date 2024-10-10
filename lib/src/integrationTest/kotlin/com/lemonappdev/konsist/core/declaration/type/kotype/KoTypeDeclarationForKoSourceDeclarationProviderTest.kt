package com.lemonappdev.konsist.core.declaration.type.kotype

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
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withoutModifiers
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.externalsample.SampleExternalClass
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleObject
import com.lemonappdev.konsist.testdata.SampleType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldNotBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
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
        fqn: String?,
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
            (it?.sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo fqn

            it
                ?.hasSourceDeclaration { declaration ->
                    (declaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName == fqn
                }?.shouldBeEqualTo(true)

            it?.hasSourceDeclaration { declaration -> declaration.hasNameEndingWith("Suffix") } shouldBeEqualTo false
            kClassOf?.let { kClass -> it?.hasSourceDeclarationOf(kClass) }?.shouldBeEqualTo(true)
            it?.hasSourceDeclarationOf(notKClassOf) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideNestedDeclarationsWithParentsWithoutFullyQualifiedName")
    fun `source declaration when nested declaration names are the same and parent has no fqn`(
        fileName: String,
        instanceOf: KClass<*>,
        notInstanceOf: KClass<*>,
        fqn: String?,
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
            (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo fqn

            it
                .hasSourceDeclaration { declaration ->
                    (declaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName == fqn
                }.shouldBeEqualTo(true)

            it.hasSourceDeclaration { declaration -> declaration.hasNameEndingWith("Suffix") } shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideNestedDeclarationsWithParentsWithFullyQualifiedName")
    fun `source declaration when nested declaration names are the same and parent has fqn`(
        fileName: String,
        instanceOf: KClass<*>,
        notInstanceOf: KClass<*>,
        fqn: String?,
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
            (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo fqn

            it
                .hasSourceDeclaration { declaration ->
                    (declaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName == fqn
                }.shouldBeEqualTo(true)

            it.hasSourceDeclaration { declaration -> declaration.hasNameEndingWith("Suffix") } shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideNestedDeclarationsWithParentsWithoutFullyQualifiedNameDifferentCombinations")
    fun `source declaration when nested declaration names are the same and parent has no fqn - different combinations`(
        fileName: String,
        instanceOf: KClass<*>,
        notInstanceOf: KClass<*>,
        fqn: String?,
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
            (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo fqn

            it
                .hasSourceDeclaration { declaration ->
                    (declaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName == fqn
                }.shouldBeEqualTo(true)

            it.hasSourceDeclaration { declaration -> declaration.hasNameEndingWith("Suffix") } shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideNestedDeclarationsWithParentsWithFullyQualifiedNameDifferentCombinations")
    fun `source declaration when nested declaration names are the same and parent has fqn - different combinations`(
        fileName: String,
        instanceOf: KClass<*>,
        notInstanceOf: KClass<*>,
        fqn: String?,
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
            (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo fqn

            it
                .hasSourceDeclaration { declaration ->
                    (declaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName == fqn
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
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration

        // then
        assertSoftly(sut) {
//            it?.hasSourceDeclaration { declaration -> declaration.name == "*" } shouldBeEqualTo true
//            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
//            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo false
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
                    KoGenericTypeDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    String::class,
                    null,
                ),
                arguments(
                    "not-nullable-generic-type",
                    KoGenericTypeDeclaration::class,
                    KoClassDeclaration::class,
                    null,
                    String::class,
                    null,
                ),
                arguments(
                    "nullable-class-type",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    SampleType::class,
                    String::class,
                    "com.lemonappdev.konsist.testdata.SampleType",
                ),
                arguments(
                    "not-nullable-class-type",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    SampleType::class,
                    String::class,
                    "com.lemonappdev.konsist.testdata.SampleType",
                ),
                arguments(
                    "nullable-interface-type",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    SampleInterface::class,
                    String::class,
                    "com.lemonappdev.konsist.testdata.SampleInterface",
                ),
                arguments(
                    "not-nullable-interface-type",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    SampleInterface::class,
                    String::class,
                    "com.lemonappdev.konsist.testdata.SampleInterface",
                ),
                arguments(
                    "nullable-object-type",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    SampleObject::class,
                    String::class,
                    "com.lemonappdev.konsist.testdata.SampleObject",
                ),
                arguments(
                    "not-nullable-object-type",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    SampleObject::class,
                    String::class,
                    "com.lemonappdev.konsist.testdata.SampleObject",
                ),
                arguments(
                    "nullable-function-type",
                    KoFunctionTypeDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    null,
                    String::class,
                    null,
                ),
                arguments(
                    "not-nullable-function-type",
                    KoFunctionTypeDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    null,
                    String::class,
                    null,
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
                    "com.lemonappdev.konsist.testdata.SampleTypeAlias",
                ),
                arguments(
                    "not-nullable-typealias-type",
                    KoTypeAliasDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    null,
                    String::class,
                    "SampleTypeAlias",
                ),
                arguments(
                    "nullable-external-type",
                    KoExternalDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    SampleExternalClass::class,
                    String::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalClass",
                ),
                arguments(
                    "not-nullable-external-type",
                    KoExternalDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    SampleExternalClass::class,
                    String::class,
                    "com.lemonappdev.konsist.externalsample.SampleExternalClass",
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
                    "SecondInterface.SampleNestedClassWithTheSameName",
                ),
                arguments(
                    "not-nullable-nested-class-type-with-the-same-name-and-parent-without-fqn",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.SampleNestedClassWithTheSameName",
                ),
                arguments(
                    "nullable-nested-interface-type-with-the-same-name-and-parent-without-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.SampleNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "not-nullable-nested-interface-type-with-the-same-name-and-parent-without-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.SampleNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nullable-nested-object-type-with-the-same-name-and-parent-without-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.SampleNestedObjectWithTheSameName",
                ),
                arguments(
                    "not-nullable-nested-object-type-with-the-same-name-and-parent-without-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.SampleNestedObjectWithTheSameName",
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
                    "SecondInterface.SampleNestedClassWithTheSameName",
                ),
                arguments(
                    "not-nullable-nested-class-type-with-the-same-name-and-parent-with-fqn",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.SampleNestedClassWithTheSameName",
                ),
                arguments(
                    "nullable-nested-interface-type-with-the-same-name-and-parent-with-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.SampleNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "not-nullable-nested-interface-type-with-the-same-name-and-parent-with-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.SampleNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nullable-nested-object-type-with-the-same-name-and-parent-with-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.SampleNestedObjectWithTheSameName",
                ),
                arguments(
                    "not-nullable-nested-object-type-with-the-same-name-and-parent-with-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "SecondInterface.SampleNestedObjectWithTheSameName",
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
                    "com.samplepackage.SecondInterface.SampleNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-class-type-with-the-same-name-and-parent-without-fqn-using-part-of-fqn",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.SecondInterface.SampleNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-class-type-with-the-same-name-and-parent-without-fqn-using-all-fqn-of-other-declaration",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.FirstInterface.SampleNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-class-type-with-the-same-name-and-parent-without-fqn-using-part-of-fqn-of-other-declaration",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.FirstInterface.SampleNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-without-fqn-using-all-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.SecondInterface.SampleNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-without-fqn-using-part-of-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.SecondInterface.SampleNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-without-fqn-using-all-fqn-of-other-declaration",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.FirstInterface.SampleNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-without-fqn-using-part-of-fqn-of-other-declaration",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.FirstInterface.SampleNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-without-fqn-using-all-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.SecondInterface.SampleNestedObjectWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-without-fqn-using-part-of-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.SecondInterface.SampleNestedObjectWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-without-fqn-using-all-fqn-of-other-declaration",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.FirstInterface.SampleNestedObjectWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-without-fqn-using-part-of-fqn-of-other-declaration",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.FirstInterface.SampleNestedObjectWithTheSameName",
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
                    "com.samplepackage.SecondInterface.SampleNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-class-type-with-the-same-name-and-parent-with-fqn-using-part-of-fqn",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.SecondInterface.SampleNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-class-type-with-the-same-name-and-parent-with-fqn-using-all-fqn-of-other-declaration",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.FirstInterface.SampleNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-class-type-with-the-same-name-and-parent-with-fqn-using-part-of-fqn-of-other-declaration",
                    KoClassDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.FirstInterface.SampleNestedClassWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-with-fqn-using-all-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.SecondInterface.SampleNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-with-fqn-using-part-of-fqn",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.SecondInterface.SampleNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-with-fqn-using-all-fqn-of-other-declaration",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.FirstInterface.SampleNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-interface-type-with-the-same-name-and-parent-with-fqn-using-part-of-fqn-of-other-declaration",
                    KoInterfaceDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.FirstInterface.SampleNestedInterfaceWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-with-fqn-using-all-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.SecondInterface.SampleNestedObjectWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-with-fqn-using-part-of-fqn",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.SecondInterface.SampleNestedObjectWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-with-fqn-using-all-fqn-of-other-declaration",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.FirstInterface.SampleNestedObjectWithTheSameName",
                ),
                arguments(
                    "nested-object-type-with-the-same-name-and-parent-with-fqn-using-part-of-fqn-of-other-declaration",
                    KoObjectDeclaration::class,
                    KoKotlinTypeDeclaration::class,
                    "com.samplepackage.FirstInterface.SampleNestedObjectWithTheSameName",
                ),
            )
    }
}
