package com.lemonappdev.konsist.core.declaration.type.kogenerictype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGenericTypeDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `generic-type-reside-in-package`() {
        // given
        val sut =
            getSnippetFile("generic-type-reside-in-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPackage("com..") shouldBeEqualTo true
            it?.resideInPackage("com") shouldBeEqualTo false
            it?.resideOutsidePackage("com..") shouldBeEqualTo false
            it?.resideOutsidePackage("com") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kogenerictype/snippet/forkoresideinpackageprovider/", fileName)
}
