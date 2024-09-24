package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoSetterDeclaration
import com.lemonappdev.konsist.api.provider.KoSetterProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterProviderListExtTest {
    @Test
    fun `setters returns setters from all declarations`() {
        // given
        val setter1: KoSetterDeclaration = mockk()
        val setter2: KoSetterDeclaration = mockk()
        val declaration1: KoSetterProvider =
            mockk {
                every { setter } returns setter1
            }
        val declaration2: KoSetterProvider =
            mockk {
                every { setter } returns setter2
            }
        val declaration3: KoSetterProvider =
            mockk {
                every { setter } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.setters

        // then
        sut shouldBeEqualTo listOf(setter1, setter2)
    }

    @Test
    fun `withSetter() returns declaration with setter`() {
        // given
        val declaration1: KoSetterProvider =
            mockk {
                every { hasSetter } returns true
            }
        val declaration2: KoSetterProvider =
            mockk {
                every { hasSetter } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSetter()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSetter() returns declaration without setter`() {
        // given
        val declaration1: KoSetterProvider =
            mockk {
                every { hasSetter } returns true
            }
        val declaration2: KoSetterProvider =
            mockk {
                every { hasSetter } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSetter()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
