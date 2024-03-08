package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoPrimaryConstructorProviderTest {
    @Test
    fun `class-has-no-primary-constructor`() {
        // given
        val sut =
            getSnippetFile("class-has-no-primary-constructor")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            primaryConstructor shouldBeEqualTo null
            hasPrimaryConstructor shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-primary-constructor`() {
        // given
        val sut =
            getSnippetFile("class-has-primary-constructor")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            primaryConstructor shouldNotBeEqualTo null
            hasPrimaryConstructor shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkoprimaryconstructorprovider/", fileName)
}
