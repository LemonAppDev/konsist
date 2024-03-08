package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyProviderExtTest {
    @Test
    fun `hasValidKDocPropertyTags() returns false when declaration not implement KoKDocProvider`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val property1: KoPropertyDeclaration =
            mockk {
                every { name } returns name1
            }
        val property2: KoPropertyDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration: KoPropertyProvider =
            mockk {
                every { properties() } returns listOf(property1, property2)
            }

        // when
        val sut = declaration.hasValidKDocPropertyTags()

        // then
        sut shouldBeEqualTo false
    }
}
