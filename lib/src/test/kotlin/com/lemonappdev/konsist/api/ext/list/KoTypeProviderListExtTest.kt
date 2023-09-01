package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeProviderListExtTest {
    @Test
    fun `types returns types from all declarations`() {
        // given
        val type1: KoTypeDeclaration = mockk()
        val type2: KoTypeDeclaration = mockk()
        val declaration1: KoTypeProvider = mockk {
            every { type } returns type1
        }
        val declaration2: KoTypeProvider = mockk {
            every { type } returns type2
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.types

        // then
        sut shouldBeEqualTo listOf(type1, type2)
    }
}
