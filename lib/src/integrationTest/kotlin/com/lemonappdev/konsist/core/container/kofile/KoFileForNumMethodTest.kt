package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForNumMethodTest {
    @Test
    fun `file-contains-package-and-class`() {
        // given
        val sut = getSnippetFile("file-contains-package-and-class")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            numDeclarations() shouldBeEqualTo 2
            numClasses() shouldBeEqualTo 1
        }
    }

    @Test
    fun `file-contains-nested-classes`() {
        // given
        val sut = getSnippetFile("file-contains-nested-classes")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            numClasses(includeNested = false) shouldBeEqualTo 1
            numClasses(includeNested = true) shouldBeEqualTo 2
        }
    }

    @Test
    fun `file-contains-package-and-property`() {
        // given
        val sut = getSnippetFile("file-contains-package-and-property")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            numDeclarations() shouldBeEqualTo 2
            numProperties() shouldBeEqualTo 1
        }
    }

    @Test
    fun `file-contains-nested-and-local-properties`() {
        // given
        val sut = getSnippetFile("file-contains-nested-and-local-properties")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            numProperties(includeNested = false, includeLocal = false) shouldBeEqualTo 0
            numProperties(includeNested = true, includeLocal = false) shouldBeEqualTo 1
            numProperties(includeNested = false, includeLocal = true) shouldBeEqualTo 1
            numProperties(includeNested = true, includeLocal = true) shouldBeEqualTo 2
        }
    }

    @Test
    fun `file-contains-package-and-function`() {
        // given
        val sut = getSnippetFile("file-contains-package-and-function")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            numDeclarations() shouldBeEqualTo 2
            numFunctions() shouldBeEqualTo 1
        }
    }

    @Test
    fun `file-contains-nested-and-local-functions`() {
        // given
        val sut = getSnippetFile("file-contains-nested-and-local-functions")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            numFunctions(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            numFunctions(includeNested = true, includeLocal = false) shouldBeEqualTo 2
            numFunctions(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numFunctions(includeNested = true, includeLocal = true) shouldBeEqualTo 3
        }
    }

    @Test
    fun `file-contains-package-and-object`() {
        // given
        val sut = getSnippetFile("file-contains-package-and-object")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            numDeclarations() shouldBeEqualTo 2
            numObjects() shouldBeEqualTo 1
        }
    }

    @Test
    fun `file-contains-nested-objects`() {
        // given
        val sut = getSnippetFile("file-contains-nested-objects")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            numObjects(includeNested = false) shouldBeEqualTo 1
            numObjects(includeNested = true) shouldBeEqualTo 2
        }
    }

    @Test
    fun `file-contains-package-and-interface`() {
        // given
        val sut = getSnippetFile("file-contains-package-and-interface")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            numDeclarations() shouldBeEqualTo 2
            numInterfaces() shouldBeEqualTo 1
        }
    }

    @Test
    fun `file-contains-nested-interfaces`() {
        // given
        val sut = getSnippetFile("file-contains-nested-interfaces")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            numInterfaces(includeNested = false) shouldBeEqualTo 1
            numInterfaces(includeNested = true) shouldBeEqualTo 2
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/kofile/snippet/fornummethod/", fileName)
}
