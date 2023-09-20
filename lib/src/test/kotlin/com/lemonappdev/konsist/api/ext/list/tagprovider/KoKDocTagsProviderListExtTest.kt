package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.ext.list.tagprovider.authorTags
import com.lemonappdev.konsist.api.ext.list.tagprovider.exceptionTags
import com.lemonappdev.konsist.api.ext.list.tagprovider.paramTags
import com.lemonappdev.konsist.api.ext.list.tagprovider.propertyTags
import com.lemonappdev.konsist.api.ext.list.tagprovider.sampleTags
import com.lemonappdev.konsist.api.ext.list.tagprovider.seeTags
import com.lemonappdev.konsist.api.ext.list.tagprovider.tags
import com.lemonappdev.konsist.api.ext.list.tagprovider.throwsTags
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagsProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocTagsProviderListExtTest {
    @Test
    fun `tags returns tags from all declarations`() {
        // given
        val tag1: KoKDocTagDeclaration = mockk()
        val tag2: KoKDocTagDeclaration = mockk()
        val tag3: KoKDocTagDeclaration = mockk()
        val declaration1: KoKDocTagsProvider = mockk {
            every { tags } returns listOf(tag1, tag2)
        }
        val declaration2: KoKDocTagsProvider = mockk {
            every { tags } returns listOf(tag3)
        }
        val declaration3: KoKDocTagsProvider = mockk {
            every { tags } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.tags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }
}
