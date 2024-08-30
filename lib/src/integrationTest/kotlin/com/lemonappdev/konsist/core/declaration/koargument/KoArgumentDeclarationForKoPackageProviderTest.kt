package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.annotations
import com.lemonappdev.konsist.api.ext.list.arguments
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoPackageProviderTest {
    @Test
    fun `argument-in-enum-const-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-is-not-in-package")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `argument-in-enum-const-is-in-package`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-is-in-package")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        sut.packagee?.name shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `argument-in-annotation-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-is-not-in-package")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `argument-in-annotation-is-in-package`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-is-in-package")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        sut.packagee?.name shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koargument/snippet/forkopackageprovider/", fileName)
}
