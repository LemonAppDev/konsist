package com.lemonappdev.konsist.api.ext.sequence.kotypedeclaration

import com.lemonappdev.konsist.api.ext.sequence.withKotlinType
import com.lemonappdev.konsist.api.ext.sequence.withoutKotlinType
import com.lemonappdev.konsist.core.declaration.KoTypeDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationForIsKotlinTypeSequenceExtTest {
    @Test
    fun `withKotlinType() returns type with Kotlin basic type`() {
        // given
        val type1: KoTypeDeclarationImpl = mockk {
            every { isKotlinType } returns true
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { isKotlinType } returns false
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withKotlinType()

        // then
        sut.toList() shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutKotlinType() returns type without Kotlin basic type`() {
        // given
        val type1: KoTypeDeclarationImpl = mockk {
            every { isKotlinType } returns true
        }
        val type2: KoTypeDeclarationImpl = mockk {
            every { isKotlinType } returns false
        }
        val types = sequenceOf(type1, type2)

        // when
        val sut = types.withoutKotlinType()

        // then
        sut.toList() shouldBeEqualTo listOf(type2)
    }
}
