package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `parent-from-file-of-class-not-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-class-not-reside-in-file-package")
            .classes()
            .parents
            .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `parent-from-file-of-class-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-class-reside-in-file-package")
            .classes()
            .parents
            .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `parent-from-file-of-class-not-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-class-not-reside-outside-file-package")
            .classes()
            .parents
            .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `parent-from-file-of-class-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-class-reside-outside-file-package")
            .classes()
            .parents
            .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    @Test
    fun `parent-from-file-of-interface-not-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-interface-not-reside-in-file-package")
            .interfaces()
            .parents
            .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `parent-from-file-of-interface-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-interface-reside-in-file-package")
            .interfaces()
            .parents
            .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `parent-from-file-of-interface-not-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-interface-not-reside-outside-file-package")
            .interfaces()
            .parents
            .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `parent-from-file-of-interface-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-interface-reside-outside-file-package")
            .interfaces()
            .parents
            .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    @Test
    fun `parent-from-file-of-object-not-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-object-not-reside-in-file-package")
            .objects()
            .parents
            .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `parent-from-file-of-object-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-object-reside-in-file-package")
            .objects()
            .parents
            .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `parent-from-file-of-object-not-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-object-not-reside-outside-file-package")
            .objects()
            .parents
            .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `parent-from-file-of-object-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-file-of-object-reside-outside-file-package")
            .objects()
            .parents
            .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    @Test
    fun `parent-from-import-of-class-not-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-class-not-reside-in-file-package")
            .classes()
            .parents
            .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `parent-from-import-of-class-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-class-reside-in-file-package")
            .classes()
            .parents
            .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `parent-from-import-of-class-not-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-class-not-reside-outside-file-package")
            .classes()
            .parents
            .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `parent-from-import-of-class-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-class-reside-outside-file-package")
            .classes()
            .parents
            .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    @Test
    fun `parent-from-import-of-interface-not-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-interface-not-reside-in-file-package")
            .interfaces()
            .parents
            .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `parent-from-import-of-interface-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-interface-reside-in-file-package")
            .interfaces()
            .parents
            .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `parent-from-import-of-interface-not-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-interface-not-reside-outside-file-package")
            .interfaces()
            .parents
            .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `parent-from-import-of-interface-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-interface-reside-outside-file-package")
            .interfaces()
            .parents
            .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    @Test
    fun `parent-from-import-of-object-not-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-object-not-reside-in-file-package")
            .objects()
            .parents
            .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `parent-from-import-of-object-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-object-reside-in-file-package")
            .objects()
            .parents
            .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `parent-from-import-of-object-not-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-object-not-reside-outside-file-package")
            .objects()
            .parents
            .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `parent-from-import-of-object-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("parent-from-import-of-object-reside-outside-file-package")
            .objects()
            .parents
            .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparent/snippet/forkoresideinpackageprovider/", fileName)
}
