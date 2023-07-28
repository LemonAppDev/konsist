<<<<<<<< HEAD:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/list/KoTopLevelProviderListExtTest.kt
package com.lemonappdev.konsist.api.ext.list
========
package com.lemonappdev.konsist.api.ext.sequence
>>>>>>>> main:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/sequence/KoTopLevelProviderSequenceExtTest.kt

import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

<<<<<<<< HEAD:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/list/KoTopLevelProviderListExtTest.kt
class KoTopLevelProviderListExtTest {
========
class KoTopLevelProviderSequenceExtTest {
>>>>>>>> main:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/sequence/KoTopLevelProviderSequenceExtTest.kt
    @Test
    fun `withTopLevel() returns declaration which is top level declaration`() {
        // given
        val declaration1: KoTopLevelProvider = mockk {
            every { isTopLevel() } returns true
        }
        val declaration2: KoTopLevelProvider = mockk {
            every { isTopLevel() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTopLevel()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTopLevel() returns declaration which is not top level declaration`() {
        // given
        val declaration1: KoTopLevelProvider = mockk {
            every { isTopLevel() } returns true
        }
        val declaration2: KoTopLevelProvider = mockk {
            every { isTopLevel() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTopLevel()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }
}
