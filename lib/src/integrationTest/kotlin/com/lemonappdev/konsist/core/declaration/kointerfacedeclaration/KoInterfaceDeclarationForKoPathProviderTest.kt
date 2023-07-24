package com.lemonappdev.konsist.core.declaration.kointerfacedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.ABSTRACT
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoInterfaceDeclarationForKoPathProviderTest {
    @Test
    fun `interface-file-path`() {
        // given
        val sut = getSnippetFile("interface-file-path")
            .interfaces()
            .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("kointerfacedeclaration/snippet/forkopathprovider/interface-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-project-file-path`() {
        // given
        val sut = getSnippetFile("interface-project-file-path")
            .interfaces()
            .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kointerfacedeclaration/snippet/" +
                        "forkopathprovider/interface-project-file-path.kt",
            )
    }

    @Test
    fun `interface-reside-in-file-path`() {
        // given
        val sut = getSnippetFile("interface-reside-in-file-path")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..kointerfacedeclaration/snippet..", true) shouldBeEqualTo true
            resideInPath("..kointerfacedeclaration..interface-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("kointerfacedeclaration/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-reside-in-project-file-path`() {
        // given
        val sut = getSnippetFile("interface-reside-in-project-file-path")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..kointerfacedeclaration/snippet..", false) shouldBeEqualTo true
            resideInPath("..kointerfacedeclaration..interface-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("kointerfacedeclaration/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerfacedeclaration/snippet/forkopathprovider/", fileName)
}
