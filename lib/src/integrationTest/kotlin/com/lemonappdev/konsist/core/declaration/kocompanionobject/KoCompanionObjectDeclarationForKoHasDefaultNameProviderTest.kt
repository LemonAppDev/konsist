package com.lemonappdev.konsist.core.declaration.kocompanionobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoCompanionObjectDeclarationForKoHasDefaultNameProviderTest {
    @Test
    fun `companion-object-has-default-name`() {
        // given
        val sut =
            getSnippetFile("companion-object-has-default-name")
                .companionObjects()
                .first()

        // then
        sut.hasDefaultName shouldBeEqualTo true
    }

    @Test
    fun `companion-object-has-given-name`() {
        // given
        val sut =
            getSnippetFile("companion-object-has-given-name")
                .companionObjects()
                .first()

        // then
        sut.hasDefaultName shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kocompanionobject/snippet/forkohasdefaultnameprovider/", fileName)
}
