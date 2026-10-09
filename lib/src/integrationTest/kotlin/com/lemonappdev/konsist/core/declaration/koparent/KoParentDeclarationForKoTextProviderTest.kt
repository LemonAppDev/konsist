package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoParentDeclarationForKoTextProviderTest {
    @ParameterizedTest
    @MethodSource("provideClasses")
    fun `class-parent-has-text`(
        fileName: String,
        text: String,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .parents()
                .first()

        // then
        sut.text shouldBeEqualTo text
    }

    @ParameterizedTest
    @MethodSource("provideInterfaces")
    fun `interface-parent-has-text`(
        fileName: String,
        text: String,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .interfaces()
                .parents()
                .first()

        // then
        sut.text shouldBeEqualTo text
    }

    @ParameterizedTest
    @MethodSource("provideObjects")
    fun `object-parent-has-text`(
        fileName: String,
        text: String,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .objects()
                .parents()
                .first()

        // then
        sut.text shouldBeEqualTo text
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparent/snippet/forkotextprovider/", fileName)

    companion object {
        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideClasses() =
            listOf(
                arguments("class-with-parent-class-from-file", "FixtureSuperClass()"),
                arguments("class-with-generic-parent-class-from-file", "FixtureGenericSuperClass<Int>()"),
                arguments("class-with-parametrized-parent-class-from-file", "FixtureParametrizedSuperClass(\"param\")"),
                arguments(
                    "class-with-parametrized-and-generic-parent-class-from-file",
                    "FixtureParametrizedSuperClass<Int>(\"param\")",
                ),
                arguments("class-with-parent-interface-from-file", "FixtureSuperInterface"),
                arguments("class-with-generic-parent-interface-from-file", "FixtureGenericSuperInterface<Int>"),
                arguments("class-with-parent-by-delegation-from-file", "FixtureSuperInterface by fixtureProperty"),
                arguments(
                    "class-with-multiline-parent-from-file",
                    """
                    SomeParentClass(
                        "some parent constructor param here"
                    )
                    """.trimIndent(),
                ),
                arguments("class-with-parent-class-from-import", "FixtureParentClass()"),
                arguments("class-with-generic-parent-class-from-import", "FixtureCollection1<Int>()"),
                arguments("class-with-parametrized-parent-class-from-import", "FixtureClassWithParameter(\"param\")"),
                arguments(
                    "class-with-parametrized-and-generic-parent-class-from-import",
                    "FixtureGenericClassWithParameter<Int>(\"param\")",
                ),
                arguments("class-with-parent-interface-from-import", "FixtureInterface"),
                arguments("class-with-generic-parent-interface-from-import", "FixtureGenericSuperInterface<Int>"),
                arguments("class-with-parent-by-delegation-from-import", "FixtureInterface by fixtureProperty"),
                arguments(
                    "class-with-multiline-parent-from-import",
                    """
                    FixtureClassWithParameter(
                        "some parent constructor param here"
                    )
                    """.trimIndent(),
                ),
                arguments("class-with-external-parent-class", "FixtureExternalClass()"),
                arguments("class-with-generic-external-parent-class", "FixtureExternalGenericClass<Int>()"),
                arguments(
                    "class-with-parametrized-external-parent-class",
                    "FixtureExternalClassWithParameter(\"param\")",
                ),
                arguments(
                    "class-with-parametrized-and-generic-external-parent-class",
                    "FixtureExternalGenericClassWithParameter<Int>(\"param\")",
                ),
                arguments("class-with-external-parent-interface", "FixtureExternalInterface"),
                arguments("class-with-generic-external-parent-interface", "FixtureExternalGenericInterface<Int>"),
                arguments("class-with-external-parent-by-delegation", "FixtureExternalInterface by fixtureProperty"),
                arguments(
                    "class-with-multiline-external-parent",
                    """
                    FixtureExternalClassWithParameter(
                        "some parent constructor param here"
                    )
                    """.trimIndent(),
                ),
                arguments("class-with-typealias-parent", "FixtureTypeAlias()"),
                arguments("class-with-import-alias-parent", "FixtureImportAlias()"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfaces() =
            listOf(
                arguments("interface-with-parent-interface-from-file", "FixtureSuperInterface"),
                arguments("interface-with-generic-parent-interface-from-file", "FixtureGenericSuperInterface<Int>"),
                arguments("interface-with-parent-interface-from-import", "FixtureParentInterface"),
                arguments("interface-with-generic-parent-interface-from-import", "FixtureGenericSuperInterface<Int>"),
                arguments("interface-with-external-parent-interface", "FixtureExternalInterface"),
                arguments("interface-with-generic-external-parent-interface", "FixtureExternalGenericInterface<Int>"),
                arguments("interface-with-typealias-parent", "FixtureTypeAlias"),
                arguments("interface-with-import-alias-parent", "FixtureImportAlias"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideObjects() =
            listOf(
                arguments("object-with-parent-class-from-file", "FixtureSuperClass()"),
                arguments("object-with-generic-parent-class-from-file", "FixtureGenericSuperClass<Int>()"),
                arguments("object-with-parametrized-parent-class-from-file", "FixtureParametrizedSuperClass(\"param\")"),
                arguments(
                    "object-with-parametrized-and-generic-parent-class-from-file",
                    "FixtureParametrizedSuperClass<Int>(\"param\")",
                ),
                arguments("object-with-parent-interface-from-file", "FixtureSuperInterface"),
                arguments("object-with-generic-parent-interface-from-file", "FixtureGenericSuperInterface<Int>"),
                arguments(
                    "object-with-multiline-parent-from-file",
                    """
                    SomeParentClass(
                        "some parent constructor param here"
                    )
                    """.trimIndent(),
                ),
                arguments("object-with-parent-class-from-import", "FixtureParentClass()"),
                arguments("object-with-generic-parent-class-from-import", "FixtureCollection1<Int>()"),
                arguments("object-with-parametrized-parent-class-from-import", "FixtureClassWithParameter(\"param\")"),
                arguments(
                    "object-with-parametrized-and-generic-parent-class-from-import",
                    "FixtureGenericClassWithParameter<Int>(\"param\")",
                ),
                arguments("object-with-parent-interface-from-import", "FixtureInterface"),
                arguments("object-with-generic-parent-interface-from-import", "FixtureGenericSuperInterface<Int>"),
                arguments(
                    "object-with-multiline-parent-from-import",
                    """
                    FixtureClassWithParameter(
                        "some parent constructor param here"
                    )
                    """.trimIndent(),
                ),
                arguments("object-with-external-parent-class", "FixtureExternalClass()"),
                arguments("object-with-generic-external-parent-class", "FixtureExternalGenericClass<Int>()"),
                arguments(
                    "object-with-parametrized-external-parent-class",
                    "FixtureExternalClassWithParameter(\"param\")",
                ),
                arguments(
                    "object-with-parametrized-and-generic-external-parent-class",
                    "FixtureExternalGenericClassWithParameter<Int>(\"param\")",
                ),
                arguments("object-with-external-parent-interface", "FixtureExternalInterface"),
                arguments("object-with-generic-external-parent-interface", "FixtureExternalGenericInterface<Int>"),
                arguments(
                    "object-with-multiline-external-parent",
                    """
                    FixtureExternalClassWithParameter(
                        "some parent constructor param here"
                    )
                    """.trimIndent(),
                ),
                arguments("object-with-typealias-parent", "FixtureTypeAlias()"),
                arguments("object-with-import-alias-parent", "FixtureImportAlias()"),
            )
    }
}
