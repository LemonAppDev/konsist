package com.lemon.konsist.core.koprimaryconstructor

import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.core.assertion.check.check
import com.lemon.konsist.core.assertion.check.checkNot
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPrimaryConstructorTest {
    @Test
    fun `class-with-primary-constructor`() {
        // given
        val sut = getSut("class-with-primary-constructor")

        // then
        sut.classes()
            .first()
            .primaryConstructor
            ?.name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `class-with-public-primary-constructor`() {
        // given
        val sut = getSut("class-with-public-primary-constructor")

        // then
        sut.classes()
            .mapNotNull { it.primaryConstructor }
            .check { it.isPublic }
    }

    @Test
    fun `class-with-private-primary-constructor`() {
        // given
        val sut = getSut("class-with-private-primary-constructor")

        // then
        sut.classes()
            .mapNotNull { it.primaryConstructor }
            .check { it.isPrivate }
    }

    @Test
    fun `class-with-protected-primary-constructor`() {
        // given
        val sut = getSut("class-with-protected-primary-constructor")

        // then
        sut.classes()
            .mapNotNull { it.primaryConstructor }
            .check { it.isProtected }
    }

    @Test
    fun `class-with-internal-primary-constructor`() {
        // given
        val sut = getSut("class-with-internal-primary-constructor")

        // then
        sut.classes()
            .mapNotNull { it.primaryConstructor }
            .check { it.isInternal }
    }

    @Test
    fun `class-with-parameter-in-primary-constructor`() {
        // given
        val sut = getSut("class-with-parameter-in-primary-constructor")

        // then
        sut.classes()
            .firstNotNullOf { it.primaryConstructor }
            .parameters
            .map { it.name } shouldBeEqualTo listOf("sampleParameter")
    }

    @Test
    fun `class-with-primary-constructor-has-parameter-named`() {
        // given
        val sut = getSut("class-with-primary-constructor-has-parameter-named")

        // then
        sut.classes()
            .mapNotNull { it.primaryConstructor }
            .check { it.hasParameterNamed("sampleName") }
    }

    @Test
    fun `class-with-primary-constructor-has-not-parameter-named`() {
        // given
        val sut = getSut("class-with-primary-constructor-has-not-parameter-named")

        // then
        sut.classes()
            .mapNotNull { it.primaryConstructor }
            .checkNot { it.hasParameterNamed("otherName") }
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/koprimaryconstructor/snippet/$fileName.kt.txt")
}
