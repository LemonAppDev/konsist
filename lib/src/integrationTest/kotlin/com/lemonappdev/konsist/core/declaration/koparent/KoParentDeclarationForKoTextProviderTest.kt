package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoParentDeclarationForKoTextProviderTest {
    @ParameterizedTest
    @MethodSource("provideClasses")
    fun `class-parent-has-text`(fileName: String, text: String) {
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
    fun `interface-parent-has-text`(fileName: String, text: String) {
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
    fun `object-parent-has-text`(fileName: String, text: String) {
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
        @Suppress("unused")
        @JvmStatic
        fun provideClasses() =
            listOf(
                arguments("class-with-parent-class-from-file", "SampleSuperClass()"),
                arguments("class-with-generic-parent-class-from-file", "SampleGenericSuperClass<Int>()"),
                arguments("class-with-parametrized-parent-class-from-file", "SampleParametrizedSuperClass(\"param\")"),
                arguments(
                    "class-with-parametrized-and-generic-parent-class-from-file",
                    "SampleParametrizedSuperClass<Int>(\"param\")"
                ),
                arguments("class-with-parent-interface-from-file", "SampleSuperInterface"),
                arguments("class-with-generic-parent-interface-from-file", "SampleGenericSuperInterface<Int>"),
                arguments("class-with-parent-by-delegation-from-file", "SampleSuperInterface by sampleProperty"),
                arguments(
                    "class-with-multiline-parent-from-file",
                    """
            SomeParentClass(
                "some parent constructor param here"
            )
            """.trimIndent()
                ),
                arguments("class-with-parent-class-from-import", "SampleParentClass()"),
                arguments("class-with-generic-parent-class-from-import", "SampleCollection1<Int>()"),
                arguments("class-with-parametrized-parent-class-from-import", "SampleClassWithParameter(\"param\")"),
                arguments(
                    "class-with-parametrized-and-generic-parent-class-from-import",
                    "SampleGenericClassWithParameter<Int>(\"param\")"
                ),
                arguments("class-with-parent-interface-from-import", "SampleInterface"),
                arguments("class-with-generic-parent-interface-from-import", "SampleGenericSuperInterface<Int>"),
                arguments("class-with-parent-by-delegation-from-import", "SampleInterface by sampleProperty"),
                arguments(
                    "class-with-multiline-parent-from-import",
                    """
            SampleClassWithParameter(
                "some parent constructor param here"
            )
            """.trimIndent()
                ),
                arguments("class-with-external-parent-class", "SampleExternalClass()"),
                arguments("class-with-generic-external-parent-class", "SampleExternalGenericClass<Int>()"),
                arguments(
                    "class-with-parametrized-external-parent-class",
                    "SampleExternalClassWithParameter(\"param\")"
                ),
                arguments(
                    "class-with-parametrized-and-generic-external-parent-class",
                    "SampleExternalGenericClassWithParameter<Int>(\"param\")"
                ),
                arguments("class-with-external-parent-interface", "SampleExternalInterface"),
                arguments("class-with-generic-external-parent-interface", "SampleExternalGenericInterface<Int>"),
                arguments("class-with-external-parent-by-delegation", "SampleExternalInterface by sampleProperty"),
                arguments(
                    "class-with-multiline-external-parent",
                    """
            SampleExternalClassWithParameter(
                "some parent constructor param here"
            )
            """.trimIndent()
                ),
                arguments("class-with-typealias-parent", "SampleTypeAlias()"),
                arguments("class-with-import-alias-parent", "SampleImportAlias()"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfaces() =
            listOf(
                arguments("interface-with-parent-interface-from-file", "SampleSuperInterface"),
                arguments("interface-with-generic-parent-interface-from-file", "SampleGenericSuperInterface<Int>"),
                arguments("interface-with-parent-interface-from-import", "SampleParentInterface"),
                arguments("interface-with-generic-parent-interface-from-import", "SampleGenericSuperInterface<Int>"),
                arguments("interface-with-external-parent-interface", "SampleExternalInterface"),
                arguments("interface-with-generic-external-parent-interface", "SampleExternalGenericInterface<Int>"),
                arguments("interface-with-typealias-parent", "SampleTypeAlias"),
                arguments("interface-with-import-alias-parent", "SampleImportAlias"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideObjects() =
            listOf(
                arguments("object-with-parent-class-from-file", "SampleSuperClass()"),
                arguments("object-with-generic-parent-class-from-file", "SampleGenericSuperClass<Int>()"),
                arguments("object-with-parametrized-parent-class-from-file", "SampleParametrizedSuperClass(\"param\")"),
                arguments(
                    "object-with-parametrized-and-generic-parent-class-from-file",
                    "SampleParametrizedSuperClass<Int>(\"param\")"
                ),
                arguments("object-with-parent-interface-from-file", "SampleSuperInterface"),
                arguments("object-with-generic-parent-interface-from-file", "SampleGenericSuperInterface<Int>"),
                arguments(
                    "object-with-multiline-parent-from-file",
                    """
            SomeParentClass(
                "some parent constructor param here"
            )
            """.trimIndent()
                ),
                arguments("object-with-parent-class-from-import", "SampleParentClass()"),
                arguments("object-with-generic-parent-class-from-import", "SampleCollection1<Int>()"),
                arguments("object-with-parametrized-parent-class-from-import", "SampleClassWithParameter(\"param\")"),
                arguments(
                    "object-with-parametrized-and-generic-parent-class-from-import",
                    "SampleGenericClassWithParameter<Int>(\"param\")"
                ),
                arguments("object-with-parent-interface-from-import", "SampleInterface"),
                arguments("object-with-generic-parent-interface-from-import", "SampleGenericSuperInterface<Int>"),
                arguments(
                    "object-with-multiline-parent-from-import",
                    """
            SampleClassWithParameter(
                "some parent constructor param here"
            )
            """.trimIndent()
                ),
                arguments("object-with-external-parent-class", "SampleExternalClass()"),
                arguments("object-with-generic-external-parent-class", "SampleExternalGenericClass<Int>()"),
                arguments(
                    "object-with-parametrized-external-parent-class",
                    "SampleExternalClassWithParameter(\"param\")"
                ),
                arguments(
                    "object-with-parametrized-and-generic-external-parent-class",
                    "SampleExternalGenericClassWithParameter<Int>(\"param\")"
                ),
                arguments("object-with-external-parent-interface", "SampleExternalInterface"),
                arguments("object-with-generic-external-parent-interface", "SampleExternalGenericInterface<Int>"),
                arguments(
                    "object-with-multiline-external-parent",
                    """
            SampleExternalClassWithParameter(
                "some parent constructor param here"
            )
            """.trimIndent()
                ),
                arguments("object-with-typealias-parent", "SampleTypeAlias()"),
                arguments("object-with-import-alias-parent", "SampleImportAlias()"),
            )
    }
}
