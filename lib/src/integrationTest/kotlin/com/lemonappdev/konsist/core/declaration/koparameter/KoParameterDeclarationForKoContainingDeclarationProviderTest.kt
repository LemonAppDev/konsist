package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `parameter-in-constructor-parent-declaration`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-parent-declaration")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.containingDeclaration?.shouldBeInstanceOf(KoClassDeclaration::class)
    }

    @Test
    fun `parameter-in-function-invocation-parent-declaration`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-parent-declaration")
                .functions()
                .first()
                .parameters
                .first()

        // then
        sut.containingDeclaration.shouldBeInstanceOf(KoFunctionDeclaration::class)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/snippet/forkocontainingdeclarationprovider/", fileName)
}
