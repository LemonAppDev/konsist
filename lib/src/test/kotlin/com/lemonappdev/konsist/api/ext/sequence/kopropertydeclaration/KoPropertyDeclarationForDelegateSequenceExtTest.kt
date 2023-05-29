package com.lemonappdev.konsist.api.ext.sequence.kopropertydeclaration

import com.lemonappdev.konsist.api.ext.sequence.withDelegate
import com.lemonappdev.konsist.api.ext.sequence.withoutDelegate
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForDelegateSequenceExtTest {
    @Test
    fun `withDelegate() returns property with any delegate`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withDelegate()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withDelegate(name) returns properties with one of given delegate names`() {
        // given
        val delegateName1 = "DelegateName1"
        val delegateName2 = "DelegateName2"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns true
            every { hasDelegate(delegateName2) } returns false
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns false
            every { hasDelegate(delegateName2) } returns true
        }
        val property3: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns false
            every { hasDelegate(delegateName2) } returns false
        }
        val properties = sequenceOf(property1, property2, property3)

        // when
        val sut = properties.withDelegate(delegateName1, delegateName2)

        // then
        sut.toList() shouldBeEqualTo listOf(property1, property2)
    }

    @Test
    fun `withoutDelegate() returns property without any delegate`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutDelegate()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }

    @Test
    fun `withoutDelegate(name) returns property without any of delegate with given name`() {
        // given
        val delegateName1 = "DelegateName1"
        val delegateName2 = "DelegateName2"
        val property1: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns true
            every { hasDelegate(delegateName2) } returns false
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns false
            every { hasDelegate(delegateName2) } returns true
        }
        val property3: KoPropertyDeclarationImpl = mockk {
            every { hasDelegate(delegateName1) } returns false
            every { hasDelegate(delegateName2) } returns false
        }
        val properties = sequenceOf(property1, property2, property3)

        // when
        val sut = properties.withoutDelegate(delegateName1, delegateName2)

        // then
        sut.toList() shouldBeEqualTo listOf(property3)
    }
}
