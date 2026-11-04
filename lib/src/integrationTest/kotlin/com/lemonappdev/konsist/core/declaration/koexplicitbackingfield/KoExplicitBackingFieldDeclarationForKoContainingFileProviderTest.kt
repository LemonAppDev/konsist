package com.lemonappdev.konsist.core.declaration.koexplicitbackingfield

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExplicitBackingFieldDeclarationForKoContainingFileProviderTest {
    @Test
    fun `explicit-backing-field-containing-file`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-containing-file")
                .properties()
                .first()
                .explicitBackingField

        // then
        sut
            ?.containingFile
            ?.nameWithExtension
            ?.endsWith("file.kt")
            ?.shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koexplicitbackingfield/snippet/forkocontainingfileprovider/", fileName)
}
