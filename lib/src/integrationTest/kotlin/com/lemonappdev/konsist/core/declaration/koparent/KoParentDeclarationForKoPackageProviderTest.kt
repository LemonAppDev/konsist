package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoParentDeclarationForKoPackageProviderTest {
    @ParameterizedTest
    @MethodSource("provideClassesForPackage")
    fun `class-parent-is-in-package`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .parents()
                .first()

        // then
        sut.packagee?.name shouldBeEqualTo "com.samplepackage"
    }

    @ParameterizedTest
    @MethodSource("provideClassesForNoPackage")
    fun `class-parent-is-not-in-package`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .parents()
                .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @ParameterizedTest
    @MethodSource("provideInterfacesForPackage")
    fun `interface-parent-is-in-package`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .interfaces()
                .parents()
                .first()

        // then
        sut.packagee?.name shouldBeEqualTo "com.samplepackage"
    }

    @ParameterizedTest
    @MethodSource("provideInterfacesForNoPackage")
    fun `interface-parent-is-not-in-package`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .interfaces()
                .parents()
                .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @ParameterizedTest
    @MethodSource("provideObjectsForPackage")
    fun `object-parent-is-in-package`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .objects()
                .parents()
                .first()

        // then
        sut.packagee?.name shouldBeEqualTo "com.samplepackage"
    }

    @ParameterizedTest
    @MethodSource("provideObjectsForNoPackage")
    fun `object-parent-is-not-in-package`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .objects()
                .parents()
                .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koparent/snippet/forkopackageprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideClassesForNoPackage() =
            listOf(
                arguments("parent-from-file-of-class-is-not-in-package"),
                arguments("parent-from-import-of-class-is-not-in-package"),
                arguments("external-parent-of-class-is-not-in-package"),
                arguments("typealias-parent-of-class-is-not-in-package"),
                arguments("import-alias-parent-of-class-is-not-in-package"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideClassesForPackage() =
            listOf(
                arguments("parent-from-file-of-class-is-in-package"),
                arguments("parent-from-import-of-class-is-in-package"),
                arguments("external-parent-of-class-is-in-package"),
                arguments("typealias-parent-of-class-is-in-package"),
                arguments("import-alias-parent-of-class-is-in-package"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfacesForNoPackage() =
            listOf(
                arguments("parent-from-file-of-interface-is-not-in-package"),
                arguments("parent-from-import-of-interface-is-not-in-package"),
                arguments("external-parent-of-interface-is-not-in-package"),
                arguments("typealias-parent-of-interface-is-not-in-package"),
                arguments("import-alias-parent-of-interface-is-not-in-package"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfacesForPackage() =
            listOf(
                arguments("parent-from-file-of-interface-is-in-package"),
                arguments("parent-from-import-of-interface-is-in-package"),
                arguments("external-parent-of-interface-is-in-package"),
                arguments("typealias-parent-of-interface-is-in-package"),
                arguments("import-alias-parent-of-interface-is-in-package"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideObjectsForNoPackage() =
            listOf(
                arguments("parent-from-file-of-object-is-not-in-package"),
                arguments("parent-from-import-of-object-is-not-in-package"),
                arguments("external-parent-of-object-is-not-in-package"),
                arguments("typealias-parent-of-object-is-not-in-package"),
                arguments("import-alias-parent-of-object-is-not-in-package"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideObjectsForPackage() =
            listOf(
                arguments("parent-from-file-of-object-is-in-package"),
                arguments("parent-from-import-of-object-is-in-package"),
                arguments("external-parent-of-object-is-in-package"),
                arguments("typealias-parent-of-object-is-in-package"),
                arguments("import-alias-parent-of-object-is-in-package"),
            )
    }
}
