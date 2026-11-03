package com.lemonappdev.konsist.core.verify.kodeclarationassert.asserttrue

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.annotations
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.kDocs
import com.lemonappdev.konsist.api.ext.list.localFunctions
import com.lemonappdev.konsist.api.ext.list.tagprovider.tags
import com.lemonappdev.konsist.api.ext.list.typeArguments
import com.lemonappdev.konsist.api.verify.assertTrue
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class SuppressAssertTrueOnDeclarationListTest {
    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider",
            ).classes(includeNested = true)

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks

        // then
        sut.assertTrue { it.hasLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks

        // then
        sut.assertTrue { it.hasLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks

        // then
        sut.assertTrue(testName = "suppress-text") { it.hasLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks

        // then
        sut.assertTrue(testName = "suppress-text") { it.hasLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks

        // then
        sut.assertTrue { it.hasLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks

        // then
        sut.assertTrue { it.hasLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-parameter-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks

        // then
        sut.assertTrue(testName = "suppress-text") { it.hasLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-parameter-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks

        // then
        sut.assertTrue(testName = "suppress-text") { it.hasLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration",
            ).classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration",
            ).classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration",
            ).classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-with-few-parameters`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-with-few-parameters")
                .functions(includeNested = true)

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-parameter-with-few-parameters`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-with-few-parameters")
                .functions(includeNested = true)

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-on-declarations-which-items-have-null-parent`() {
        // given
        val scope1 = getSnippetFile("assert-suppress-by-konsist-and-name-on-declarations-which-items-have-null-parent")
        val scope2 = getSnippetFile("file-without-suppress")

        val sut =
            (scope1 + scope2)
                .files

        // then
        sut.assertTrue { it.name.endsWith("suppress") }
    }

    @Test
    fun `assert-suppress-by-name-on-declarations-which-items-have-null-parent`() {
        // given
        val scope1 = getSnippetFile("assert-suppress-by-name-on-declarations-which-items-have-null-parent")
        val scope2 = getSnippetFile("file-without-suppress")

        val sut =
            (scope1 + scope2)
                .files

        // then
        sut.assertTrue { it.name.endsWith("suppress") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-on-declarations-which-items-have-null-parent`() {
        // given
        val scope1 = getSnippetFile("assert-suppress-by-konsist-and-parameter-on-declarations-which-items-have-null-parent")
        val scope2 = getSnippetFile("file-without-suppress")

        val sut =
            (scope1 + scope2)
                .files

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("suppress") }
    }

    @Test
    fun `assert-suppress-by-parameter-on-declarations-which-items-have-null-parent`() {
        // given
        val scope1 = getSnippetFile("assert-suppress-by-parameter-on-declarations-which-items-have-null-parent")
        val scope2 = getSnippetFile("file-without-suppress")

        val sut =
            (scope1 + scope2)
                .files

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("suppress") }
    }

    @Test
    fun `assert-suppress-with-few-parameters-on-declarations-which-items-have-null-parent`() {
        // given
        val scope1 = getSnippetFile("assert-suppress-with-few-parameters-on-declarations-which-items-have-null-parent")
        val scope2 = getSnippetFile("file-without-suppress")

        val sut =
            (scope1 + scope2)
                .files

        // then
        sut.assertTrue { it.name.endsWith("suppress") }
    }

    @Test
    fun `assert-suppress-by-parameter-with-few-parameters-on-declarations-which-items-have-null-parent`() {
        // given
        val scope1 = getSnippetFile("assert-suppress-by-parameter-with-few-parameters-on-declarations-which-items-have-null-parent")
        val scope2 = getSnippetFile("file-without-suppress")

        val sut =
            (scope1 + scope2)
                .files

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("suppress") }
    }

    @Test
    fun `assert-suppress-with-suppress-name-parameter`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-with-suppress-name-parameter")
                .functions(includeNested = true)

        // then
        sut.assertTrue(testName = "konsist.assert-suppress-with-suppress-name-parameter") {
            it.name.endsWith("Kotest")
        }
    }

    @Test
    fun `assert-suppress-by-name-containing-comma`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-name-containing-comma").classes()

        // then
        sut.assertTrue(testName = "suppress, text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-containing-comma`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-konsist-and-name-containing-comma").classes()

        // then
        sut.assertTrue(testName = "suppress, text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-in-named-array-argument`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-name-in-named-array-argument").classes()

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-in-named-array-argument`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-konsist-and-name-in-named-array-argument").classes()

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-in-spread-array-argument`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-name-in-spread-array-argument").classes()

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-in-spread-array-argument`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-konsist-and-name-in-spread-array-argument").classes()

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-in-fully-qualified-suppress-annotation`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-name-in-fully-qualified-suppress-annotation").classes()

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-in-fully-qualified-suppress-annotation`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-konsist-and-name-in-fully-qualified-suppress-annotation").classes()

        // then
        sut.assertTrue(testName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-not-suppress-by-part-of-name-containing-comma`() {
        // given
        val sut = getSnippetFile("assert-not-suppress-by-part-of-name-containing-comma").classes()

        // when
        val func = { sut.assertTrue(testName = "suppress") { it.name.endsWith("Text") } }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-not-suppress-by-konsist-and-part-of-name-containing-comma`() {
        // given
        val sut = getSnippetFile("assert-not-suppress-by-konsist-and-part-of-name-containing-comma").classes()

        // when
        val func = { sut.assertTrue(testName = "suppress") { it.name.endsWith("Text") } }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-declarations-are-imports`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-name-at-file-level-when-declarations-are-imports").imports

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-declaration-is-package`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-name-at-file-level-when-declaration-is-package").packages

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-declarations-are-annotations`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-name-at-file-level-when-declarations-are-annotations").classes().annotations

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-declarations-are-annotations`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-name-at-declaration-parent-level-when-declarations-are-annotations",
            ).classes().annotations

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-declarations-are-kdocs`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-declarations-are-kdocs").classes().kDocs

        // then
        sut.assertTrue { it.text.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-declarations-are-imports`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-declarations-are-imports").imports

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-declaration-is-package`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-declaration-is-package").packages

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-declarations-are-annotations`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-name-at-file-level-when-declarations-are-annotations",
            ).classes().annotations

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-declarations-are-annotations`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-declarations-are-annotations",
            ).classes().annotations

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-declarations-are-kdocs`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-declarations-are-kdocs",
            ).classes().kDocs

        // then
        sut.assertTrue { it.text.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-declarations-are-kdoc-tags`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-declarations-are-kdoc-tags").classes().kDocs.tags

        // then
        sut.assertTrue { it.description.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-declarations-are-type-arguments`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-name-at-declaration-parent-level-when-declarations-are-type-arguments",
            ).properties()
                .mapNotNull {
                    it.type
                }.typeArguments

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-not-suppress-annotations-of-not-suppressed-declaration`() {
        // given
        val sut = getSnippetFile("assert-not-suppress-annotations-of-not-suppressed-declaration").classes().annotations

        // when
        val func = { sut.assertTrue { it.name.endsWith("Text") } }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/asserttrue/snippet/", fileName)
}
