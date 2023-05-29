package com.lemonappdev.konsist.core.declaration.koparentdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForHasDelegateTest {
    @Test
    fun `class-has-parent-class-without-delegate`() {
        // given
        val sut = getSnippetFile("class-has-parent-class-without-delegate")
            .classes()
            .first()
            .parentClass

        // then
        sut?.hasDelegate() shouldBeEqualTo false
    }

    @Test
    fun `class-has-parent-interface-without-delegate`() {
        // given
        val sut = getSnippetFile("class-has-parent-interface-without-delegate")
            .classes()
            .first()
            .parentInterfaces
            .first()

        // then
        sut.hasDelegate() shouldBeEqualTo false
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
            hasDelegate() shouldBeEqualTo true
            hasDelegate("SampleSuperInterface") shouldBeEqualTo true
            hasDelegate("SampleOtherInterface") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparentdeclaration/snippet/forhasdelegate/".toNormalizedPath(), fileName)
}
