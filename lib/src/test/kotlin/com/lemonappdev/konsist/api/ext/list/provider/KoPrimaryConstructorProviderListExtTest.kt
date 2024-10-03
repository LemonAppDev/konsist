package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPrimaryConstructorProviderListExtTest {
    @Test
    fun `primaryConstructors returns primary constructors from all declarations`() {
        // given
        val primaryConstructor1: KoPrimaryConstructorDeclaration = mockk()
        val primaryConstructor2: KoPrimaryConstructorDeclaration = mockk()
        val declaration1: KoPrimaryConstructorProvider =
            mockk {
                every { primaryConstructor } returns primaryConstructor1
            }
        val declaration2: KoPrimaryConstructorProvider =
            mockk {
                every { primaryConstructor } returns primaryConstructor2
            }
        val declaration3: KoPrimaryConstructorProvider =
            mockk {
                every { primaryConstructor } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.primaryConstructors

        // then
        sut shouldBeEqualTo listOf(primaryConstructor1, primaryConstructor2)
    }

    @Test
    fun `withPrimaryConstructor() returns declaration with primary constructor`() {
        // given
        val declaration1: KoPrimaryConstructorProvider =
            mockk {
                every { hasPrimaryConstructor } returns true
            }
        val declaration2: KoPrimaryConstructorProvider =
            mockk {
                every { hasPrimaryConstructor } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPrimaryConstructor()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPrimaryConstructor() returns declaration without primary constructor`() {
        // given
        val declaration1: KoPrimaryConstructorProvider =
            mockk {
                every { hasPrimaryConstructor } returns true
            }
        val declaration2: KoPrimaryConstructorProvider =
            mockk {
                every { hasPrimaryConstructor } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPrimaryConstructor()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
