package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoNameProviderTest {
    @Test
    fun `class-with-parent-class`() {
        // given
        val sut = getSnippetFile("class-with-parent-class")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleSuperClass"
    }

    @Test
    fun `class-with-generic-parent-class`() {
        // given
        val sut = getSnippetFile("class-with-generic-parent-class")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleGenericSuperClass"
    }

    @Test
    fun `class-with-parametrized-parent-class`() {
        // given
        val sut = getSnippetFile("class-with-parametrized-parent-class")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleParametrizedSuperClass"
    }

    @Test
    fun `class-with-parametrized-and-generic-parent-class`() {
        // given
        val sut = getSnippetFile("class-with-parametrized-and-generic-parent-class")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleParametrizedSuperClass"
    }

    @Test
    fun `class-with-parent-interface`() {
        // given
        val sut = getSnippetFile("class-with-parent-interface")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleSuperInterface"
    }

    @Test
    fun `class-with-generic-parent-interface`() {
        // given
        val sut = getSnippetFile("class-with-generic-parent-interface")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleGenericSuperInterface"
    }

    @Test
    fun `class-with-parent-by-delegation`() {
        // given
        val sut = getSnippetFile("class-with-parent-by-delegation")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleSuperInterface"
    }

    @Test
    fun `class-with-multiline-parent`() {
        // given
        val sut = getSnippetFile("class-with-multiline-parent")
            .classes()
            .first()
            .parents
            .first()

        // then
        sut
            .name
            .shouldBeEqualTo("SomeParentClass")
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparent/snippet/forkonameprovider/", fileName)
}
