package com.lemonappdev.konsist.core.declaration.type.kostarprojection

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.returnTypes
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoStarProjectionDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `star-projection-reside-in-package`() {
        // given
        val sut =
            getSnippetFile("star-projection-reside-in-package")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration

        // then
        assertSoftly(sut) {
            it?.resideInPackage("com..") shouldBeEqualTo true
            it?.resideInPackage("com") shouldBeEqualTo false
            it?.resideOutsidePackage("com..") shouldBeEqualTo false
            it?.resideOutsidePackage("com") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kostarprojection/snippet/forkoresideinpackageprovider/", fileName)
}
