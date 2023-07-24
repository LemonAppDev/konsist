package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
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
            containsLocalClass("SampleClass") shouldBeEqualTo false
            localClasses()
                .toList()
                .shouldBeEqualTo(emptyList())
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
            containsLocalClass("SampleClass") shouldBeEqualTo true
            localClasses()
                .toList()
                .map { it.name }
                .shouldBeEqualTo(listOf("SampleClass"))
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forkolocalclassprovider/", fileName)
}
