package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationCore
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassProviderListExtTest {
    @Test
    fun `classes() returns classes from all declarations`() {
        // given
        val class1: KoClassDeclarationCore = mockk()
        val class2: KoClassDeclarationCore = mockk()
        val class3: KoClassDeclarationCore = mockk()
        val declaration1: KoClassProvider = mockk {
            every { classes(includeNested = true, includeLocal = false) } returns listOf(class1, class2)
        }
        val declaration2: KoClassProvider = mockk {
            every { classes(includeNested = true, includeLocal = false) } returns listOf(class3)
        }
        val declaration3: KoClassProvider = mockk {
            every { classes(includeNested = true, includeLocal = false) } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.classes(includeNested = true, includeLocal = false)

        // then
        sut shouldBeEqualTo listOf(class1, class2, class3)
    }
}
