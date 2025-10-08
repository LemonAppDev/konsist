package com.lemonappdev.konsist.core.declaration.koimport

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoImportDeclarationForKoRepresentsTypeProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesForComplexType")
    fun `import-represents-complex-type`(
        type: String?,
        ignoreCase: Boolean,
        value: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile("import-represents-complex-type")
                .imports
                .first()

        // then
        sut.representsType(type, ignoreCase) shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesForKotlinBasicType")
    fun `import-represents-kotlin-type`(
        type: String?,
        ignoreCase: Boolean,
        value: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile("import-represents-kotlin-type")
                .imports
                .first()

        // then
        sut.representsType(type, ignoreCase) shouldBeEqualTo value
    }

    @Suppress("SameParameterValue")
    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koimport/snippet/forkorepresentstypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForComplexType() =
            listOf(
                arguments("SampleClass", false, true),
                arguments("sampleclass", false, false),
                arguments("sampleclass", true, true),
                arguments("OtherClass", false, false),
                arguments("otherclass", false, false),
                arguments("otherclass", true, false),
                arguments("com.lemonappdev.konsist.testdata.SampleClass", false, true),
                arguments("com.lemonappdev.konsist.testdata.sampleclass", false, false),
                arguments("com.lemonappdev.konsist.testdata.sampleclass", true, true),
                arguments("com.lemonappdev.konsist.testdata.OtherClass", false, false),
                arguments("com.lemonappdev.konsist.testdata.otherclass", false, false),
                arguments("com.lemonappdev.konsist.testdata.otherclass", true, false),
                arguments(null, false, false),
                arguments(null, true, false),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForKotlinBasicType() =
            listOf(
                arguments("String", false, true),
                arguments("string", false, false),
                arguments("string", true, true),
                arguments("List", false, false),
                arguments("list", false, false),
                arguments("list", true, false),
                arguments("kotlin.String", false, true),
                arguments("kotlin.string", false, false),
                arguments("kotlin.string", true, true),
                arguments("kotlin.collection.List", false, false),
                arguments("kotlin.collection.list", false, false),
                arguments("kotlin.collection.list", true, false),
                arguments(null, false, false),
                arguments(null, true, false),
            )
    }
}
