package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoContainingDeclarationProviderListExtTest {
    @Test
    fun `containingDeclarations returns containing declarations from all declarations`() {
        // given
        val containingDeclaration1: KoClassDeclaration = mockk()
        val containingDeclaration2: KoInterfaceDeclaration = mockk()
        val declaration1: KoContainingDeclarationProvider =
            mockk {
                every { containingDeclaration } returns containingDeclaration1
            }
        val declaration2: KoContainingDeclarationProvider =
            mockk {
                every { containingDeclaration } returns containingDeclaration2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.containingDeclarations

        // then
        sut shouldBeEqualTo listOf(containingDeclaration1, containingDeclaration2)
    }
}
