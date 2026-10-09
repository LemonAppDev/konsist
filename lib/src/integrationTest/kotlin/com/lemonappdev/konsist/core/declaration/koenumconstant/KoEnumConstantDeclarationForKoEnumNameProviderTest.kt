package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoEnumNameProviderTest {
    @Test
    fun `enum-name`() {
        // given
        val sut =
            getSnippetFile("enum-name")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        sut.enumName shouldBeEqualTo "FixtureClass"
    }

    @Test
    fun `full-enum-name`() {
        // given
        val sut =
            getSnippetFile("full-enum-name")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        sut.fullEnumName shouldBeEqualTo "FixtureClass.FIXTURE_CONSTANT_1"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkoenumnameprovider/", fileName)
}
