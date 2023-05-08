package com.lemonappdev.konsist.core.declaration.kocomplexdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoComplexDeclarationTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declarations`(
        fileName: String,
        includeNested: Boolean,
        includeLocal: Boolean,
        value: List<String>,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .filterIsInstance<KoComplexDeclaration>()
            .first()
            .declarations(includeNested = includeNested, includeLocal = includeLocal)
            .toList()
            .map { it.name }

        // then
        sut shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesWithModifiers")
    fun `declarations-with-visibility-modifiers`(
        fileName: String,
        includeNested: Boolean,
        value: List<String>,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .filterIsInstance<KoComplexDeclaration>()
            .first()
            .declarations(listOf(KoModifier.PRIVATE), includeNested = includeNested)
            .toList()
            .map { it.name }

        // then
        sut shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesForRepresentsType")
    fun `represents-type`(
        fileName: String,
        type: String,
        value: Boolean
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoComplexDeclaration>()
            .first()
            .representsType(type)

        // then
        sut shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesForCompanionObject")
    fun `companion-object-declarations`(
        fileName: String,
        includeNested: Boolean,
        includeLocal: Boolean,
        value: List<String>,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .companionObjects()
            .first()
            .declarations(includeNested = includeNested, includeLocal = includeLocal)
            .toList()
            .map { it.name }

        // then
        sut shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesWithModifiersForCompanionObject")
    fun `companion-object-declarations-with-visibility-modifiers`(
        fileName: String,
        includeNested: Boolean,
        value: List<String>,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .companionObjects()
            .first()
            .declarations(listOf(KoModifier.PRIVATE), includeNested = includeNested)
            .toList()
            .map { it.name }

        // then
        sut shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesForRepresentsTypeForCompanionObject")
    fun `companion-object-represents-type`(
        fileName: String,
        type: String,
        value: Boolean
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .interfaces()
            .first()
            .companionObjects()
            .first()
            .representsType(type)

        // then
        sut shouldBeEqualTo value
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-contains-no-declarations", true, true, emptyList<String>()),
            arguments(
                "class-contains-declarations", false, false, listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                    "SampleCompanionObject",
                )
            ),
            arguments(
                "class-contains-nested-declarations", true, false, listOf(
                    "sampleFunction",
                    "SampleClass",
                    "SampleClassNestedInsideClass",
                    "SampleObjectNestedInsideClass",
                    "SampleInterfaceNestedInsideClass",
                    "SampleCompanionObjectNestedInsideClass",
                    "SampleObject",
                    "SampleClassNestedInsideObject",
                    "SampleObjectNestedInsideObject",
                    "SampleInterfaceNestedInsideObject",
                    "SampleInterface",
                    "SampleClassNestedInsideInterface",
                    "SampleObjectNestedInsideInterface",
                    "SampleInterfaceNestedInsideInterface",
                    "SampleCompanionObjectNestedInsideInterface",
                )
            ),
            arguments(
                "class-contains-nested-declarations", false, false, listOf(
                    "sampleFunction",
                    "SampleClass",
                    "SampleObject",
                    "SampleInterface",
                )
            ),
            arguments(
                "class-contains-local-declarations", false, true, listOf(
                    "sampleFunction",
                    "sampleLocalProperty1",
                    "sampleLocalFunction1",
                    "sampleLocalProperty2",
                    "sampleLocalFunction2",
                    "SampleLocalClass1",
                    "sampleLocalFunction2",
                )
            ),
            arguments(
                "class-contains-local-declarations", false, false, listOf(
                    "sampleFunction"
                )
            ),
            arguments("interface-contains-no-declarations", true, true, emptyList<String>()),
            arguments(
                "interface-contains-declarations", false, false, listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                    "SampleCompanionObject",
                )
            ),
            arguments(
                "interface-contains-nested-declarations", true, false, listOf(
                    "sampleFunction",
                    "SampleClass",
                    "SampleClassNestedInsideClass",
                    "SampleObjectNestedInsideClass",
                    "SampleInterfaceNestedInsideClass",
                    "SampleCompanionObjectNestedInsideClass",
                    "SampleObject",
                    "SampleClassNestedInsideObject",
                    "SampleObjectNestedInsideObject",
                    "SampleInterfaceNestedInsideObject",
                    "SampleInterface",
                    "SampleClassNestedInsideInterface",
                    "SampleObjectNestedInsideInterface",
                    "SampleInterfaceNestedInsideInterface",
                    "SampleCompanionObjectNestedInsideInterface",
                )
            ),
            arguments(
                "interface-contains-nested-declarations", false, false, listOf(
                    "sampleFunction",
                    "SampleClass",
                    "SampleObject",
                    "SampleInterface",
                )
            ),
            arguments(
                "interface-contains-local-declarations", false, true, listOf(
                    "sampleFunction",
                    "sampleLocalProperty1",
                    "sampleLocalFunction1",
                    "sampleLocalProperty2",
                    "sampleLocalFunction2",
                    "SampleLocalClass1",
                    "sampleLocalFunction2",
                )
            ),
            arguments(
                "interface-contains-local-declarations", false, false, listOf(
                    "sampleFunction"
                )
            ),
            arguments("object-contains-no-declarations", true, true, emptyList<String>()),
            arguments(
                "object-contains-declarations", false, false, listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                    "SampleCompanionObject",
                )
            ),
            arguments(
                "object-contains-nested-declarations", true, false, listOf(
                    "sampleFunction",
                    "SampleClass",
                    "SampleClassNestedInsideClass",
                    "SampleObjectNestedInsideClass",
                    "SampleInterfaceNestedInsideClass",
                    "SampleCompanionObjectNestedInsideClass",
                    "SampleObject",
                    "SampleClassNestedInsideObject",
                    "SampleObjectNestedInsideObject",
                    "SampleInterfaceNestedInsideObject",
                    "SampleInterface",
                    "SampleClassNestedInsideInterface",
                    "SampleObjectNestedInsideInterface",
                    "SampleInterfaceNestedInsideInterface",
                    "SampleCompanionObjectNestedInsideInterface",
                )
            ),
            arguments(
                "object-contains-nested-declarations", false, false, listOf(
                    "sampleFunction",
                    "SampleClass",
                    "SampleObject",
                    "SampleInterface",
                )
            ),
            arguments(
                "object-contains-local-declarations", false, true, listOf(
                    "sampleFunction",
                    "sampleLocalProperty1",
                    "sampleLocalFunction1",
                    "sampleLocalProperty2",
                    "sampleLocalFunction2",
                    "SampleLocalClass1",
                    "sampleLocalFunction2",
                )
            ),
            arguments(
                "object-contains-local-declarations", false, false, listOf(
                    "sampleFunction"
                )
            ),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesWithModifiers() = listOf(
            arguments(
                "class-contains-declarations-with-visibility-modifiers", true, listOf(
                    "sampleFunction1",
                    "SampleClass1",
                    "SampleClassNestedInsideClass2",
                    "SampleObjectNestedInsideClass2",
                    "SampleInterfaceNestedInsideClass2",
                    "SampleCompanionObjectNestedInsideClass2",
                    "SampleObject1",
                    "SampleClassNestedInsideObject2",
                    "SampleObjectNestedInsideObject2",
                    "SampleInterfaceNestedInsideObject2",
                    "SampleInterface1",
                    "SampleClassNestedInsideInterface2",
                    "SampleObjectNestedInsideInterface2",
                    "SampleInterfaceNestedInsideInterface2",
                    "SampleCompanionObjectNestedInsideInterface2",
                )
            ),
            arguments(
                "class-contains-declarations-with-visibility-modifiers", false, listOf(
                    "sampleFunction1",
                    "SampleClass1",
                    "SampleObject1",
                    "SampleInterface1",
                )
            ),
            arguments(
                "interface-contains-declarations-with-visibility-modifiers", true, listOf(
                    "sampleFunction1",
                    "SampleClass1",
                    "SampleClassNestedInsideClass2",
                    "SampleObjectNestedInsideClass2",
                    "SampleInterfaceNestedInsideClass2",
                    "SampleCompanionObjectNestedInsideClass2",
                    "SampleObject1",
                    "SampleClassNestedInsideObject2",
                    "SampleObjectNestedInsideObject2",
                    "SampleInterfaceNestedInsideObject2",
                    "SampleInterface1",
                    "SampleClassNestedInsideInterface2",
                    "SampleObjectNestedInsideInterface2",
                    "SampleInterfaceNestedInsideInterface2",
                    "SampleCompanionObjectNestedInsideInterface2",
                )
            ),
            arguments(
                "interface-contains-declarations-with-visibility-modifiers", false, listOf(
                    "sampleFunction1",
                    "SampleClass1",
                    "SampleObject1",
                    "SampleInterface1",
                )
            ),
            arguments(
                "object-contains-declarations-with-visibility-modifiers", true, listOf(
                    "sampleFunction1",
                    "SampleClass1",
                    "SampleClassNestedInsideClass2",
                    "SampleObjectNestedInsideClass2",
                    "SampleInterfaceNestedInsideClass2",
                    "SampleCompanionObjectNestedInsideClass2",
                    "SampleObject1",
                    "SampleClassNestedInsideObject2",
                    "SampleObjectNestedInsideObject2",
                    "SampleInterfaceNestedInsideObject2",
                    "SampleInterface1",
                    "SampleClassNestedInsideInterface2",
                    "SampleObjectNestedInsideInterface2",
                    "SampleInterfaceNestedInsideInterface2",
                    "SampleCompanionObjectNestedInsideInterface2",
                )
            ),
            arguments(
                "object-contains-declarations-with-visibility-modifiers", false, listOf(
                    "sampleFunction1",
                    "SampleClass1",
                    "SampleObject1",
                    "SampleInterface1",
                )
            ),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForRepresentsType() = listOf(
            arguments("class-represents-type", "SampleClass", true),
            arguments("class-represents-type", "OtherClass", false),
            arguments("class-represents-type", "com.lemonappdev.konsist.testdata.SampleClass", true),
            arguments("class-represents-type", "com.lemonappdev.konsist.testdata.OtherClass", false),
            arguments("interface-represents-type", "SampleInterface", true),
            arguments("interface-represents-type", "OtherInterface", false),
            arguments("interface-represents-type", "com.lemonappdev.konsist.testdata.SampleInterface", true),
            arguments("interface-represents-type", "com.lemonappdev.konsist.testdata.OtherInterface", false),
            arguments("object-represents-type", "SampleObject", true),
            arguments("object-represents-type", "OtherObject", false),
            arguments("object-represents-type", "com.lemonappdev.konsist.testdata.SampleObject", true),
            arguments("object-represents-type", "com.lemonappdev.konsist.testdata.OtherObject", false),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForCompanionObject() = listOf(
            arguments("companion-object-contains-no-declarations", true, true, emptyList<String>()),
            arguments(
                "companion-object-contains-declarations", false, false, listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleObject",
                    "SampleInterface",
                )
            ),
            arguments(
                "companion-object-contains-nested-declarations", true, false, listOf(
                    "sampleFunction",
                    "SampleClass",
                    "SampleClassNestedInsideClass",
                    "SampleObjectNestedInsideClass",
                    "SampleInterfaceNestedInsideClass",
                    "SampleCompanionObjectNestedInsideClass",
                    "SampleObject",
                    "SampleClassNestedInsideObject",
                    "SampleObjectNestedInsideObject",
                    "SampleInterfaceNestedInsideObject",
                    "SampleInterface",
                    "SampleClassNestedInsideInterface",
                    "SampleObjectNestedInsideInterface",
                    "SampleInterfaceNestedInsideInterface",
                )
            ),
            arguments(
                "companion-object-contains-nested-declarations", false, false, listOf(
                    "sampleFunction",
                    "SampleClass",
                    "SampleObject",
                    "SampleInterface",
                )
            ),
            arguments(
                "companion-object-contains-local-declarations", false, true, listOf(
                    "sampleFunction",
                    "sampleLocalProperty1",
                    "sampleLocalFunction1",
                    "sampleLocalProperty2",
                    "sampleLocalFunction2",
                    "SampleLocalClass1",
                    "sampleLocalFunction2",
                )
            ),
            arguments(
                "companion-object-contains-local-declarations", false, false, listOf(
                    "sampleFunction"
                )
            ),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesWithModifiersForCompanionObject() = listOf(
            arguments(
                "companion-object-contains-declarations-with-visibility-modifiers", true, listOf(
                    "sampleFunction1",
                    "SampleClass1",
                    "SampleClassNestedInsideClass2",
                    "SampleObjectNestedInsideClass2",
                    "SampleInterfaceNestedInsideClass2",
                    "SampleCompanionObjectNestedInsideClass2",
                    "SampleObject1",
                    "SampleClassNestedInsideObject2",
                    "SampleObjectNestedInsideObject2",
                    "SampleInterfaceNestedInsideObject2",
                    "SampleInterface1",
                    "SampleClassNestedInsideInterface2",
                    "SampleObjectNestedInsideInterface2",
                    "SampleInterfaceNestedInsideInterface2",
                )
            ),
            arguments(
                "companion-object-contains-declarations-with-visibility-modifiers", false, listOf(
                    "sampleFunction1",
                    "SampleClass1",
                    "SampleObject1",
                    "SampleInterface1",
                )
            ),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForRepresentsTypeForCompanionObject() = listOf(
            arguments("companion-object-represents-type", "SampleCompanionObject", true),
            arguments("companion-object-represents-type", "OtherCompanionObject", false),
            arguments("companion-object-represents-type", "com.lemonappdev.konsist.testdata.SampleTopLevelInterface.SampleCompanionObject", true),
            arguments("companion-object-represents-type", "com.lemonappdev.konsist.testdata.OtherCompanionObject", false),
        )
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kocomplexdeclaration/snippet/", fileName)
}
