package com.lemonappdev.konsist.core.declaration.kopackage

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageDeclarationForKoTextProviderTest {
    @Test
    fun `package-text`() {
        // given
        val sut =
            getSnippetFile("package-text")
                .packages
                .first()

        // then
        sut.text shouldBeEqualTo "package com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kopackage/snippet/forkotextprovider/", fileName)
}
