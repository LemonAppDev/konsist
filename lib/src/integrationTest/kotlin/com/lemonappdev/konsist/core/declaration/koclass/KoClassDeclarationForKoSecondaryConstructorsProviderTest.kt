package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoSecondaryConstructorsProviderTest {
    @Test
    fun `class-has-no-secondary-constructor`() {
        // given
        val sut = getSnippetFile("class-has-no-secondary-constructor")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            secondaryConstructors.isEmpty() shouldBeEqualTo true
            numSecondaryConstructors shouldBeEqualTo 0
            hasSecondaryConstructors shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-secondary-constructor`() {
        // given
        val sut = getSnippetFile("class-has-secondary-constructor")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            secondaryConstructors.isNotEmpty() shouldBeEqualTo true
            numSecondaryConstructors shouldBeEqualTo 1
            hasSecondaryConstructors shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-primary-and-secondary-constructor`() {
        // given
        val sut = getSnippetFile("class-has-primary-and-secondary-constructor")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            secondaryConstructors shouldHaveSize 1
            numSecondaryConstructors shouldBeEqualTo 1
            hasSecondaryConstructors shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkosecondaryconstructorsprovider/", fileName)
}
