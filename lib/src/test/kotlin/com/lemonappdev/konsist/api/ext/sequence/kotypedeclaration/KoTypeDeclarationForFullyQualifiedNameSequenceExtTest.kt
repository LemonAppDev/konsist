package com.lemonappdev.konsist.api.ext.sequence.kotypedeclaration

import com.lemonappdev.konsist.api.ext.sequence.withFullyQualifiedName
import com.lemonappdev.konsist.api.ext.sequence.withoutFullyQualifiedName
import com.lemonappdev.konsist.core.declaration.KoTypeDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationForFullyQualifiedNameSequenceExtTest {
    @Test
    fun `withFullyQualifiedName() returns types with one of given names`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val fullyQualifiedName3 = "fullyQualifiedName3"
        val type1: KoTypeDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val type3: KoTypeDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName3
        }
        val types = sequenceOf(type1, type2, type3)

        // when
        val sut = types.withFullyQualifiedName(fullyQualifiedName1, fullyQualifiedName2)

        // then
        sut.toList() shouldBeEqualTo listOf(type1, type2)
    }

    @Test
    fun `withoutFullyQualifiedName() returns type without any of given names`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val fullyQualifiedName3 = "fullyQualifiedName3"
        val type1: KoTypeDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val type3: KoTypeDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName3
        }
        val types = sequenceOf(type1, type2, type3)

        // when
        val sut = types.withoutFullyQualifiedName(fullyQualifiedName1, fullyQualifiedName2)

        // then
        sut.toList() shouldBeEqualTo listOf(type3)
    }
}
