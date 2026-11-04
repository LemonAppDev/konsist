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
    fun `withDelegate(empty list) returns declaration with any delegate`() {
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
        val sut = declarations.withDelegate(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withDelegate(empty set) returns declaration with any delegate`() {
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
        val sut = declarations.withDelegate(emptySet())

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
    fun `withDelegate(List) returns declarations with one of given delegate name`() {
        // given
        val delegateName1 = "delegateName1"
        val delegateName2 = "delegateName2"
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
        val delegateNames = listOf(delegateName1, delegateName2)

        // when
        val sut = declarations.withDelegate(delegateNames)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withDelegate(Set) returns declarations with one of given delegate name`() {
        // given
        val delegateName1 = "delegateName1"
        val delegateName2 = "delegateName2"
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
        val delegateNames = setOf(delegateName1, delegateName2)

        // when
        val sut = declarations.withDelegate(delegateNames)

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
    fun `withoutDelegate(empty list) returns declaration without delegate`() {
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
        val sut = declarations.withoutDelegate(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutDelegate(empty set) returns declaration without delegate`() {
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
        val sut = declarations.withoutDelegate(emptySet())

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

    @Test
    fun `withoutDelegate(List) returns declaration without any of given delegate name`() {
        // given
        val delegateName1 = "delegateName1"
        val delegateName2 = "delegateName2"
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
        val delegateNames = listOf(delegateName1, delegateName2)

        // when
        val sut = declarations.withoutDelegate(delegateNames)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutDelegate(Set) returns declaration without any of given delegate name`() {
        // given
        val delegateName1 = "delegateName1"
        val delegateName2 = "delegateName2"
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
        val delegateNames = setOf(delegateName1, delegateName2)

        // when
        val sut = declarations.withoutDelegate(delegateNames)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withLazyDelegate() returns declaration with any lazy delegate`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate() } returns true
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLazyDelegate()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withLazyDelegate(empty list) returns declaration with any lazy delegate`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate() } returns true
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLazyDelegate(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withLazyDelegate(mode) returns declaration with given mode`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns true
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns false
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns false
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns true
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withLazyDelegate(modes) returns declarations with one of given modes`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns true
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns false
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns false
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns true
            }
        val declaration3: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns false
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED, LazyThreadSafetyMode.NONE)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withLazyDelegate(set of modes) returns declarations with one of given modes`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns true
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns false
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns false
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns true
            }
        val declaration3: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns false
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withLazyDelegate(setOf(LazyThreadSafetyMode.SYNCHRONIZED, LazyThreadSafetyMode.NONE))

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutLazyDelegate() returns declaration without any lazy delegate`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate() } returns true
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLazyDelegate()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutLazyDelegate(empty list) returns declaration without any lazy delegate`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate() } returns true
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLazyDelegate(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutLazyDelegate(mode) returns declaration without given mode`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns true
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns false
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns false
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns true
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutLazyDelegate(modes) returns declaration without any of given modes`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns true
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns false
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns false
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns true
            }
        val declaration3: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns false
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED, LazyThreadSafetyMode.NONE)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutLazyDelegate(set of modes) returns declaration without any of given modes`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns true
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns false
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns false
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns true
            }
        val declaration3: KoDelegateProvider =
            mockk {
                every { hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) } returns false
                every { hasLazyDelegate(LazyThreadSafetyMode.NONE) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutLazyDelegate(setOf(LazyThreadSafetyMode.SYNCHRONIZED, LazyThreadSafetyMode.NONE))

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withObservableDelegate() returns declaration with observable delegate`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasObservableDelegate } returns true
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasObservableDelegate } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withObservableDelegate()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutObservableDelegate() returns declaration without observable delegate`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasObservableDelegate } returns true
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasObservableDelegate } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutObservableDelegate()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withVetoableDelegate() returns declaration with vetoable delegate`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasVetoableDelegate } returns true
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasVetoableDelegate } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withVetoableDelegate()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutVetoableDelegate() returns declaration without vetoable delegate`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasVetoableDelegate } returns true
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasVetoableDelegate } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutVetoableDelegate()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withNotNullDelegate() returns declaration with notNull delegate`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasNotNullDelegate } returns true
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasNotNullDelegate } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withNotNullDelegate()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutNotNullDelegate() returns declaration without notNull delegate`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasNotNullDelegate } returns true
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasNotNullDelegate } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutNotNullDelegate()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withPropertyReferenceDelegate() returns declaration with property reference delegate`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasPropertyReferenceDelegate } returns true
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasPropertyReferenceDelegate } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPropertyReferenceDelegate()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPropertyReferenceDelegate() returns declaration without property reference delegate`() {
        // given
        val declaration1: KoDelegateProvider =
            mockk {
                every { hasPropertyReferenceDelegate } returns true
            }
        val declaration2: KoDelegateProvider =
            mockk {
                every { hasPropertyReferenceDelegate } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPropertyReferenceDelegate()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
