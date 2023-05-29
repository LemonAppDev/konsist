package com.lemonappdev.konsist.core.declaration.koparentdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForNameTest {
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

    @Test
    fun `class-with-parents`() {
        // given
        val sut = getSnippetFile("class-with-parents")
            .classes()
            .first()
            .parents

        // then
        assertSoftly(sut) {
            map { it.name } shouldBeEqualTo listOf("SampleSuperInterface1", "SampleSuperInterface2", "SampleSuperClass")
            mapNotNull { it.delegateName } shouldBeEqualTo listOf("sampleProperty")
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparentdeclaration/snippet/forname/".toNormalizedPath(), fileName)
}
