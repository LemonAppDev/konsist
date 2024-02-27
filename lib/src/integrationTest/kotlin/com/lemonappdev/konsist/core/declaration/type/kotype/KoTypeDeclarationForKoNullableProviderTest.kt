 package com.lemonappdev.konsist.core.declaration.type.kotype

 import com.lemonappdev.konsist.TestSnippetProvider
 import org.amshove.kluent.shouldBeEqualTo
 import org.junit.jupiter.params.ParameterizedTest
 import org.junit.jupiter.params.provider.Arguments.arguments
 import org.junit.jupiter.params.provider.MethodSource

 class KoTypeDeclarationForKoNullableProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `is-nullable`(
        fileName: String,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        sut?.isNullable shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotype/snippet/forkonullableprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("kotlin-basic-type-is-nullable", true),
            arguments("kotlin-basic-type-is-not-nullable", false),
            arguments("kotlin-collection-type-is-nullable", true),
            arguments("kotlin-collection-type-is-not-nullable", false),
            arguments("class-type-is-nullable", true),
            arguments("class-type-is-not-nullable", false),
//            arguments("import-alias-is-nullable", true),
//            arguments("import-alias-is-not-nullable", false),
        )
    }
 }
