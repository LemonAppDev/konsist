package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageProviderListExtTest {
    @Test
    fun `packages returns packages from all declarations`() {
        // given
        val packagee1: KoPackageDeclaration = mockk()
        val packagee2: KoPackageDeclaration = mockk()
        val declaration1: KoPackageProvider =
            mockk {
                every { packagee } returns packagee1
            }
        val declaration2: KoPackageProvider =
            mockk {
                every { packagee } returns packagee2
            }
        val declaration3: KoPackageProvider =
            mockk {
                every { packagee } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.packages

        // then
        sut shouldBeEqualTo listOf(packagee1, packagee2)
    }
}
