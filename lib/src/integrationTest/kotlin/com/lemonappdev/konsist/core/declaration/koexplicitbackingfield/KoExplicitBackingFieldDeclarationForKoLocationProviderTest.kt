package com.lemonappdev.konsist.core.declaration.koexplicitbackingfield

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.core.filesystem.PathProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExplicitBackingFieldDeclarationForKoLocationProviderTest {
    @Test
    fun `explicit-backing-field-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-location-with-single-digit")
                .properties()
                .first()
                .explicitBackingField

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:2:5"
    }

    @Test
    fun `explicit-backing-field-location-with-text`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-location-with-text")
                .properties()
                .first()
                .explicitBackingField

        // then
        assertSoftly(sut?.locationWithText) {
            it?.startsWith("Location: ${PathProvider.rootProjectPath}") shouldBeEqualTo true
            sut?.projectPath?.let { path -> it?.contains(path) } shouldBeEqualTo true
            it?.contains("field = mutableListOf(0)") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koexplicitbackingfield/snippet/forkolocationprovider/", fileName)
}
