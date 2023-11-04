package com.lemonappdev.konsist.core.declaration.kotypealias

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `typealias-with-file-parent-declaration`() {
        // given
        val sut =
            getSnippetFile("typealias-with-file-parent-declaration")
                .typeAliases
                .first()

        // then
        assertSoftly(sut) {
            containingDeclaration shouldNotBeEqualTo null
            (containingDeclaration as KoNameProvider).name shouldBeEqualTo "typealias-with-file-parent-declaration"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kotypealias/snippet/forkocontainingdeclarationprovider/", fileName)
}
