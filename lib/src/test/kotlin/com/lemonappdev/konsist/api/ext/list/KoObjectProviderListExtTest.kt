package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoObjectProvider
import com.lemonappdev.konsist.core.declaration.KoObjectDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectProviderListExtTest {
    @Test
    fun `objects() returns objects from all declarations`() {
        // given
        val object1: KoObjectDeclarationImpl = mockk()
        val object2: KoObjectDeclarationImpl = mockk()
        val object3: KoObjectDeclarationImpl = mockk()
        val declaration1: KoObjectProvider = mockk {
            every { objects(includeNested = true) } returns listOf(object1, object2)
        }
        val declaration2: KoObjectProvider = mockk {
            every { objects(includeNested = true) } returns listOf(object3)
        }
        val declaration3: KoObjectProvider = mockk {
            every { objects(includeNested = true) } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.objects(includeNested = true)

        // then
        sut shouldBeEqualTo listOf(object1, object2, object3)
    }
}
