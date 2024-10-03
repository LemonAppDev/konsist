package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.provider.annotations
import com.lemonappdev.konsist.api.ext.list.provider.arguments
import com.lemonappdev.konsist.api.ext.list.provider.enumConstants
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoResideInOrOutsidePackageProviderTest {
    @Test
    fun `argument-in-enum-const-not-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-not-reside-in-file-package")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `argument-in-enum-const-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-reside-in-file-package")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `argument-in-enum-const-not-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-not-reside-outside-file-package")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `argument-in-enum-const-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-reside-outside-file-package")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    @Test
    fun `argument-in-annotation-not-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-not-reside-in-file-package")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `argument-in-annotation-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-reside-in-file-package")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `argument-in-annotation-not-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-not-reside-outside-file-package")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `argument-in-annotation-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-reside-outside-file-package")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koargument/snippet/forkoresideinoroutsidepackageprovider/", fileName)
}
