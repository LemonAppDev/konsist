package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.core.ext.sep
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForResideInTest {
    @Test
    fun `reside-in-path`() {
        // given
        val sut = getSnippetFile("reside-in-path")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..") shouldBeEqualTo true
            resideInPath("..kofile${sep}snippet..") shouldBeEqualTo true
            resideInPath("..kofile..reside-in-path.kt") shouldBeEqualTo true
            resideInPath("kofile${sep}snippet$sep") shouldBeEqualTo false
        }
    }

    @Test
    fun `reside-in-root-project-path`() {
        // given
        val sut = getSnippetFile("reside-in-root-project-path")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            resideInRootProjectPath("..snippet..") shouldBeEqualTo true
            resideInRootProjectPath("..kofile${sep}snippet..") shouldBeEqualTo true
            resideInRootProjectPath("..kofile..reside-in-root-project-path.kt") shouldBeEqualTo true
            resideInRootProjectPath("kofile${sep}snippet$sep") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/container/kofile/snippet/forresidein/".toNormalizedPath(), fileName)
}
