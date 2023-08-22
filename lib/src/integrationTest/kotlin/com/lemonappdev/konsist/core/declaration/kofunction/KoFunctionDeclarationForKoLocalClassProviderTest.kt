package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoLocalClassProviderTest {
    @Test
    fun `function-contains-no-local-classes`() {
        // given
        val sut = getSnippetFile("function-contains-no-local-classes")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            numLocalClasses shouldBeEqualTo 0
            countLocalClasses { it.name == "SampleClass" } shouldBeEqualTo 0
            containsLocalClass { it.name == "SampleClass" } shouldBeEqualTo false
            localClasses shouldBeEqualTo emptyList()
        }
    }

    @Test
    fun `function-contains-local-class`() {
        // given
        val sut = getSnippetFile("function-contains-local-class")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            numLocalClasses shouldBeEqualTo 2
            countLocalClasses { it.name == "SampleClass1" } shouldBeEqualTo 1
            containsLocalClass { it.name == "SampleClass1"} shouldBeEqualTo true
            containsLocalClass { it.name == "OtherClass"} shouldBeEqualTo false
            localClasses
                .map { it.name }
                .shouldBeEqualTo(listOf("SampleClass1", "SampleClass2" ))
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/snippet/forkolocalclassprovider/", fileName)
}
