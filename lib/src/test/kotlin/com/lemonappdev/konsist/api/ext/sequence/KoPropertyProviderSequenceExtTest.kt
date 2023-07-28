package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyProviderSequenceExtTest {
    @Test
    fun `properties() returns properties from all declarations`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk()
        val property2: KoPropertyDeclarationImpl = mockk()
        val property3: KoPropertyDeclarationImpl = mockk()
        val declaration1: KoPropertyProvider = mockk {
            every { properties(includeNested = true, includeLocal = false) } returns sequenceOf(property1, property2)
        }
        val declaration2: KoPropertyProvider = mockk {
            every { properties(includeNested = true, includeLocal = false) } returns sequenceOf(property3)
        }
        val declaration3: KoPropertyProvider = mockk {
            every { properties(includeNested = true, includeLocal = false) } returns emptySequence()
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.properties(includeNested = true, includeLocal = false)

        // then
        sut.toList() shouldBeEqualTo listOf(property1, property2, property3)
    }
}
