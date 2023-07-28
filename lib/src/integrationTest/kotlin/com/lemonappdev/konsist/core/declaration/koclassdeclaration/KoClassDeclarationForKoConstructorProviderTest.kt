package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoConstructorProviderTest {
    @Test
    fun `class-has-primary-and-secondary-constructors`() {
        // given
        val sut = getSnippetFile("class-has-primary-and-secondary-constructors")
            .classes()
            .first()

        // then
        sut.constructors shouldHaveSize 3
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forkoconstructorsprovider/", fileName)
}
