package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForTypeAliasTest {
    @Test
    fun `file-contains-no-typealias`() {
        // given
        val sut = getSnippetFile("file-contains-no-typealias")
            .files()
            .first()

        // then
        sut
            .typeAliases
            .isEmpty()
            .shouldBeEqualTo(true)
    }

    @Test
    fun `file-has-no-typealias`() {
        // given
        val sut = getSnippetFile("file-has-no-typealias")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            hasTypeAliases() shouldBeEqualTo false
            hasTypeAliases("SampleTypeAlias") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-typealias`() {
        // given
        val sut = getSnippetFile("file-contains-typealias")
            .files()
            .first()

        // then
        assertSoftly(
            sut
                .typeAliases
                .first(),
        ) {
            name shouldBeEqualTo "SampleTypeAlias"
            type.sourceType shouldBeEqualTo "() -> Int"
        }
    }

    @Test
    fun `file-has-typealiases`() {
        // given
        val sut = getSnippetFile("file-has-typealiases")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            hasTypeAliases() shouldBeEqualTo true
            hasTypeAliases("SampleTypeAlias1") shouldBeEqualTo true
            hasTypeAliases("SampleTypeAlias1", "SampleTypeAlias2") shouldBeEqualTo true
            hasTypeAliases("OtherTypeAlias") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope(
        "core/container/kofile/snippet/fortypealias/".toNormalizedPath(),
        fileName,
    )
}
