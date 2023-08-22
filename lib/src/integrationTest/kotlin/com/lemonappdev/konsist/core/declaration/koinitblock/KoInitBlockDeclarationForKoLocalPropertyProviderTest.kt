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
            countLocalProperties { it.name == "sampleLocalProperty" } shouldBeEqualTo 0
            containsLocalProperty { it.name == "sampleLocalProperty"} shouldBeEqualTo false
            localProperties shouldBeEqualTo emptyList()
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
            numLocalProperties shouldBeEqualTo 2
            countLocalProperties { it.name == "sampleLocalProperty1" } shouldBeEqualTo 1
            containsLocalProperty { it.name == "sampleLocalProperty1"} shouldBeEqualTo true
            containsLocalProperty { it.name == "otherLocalProperty"} shouldBeEqualTo false
            localProperties
                .map { it.name }
                .shouldBeEqualTo(listOf("sampleLocalProperty1", "sampleLocalProperty2"))
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkolocalpropertyprovider/", fileName)
}
