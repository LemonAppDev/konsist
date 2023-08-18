package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoLocalPropertyProviderTest {
    @Test
    fun `init-block-contains-no-local-property`() {
        // given
        val sut = getSnippetFile("init-block-contains-no-local-property")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        assertSoftly(sut) {
            numLocalProperties shouldBeEqualTo 0
            containsLocalProperty("sampleLocalProperty") shouldBeEqualTo false
            localProperties
                .shouldBeEqualTo(emptyList())
        }
    }

    @Test
    fun `init-block-contains-local-property`() {
        // given
        val sut = getSnippetFile("init-block-contains-local-property")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        assertSoftly(sut) {
            numLocalProperties shouldBeEqualTo 1
            containsLocalProperty("sampleLocalProperty") shouldBeEqualTo true
            localProperties
                .map { it.name }
                .shouldBeEqualTo(listOf("sampleLocalProperty"))
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkolocalpropertyprovider/", fileName)
}
