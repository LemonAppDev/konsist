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
        value: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile("import-represents-complex-type")
                .imports
                .first()

        // then
        sut.representsType(type) shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesForKotlinBasicType")
    fun `import-represents-kotlin-type`(
        type: String?,
        value: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile("import-represents-kotlin-type")
                .imports
                .first()

        // then
        sut.representsType(type) shouldBeEqualTo value
    }

    @Suppress("SameParameterValue")
    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koimport/snippet/forkorepresentstypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForComplexType() =
            listOf(
                arguments("SampleClass", true),
                arguments("OtherClass", false),
                arguments("com.lemonappdev.konsist.testdata.SampleClass", true),
                arguments("com.lemonappdev.konsist.testdata.OtherClass", false),
                arguments(null, false),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForKotlinBasicType() =
            listOf(
                arguments("String", true),
                arguments("List", false),
                arguments("kotlin.String", true),
                arguments("kotlin.collection.List", false),
                arguments(null, false),
            )
    }
}
