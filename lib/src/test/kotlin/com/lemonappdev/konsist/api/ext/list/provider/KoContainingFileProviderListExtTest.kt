package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoContainingFileProviderListExtTest {
    @Test
    fun `containingFiles returns containing files from all declarations`() {
        // given
        val containingFile1: KoFileDeclaration = mockk()
        val containingFile2: KoFileDeclaration = mockk()
        val declaration1: KoContainingFileProvider =
            mockk {
                every { containingFile } returns containingFile1
            }
        val declaration2: KoContainingFileProvider =
            mockk {
                every { containingFile } returns containingFile2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.containingFiles

        // then
        sut shouldBeEqualTo listOf(containingFile1, containingFile2)
    }
}
