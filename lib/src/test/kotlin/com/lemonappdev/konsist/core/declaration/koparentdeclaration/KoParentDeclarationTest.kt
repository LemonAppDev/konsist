package com.lemonappdev.konsist.core.declaration.koparentdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationTest {

    @Test
    fun `class-has-parent-class`() {
        // given
        val sut = getSnippetFile("class-has-parent-class")
            .classes()
            .first()
            .parentClass

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "SampleSuperClass"
            it?.delegateName shouldBeEqualTo null
            it?.hasDelegate() shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-parent-interface`() {
        // given
        val sut = getSnippetFile("class-has-parent-interface")
            .classes()
            .first()
            .parentInterfaces
            .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleSuperInterface"
            delegateName shouldBeEqualTo null
            hasDelegate() shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-parent-by-delegation`() {
        // given
        val sut = getSnippetFile("class-has-parent-by-delegation")
            .classes()
            .first()
            .parentInterfaces
            .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleSuperInterface"
            delegateName shouldBeEqualTo "sampleProperty"
            hasDelegate() shouldBeEqualTo true
            hasDelegate("SampleSuperInterface") shouldBeEqualTo true
            hasDelegate("SampleOtherInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-parents`() {
        // given
        val sut = getSnippetFile("class-has-parents")
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
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparentdeclaration/snippet/", fileName)
}
