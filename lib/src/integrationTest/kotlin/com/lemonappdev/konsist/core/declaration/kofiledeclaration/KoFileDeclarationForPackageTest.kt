package com.lemonappdev.konsist.core.declaration.kofiledeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForPackageTest {
    @Test
    fun `file-contains-package`() {
        // given
        val sut = getSnippetFile("file-contains-package")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            packagee?.name shouldBeEqualTo "samplepackage"
            hasPackage("com.samplepackage") shouldBeEqualTo true
            hasPackage("com..") shouldBeEqualTo true
            hasPackage("com") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-no-package`() {
        // given
        val sut = getSnippetFile("file-contains-no-package")
            .files()
            .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofiledeclaration/snippet/forpackage/", fileName)
}
