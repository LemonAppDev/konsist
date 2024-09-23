package com.lemonappdev.konsist.core.declaration.type.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.returnTypes
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterDeclarationForKoPackageProviderTest {
    @Test
    fun `type-parameter-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("type-parameter-is-not-in-package")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        sut?.packagee shouldBeEqualTo null
    }

    @Test
    fun `type-parameter-is-in-package`() {
        // given
        val sut =
            getSnippetFile("type-parameter-is-in-package")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        sut?.packagee?.name shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotypeparameter/snippet/forkopackageprovider/",
            fileName
        )
}
