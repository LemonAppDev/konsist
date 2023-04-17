package com.lemon.konsist.core.declaration.koparent

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentTest {

    @Test
    fun `class-has-parent-class`() {
        // given
        val sut = getSnippetFile("class-has-parent-class")
            .classes()
            .first()
            .parentClass

        // then
        sut?.run {
            name shouldBeEqualTo "SampleSuperClass"
            delegateName shouldBeEqualTo null
            hasDelegate() shouldBeEqualTo false
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
        sut.run {
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
        sut.run {
            name shouldBeEqualTo "SampleSuperInterface"
            delegateName shouldBeEqualTo "SampleSuperInterface by sampleProperty"
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
        sut.run {
            map { it.name } shouldBeEqualTo listOf("SampleSuperInterface1", "SampleSuperInterface2", "SampleSuperClass")
            map { it.delegateName } shouldBeEqualTo listOf("SampleSuperInterface1 by sampleProperty", null, null)
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparent/snippet/", fileName)
}
