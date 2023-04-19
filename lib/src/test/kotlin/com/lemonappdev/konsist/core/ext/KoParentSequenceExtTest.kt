package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoParent
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentSequenceExtTest {
    @Test
    fun `withDelegate() returns parent1 which has delegate`() {
        // given
        val parent1: KoParent = mockk {
            every { hasDelegate() } returns true
        }
        val parent2: KoParent = mockk {
            every { hasDelegate() } returns false
        }
        val parents = sequenceOf(parent1, parent2)

        // when
        val sut = parents.withDelegate()

        // then
        sut.toList() shouldBeEqualTo listOf(parent1)
    }

    @Test
    fun `withDelegate(name) returns parent1 which has delegate with given name`() {
        // given
        val delegateName = "DelegateName"
        val parent1: KoParent = mockk {
            every { hasDelegate(delegateName) } returns true
        }
        val parent2: KoParent = mockk {
            every { hasDelegate(delegateName) } returns false
        }
        val parents = sequenceOf(parent1, parent2)

        // when
        val sut = parents.withDelegate(delegateName)

        // then
        sut.toList() shouldBeEqualTo listOf(parent1)
    }

    @Test
    fun `withoutDelegate() returns parent2 which has not delegate`() {
        // given
        val parent1: KoParent = mockk {
            every { hasDelegate() } returns true
        }
        val parent2: KoParent = mockk {
            every { hasDelegate() } returns false
        }
        val parents = sequenceOf(parent1, parent2)

        // when
        val sut = parents.withoutDelegate()

        // then
        sut.toList() shouldBeEqualTo listOf(parent2)
    }

    @Test
    fun `withoutDelegate(name) returns parent2 which has not delegate with given name`() {
        // given
        val delegateName = "DelegateName"
        val parent1: KoParent = mockk {
            every { hasDelegate(delegateName) } returns true
        }
        val parent2: KoParent = mockk {
            every { hasDelegate(delegateName) } returns false
        }
        val parents = sequenceOf(parent1, parent2)

        // when
        val sut = parents.withoutDelegate(delegateName)

        // then
        sut.toList() shouldBeEqualTo listOf(parent2)
    }
}
