package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoInterfaceProvider
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceProviderSequenceExtTest {
    @Test
    fun `interfaces() returns interfaces from all declarations`() {
        // given
        val interface1: KoInterfaceDeclarationImpl = mockk()
        val interface2: KoInterfaceDeclarationImpl = mockk()
        val interface3: KoInterfaceDeclarationImpl = mockk()
        val declaration1: KoInterfaceProvider = mockk {
            every { interfaces(includeNested = true) } returns sequenceOf(interface1, interface2)
        }
        val declaration2: KoInterfaceProvider = mockk {
            every { interfaces(includeNested = true) } returns sequenceOf(interface3)
        }
        val declaration3: KoInterfaceProvider = mockk {
            every { interfaces(includeNested = true) } returns emptySequence()
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.interfaces(includeNested = true)

        // then
        sut.toList() shouldBeEqualTo listOf(interface1, interface2, interface3)
    }
}
