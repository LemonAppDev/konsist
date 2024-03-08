package com.lemonappdev.konsist.core.declaration.kosecondaryconstructor

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSecondaryConstructorDeclarationTest {
    @Test
    fun `secondary-constructor-to-string`() {
        // given
        val sut =
            getSnippetFile("secondary-constructor-to-string")
                .classes()
                .first()
                .secondaryConstructors
                .first()

        // then
        val declaration = "Declaration:\nconstructor(): this(6)"
        assertSoftly(sut.toString()) {
            startsWith("Location: /") shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kosecondaryconstructor/snippet/forgeneral/", fileName)
}
