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

class KoInterfaceDeclarationForKoLocationProviderTest {
    @Test
    fun `interface-location-with-single-digit`() {
        // given
        val sut = getSnippetFile("interface-location-with-single-digit")
            .interfaces()
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:1:1"
    }

    @Test
    fun `interface-location-with-double-digit`() {
        // given
        val sut = getSnippetFile("interface-location-with-double-digit")
            .interfaces(includeNested = true)
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:11:41"
    }

    @Test
    fun `interface-location-with-text`() {
        // given
        val projectPath = getSnippetFile("interface-location-with-text")
            .interfaces()
            .first()
            .projectPath

        val sut = getSnippetFile("interface-location-with-text")
            .interfaces()
            .first()

        // then
        val declaration = "Declaration:\ninterface SampleInterface {\n}"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerfacedeclaration/snippet/forkolocationprovider/", fileName)
}
