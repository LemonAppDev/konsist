package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.core.filesystem.PathProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoLocationProviderTest {
    @Test
    fun `parameter-in-constructor-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-location-with-single-digit")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:3:20"
    }

    @Test
    fun `parameter-in-function-invocation-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-location-with-single-digit")
                .functions()
                .first()
                .parameters
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:3:21"
    }

    @Test
    fun `parameter-in-constructor-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("parameter-in-constructor-location-with-text")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.projectPath

        val sut =
            getSnippetFile("parameter-in-constructor-location-with-text")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        val declaration = "Declaration:\nval fixtureParameter: FixtureType"
        assertSoftly(sut?.locationWithText) {
            it?.startsWith("Location: ${PathProvider.rootProjectPath}") shouldBeEqualTo true
            projectPath?.let { string -> it?.contains(string) } shouldBeEqualTo true
            it?.endsWith(declaration) shouldBeEqualTo true
        }
    }

    @Test
    fun `parameter-in-function-invocation-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("parameter-in-function-invocation-location-with-text")
                .functions()
                .first()
                .parameters
                .first()
                .projectPath

        val sut =
            getSnippetFile("parameter-in-function-invocation-location-with-text")
                .functions()
                .first()
                .parameters
                .first()

        // then
        val declaration = "Declaration:\nfixtureParameter: FixtureType"
        assertSoftly(sut.locationWithText) {
            it.startsWith("Location: ${PathProvider.rootProjectPath}") shouldBeEqualTo true
            projectPath.let { string -> it.contains(string) } shouldBeEqualTo true
            it.endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/snippet/forkolocationprovider/", fileName)
}
