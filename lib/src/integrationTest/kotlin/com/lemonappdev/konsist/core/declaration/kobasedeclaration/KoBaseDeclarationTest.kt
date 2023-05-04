package com.lemonappdev.konsist.core.declaration.kobasedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoBaseDeclarationTest {
    @Test
    fun `containing-file`() {
        // given
        val sut = getSnippetFile("containing-file")
            .files()
            .first()

        // then
        sut
            .containingFile
            .name
            .endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    @Test
    fun `class-with-parent-declaration`() {
        // given
        val sut = getSnippetFile("class-with-parent-declaration")
            .classes(includeNested = true, includeLocal = true)
            .last()

        // then
        assertSoftly(sut) {
            parentDeclaration shouldNotBeEqualTo null
            parentDeclaration?.name shouldBeEqualTo "SampleClass"
            parentDeclaration?.parentDeclaration?.name shouldBeEqualTo "SampleTopLevelInterface"
        }
    }

    @Test
    fun `class-has-parent-declaration`() {
        // given
        val sut = getSnippetFile("class-has-parent-declaration")
            .classes(includeNested = true, includeLocal = true)
            .last()

        // then
        assertSoftly(sut) {
            hasParentDeclaration() shouldBeEqualTo true
            hasParentDeclaration("SampleClass") shouldBeEqualTo true
            hasParentDeclaration("SampleOtherClass") shouldBeEqualTo false
            hasParentDeclaration("SampleTopLevelInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-without-parent-declaration`() {
        // given
        val sut = getSnippetFile("class-without-parent-declaration")
            .classes(includeNested = true, includeLocal = true)
            .last()

        // then
        assertSoftly(sut) {
            hasParentDeclaration() shouldBeEqualTo false
            hasParentDeclaration("SampleClass") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kobasedeclaration/snippet/", fileName)
}
