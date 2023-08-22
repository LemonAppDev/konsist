package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoLocalClassProviderTest {
    @Test
    fun `init-block-contains-no-local-classes`() {
        // given
        val sut = getSnippetFile("init-block-contains-no-local-classes")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        assertSoftly(sut) {
            numLocalClasses shouldBeEqualTo 0
            countLocalClasses { it.name == "SampleLocalClass" } shouldBeEqualTo 0
            containsLocalClass { it.name == "SampleLocalClass" } shouldBeEqualTo false
            localClasses shouldBeEqualTo emptyList()
        }
    }

    @Test
    fun `init-block-contains-local-class`() {
        // given
        val sut = getSnippetFile("init-block-contains-local-class")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        assertSoftly(sut) {
            numLocalClasses shouldBeEqualTo 2
            countLocalClasses { it.name == "SampleLocalClass1" } shouldBeEqualTo 1
            containsLocalClass { it.name == "SampleLocalClass1"} shouldBeEqualTo true
            containsLocalClass { it.name == "OtherClass"} shouldBeEqualTo false
            localClasses
                .map { it.name }
                .shouldBeEqualTo(listOf("SampleLocalClass1", "SampleLocalClass2"))
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkolocalclassprovider/", fileName)
}
