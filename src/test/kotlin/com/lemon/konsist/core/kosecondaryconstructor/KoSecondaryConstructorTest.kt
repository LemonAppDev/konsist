package com.lemon.konsist.core.kosecondaryconstructor

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSecondaryConstructorTest {
    @Test
    fun `class-with-public-secondary-constructor`() {
        // given
        val sut =
            getSut("class-with-public-secondary-constructor")
                .classes()
                .first()
                .secondaryConstructors
                .first()

        // then
        sut.isPublic shouldBeEqualTo true
    }

    @Test
    fun `class-with-private-secondary-constructor`() {
        // given
        val sut =
            getSut("class-with-private-secondary-constructor")
                .classes()
                .first()
                .secondaryConstructors
                .first()

        // then
        sut.isPrivate shouldBeEqualTo true
    }

    @Test
    fun `class-with-protected-secondary-constructor`() {
        // given
        val sut =
            getSut("class-with-protected-secondary-constructor")
                .classes()
                .first()
                .secondaryConstructors
                .first()

        // then
        sut.isProtected shouldBeEqualTo true
    }

    @Test
    fun `class-with-internal-secondary-constructor`() {
        // given
        val sut =
            getSut("class-with-internal-secondary-constructor")
                .classes()
                .first()
                .secondaryConstructors
                .first()

        // then
        sut.isInternal shouldBeEqualTo true
    }

    @Test
    fun `class-with-secondary-constructor`() {
        // given
        val sut =
            getSut("class-with-secondary-constructor")
                .classes()
                .first()
                .secondaryConstructors
                .first()

        // then
        sut.name shouldBeEqualTo "SampleClass"
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/kosecondaryconstructor/snippet/$fileName.kt.txt")
}
