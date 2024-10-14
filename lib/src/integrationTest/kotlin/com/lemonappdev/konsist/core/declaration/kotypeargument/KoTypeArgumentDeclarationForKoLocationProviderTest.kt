package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeArgumentDeclarationForKoLocationProviderTest {
    @Test
    fun `not-generic-type-argument-location`() {
        // given
        val sut =
            getSnippetFile("not-generic-type-argument-location")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:1:26"
    }

    @Test
    fun `not-generic-type-argument-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("not-generic-type-argument-location-with-text")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.projectPath

        val sut =
            getSnippetFile("not-generic-type-argument-location-with-text")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        val declaration = "Declaration:\nString"
        assertSoftly(sut?.locationWithText) {
            it?.startsWith("Location: /") shouldBeEqualTo true
            projectPath?.let { path -> it?.contains(path) } shouldBeEqualTo true
            it?.endsWith(declaration) shouldBeEqualTo true
        }
    }

    @Test
    fun `generic-type-argument-location`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-location")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:1:26"
    }

    @Test
    fun `generic-type-argument-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("generic-type-argument-location-with-text")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.projectPath

        val sut =
            getSnippetFile("generic-type-argument-location-with-text")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        val declaration = "Declaration:\nSet<String>"
        assertSoftly(sut?.locationWithText) {
            it?.startsWith("Location: /") shouldBeEqualTo true
            projectPath?.let { path -> it?.contains(path) } shouldBeEqualTo true
            it?.endsWith(declaration) shouldBeEqualTo true
        }
    }

    @Test
    fun `generic-complex-type-argument-location`() {
        // given
        val sut =
            getSnippetFile("generic-complex-type-argument-location")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:1:26"
    }

    @Test
    fun `generic-complex-type-argument-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("generic-complex-type-argument-location-with-text")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.projectPath

        val sut =
            getSnippetFile("generic-complex-type-argument-location-with-text")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        val declaration = "Declaration:\nMap<List<String>, Int>"
        assertSoftly(sut?.locationWithText) {
            it?.startsWith("Location: /") shouldBeEqualTo true
            projectPath?.let { path -> it?.contains(path) } shouldBeEqualTo true
            it?.endsWith(declaration) shouldBeEqualTo true
        }
    }

    @Test
    fun `star-projection-type-argument-location`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-argument-location")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:1:26"
    }

    @Test
    fun `star-projection-type-argument-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("star-projection-type-argument-location-with-text")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.projectPath

        val sut =
            getSnippetFile("star-projection-type-argument-location-with-text")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        val declaration = "Declaration:\n*"
        assertSoftly(sut?.locationWithText) {
            it?.startsWith("Location: /") shouldBeEqualTo true
            projectPath?.let { path -> it?.contains(path) } shouldBeEqualTo true
            it?.endsWith(declaration) shouldBeEqualTo true
        }
    }

    @Test
    fun `out-projection-type-argument-location`() {
        // given
        val sut =
            getSnippetFile("out-projection-type-argument-location")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:1:33"
    }

    @Test
    fun `out-projection-type-argument-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("out-projection-type-argument-location-with-text")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.projectPath

        val sut =
            getSnippetFile("out-projection-type-argument-location-with-text")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        val declaration = "Declaration:\nout String"
        assertSoftly(sut?.locationWithText) {
            it?.startsWith("Location: /") shouldBeEqualTo true
            projectPath?.let { path -> it?.contains(path) } shouldBeEqualTo true
            it?.endsWith(declaration) shouldBeEqualTo true
        }
    }

    @Test
    fun `in-projection-type-argument-location`() {
        // given
        val sut =
            getSnippetFile("in-projection-type-argument-location")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:1:33"
    }

    @Test
    fun `in-projection-type-argument-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("in-projection-type-argument-location-with-text")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.projectPath

        val sut =
            getSnippetFile("in-projection-type-argument-location-with-text")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        val declaration = "Declaration:\nin String"
        assertSoftly(sut?.locationWithText) {
            it?.startsWith("Location: /") shouldBeEqualTo true
            projectPath?.let { path -> it?.contains(path) } shouldBeEqualTo true
            it?.endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypeargument/snippet/forkolocationprovider/",
            fileName,
        )
}
