package com.lemonappdev.konsist.core.declaration.kotypealiasdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoParentProviderTest {
    @Test
    fun `typealias-without-parent`() {
        // given
        val sut = getSnippetFile("typealias-without-parent")
            .typeAliases
            .first()

        // then
        sut.parent shouldBeEqualTo null
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kotypealiasdeclaration/snippet/forkoparentprovider/", fileName)
}
