package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoLocalClassProviderListExtTest {
    @Test
    fun `localClasses returns local classes from all declarations`() {
        // given
        val localClass1: KoClassDeclaration = mockk()
        val localClass2: KoClassDeclaration = mockk()
        val localClass3: KoClassDeclaration = mockk()
        val declaration1: KoLocalClassProvider = mockk {
            every { localClasses } returns listOf(localClass1, localClass2)
        }
        val declaration2: KoLocalClassProvider = mockk {
            every { localClasses } returns listOf(localClass3)
        }
        val declaration3: KoLocalClassProvider = mockk {
            every { localClasses } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.localClasses

        // then
        sut shouldBeEqualTo listOf(localClass1, localClass2, localClass3)
    }
}
