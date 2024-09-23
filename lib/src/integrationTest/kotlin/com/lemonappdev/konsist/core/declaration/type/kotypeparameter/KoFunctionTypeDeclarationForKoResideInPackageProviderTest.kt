package com.lemonappdev.konsist.core.declaration.type.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.returnTypes
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `type-parameter-reside-in-package`() {
        // given
        val sut =
            getSnippetFile("type-parameter-reside-in-package")
.functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPackage("com..") shouldBeEqualTo true
            it?.resideInPackage("com") shouldBeEqualTo false
            it?.resideOutsidePackage("com..") shouldBeEqualTo false
            it?.resideOutsidePackage("com") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotypeparameter/snippet/forkoresideinpackageprovider/", fileName)
}
