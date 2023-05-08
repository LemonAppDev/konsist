package com.lemonappdev.konsist.core.declaration.kocomplexdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoComplexDeclarationForDeclarationsTest {
    @ParameterizedTest
    @MethodSource("provideValuesWithoutModifiers")
    fun `declarations`(
        fileName: String,
        declarationName: String,
        includeNested: Boolean,
        includeLocal: Boolean,
        value: List<String>,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoComplexDeclaration>()
            .first { it.name == declarationName }

        // then
        sut
            .declarations(includeNested = includeNested, includeLocal = includeLocal)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(value)
    }

    @ParameterizedTest
    @MethodSource("provideValuesWithModifiers")
    fun `declarations-with-visibility-modifiers`(
        fileName: String,
        declarationName: String,
        includeNested: Boolean,
        value: List<String>,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoComplexDeclaration>()
            .first { it.name == declarationName }

        // then
        sut
            .declarations(listOf(KoModifier.PRIVATE), includeNested = includeNested)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(value)
    }

    companion object {
        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideValuesWithoutModifiers() = listOf(
            arguments("class-contains-no-declarations", "SampleClass", true, true, emptyList<String>()),
            arguments(
                "class-contains-declarations",
                "SampleTopLevelClass",
                false,
                false,
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                    "SampleCompanionObject",
                ),
            ),
            arguments(
                "class-contains-nested-declarations",
                "SampleTopLevelClass",
                true,
                false,
                listOf(
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
                ),
            ),
            arguments(
                "class-contains-nested-declarations",
                "SampleTopLevelClass",
                false,
                false,
                listOf(
                    "sampleFunction",
                    "SampleClass",
                    "SampleObject",
                    "SampleInterface",
                ),
            ),
            arguments(
                "class-contains-local-declarations",
                "SampleTopLevelClass",
                false,
                true,
                listOf(
                    "sampleFunction",
                    "sampleLocalProperty1",
                    "sampleLocalFunction1",
                    "sampleLocalProperty2",
                    "sampleLocalFunction2",
                    "SampleLocalClass1",
                    "sampleLocalFunction2",
                ),
            ),
            arguments(
                "class-contains-local-declarations",
                "SampleTopLevelClass",
                false,
                false,
                listOf(
                    "sampleFunction",
                ),
            ),
            arguments("interface-contains-no-declarations", "SampleInterface", true, true, emptyList<String>()),
            arguments(
                "interface-contains-declarations",
                "SampleTopLevelInterface",
                false,
                false,
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                    "SampleCompanionObject",
                ),
            ),
            arguments(
                "interface-contains-nested-declarations",
                "SampleTopLevelInterface",
                true,
                false,
                listOf(
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
                ),
            ),
            arguments(
                "interface-contains-nested-declarations",
                "SampleTopLevelInterface",
                false,
                false,
                listOf(
                    "sampleFunction",
                    "SampleClass",
                    "SampleObject",
                    "SampleInterface",
                ),
            ),
            arguments(
                "interface-contains-local-declarations",
                "SampleTopLevelInterface",
                false,
                true,
                listOf(
                    "sampleFunction",
                    "sampleLocalProperty1",
                    "sampleLocalFunction1",
                    "sampleLocalProperty2",
                    "sampleLocalFunction2",
                    "SampleLocalClass1",
                    "sampleLocalFunction2",
                ),
            ),
            arguments(
                "interface-contains-local-declarations",
                "SampleTopLevelInterface",
                false,
                false,
                listOf(
                    "sampleFunction",
                ),
            ),
            arguments("object-contains-no-declarations", "SampleObject", true, true, emptyList<String>()),
            arguments(
                "object-contains-declarations",
                "SampleTopLevelObject",
                false,
                false,
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                    "SampleCompanionObject",
                ),
            ),
            arguments(
                "object-contains-nested-declarations",
                "SampleTopLevelObject",
                true,
                false,
                listOf(
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
                ),
            ),
            arguments(
                "object-contains-nested-declarations",
                "SampleTopLevelObject",
                false,
                false,
                listOf(
                    "sampleFunction",
                    "SampleClass",
                    "SampleObject",
                    "SampleInterface",
                ),
            ),
            arguments(
                "object-contains-local-declarations",
                "SampleTopLevelObject",
                false,
                true,
                listOf(
                    "sampleFunction",
                    "sampleLocalProperty1",
                    "sampleLocalFunction1",
                    "sampleLocalProperty2",
                    "sampleLocalFunction2",
                    "SampleLocalClass1",
                    "sampleLocalFunction2",
                ),
            ),
            arguments(
                "object-contains-local-declarations",
                "SampleTopLevelObject",
                false,
                false,
                listOf(
                    "sampleFunction",
                ),
            ),
            arguments("companion-object-contains-no-declarations", "SampleCompanionObject", true, true, emptyList<String>()),
            arguments(
                "companion-object-contains-declarations",
                "SampleTopLevelCompanionObject",
                false,
                false,
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleObject",
                    "SampleInterface",
                ),
            ),
            arguments(
                "companion-object-contains-nested-declarations",
                "SampleTopLevelCompanionObject",
                true,
                false,
                listOf(
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
                ),
            ),
            arguments(
                "companion-object-contains-nested-declarations",
                "SampleTopLevelCompanionObject",
                false,
                false,
                listOf(
                    "sampleFunction",
                    "SampleClass",
                    "SampleObject",
                    "SampleInterface",
                ),
            ),
            arguments(
                "companion-object-contains-local-declarations",
                "SampleTopLevelCompanionObject",
                false,
                true,
                listOf(
                    "sampleFunction",
                    "sampleLocalProperty1",
                    "sampleLocalFunction1",
                    "sampleLocalProperty2",
                    "sampleLocalFunction2",
                    "SampleLocalClass1",
                    "sampleLocalFunction2",
                ),
            ),
            arguments(
                "companion-object-contains-local-declarations",
                "SampleTopLevelCompanionObject",
                false,
                false,
                listOf(
                    "sampleFunction",
                ),
            ),
        )

        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideValuesWithModifiers() = listOf(
            arguments(
                "class-contains-declarations-with-visibility-modifiers",
                "SampleTopLevelClass",
                true,
                listOf(
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
                ),
            ),
            arguments(
                "class-contains-declarations-with-visibility-modifiers",
                "SampleTopLevelClass",
                false,
                listOf(
                    "sampleFunction1",
                    "SampleClass1",
                    "SampleObject1",
                    "SampleInterface1",
                ),
            ),
            arguments(
                "interface-contains-declarations-with-visibility-modifiers",
                "SampleTopLevelInterface",
                true,
                listOf(
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
                ),
            ),
            arguments(
                "interface-contains-declarations-with-visibility-modifiers",
                "SampleTopLevelInterface",
                false,
                listOf(
                    "sampleFunction1",
                    "SampleClass1",
                    "SampleObject1",
                    "SampleInterface1",
                ),
            ),
            arguments(
                "object-contains-declarations-with-visibility-modifiers",
                "SampleTopLevelObject",
                true,
                listOf(
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
                ),
            ),
            arguments(
                "object-contains-declarations-with-visibility-modifiers",
                "SampleTopLevelObject",
                false,
                listOf(
                    "sampleFunction1",
                    "SampleClass1",
                    "SampleObject1",
                    "SampleInterface1",
                ),
            ),
            arguments(
                "companion-object-contains-declarations-with-visibility-modifiers",
                "SampleTopLevelCompanionObject",
                true,
                listOf(
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
                ),
            ),
            arguments(
                "companion-object-contains-declarations-with-visibility-modifiers",
                "SampleTopLevelCompanionObject",
                false,
                listOf(
                    "sampleFunction1",
                    "SampleClass1",
                    "SampleObject1",
                    "SampleInterface1",
                ),
            ),
        )
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kocomplexdeclaration/snippet/fordeclarations/", fileName)
}
