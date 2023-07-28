package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionProviderListExtTest {
    @Test
    fun `functions() returns functions from all declarations`() {
        // given
        val function1: KoFunctionDeclarationImpl = mockk()
        val function2: KoFunctionDeclarationImpl = mockk()
        val function3: KoFunctionDeclarationImpl = mockk()
        val declaration1: KoFunctionProvider = mockk {
            every { functions(includeNested = true, includeLocal = false) } returns listOf(
                function1,
                function2,
            )
        }
        val declaration2: KoFunctionProvider = mockk {
            every { functions(includeNested = true, includeLocal = false) } returns listOf(function3)
        }
        val declaration3: KoFunctionProvider = mockk {
            every { functions(includeNested = true, includeLocal = false) } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.functions(includeNested = true, includeLocal = false)

        // then
        sut.toList() shouldBeEqualTo listOf(function1, function2, function3)
    }
}
