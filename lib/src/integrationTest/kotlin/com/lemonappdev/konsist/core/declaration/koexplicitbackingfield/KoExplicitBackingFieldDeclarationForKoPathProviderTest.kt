package com.lemonappdev.konsist.core.declaration.koexplicitbackingfield

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExplicitBackingFieldDeclarationForKoPathProviderTest {
    @Test
    fun `explicit-backing-field-file-path`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-file-path")
                .properties()
                .first()
                .explicitBackingField

        // then
        assertSoftly {
            sut?.path?.startsWith("//") shouldBeEqualTo false
            sut
                ?.path
                ?.endsWith(
                    "koexplicitbackingfield/snippet/forkopathprovider/explicit-backing-field-file-path.kt".toOsSeparator(),
                ) shouldBeEqualTo true
        }
    }

    @Test
    fun `explicit-backing-field-project-file-path`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-project-file-path")
                .properties()
                .first()
                .explicitBackingField

        // then
        sut
            ?.projectPath
            ?.shouldBeEqualTo(
                (
                    "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koexplicitbackingfield/snippet/" +
                        "forkopathprovider/explicit-backing-field-project-file-path.kt"
                ).toOsSeparator(),
            )
    }

    @Test
    fun `explicit-backing-field-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-reside-in-file-path")
                .properties()
                .first()
                .explicitBackingField

        // then
        assertSoftly {
            sut?.residesInPath("..snippet..", true) shouldBeEqualTo true
            sut?.residesInPath("..koexplicitbackingfield/snippet..", true) shouldBeEqualTo true
            sut?.residesInPath("..koexplicitbackingfield..explicit-backing-field-reside-in-file-path.kt", true) shouldBeEqualTo true
            sut?.residesInPath("koexplicitbackingfield/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `explicit-backing-field-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-reside-in-project-file-path")
                .properties()
                .first()
                .explicitBackingField

        // then
        assertSoftly {
            sut?.residesInPath("..snippet..", false) shouldBeEqualTo true
            sut?.residesInPath("..koexplicitbackingfield/snippet..", false) shouldBeEqualTo true
            sut?.residesInPath(
                "..koexplicitbackingfield..explicit-backing-field-reside-in-project-file-path.kt",
                false,
            ) shouldBeEqualTo true
            sut?.residesInPath("koexplicitbackingfield/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koexplicitbackingfield/snippet/forkopathprovider/", fileName)
}
