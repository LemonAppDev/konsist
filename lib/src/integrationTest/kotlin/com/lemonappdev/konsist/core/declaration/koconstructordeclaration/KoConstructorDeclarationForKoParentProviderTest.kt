package com.lemonappdev.konsist.core.declaration.koconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorDeclarationForKoParentProviderTest {
    @Test
    fun `constructor-parent`() {
        // given
        val sut = getSnippetFile("constructor-parent")
            .classes()
            .first()
            .constructors
            .first()

        // then
        assertSoftly(sut) {
            parent shouldNotBeEqualTo null
            (parent as KoNameProvider).name shouldBeEqualTo "SampleClass"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koconstructordeclaration/snippet/forkoparentprovider/", fileName)
}
