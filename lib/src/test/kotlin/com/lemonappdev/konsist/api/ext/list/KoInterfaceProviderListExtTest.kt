package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoInterfaceProvider
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceProviderListExtTest {
    @Test
    fun `interfaces() returns interfaces from all declarations`() {
        // given
        val interface1: KoInterfaceDeclarationImpl = mockk()
        val interface2: KoInterfaceDeclarationImpl = mockk()
        val interface3: KoInterfaceDeclarationImpl = mockk()
        val declaration1: KoInterfaceProvider = mockk {
            every { interfaces(includeNested = true) } returns listOf(interface1, interface2)
        }
        val declaration2: KoInterfaceProvider = mockk {
            every { interfaces(includeNested = true) } returns listOf(interface3)
        }
        val declaration3: KoInterfaceProvider = mockk {
            every { interfaces(includeNested = true) } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.interfaces(includeNested = true)

        // then
        sut shouldBeEqualTo listOf(interface1, interface2, interface3)
    }
}
