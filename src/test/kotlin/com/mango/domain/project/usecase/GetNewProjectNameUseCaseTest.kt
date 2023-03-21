package com.mango.domain.project.usecase

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class GetNewProjectNameUseCaseTest {
    private val sut = GetNewProjectNameUseCase()

    @Test
    fun `returns name with 'copy' suffix`() {
        // given
        val name = "name"

        // when
        val actual = sut(name)

        // then
        actual shouldBeEqualTo "name copy"
    }

    @Test
    fun `returns name with 'copy 2' suffix if it contains 'copy'`() {
        // given
        val name = "name copy"

        // when
        val actual = sut(name)

        // then
        actual shouldBeEqualTo "name copy 2"
    }

    @Test
    fun `returns name with 'copy 3' suffix if it contains 'copy 2'`() {
        // given
        val name = "name copy 2"

        // when
        val actual = sut(name)

        // then
        actual shouldBeEqualTo "name copy 3"
    }

    @Test
    fun `returns name with 'copy' suffix when name also contains 'copy' inside it`() {
        // given
        val name = "my name copy two"

        // when
        val actual = sut(name)

        // then
        actual shouldBeEqualTo "my name copy two copy"
    }

    @Test
    fun `returns name with 'copy copy 2' suffix when name ends with 'copy copy'`() {
        // given
        val name = "my name copy copy"

        // when
        val actual = sut(name)

        // then
        actual shouldBeEqualTo "my name copy copy 2"
    }

    @Test
    fun `returns name with 'copy' suffix when name ends with 'copy   '`() {
        // given
        val name = "my name copy   "

        // when
        val actual = sut(name)

        // then
        actual shouldBeEqualTo "my name copy   copy"
    }

    @Test
    fun `returns '2 copy' when name is equal to '2'`() {
        // given
        val name = "2"

        // when
        val actual = sut(name)

        // then
        actual shouldBeEqualTo "2 copy"
    }

    @Test
    fun `returns 'copy 2' when name is equal to 'copy'`() {
        // given
        val name = "copy"

        // when
        val actual = sut(name)

        // then
        actual shouldBeEqualTo "copy 2"
    }

    @Test
    fun `returns 'copy  copy' when name is equal to 'copy '`() {
        // given
        val name = "copy "

        // when
        val actual = sut(name)

        // then
        actual shouldBeEqualTo "copy copy"
    }
}
