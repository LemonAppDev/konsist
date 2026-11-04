package com.lemonappdev.konsist.core.declaration.koexplicitbackingfield

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.core.filesystem.PathProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExplicitBackingFieldDeclarationTest {
    @Test
    fun `explicit-backing-field-to-string`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-to-string")
                .properties()
                .first()
                .explicitBackingField

        // then
        assertSoftly(sut.toString()) {
            startsWith("Location: ${PathProvider.rootProjectPath}") shouldBeEqualTo true
            contains("field = mutableListOf(0)") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koexplicitbackingfield/snippet/forgeneral/", fileName)
}
