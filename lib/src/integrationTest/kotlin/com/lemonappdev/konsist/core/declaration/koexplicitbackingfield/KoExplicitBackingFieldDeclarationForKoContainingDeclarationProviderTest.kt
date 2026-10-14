package com.lemonappdev.konsist.core.declaration.koexplicitbackingfield

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExplicitBackingFieldDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `explicit-backing-field-parent-declaration`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-parent-declaration")
                .properties()
                .first()
                .explicitBackingField

        // then
        (sut?.containingDeclaration as KoNameProvider).name shouldBeEqualTo "fixtureProperty"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koexplicitbackingfield/snippet/forkocontainingdeclarationprovider/", fileName)
}
