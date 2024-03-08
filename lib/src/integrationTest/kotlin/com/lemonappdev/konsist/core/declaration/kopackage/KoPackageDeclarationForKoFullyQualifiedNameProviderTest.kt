package com.lemonappdev.konsist.core.declaration.kopackage

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `package-has-qualified-name`() {
        // given
        val sut =
            getSnippetFile("package-has-qualified-name")
                .packages

        // then
        sut
            .map { it.fullyQualifiedName }
            .first()
            .shouldBeEqualTo("com.samplepackage")
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopackage/snippet/forkofullyqualifiednameprovider/", fileName)
}
