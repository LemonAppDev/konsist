package com.lemonappdev.konsist.core.declaration.kopackage

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageDeclarationForKoNameProviderTest {
    @Test
    fun `package-does-not-exists`() {
        // given
        val sut =
            getSnippetFile("package-does-not-exists")
                .packages
                .firstOrNull()

        // then
        sut?.name shouldBeEqualTo null
    }

    @Test
    fun `package-has-name`() {
        // given
        val sut =
            getSnippetFile("package-has-name")
                .packages
                .first()

        // then
        sut.name shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopackage/snippet/forkonameprovider/", fileName)
}
