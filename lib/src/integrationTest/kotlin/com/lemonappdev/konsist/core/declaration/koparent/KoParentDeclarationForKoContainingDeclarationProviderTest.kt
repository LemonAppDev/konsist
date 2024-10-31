package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoParentDeclarationForKoContainingDeclarationProviderTest {
    @ParameterizedTest
    @MethodSource("provideClasses")
    fun `class-parent-has-no-argument`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @ParameterizedTest
    @MethodSource("provideInterfaces")
    fun `interface-parent-has-no-argument`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .interfaces()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoInterfaceDeclaration::class
    }

    @ParameterizedTest
    @MethodSource("provideObjects")
    fun `object-parent-has-no-argument`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koparent/snippet/forkocontainingdeclarationprovider/",
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideClasses() =
            listOf(
                arguments("class-with-parent-class-from-file"),
                arguments("class-with-generic-parent-class-from-file"),
                arguments("class-with-parametrized-parent-class-from-file"),
                arguments("class-with-parametrized-and-generic-parent-class-from-file"),
                arguments("class-with-parent-interface-from-file"),
                arguments("class-with-generic-parent-interface-from-file"),
                arguments("class-with-parent-by-delegation-from-file"),
                arguments("class-with-multiline-parent-from-file"),
                arguments("class-with-parent-class-from-import"),
                arguments("class-with-generic-parent-class-from-import"),
                arguments("class-with-parametrized-parent-class-from-import"),
                arguments("class-with-parametrized-and-generic-parent-class-from-import"),
                arguments("class-with-parent-interface-from-import"),
                arguments("class-with-generic-parent-interface-from-import"),
                arguments("class-with-parent-by-delegation-from-import"),
                arguments("class-with-multiline-parent-from-import"),
                arguments("class-with-external-parent-class"),
                arguments("class-with-generic-external-parent-class"),
                arguments("class-with-parametrized-external-parent-class"),
                arguments("class-with-parametrized-and-generic-external-parent-class"),
                arguments("class-with-external-parent-interface"),
                arguments("class-with-generic-external-parent-interface"),
                arguments("class-with-external-parent-by-delegation"),
                arguments("class-with-typealias-parent"),
                arguments("class-with-import-alias-parent"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfaces() =
            listOf(
                arguments("interface-with-parent-interface-from-file"),
                arguments("interface-with-generic-parent-interface-from-file"),
                arguments("interface-with-parent-interface-from-import"),
                arguments("interface-with-generic-parent-interface-from-import"),
                arguments("interface-with-external-parent-interface"),
                arguments("interface-with-generic-external-parent-interface"),
                arguments("interface-with-typealias-parent"),
                arguments("interface-with-import-alias-parent"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideObjects() =
            listOf(
                arguments("object-with-parent-class-from-file"),
                arguments("object-with-generic-parent-class-from-file"),
                arguments("object-with-parametrized-parent-class-from-file"),
                arguments("object-with-parametrized-and-generic-parent-class-from-file"),
                arguments("object-with-parent-interface-from-file"),
                arguments("object-with-generic-parent-interface-from-file"),
                arguments("object-with-multiline-parent-from-file"),
                arguments("object-with-parent-class-from-import"),
                arguments("object-with-generic-parent-class-from-import"),
                arguments("object-with-parametrized-parent-class-from-import"),
                arguments("object-with-parametrized-and-generic-parent-class-from-import"),
                arguments("object-with-parent-interface-from-import"),
                arguments("object-with-generic-parent-interface-from-import"),
                arguments("object-with-multiline-parent-from-import"),
                arguments("object-with-external-parent-class"),
                arguments("object-with-generic-external-parent-class"),
                arguments("object-with-parametrized-external-parent-class"),
                arguments("object-with-parametrized-and-generic-external-parent-class"),
                arguments("object-with-external-parent-interface"),
                arguments("object-with-generic-external-parent-interface"),
                arguments("object-with-multiline-external-parent"),
                arguments("object-with-typealias-parent"),
                arguments("object-with-import-alias-parent"),
            )
    }
}
