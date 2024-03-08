package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.initBlocks
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoLocalClassProviderTest {
    @Test
    fun `init-block-contains-no-local-classes`() {
        // given
        val sut =
            getSnippetFile("init-block-contains-no-local-classes")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly(sut) {
            localClasses shouldBeEqualTo emptyList()
            numLocalClasses shouldBeEqualTo 0
            countLocalClasses { it.name == "SampleLocalClass" } shouldBeEqualTo 0
            hasLocalClasses() shouldBeEqualTo false
            hasLocalClassWithName("SampleLocalClass") shouldBeEqualTo false
            hasLocalClassesWithAllNames("SampleLocalClass1", "SampleLocalClass2") shouldBeEqualTo false
            hasLocalClass { it.name == "SampleLocalClass" } shouldBeEqualTo false
            hasAllLocalClasses { it.name == "SampleLocalClass" } shouldBeEqualTo true
            containsLocalClass { it.name == "SampleLocalClass" } shouldBeEqualTo false
        }
    }

    @Test
    fun `init-block-contains-local-class`() {
        // given
        val sut =
            getSnippetFile("init-block-contains-local-class")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly(sut) {
            localClasses.map { it.name } shouldBeEqualTo listOf("SampleLocalClass1", "SampleLocalClass2")
            numLocalClasses shouldBeEqualTo 2
            countLocalClasses { it.name == "SampleLocalClass1" } shouldBeEqualTo 1
            hasLocalClasses() shouldBeEqualTo true
            hasLocalClassWithName("SampleLocalClass1") shouldBeEqualTo true
            hasLocalClassWithName("OtherLocalClass") shouldBeEqualTo false
            hasLocalClassWithName("SampleLocalClass1", "OtherLocalClass") shouldBeEqualTo true
            hasLocalClassesWithAllNames("SampleLocalClass1") shouldBeEqualTo true
            hasLocalClassesWithAllNames("SampleLocalClass1", "SampleLocalClass2") shouldBeEqualTo true
            hasLocalClassesWithAllNames("SampleLocalClass1", "OtherLocalClass") shouldBeEqualTo false
            hasLocalClass { it.name == "SampleLocalClass1" } shouldBeEqualTo true
            hasLocalClass { it.name == "OtherLocalClass" } shouldBeEqualTo false
            hasAllLocalClasses { it.name.endsWith("2") || it.name == "SampleLocalClass1" } shouldBeEqualTo true
            hasAllLocalClasses { it.name.endsWith("2") } shouldBeEqualTo false
            containsLocalClass { it.name == "SampleLocalClass1" } shouldBeEqualTo true
            containsLocalClass { it.name == "OtherLocalClass" } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkolocalclassprovider/", fileName)
}
