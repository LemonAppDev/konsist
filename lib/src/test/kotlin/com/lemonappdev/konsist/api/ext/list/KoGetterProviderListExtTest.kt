package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoGetterDeclaration
import com.lemonappdev.konsist.api.provider.KoGetterProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterProviderListExtTest {
    @Test
    fun `getters returns getters from all declarations`() {
        // given
        val getter1: KoGetterDeclaration = mockk()
        val getter2: KoGetterDeclaration = mockk()
        val declaration1: KoGetterProvider =
            mockk {
                every { getter } returns getter1
            }
        val declaration2: KoGetterProvider =
            mockk {
                every { getter } returns getter2
            }
        val declaration3: KoGetterProvider =
            mockk {
                every { getter } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.getters

        // then
        sut shouldBeEqualTo listOf(getter1, getter2)
    }

    @Test
    fun `withGetter() returns declaration with getter`() {
        // given
        val declaration1: KoGetterProvider =
            mockk {
                every { hasGetter } returns true
            }
        val declaration2: KoGetterProvider =
            mockk {
                every { hasGetter } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withGetter()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutGetter() returns declaration without getter`() {
        // given
        val declaration1: KoGetterProvider =
            mockk {
                every { hasGetter } returns true
            }
        val declaration2: KoGetterProvider =
            mockk {
                every { hasGetter } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutGetter()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
