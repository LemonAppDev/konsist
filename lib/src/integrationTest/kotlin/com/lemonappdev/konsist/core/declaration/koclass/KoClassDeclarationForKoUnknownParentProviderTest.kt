package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface1
import com.lemonappdev.konsist.testdata.SampleParentInterface2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoUnknownParentProviderTest {
    @Test
    fun `class-has-no-unknown-parent`() {
        // given
        val sut = getSnippetFile("class-has-no-unknown-parent")
            .classes()
            .first()

        // then
        sut.unknownParents shouldBeEqualTo emptyList()
    }

    @Test
    fun `class-has-only-unknown-parents`() {
        // given
        val sut = getSnippetFile("class-has-only-unknown-parents")
            .classes()
            .first()

        // then
        sut.unknownParents.map { it.name } shouldBeEqualTo listOf("UnknownParent1", "UnknownParent2")
    }

    @Test
    fun `class-has-known-and-unknown-parents`() {
        // given
        val sut = getSnippetFile("class-has-known-and-unknown-parents")
            .classes()
            .first()

        // then
        sut.unknownParents.map { it.name } shouldBeEqualTo listOf("UnknownParent1", "UnknownParent2")
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkounknownparentprovider/", fileName)
}
