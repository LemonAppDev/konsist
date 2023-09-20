package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocThrowsTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocThrowsTagProviderListExtTest {
    @Test
    fun `throwsTags returns throws tags from all declarations`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk()
        val tag2: KoValuedKDocTagDeclaration = mockk()
        val tag3: KoValuedKDocTagDeclaration = mockk()
        val declaration1: KoKDocThrowsTagProvider = mockk {
            every { throwsTags } returns listOf(tag1, tag2)
        }
        val declaration2: KoKDocThrowsTagProvider = mockk {
            every { throwsTags } returns listOf(tag3)
        }
        val declaration3: KoKDocThrowsTagProvider = mockk {
            every { throwsTags } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.throwsTags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }
}
