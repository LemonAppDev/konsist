package com.lemonappdev.konsist.api.ext.sequence.kotypedeclaration

import com.lemonappdev.konsist.api.ext.sequence.withGenericType
import com.lemonappdev.konsist.api.ext.sequence.withoutGenericType
import com.lemonappdev.konsist.core.declaration.KoTypeDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationForIsGenericTypeSequenceExtTest {
    @Test
    fun `withGenericType() returns type with generic type`() {
        // given
        val type1: KoTypeDeclarationImpl = mockk {
            every { isGenericType } returns true
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { isGenericType } returns false
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withGenericType()

        // then
        sut.toList() shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutGenericType() returns type without generic type`() {
        // given
        val type1: KoTypeDeclarationImpl = mockk {
            every { isGenericType } returns true
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { isGenericType } returns false
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withoutGenericType()

        // then
        sut.toList() shouldBeEqualTo listOf(type2)
    }
}
