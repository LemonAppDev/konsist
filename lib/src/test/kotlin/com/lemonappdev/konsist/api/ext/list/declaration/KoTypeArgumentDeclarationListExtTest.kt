package com.lemonappdev.konsist.api.ext.list.declaration

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeArgumentDeclarationListExtTest {
    @Test
    fun `flatten() returns type arguments without nested ones`() {
        // given
        val typeArgument1: KoTypeArgumentDeclaration = mockk {
            every { typeArguments } returns null
        }
        val nestedTypeArgument: KoTypeArgumentDeclaration = mockk {
            every { typeArguments } returns null
        }
        val typeArgument2: KoTypeArgumentDeclaration = mockk {
            every { typeArguments } returns listOf(nestedTypeArgument)
        }
        val nestedTypeArgument1: KoTypeArgumentDeclaration = mockk {
            every { typeArguments } returns null
        }
        val nestedTypeArgument2: KoTypeArgumentDeclaration = mockk {
            every { typeArguments } returns null
        }
        val typeArgument3: KoTypeArgumentDeclaration = mockk {
            every { typeArguments } returns listOf(nestedTypeArgument1, nestedTypeArgument2)
        }

        val typeArguments = listOf(typeArgument1, typeArgument2, typeArgument3)

        // when
        val sut = typeArguments.flatten()

        // then
        sut shouldBeEqualTo listOf(nestedTypeArgument, nestedTypeArgument1, nestedTypeArgument2)
    }

    @Test
    fun `flatten() returns type arguments with nested ones`() {
        // given
        val typeArgument1: KoTypeArgumentDeclaration = mockk {
            every { typeArguments } returns null
        }
        val typeArgumentDeclaration1: KoTypeArgumentDeclaration = mockk {
            every { typeArguments } returns listOf(typeArgument1)
        }
        val typeArgument2: KoTypeArgumentDeclaration = mockk {
            every { typeArguments } returns null
        }
        val typeArgument3: KoTypeArgumentDeclaration = mockk {
            every { typeArguments } returns listOf(typeArgument2)
        }
        val typeArgumentDeclaration2: KoTypeArgumentDeclaration = mockk {
            every { typeArguments } returns listOf(typeArgument3)
        }

        val typeArguments = listOf(typeArgumentDeclaration1, typeArgumentDeclaration2,)

        // when
        val sut = typeArguments.flatten()

        // then
        sut shouldBeEqualTo listOf(typeArgument1, typeArgument3, typeArgument2)
    }
}
