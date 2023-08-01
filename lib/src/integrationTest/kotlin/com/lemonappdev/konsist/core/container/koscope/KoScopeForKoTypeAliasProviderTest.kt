package com.lemonappdev.konsist.core.container.koscope

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForKoTypeAliasProviderTest {
    @Test
    fun `scope-has-no-typealias`() {
        // given
        val sut = getSnippetFile("scope-has-no-typealias")

        // then
        assertSoftly(sut) {
            typeAliases shouldBeEqualTo emptyList()
            hasTypeAliases() shouldBeEqualTo false
            hasTypeAliases("SampleTypeAlias") shouldBeEqualTo false
        }
    }

    @Test
    fun `scope-contains-typealias`() {
        // given
        val sut = getSnippetFile("scope-contains-typealias")

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
    fun `scope-has-typealiases`() {
        // given
        val sut = getSnippetFile("scope-has-typealiases")

        // then
        assertSoftly(sut) {
            hasTypeAliases() shouldBeEqualTo true
            hasTypeAliases("SampleTypeAlias1") shouldBeEqualTo true
            hasTypeAliases("SampleTypeAlias1", "SampleTypeAlias2") shouldBeEqualTo true
            hasTypeAliases("OtherTypeAlias") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/koscope/snippet/forkotypealiasprovider/", fileName)
}
