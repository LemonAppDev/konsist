package com.lemonappdev.konsist.core.verify.commonassert

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.verify.assert
import com.lemonappdev.konsist.core.verify.assertNot
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CommonAssertTest {
    @Test
    fun `declaration-assert-test-method-name`() {
        // given
        val sut = getSnippetFile("declaration-assert-test-method-name")
            .classes()

        // then
        try {
            sut.assert { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'declaration-assert-test-method-name' has failed. Invalid declarations (1)") ?: throw e
        }
    }

    @Test
    fun `provider-assert-test-method-name`() {
        // given
        val sut = getSnippetFile("provider-assert-test-method-name")
            .declarations()
            .filterIsInstance<KoNameProvider>()

        // then
        try {
            sut.assert { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'provider-assert-test-method-name' has failed. Invalid declarations (1)") ?: throw e
        }
    }

    @Test
    fun `file-assert-test-method-name`() {
        // given
        val sut = getSnippetFile("file-assert-test-method-name")
            .files

        // then
        try {
            sut.assert { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'file-assert-test-method-name' has failed. Invalid files (1)") ?: throw e
        }
    }

    @Test
    fun `declaration-assert-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("declaration-assert-fails-when-declaration-list-is-empty")
            .classes()

        // when
        val func = {
            sut.assert { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling the 'assert' method."
    }

    @Test
    fun `declaration-assert-not-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("declaration-assert-not-fails-when-declaration-list-is-empty")
            .classes()

        // when
        val func = {
            sut.assertNot { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling the 'assertNot' method."
    }

    @Test
    fun `provider-assert-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("provider-assert-fails-when-declaration-list-is-empty")
            .declarations()
            .filterIsInstance<KoNameProvider>()

        // when
        val func = {
            sut.assert { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
                "Declaration list is empty. Please make sure that list of declarations contain items before calling the 'assert' method."
    }

    @Test
    fun `provider-assert-not-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("provider-assert-not-fails-when-declaration-list-is-empty")
            .declarations()
            .filterIsInstance<KoNameProvider>()

        // when
        val func = {
            sut.assertNot { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
                "Declaration list is empty. Please make sure that list of declarations contain items before calling the 'assertNot' method."
    }

    @Test
    fun `file-assert-fails-when-files-list-is-empty`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/verify/commonassert/snippet/emptypackage")
            .files

        // when
        val func = {
            sut.assert { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "File list is empty. Please make sure that list of files contain items before calling the 'assert' method."
    }

    @Test
    fun `file-assert-not-fails-when-files-list-is-empty`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/verify/commonassert/snippet/emptypackage")
            .files

        // when
        val func = {
            sut.assertNot { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "File list is empty. Please make sure that list of files contain items before calling the 'assertNot' method."
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/commonassert/snippet/", fileName)
}
