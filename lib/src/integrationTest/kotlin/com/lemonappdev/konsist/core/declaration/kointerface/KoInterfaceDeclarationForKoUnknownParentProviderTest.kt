package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface1
import com.lemonappdev.konsist.testdata.SampleParentInterface2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoUnknownParentProviderTest {
    @Test
    fun `interface-has-no-unknown-parent`() {
        // given
        val sut = getSnippetFile("interface-has-no-unknown-parent")
            .interfaces()
            .first()

        // then
        sut.unknownParents shouldBeEqualTo emptyList()
    }

    @Test
    fun `interface-has-only-unknown-parents`() {
        // given
        val sut = getSnippetFile("interface-has-only-unknown-parents")
            .interfaces()
            .first()

        // then
        sut.unknownParents.map { it.name } shouldBeEqualTo listOf("UnknownParent1", "UnknownParent2")
    }

    @Test
    fun `interface-has-known-and-unknown-parents`() {
        // given
        val sut = getSnippetFile("interface-has-known-and-unknown-parents")
            .interfaces()
            .first()

        // then
        sut.unknownParents.map { it.name } shouldBeEqualTo listOf("UnknownParent1", "UnknownParent2")
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkounknownparentprovider/", fileName)
}
