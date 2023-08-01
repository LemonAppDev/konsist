package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationProviderListExtTest {
    @Test
    fun `declarations() returns declarations from all declarations`() {
        // given
        val class1: KoClassDeclarationImpl = mockk()
        val function1: KoFunctionDeclarationImpl = mockk()
        val class2: KoClassDeclarationImpl = mockk()
        val interface1: KoInterfaceDeclarationImpl = mockk()
        val property1: KoPropertyDeclarationImpl = mockk()
        val declaration1: KoDeclarationProvider = mockk {
            every { declarations(includeNested = true, includeLocal = false) } returns listOf(class1, function1)
        }
        val declaration2: KoDeclarationProvider = mockk {
            every { declarations(includeNested = true, includeLocal = false) } returns listOf(class2, interface1)
        }
        val declaration3: KoDeclarationProvider = mockk {
            every { declarations(includeNested = true, includeLocal = false) } returns listOf(property1)
        }
        val declaration4: KoDeclarationProvider = mockk {
            every { declarations(includeNested = true, includeLocal = false) } returns emptyList()
        }
        val declarations = listOf(
            declaration1,
            declaration2,
            declaration3,
            declaration4,
        )

        // when
        val sut = declarations.declarations(includeNested = true, includeLocal = false)

        // then
        sut shouldBeEqualTo listOf(class1, function1, class2, interface1, property1)
    }
}
