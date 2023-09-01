package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider
import com.lemonappdev.konsist.api.provider.KoLocalDeclarationProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoLocalDeclarationProviderListExtTest {
    @Test
    fun `localDeclarations returns local declarations from all declarations`() {
        // given
        val localDeclaration1: KoBaseDeclaration = mockk()
        val localDeclaration2: KoBaseDeclaration = mockk()
        val localDeclaration3: KoBaseDeclaration = mockk()
        val declaration1: KoLocalDeclarationProvider = mockk {
            every { localDeclarations } returns listOf(localDeclaration1, localDeclaration2)
        }
        val declaration2: KoLocalDeclarationProvider = mockk {
            every { localDeclarations } returns listOf(localDeclaration3)
        }
        val declaration3: KoLocalDeclarationProvider = mockk {
            every { localDeclarations } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.localDeclarations

        // then
        sut shouldBeEqualTo listOf(localDeclaration1, localDeclaration2, localDeclaration3)
    }
}
