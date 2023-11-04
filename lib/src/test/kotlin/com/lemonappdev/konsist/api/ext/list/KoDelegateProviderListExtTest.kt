package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoDelegateProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDelegateProviderListExtTest {
    @Test
    fun `withDelegate() returns declaration with any delegate`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasDelegate() } returns true
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasDelegate() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withDelegate()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withDelegate(name) returns declaration with one of given delegate names`() {
        // given
        val delegateName1 = "DelegateName1"
        val delegateName2 = "DelegateName2"
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasDelegate(delegateName1) } returns true
                every { hasDelegate(delegateName2) } returns false
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasDelegate(delegateName1) } returns false
                every { hasDelegate(delegateName2) } returns true
            }
        val declaration3: KoDelegateProvider =
            mockk {
                every { hasDelegate(delegateName1) } returns false
                every { hasDelegate(delegateName2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withDelegate(delegateName1, delegateName2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutDelegate() returns declaration without any delegate`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasDelegate() } returns true
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasDelegate() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutDelegate()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutDelegate(name) returns declaration without delegate with any of given names`() {
        // given
        val delegateName1 = "DelegateName1"
        val delegateName2 = "DelegateName2"
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasDelegate(delegateName1) } returns true
                every { hasDelegate(delegateName2) } returns false
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasDelegate(delegateName1) } returns false
                every { hasDelegate(delegateName2) } returns true
            }
        val declaration3: KoDelegateProvider =
            mockk {
                every { hasDelegate(delegateName1) } returns false
                every { hasDelegate(delegateName2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutDelegate(delegateName1, delegateName2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
