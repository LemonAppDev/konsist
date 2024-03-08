package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoConstructorProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorProviderExtTest {
    @Test
    fun `hasValidKDocParamTags() returns false when declaration is not KoKDocProvider`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val parameter1: KoParameterDeclaration =
            mockk {
                every { name } returns name1
            }
        val parameter2: KoParameterDeclaration =
            mockk {
                every { name } returns name2
            }
        val constructor: KoPrimaryConstructorDeclaration =
            mockk {
                every { parameters } returns listOf(parameter1, parameter2)
                every { containingDeclaration } returns mockk()
            }
        val declaration: KoConstructorProvider =
            mockk {
                every { constructors } returns listOf(constructor)
            }

        // when
        val sut = declaration.hasValidKDocParamTags()

        // then
        sut shouldBeEqualTo false
    }
}
