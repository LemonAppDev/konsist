package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoIsMutableTypeProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesForIsMutableType")
    fun `is-mutable-type`(
        fileName: String,
        value: Boolean,
    ) {
        // given
        val sut = getType(fileName)

        // then
        sut?.isMutableType shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesForHasMutableTypeArgument")
    fun `has-mutable-type-argument`(
        fileName: String,
        value: Boolean,
    ) {
        // given
        val sut = getType(fileName)

        // then
        sut?.hasMutableTypeArgument shouldBeEqualTo value
    }

    private fun getType(fileName: String) =
        getSnippetFile(fileName)
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotype/snippet/forkoismutabletypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForIsMutableType() =
            listOf(
                arguments("kotlin-type-is-mutable-type", true),
                arguments("kotlin-type-is-not-mutable-type", false),
                arguments("fully-qualified-kotlin-type-is-mutable-type", true),
                arguments("known-kotlin-collection-type-is-mutable-type", true),
                arguments("known-kotlin-array-type-is-mutable-type", true),
                arguments("known-java-type-is-mutable-type", true),
                arguments("fully-qualified-java-type-is-mutable-type", true),
                arguments("generic-type-is-mutable-type", true),
                arguments("generic-type-is-not-mutable-type", false),
                arguments("type-with-mutable-type-argument-is-not-mutable-type", false),
                arguments("class-type-is-mutable-type", true),
                arguments("class-type-is-not-mutable-type", false),
                arguments("class-type-with-var-property-is-mutable-type", true),
                arguments("class-type-with-private-var-property-is-mutable-type", true),
                arguments("class-type-with-var-constructor-property-is-mutable-type", true),
                arguments("class-type-with-val-properties-is-not-mutable-type", false),
                arguments("class-type-with-var-property-in-companion-object-is-not-mutable-type", false),
                arguments("class-type-with-var-property-in-nested-class-is-not-mutable-type", false),
                arguments("class-type-with-mutable-parent-class-is-mutable-type", true),
                arguments("class-type-with-indirect-mutable-parent-class-is-mutable-type", true),
                arguments("class-type-with-mutable-parent-interface-is-mutable-type", true),
                arguments("class-type-with-not-mutable-parent-is-not-mutable-type", false),
                arguments("class-type-with-known-mutable-parent-is-mutable-type", true),
                arguments("class-type-with-mutable-kotlin-parent-interface-is-mutable-type", true),
                arguments("class-type-with-mutable-external-parent-is-mutable-type", true),
                arguments("interface-type-is-mutable-type", true),
                arguments("interface-type-is-not-mutable-type", false),
                arguments("interface-type-with-var-property-is-mutable-type", true),
                arguments("object-type-is-mutable-type", true),
                arguments("object-type-is-not-mutable-type", false),
                arguments("object-type-with-var-property-is-mutable-type", true),
                arguments("function-type-type-is-not-mutable-type", false),
                arguments("import-alias-type-is-mutable-type", true),
                arguments("import-alias-type-is-not-mutable-type", false),
                arguments("import-alias-type-with-mutable-name-is-not-mutable-type", false),
                arguments("typealias-type-is-mutable-type", true),
                arguments("typealias-type-is-not-mutable-type", false),
                arguments("typealias-type-with-mutable-name-is-not-mutable-type", false),
                arguments("typealias-to-class-type-with-var-property-is-mutable-type", true),
                arguments("external-type-is-mutable-type", true),
                arguments("external-type-is-not-mutable-type", false),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForHasMutableTypeArgument() =
            listOf(
                arguments("type-has-mutable-type-argument", true),
                arguments("type-has-nested-mutable-type-argument", true),
                arguments("type-has-typealias-mutable-type-argument", true),
                arguments("type-has-import-alias-mutable-type-argument", true),
                arguments("typealias-type-has-mutable-type-argument", true),
                arguments("mutable-type-has-not-mutable-type-argument", false),
                arguments("non-generic-type-has-not-mutable-type-argument", false),
            )
    }
}
