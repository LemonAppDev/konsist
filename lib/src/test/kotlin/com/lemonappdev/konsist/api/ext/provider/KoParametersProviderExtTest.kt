package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParametersProviderExtTest {
    @Test
    fun `hasValidKDocParamTags() returns false when declaration is not KoPrimaryConstructorDeclaration and KoKDocProvider`() {
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
        val declaration: KoParametersProvider =
            mockk {
                every { parameters } returns listOf(parameter1, parameter2)
            }

        // when
        val sut = declaration.hasValidKDocParamTags()

        // then
        sut shouldBeEqualTo false
    }
}
