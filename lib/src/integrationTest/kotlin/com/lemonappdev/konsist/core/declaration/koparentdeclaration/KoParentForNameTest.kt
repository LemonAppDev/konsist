package com.lemonappdev.konsist.core.declaration.koparentdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentForNameTest {
    @Test
    fun `class-with-parent-class`() {
        // given
        val sut = getSnippetFile("class-with-parent-class")
            .classes()
            .first()
            .parentClass

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "SampleSuperClass"
            it?.delegateName shouldBeEqualTo null
        }
    }

    @Test
    fun `class-with-parent-interface`() {
        // given
        val sut = getSnippetFile("class-with-parent-interface")
            .classes()
            .first()
            .parentInterfaces
            .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleSuperInterface"
            delegateName shouldBeEqualTo null
        }
    }

    @Test
    fun `class-with-parent-by-delegation`() {
        // given
        val sut = getSnippetFile("class-with-parent-by-delegation")
            .classes()
            .first()
            .parentInterfaces
            .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleSuperInterface"
            delegateName shouldBeEqualTo "sampleProperty"
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparentdeclaration/snippet/forname/", fileName)
}
