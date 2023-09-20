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
import com.lemonappdev.konsist.api.provider.tag.KoKDocExceptionTagProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocExceptionTagProviderListExtTest {
    @Test
    fun `exceptionTags returns exception tags from all declarations`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk()
        val tag2: KoValuedKDocTagDeclaration = mockk()
        val tag3: KoValuedKDocTagDeclaration = mockk()
        val declaration1: KoKDocExceptionTagProvider = mockk {
            every { exceptionTags } returns listOf(tag1, tag2)
        }
        val declaration2: KoKDocExceptionTagProvider = mockk {
            every { exceptionTags } returns listOf(tag3)
        }
        val declaration3: KoKDocExceptionTagProvider = mockk {
            every { exceptionTags } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.exceptionTags

        // then
        sut shouldBeEqualTo listOf(tag1, tag2, tag3)
    }
}
