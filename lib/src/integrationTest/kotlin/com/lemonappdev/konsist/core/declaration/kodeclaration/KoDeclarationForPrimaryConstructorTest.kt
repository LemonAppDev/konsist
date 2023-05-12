package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoDeclarationForPrimaryConstructorTest {


    @Test
    fun `primary-constructor-has-modifier`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-modifier")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `primary-constructor-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-modifiers-and-annotation-with-parameter")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `primary-constructor-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-modifiers-and-annotation-without-parameter")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `primary-constructor-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-modifiers-and-annotations")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kodeclaration/snippet/forprimaryconstructor/", fileName)
}
