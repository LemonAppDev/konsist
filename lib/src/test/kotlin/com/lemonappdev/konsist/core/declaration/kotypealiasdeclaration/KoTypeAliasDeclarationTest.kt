package com.lemonappdev.konsist.core.declaration.kotypealiasdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationTest {

    @Test
    fun `typealias`() {
        // given
        val sut = getSnippetFile("typealias")
            .files()
            .first()
            .typeAliases
            .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleTypeAlias"
            type.sourceType shouldBeEqualTo "() -> Int"
        }
    }

    @Test
    fun `no-typealias`() {
        // given
        val sut = getSnippetFile("no-typealias")
            .files()
            .first()
            .typeAliases

        // then
        sut.isEmpty() shouldBeEqualTo true
    }

    @Test
    fun `typealias-has-actual-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-actual-modifier")
            .files()
            .first()
            .typeAliases
            .first()

        // then
        sut.hasActualModifier() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypealiasdeclaration/snippet/", fileName)
}
