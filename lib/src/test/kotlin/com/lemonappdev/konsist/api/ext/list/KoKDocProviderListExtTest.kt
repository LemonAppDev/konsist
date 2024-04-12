package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.core.declaration.KoKDocDeclarationCore
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocProviderListExtTest {
    @Test
    fun `kDocs returns kDocs from all declarations`() {
        // given
        val kDoc1: KoKDocDeclaration = mockk()
        val kDoc2: KoKDocDeclaration = mockk()
        val declaration1: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc1
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc2
            }
        val declaration3: KoKDocProvider =
            mockk {
                every { kDoc } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.kDocs

        // then
        sut shouldBeEqualTo listOf(kDoc1, kDoc2)
    }

    @Test
    fun `withKDoc() returns declaration with any kDoc`() {
        // given
        val declaration1: KoKDocProvider =
            mockk {
                every { hasKDoc } returns true
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { hasKDoc } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKDoc()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutKDoc() returns declaration without any kDoc`() {
        // given
        val declaration1: KoKDocProvider =
            mockk {
                every { hasKDoc } returns true
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { hasKDoc } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKDoc()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
