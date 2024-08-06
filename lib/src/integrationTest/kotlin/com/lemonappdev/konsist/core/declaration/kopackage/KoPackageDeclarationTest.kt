package com.lemonappdev.konsist.core.declaration.kopackage

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageDeclarationTest {
    @Test
    fun `package-to-string`() {
        // given
        val sut =
            getSnippetFile("package-to-string")
                .packages
                .first()

        // then
        sut.toString() shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopackage/snippet/forgeneral/", fileName)
}
