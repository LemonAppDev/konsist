package com.lemon.konsist.core.kosecondaryconstructor

import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.core.assertion.check.check
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSecondaryConstructorTest {
    @Test
    fun `class-with-public-secondary-constructor`() {
        // given
        val sut = getSut("class-with-public-secondary-constructor")

        // then
        sut.classes()
            .map { it.secondaryConstructors }
            .first()
            .check { it.isPublic }
    }

    @Test
    fun `class-with-private-secondary-constructor`() {
        // given
        val sut = getSut("class-with-private-secondary-constructor")

        // then
        sut.classes()
            .map { it.secondaryConstructors }
            .first()
            .check { it.isPrivate }
    }

    @Test
    fun `class-with-protected-secondary-constructor`() {
        // given
        val sut = getSut("class-with-protected-secondary-constructor")

        // then
        sut.classes()
            .map { it.secondaryConstructors }
            .first()
            .check { it.isProtected }
    }

    @Test
    fun `class-with-internal-secondary-constructor`() {
        // given
        val sut = getSut("class-with-internal-secondary-constructor")

        // then
        sut.classes()
            .map { it.secondaryConstructors }
            .first()
            .check { it.isInternal }
    }

    @Test
    fun `class-with-secondary-constructor`() {
        // given
        val sut = getSut("class-with-secondary-constructor")

        // then
        sut.classes()
            .first()
            .secondaryConstructors
            .first()
            .name shouldBeEqualTo "SampleClass"
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/kosecondaryconstructor/snippet/$fileName.kt.txt")
}
