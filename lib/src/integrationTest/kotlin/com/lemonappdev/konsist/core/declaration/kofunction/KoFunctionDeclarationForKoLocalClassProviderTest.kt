package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoLocalClassProviderTest {
    @Test
    fun `function-contains-no-local-classes`() {
        // given
        val sut =
            getSnippetFile("function-contains-no-local-classes")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            localClasses shouldBeEqualTo emptyList()
            numLocalClasses shouldBeEqualTo 0
            countLocalClasses { it.name == "SampleClass" } shouldBeEqualTo 0
            hasLocalClasses() shouldBeEqualTo false
            hasLocalClassWithName("SampleClass") shouldBeEqualTo false
            hasLocalClassesWithAllNames("SampleClass1", "SampleClass2") shouldBeEqualTo false
            hasLocalClass { it.name == "SampleClass" } shouldBeEqualTo false
            hasAllLocalClasses { it.name == "SampleClass" } shouldBeEqualTo true
            containsLocalClass { it.name == "SampleClass" } shouldBeEqualTo false
        }
    }

    @Test
    fun `function-contains-local-class`() {
        // given
        val sut =
            getSnippetFile("function-contains-local-class")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            localClasses.map { it.name } shouldBeEqualTo listOf("SampleClass1", "SampleClass2")
            numLocalClasses shouldBeEqualTo 2
            countLocalClasses { it.name == "SampleClass1" } shouldBeEqualTo 1
            hasLocalClasses() shouldBeEqualTo true
            hasLocalClassWithName("SampleClass1") shouldBeEqualTo true
            hasLocalClassWithName("OtherClass") shouldBeEqualTo false
            hasLocalClassWithName("SampleClass1", "OtherClass") shouldBeEqualTo true
            hasLocalClassesWithAllNames("SampleClass1") shouldBeEqualTo true
            hasLocalClassesWithAllNames("SampleClass1", "SampleClass2") shouldBeEqualTo true
            hasLocalClassesWithAllNames("SampleClass1", "OtherClass") shouldBeEqualTo false
            hasLocalClass { it.name == "SampleClass1" } shouldBeEqualTo true
            hasLocalClass { it.name == "OtherClass" } shouldBeEqualTo false
            hasAllLocalClasses { it.name.endsWith("2") || it.name == "SampleClass1" } shouldBeEqualTo true
            hasAllLocalClasses { it.name.endsWith("2") } shouldBeEqualTo false
            containsLocalClass { it.name == "SampleClass1" } shouldBeEqualTo true
            containsLocalClass { it.name == "OtherClass" } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/snippet/forkolocalclassprovider/", fileName)
}
