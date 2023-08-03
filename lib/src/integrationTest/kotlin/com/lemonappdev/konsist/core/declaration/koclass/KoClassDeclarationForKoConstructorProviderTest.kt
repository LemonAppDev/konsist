package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoConstructorProviderTest {
    @Test
    fun `class-has-no-constructors`() {
        // given
        val sut = getSnippetFile("class-has-no-constructors")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            constructors shouldBeEqualTo emptyList()
            numConstructors shouldBeEqualTo 0
        }
    }

    @Test
    fun `class-has-primary-and-secondary-constructors`() {
        // given
        val sut = getSnippetFile("class-has-primary-and-secondary-constructors")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            constructors shouldNotBeEqualTo emptyList()
            numConstructors shouldBeEqualTo 3
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forkoconstructorsprovider/", fileName)
}
