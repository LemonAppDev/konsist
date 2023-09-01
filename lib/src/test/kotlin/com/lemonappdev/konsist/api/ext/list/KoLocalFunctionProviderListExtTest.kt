package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalFunctionProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoLocalFunctionProviderListExtTest {
    @Test
    fun `localFunctions returns local declarations from all declarations`() {
        // given
        val localFunction1: KoFunctionDeclaration = mockk()
        val localFunction2: KoFunctionDeclaration = mockk()
        val localFunction3: KoFunctionDeclaration = mockk()
        val declaration1: KoLocalFunctionProvider = mockk {
            every { localFunctions } returns listOf(localFunction1, localFunction2)
        }
        val declaration2: KoLocalFunctionProvider = mockk {
            every { localFunctions } returns listOf(localFunction3)
        }
        val declaration3: KoLocalFunctionProvider = mockk {
            every { localFunctions } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.localFunctions

        // then
        sut shouldBeEqualTo listOf(localFunction1, localFunction2, localFunction3)
    }
}
