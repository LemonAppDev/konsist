package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocTagProviderListExtTest {
    @Test
    fun `tags returns tags from all declarations`() {
        // given
        val tag1: KoKDocTagDeclaration = mockk()
        val tag2: KoKDocTagDeclaration = mockk()
        val tag3: KoKDocTagDeclaration = mockk()
        val declaration1: KoKDocTagProvider = mockk {
            every { tags } returns listOf(tag1, tag2)
        }
        val declaration2: KoKDocTagProvider = mockk {
            every { tags } returns listOf(tag3)
        }
        val declaration3: KoKDocTagProvider = mockk {
            every { tags } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.tags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }
}
