package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoSecondaryConstructorsProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoSecondaryConstructorsProviderListExtTest {
    @Test
    fun `secondaryConstructors returns secondary constructors from all declarations`() {
        // given
        val secondaryConstructor1: KoSecondaryConstructorDeclaration = mockk()
        val secondaryConstructor2: KoSecondaryConstructorDeclaration = mockk()
        val secondaryConstructor3: KoSecondaryConstructorDeclaration = mockk()
        val declaration1: KoSecondaryConstructorsProvider = mockk {
            every { secondaryConstructors } returns listOf(secondaryConstructor1, secondaryConstructor2)
        }
        val declaration2: KoSecondaryConstructorsProvider = mockk {
            every { secondaryConstructors } returns listOf(secondaryConstructor3)
        }
        val declaration3: KoSecondaryConstructorsProvider = mockk {
            every { secondaryConstructors } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.secondaryConstructors

        // then
        sut shouldBeEqualTo listOf(secondaryConstructor1, secondaryConstructor2, secondaryConstructor3)
    }

    @Test
    fun `withSecondaryConstructors() returns declaration with secondary constructor`() {
        // given
        val declaration1: KoSecondaryConstructorsProvider = mockk {
            every { hasSecondaryConstructors } returns true
        }
        val declaration2: KoSecondaryConstructorsProvider = mockk {
            every { hasSecondaryConstructors } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSecondaryConstructors()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSecondaryConstructors() returns declaration without secondary constructor`() {
        // given
        val declaration1: KoSecondaryConstructorsProvider = mockk {
            every { hasSecondaryConstructors } returns true
        }
        val declaration2: KoSecondaryConstructorsProvider = mockk {
            every { hasSecondaryConstructors } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSecondaryConstructors()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
