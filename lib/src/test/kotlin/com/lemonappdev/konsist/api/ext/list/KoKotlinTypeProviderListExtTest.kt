<<<<<<<< HEAD:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/list/KoKotlinTypeProviderListExtTest.kt
package com.lemonappdev.konsist.api.ext.list
========
package com.lemonappdev.konsist.api.ext.sequence
>>>>>>>> main:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/sequence/KoKotlinTypeProviderSequenceExtTest.kt

import com.lemonappdev.konsist.api.provider.KoKotlinTypeProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

<<<<<<<< HEAD:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/list/KoKotlinTypeProviderListExtTest.kt
class KoKotlinTypeProviderListExtTest {
========
class KoKotlinTypeProviderSequenceExtTest {
>>>>>>>> main:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/sequence/KoKotlinTypeProviderSequenceExtTest.kt
    @Test
    fun `withKotlinType() returns type with Kotlin basic type`() {
        // given
        val type1: KoKotlinTypeProvider = mockk {
            every { isKotlinType } returns true
        }
        val type2: KoKotlinTypeProvider = mockk {
            every { isKotlinType } returns false
        }
        val types = listOf(type1, type2)

        // when
        val sut = types.withKotlinType()

        // then
        sut.toList() shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutKotlinType() returns type without Kotlin basic type`() {
        // given
        val type1: KoKotlinTypeProvider = mockk {
            every { isKotlinType } returns true
        }
        val type2: KoKotlinTypeProvider = mockk {
            every { isKotlinType } returns false
        }
        val types = listOf(type1, type2)

        // when
        val sut = types.withoutKotlinType()

        // then
        sut.toList() shouldBeEqualTo listOf(type2)
    }
}
