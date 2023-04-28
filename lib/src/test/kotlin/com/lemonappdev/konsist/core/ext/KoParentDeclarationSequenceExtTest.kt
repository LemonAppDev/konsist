package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoParentDeclaration
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationSequenceExtTest {
    @Test
    fun `withDelegate() returns parent1 which has delegate`() {
        // given
        val parent1: KoParentDeclaration = mockk {
            every { hasDelegate() } returns true
        }
        val parent2: KoParentDeclaration = mockk {
            every { hasDelegate() } returns false
        }
        val parents = sequenceOf(parent1, parent2)

        // when
        val sut = parents.withDelegate()

        // then
        sut.toList() shouldBeEqualTo listOf(parent1)
    }

    @Test
    fun `withDelegate(name) returns parents which have one of given delegate names`() {
        // given
        val delegateName1 = "DelegateName1"
        val delegateName2 = "DelegateName2"
        val parent1: KoParentDeclaration = mockk {
            every { hasDelegate(delegateName1) } returns true
            every { hasDelegate(delegateName2) } returns false
        }
        val parent2: KoParentDeclaration = mockk {
            every { hasDelegate(delegateName1) } returns false
            every { hasDelegate(delegateName2) } returns true
        }
        val parent3: KoParentDeclaration = mockk {
            every { hasDelegate(delegateName1) } returns false
            every { hasDelegate(delegateName2) } returns false
        }
        val parents = sequenceOf(parent1, parent2, parent3)

        // when
        val sut = parents.withDelegate(delegateName1, delegateName2)

        // then
        sut.toList() shouldBeEqualTo listOf(parent1, parent2)
    }

    @Test
    fun `withoutDelegate() returns parent2 which has not delegate`() {
        // given
        val parent1: KoParentDeclaration = mockk {
            every { hasDelegate() } returns true
        }
        val parent2: KoParentDeclaration = mockk {
            every { hasDelegate() } returns false
        }
        val parents = sequenceOf(parent1, parent2)

        // when
        val sut = parents.withoutDelegate()

        // then
        sut.toList() shouldBeEqualTo listOf(parent2)
    }

    @Test
    fun `withoutDelegate(name) returns parent3 which has not delegate with any given name`() {
        // given
        val delegateName1 = "DelegateName1"
        val delegateName2 = "DelegateName2"
        val parent1: KoParentDeclaration = mockk {
            every { hasDelegate(delegateName1) } returns true
            every { hasDelegate(delegateName2) } returns false
        }
        val parent2: KoParentDeclaration = mockk {
            every { hasDelegate(delegateName1) } returns false
            every { hasDelegate(delegateName2) } returns true
        }
        val parent3: KoParentDeclaration = mockk {
            every { hasDelegate(delegateName1) } returns false
            every { hasDelegate(delegateName2) } returns false
        }
        val parents = sequenceOf(parent1, parent2, parent3)

        // when
        val sut = parents.withoutDelegate(delegateName1, delegateName2)

        // then
        sut.toList() shouldBeEqualTo listOf(parent3)
    }
}
