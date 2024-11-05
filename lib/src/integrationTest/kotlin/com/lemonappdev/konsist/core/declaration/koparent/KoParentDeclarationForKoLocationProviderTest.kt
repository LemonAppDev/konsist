package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoParentDeclarationForKoLocationProviderTest {
    @ParameterizedTest
    @MethodSource("provideClasses")
    fun `class-parent-location`(
        fileName: String,
        location: String,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .parents()
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:$location"
    }

    @ParameterizedTest
    @MethodSource("provideInterfaces")
    fun `interface-parent-location`(
        fileName: String,
        location: String,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .interfaces()
                .parents()
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:$location"
    }

    @ParameterizedTest
    @MethodSource("provideObjects")
    fun `object-parent-location`(
        fileName: String,
        location: String,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .objects()
                .parents()
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:$location"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparent/snippet/forkolocationprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideClasses() =
            listOf(
                arguments("class-with-parent-class-from-file", "1:23"),
                arguments("class-with-generic-parent-class-from-file", "1:23"),
                arguments("class-with-parametrized-parent-class-from-file", "1:23"),
                arguments("class-with-parametrized-and-generic-parent-class-from-file", "1:23"),
                arguments("class-with-parent-interface-from-file", "3:23"),
                arguments("class-with-generic-parent-interface-from-file", "3:23"),
                arguments("class-with-parent-by-delegation-from-file", "3:59"),
                arguments("class-with-multiline-parent-from-file", "1:20"),
                arguments("class-with-parent-class-from-import", "3:23"),
                arguments("class-with-generic-parent-class-from-import", "3:23"),
                arguments("class-with-parametrized-parent-class-from-import", "3:23"),
                arguments("class-with-parametrized-and-generic-parent-class-from-import", "3:23"),
                arguments("class-with-parent-interface-from-import", "3:23"),
                arguments("class-with-generic-parent-interface-from-import", "3:23"),
                arguments("class-with-parent-by-delegation-from-import", "3:54"),
                arguments("class-with-multiline-parent-from-import", "3:20"),
                arguments("class-with-external-parent-class", "3:23"),
                arguments("class-with-generic-external-parent-class", "3:23"),
                arguments("class-with-parametrized-external-parent-class", "3:23"),
                arguments("class-with-parametrized-and-generic-external-parent-class", "3:23"),
                arguments("class-with-external-parent-interface", "3:23"),
                arguments("class-with-generic-external-parent-interface", "3:23"),
                arguments("class-with-external-parent-by-delegation", "3:62"),
                arguments("class-with-multiline-external-parent", "3:23"),
                arguments("class-with-typealias-parent", "3:23"),
                arguments("class-with-import-alias-parent", "3:23"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfaces() =
            listOf(
                arguments("interface-with-parent-interface-from-file", "1:29"),
                arguments("interface-with-generic-parent-interface-from-file", "1:29"),
                arguments("interface-with-parent-interface-from-import", "3:29"),
                arguments("interface-with-generic-parent-interface-from-import", "3:29"),
                arguments("interface-with-external-parent-interface", "3:29"),
                arguments("interface-with-generic-external-parent-interface", "3:29"),
                arguments("interface-with-typealias-parent", "3:29"),
                arguments("interface-with-import-alias-parent", "3:29"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideObjects() =
            listOf(
                arguments("object-with-parent-class-from-file", "1:22"),
                arguments("object-with-generic-parent-class-from-file", "1:23"),
                arguments("object-with-parametrized-parent-class-from-file", "1:22"),
                arguments("object-with-parametrized-and-generic-parent-class-from-file", "1:22"),
                arguments("object-with-parent-interface-from-file", "3:22"),
                arguments("object-with-generic-parent-interface-from-file", "3:22"),
                arguments("object-with-multiline-parent-from-file", "1:22"),
                arguments("object-with-parent-class-from-import", "3:22"),
                arguments("object-with-generic-parent-class-from-import", "3:23"),
                arguments("object-with-parametrized-parent-class-from-import", "3:22"),
                arguments("object-with-parametrized-and-generic-parent-class-from-import", "3:22"),
                arguments("object-with-parent-interface-from-import", "3:22"),
                arguments("object-with-generic-parent-interface-from-import", "3:22"),
                arguments("object-with-multiline-parent-from-import", "3:22"),
                arguments("object-with-external-parent-class", "3:22"),
                arguments("object-with-generic-external-parent-class", "3:23"),
                arguments("object-with-parametrized-external-parent-class", "3:22"),
                arguments("object-with-parametrized-and-generic-external-parent-class", "3:22"),
                arguments("object-with-external-parent-interface", "3:22"),
                arguments("object-with-generic-external-parent-interface", "3:22"),
                arguments("object-with-multiline-external-parent", "3:22"),
                arguments("object-with-typealias-parent", "3:22"),
                arguments("object-with-import-alias-parent", "3:22"),
            )
    }
}
