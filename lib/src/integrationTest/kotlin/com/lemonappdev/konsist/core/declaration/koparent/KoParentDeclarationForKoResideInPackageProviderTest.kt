package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoParentDeclarationForKoResideInPackageProviderTest {
    @ParameterizedTest
    @MethodSource("provideClassesForPackage")
    fun `class-parent-reside-in-file-package`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            sut.resideInPackage("com..") shouldBeEqualTo true
            sut.resideInPackage("com") shouldBeEqualTo false
            sut.resideOutsidePackage("com..") shouldBeEqualTo false
            sut.resideOutsidePackage("com") shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideClassesForNoPackage")
    fun `class-parent-not-reside-in-file-package`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            sut.resideInPackage("com..") shouldBeEqualTo false
            sut.resideInPackage("com") shouldBeEqualTo false
            sut.resideOutsidePackage("com..") shouldBeEqualTo true
            sut.resideOutsidePackage("com") shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideInterfacesForPackage")
    fun `interface-parent-reside-in-file-package`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .interfaces()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            sut.resideInPackage("com..") shouldBeEqualTo true
            sut.resideInPackage("com") shouldBeEqualTo false
            sut.resideOutsidePackage("com..") shouldBeEqualTo false
            sut.resideOutsidePackage("com") shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideInterfacesForNoPackage")
    fun `interface-parent-not-reside-in-file-package`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .interfaces()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            sut.resideInPackage("com..") shouldBeEqualTo false
            sut.resideInPackage("com") shouldBeEqualTo false
            sut.resideOutsidePackage("com..") shouldBeEqualTo true
            sut.resideOutsidePackage("com") shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideObjectsForPackage")
    fun `object-parent-reside-in-file-package`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            sut.resideInPackage("com..") shouldBeEqualTo true
            sut.resideInPackage("com") shouldBeEqualTo false
            sut.resideOutsidePackage("com..") shouldBeEqualTo false
            sut.resideOutsidePackage("com") shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideObjectsForNoPackage")
    fun `object-parent-not-reside-in-file-package`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            sut.resideInPackage("com..") shouldBeEqualTo false
            sut.resideInPackage("com") shouldBeEqualTo false
            sut.resideOutsidePackage("com..") shouldBeEqualTo true
            sut.resideOutsidePackage("com") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparent/snippet/forkoresideinpackageprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideClassesForNoPackage() =
            listOf(
                arguments("parent-from-file-of-class-not-reside-in-file-package"),
                arguments("parent-from-import-of-class-not-reside-in-file-package"),
                arguments("external-parent-of-class-not-reside-in-file-package"),
                arguments("typealias-parent-of-class-not-reside-in-file-package"),
                arguments("import-alias-parent-of-class-not-reside-in-file-package"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideClassesForPackage() =
            listOf(
                arguments("parent-from-file-of-class-reside-in-file-package"),
                arguments("parent-from-import-of-class-reside-in-file-package"),
                arguments("external-parent-of-class-reside-in-file-package"),
                arguments("typealias-parent-of-class-reside-in-file-package"),
                arguments("import-alias-parent-of-class-reside-in-file-package"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfacesForNoPackage() =
            listOf(
                arguments("parent-from-file-of-interface-not-reside-in-file-package"),
                arguments("parent-from-import-of-interface-not-reside-in-file-package"),
                arguments("external-parent-of-interface-not-reside-in-file-package"),
                arguments("typealias-parent-of-interface-not-reside-in-file-package"),
                arguments("import-alias-parent-of-interface-not-reside-in-file-package"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfacesForPackage() =
            listOf(
                arguments("parent-from-file-of-interface-reside-in-file-package"),
                arguments("parent-from-import-of-interface-reside-in-file-package"),
                arguments("external-parent-of-interface-reside-in-file-package"),
                arguments("typealias-parent-of-interface-reside-in-file-package"),
                arguments("import-alias-parent-of-interface-reside-in-file-package"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideObjectsForNoPackage() =
            listOf(
                arguments("parent-from-file-of-object-not-reside-in-file-package"),
                arguments("parent-from-import-of-object-not-reside-in-file-package"),
                arguments("external-parent-of-object-not-reside-in-file-package"),
                arguments("typealias-parent-of-object-not-reside-in-file-package"),
                arguments("import-alias-parent-of-object-not-reside-in-file-package"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideObjectsForPackage() =
            listOf(
                arguments("parent-from-file-of-object-reside-in-file-package"),
                arguments("parent-from-import-of-object-reside-in-file-package"),
                arguments("external-parent-of-object-reside-in-file-package"),
                arguments("typealias-parent-of-object-reside-in-file-package"),
                arguments("import-alias-parent-of-object-reside-in-file-package"),
            )
    }
}
