package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.core.declaration.KoBaseDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import withParentDeclaration
import withoutParentDeclaration

class KoBaseDeclarationImplSequenceExtTest {
    @Test
    fun `withParentDeclaration() returns baseDeclaration with any parent declaration`() {
        // given
        val baseDeclaration1: KoBaseDeclarationImpl = mockk {
            every { hasParentDeclaration() } returns true
        }
        val baseDeclaration2: KoBaseDeclarationImpl = mockk {
            every { hasParentDeclaration() } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2)

        // when
        val sut = baseDeclarations.withParentDeclaration()

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration1)
    }

    @Test
    fun `withoutParentDeclaration() returns baseDeclaration without parent declaration`() {
        // given
        val baseDeclaration1: KoBaseDeclarationImpl = mockk {
            every { hasParentDeclaration() } returns true
        }
        val baseDeclaration2: KoBaseDeclarationImpl = mockk {
            every { hasParentDeclaration() } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2)

        // when
        val sut = baseDeclarations.withoutParentDeclaration()

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration2)
    }

    @Test
    fun `withParentDeclaration() returns baseDeclarations with one of given names`() {
        // given
        val name1 = "parentName1"
        val name2 = "parentName2"
        val baseDeclaration1: KoBaseDeclarationImpl = mockk {
            every { hasParentDeclaration(name1) } returns true
            every { hasParentDeclaration(name2) } returns true
        }
        val baseDeclaration2: KoBaseDeclarationImpl = mockk {
            every { hasParentDeclaration(name1) } returns false
            every { hasParentDeclaration(name2) } returns true
        }
        val baseDeclaration3: KoBaseDeclarationImpl = mockk {
            every { hasParentDeclaration(name1) } returns false
            every { hasParentDeclaration(name2) } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2, baseDeclaration3)

        // when
        val sut = baseDeclarations.withParentDeclaration(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration1, baseDeclaration2)
    }

    @Test
    fun `withoutParentDeclaration() returns baseDeclaration without any of given names`() {
        // given
        val name1 = "parentName1"
        val name2 = "parentName2"
        val baseDeclaration1: KoBaseDeclarationImpl = mockk {
            every { hasParentDeclaration(name1) } returns true
            every { hasParentDeclaration(name2) } returns true
        }
        val baseDeclaration2: KoBaseDeclarationImpl = mockk {
            every { hasParentDeclaration(name1) } returns false
            every { hasParentDeclaration(name2) } returns true
        }
        val baseDeclaration3: KoBaseDeclarationImpl = mockk {
            every { hasParentDeclaration(name1) } returns false
            every { hasParentDeclaration(name2) } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2, baseDeclaration3)

        // when
        val sut = baseDeclarations.withoutParentDeclaration(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration3)
    }
}
