package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoLocationProviderTest {
    @Test
    fun `parent-of-class-location`() {
        // given
        val sut = getSnippetFile("parent-of-class-location")
            .classes()
            .parents
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:3:23"
    }

    @Test
    fun `parent-of-class-location-with-text`() {
        // given
        val projectPath = getSnippetFile("parent-of-class-location-with-text")
            .classes()
            .parents
            .first()
            .projectPath

        val sut = getSnippetFile("parent-of-class-location-with-text")
            .classes()
            .parents
            .first()

        // then
        val declaration = "Declaration:\nSampleSuperInterface"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    @Test
    fun `parent-of-interface-location`() {
        // given
        val sut = getSnippetFile("parent-of-interface-location")
            .interfaces()
            .parents
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:1:29"
    }

    @Test
    fun `parent-of-interface-location-with-text`() {
        // given
        val projectPath = getSnippetFile("parent-of-interface-location-with-text")
            .interfaces()
            .parents
            .first()
            .projectPath

        val sut = getSnippetFile("parent-of-interface-location-with-text")
            .interfaces()
            .parents
            .first()

        // then
        val declaration = "Declaration:\nSampleSuperInterface"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    @Test
    fun `parent-of-object-location`() {
        // given
        val sut = getSnippetFile("parent-of-object-location")
            .objects()
            .parents
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:3:22"
    }

    @Test
    fun `parent-of-object-location-with-text`() {
        // given
        val projectPath = getSnippetFile("parent-of-object-location-with-text")
            .objects()
            .parents
            .first()
            .projectPath

        val sut = getSnippetFile("parent-of-object-location-with-text")
            .objects()
            .parents
            .first()

        // then
        val declaration = "Declaration:\nSampleSuperInterface"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparent/snippet/forkolocationprovider/", fileName)
}
