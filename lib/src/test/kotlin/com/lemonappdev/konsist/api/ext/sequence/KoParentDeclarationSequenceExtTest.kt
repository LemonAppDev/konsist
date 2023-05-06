package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.core.declaration.KoParentDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationSequenceExtTest {
    @Test
    fun `withDelegate() returns parent with any delegate`() {
        // given
        val parent1: KoParentDeclarationImpl = mockk {
            every { hasDelegate() } returns true
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { hasDelegate() } returns false
        }
        val parents = sequenceOf(parent1, parent2)

        // when
        val sut = parents.withDelegate()

        // then
        sut.toList() shouldBeEqualTo listOf(parent1)
    }

    @Test
    fun `withDelegate(name) returns parent with one of given delegate names`() {
        // given
        val delegateName1 = "DelegateName1"
        val delegateName2 = "DelegateName2"
        val parent1: KoParentDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns true
            every { hasDelegate(delegateName2) } returns false
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns false
            every { hasDelegate(delegateName2) } returns true
        }
        val parent3: KoParentDeclarationImpl = mockk {
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
    fun `withoutDelegate() returns parent without any delegate`() {
        // given
        val parent1: KoParentDeclarationImpl = mockk {
            every { hasDelegate() } returns true
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { hasDelegate() } returns false
        }
        val parents = sequenceOf(parent1, parent2)

        // when
        val sut = parents.withoutDelegate()

        // then
        sut.toList() shouldBeEqualTo listOf(parent2)
    }

    @Test
    fun `withoutDelegate(name) returns parent without delegate with any of given names`() {
        // given
        val delegateName1 = "DelegateName1"
        val delegateName2 = "DelegateName2"
        val parent1: KoParentDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns true
            every { hasDelegate(delegateName2) } returns false
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns false
            every { hasDelegate(delegateName2) } returns true
        }
        val parent3: KoParentDeclarationImpl = mockk {
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
