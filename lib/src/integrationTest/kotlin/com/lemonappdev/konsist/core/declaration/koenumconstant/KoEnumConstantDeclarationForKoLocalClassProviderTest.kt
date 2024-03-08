package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoLocalClassProviderTest {
    @Test
    fun `enum-constant-contains-no-local-classes`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-no-local-classes")
                .classes()
                .enumConstants
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
    fun `enum-constant-contains-local-class`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-local-class")
                .classes()
                .enumConstants
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
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkolocalclassprovider/", fileName)
}
