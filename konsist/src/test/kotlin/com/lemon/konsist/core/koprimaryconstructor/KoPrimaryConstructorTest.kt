package com.lemon.konsist.core.koprimaryconstructor

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPrimaryConstructorTest {
    @Test
    fun `class-with-primary-constructor`() {
        // given
        val sut =
            getSut("class-with-primary-constructor")
                .classes()
                .first()
                .primaryConstructor

        // then
        sut?.name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `class-with-public-primary-constructor`() {
        // given
        val sut =
            getSut("class-with-public-primary-constructor")
                .classes()
                .first()
                .primaryConstructor

        // then
        sut?.isPublic shouldBeEqualTo true
    }

    @Test
    fun `class-with-private-primary-constructor`() {
        // given
        val sut =
            getSut("class-with-private-primary-constructor")
                .classes()
                .first()
                .primaryConstructor

        // then
        sut?.isPrivate shouldBeEqualTo true
    }

    @Test
    fun `class-with-protected-primary-constructor`() {
        // given
        val sut =
            getSut("class-with-protected-primary-constructor")
                .classes()
                .first()
                .primaryConstructor

        // then
        sut?.isProtected shouldBeEqualTo true
    }

    @Test
    fun `class-with-internal-primary-constructor`() {
        // given
        val sut =
            getSut("class-with-internal-primary-constructor")
                .classes()
                .first()
                .primaryConstructor

        // then
        sut?.isInternal shouldBeEqualTo true
    }

    @Test
    fun `class-with-parameter-in-primary-constructor`() {
        // given
        val sut =
            getSut("class-with-parameter-in-primary-constructor")
                .classes()
                .first()
                .primaryConstructor

        // then
        sut
            ?.parameters
            ?.map { it.name } shouldBeEqualTo listOf("sampleParameter")
    }

    @Test
    fun `class-with-primary-constructor-has-parameter-named`() {
        // given
        val sut =
            getSut("class-with-primary-constructor-has-parameter-named")
                .classes()
                .first()
                .primaryConstructor

        // then
        sut?.hasParameterNamed("sampleName") shouldBeEqualTo true
    }

    @Test
    fun `class-with-primary-constructor-has-not-parameter-named`() {
        // given
        val sut =
            getSut("class-with-primary-constructor-has-not-parameter-named")
                .classes()
                .first()
                .primaryConstructor

        // then
        sut?.hasParameterNamed("otherName") shouldBeEqualTo false
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/koprimaryconstructor/snippet/$fileName.kt.txt")
}
