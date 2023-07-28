<<<<<<<< HEAD:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/list/KoWildcardProviderListExtTest.kt
package com.lemonappdev.konsist.api.ext.list
========
package com.lemonappdev.konsist.api.ext.sequence
>>>>>>>> main:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/sequence/KoWildcardProviderSequenceExtTest.kt

import com.lemonappdev.konsist.api.provider.KoWildcardProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

<<<<<<<< HEAD:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/list/KoWildcardProviderListExtTest.kt
class KoWildcardProviderListExtTest {
========
class KoWildcardProviderSequenceExtTest {
>>>>>>>> main:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/sequence/KoWildcardProviderSequenceExtTest.kt
    @Test
    fun `withWildcard() returns import with wildcard`() {
        // given
        val import1: KoWildcardProvider = mockk {
            every { isWildcard } returns true
        }
        val import2: KoWildcardProvider = mockk {
            every { isWildcard } returns false
        }
        val imports = listOf(import1, import2)

        // when
        val sut = imports.withWildcard()

        // then
        sut.toList() shouldBeEqualTo listOf(import1)
    }

    @Test
    fun `withoutWildcard() returns import without wildcard`() {
        // given
        val import1: KoWildcardProvider = mockk {
            every { isWildcard } returns true
        }
        val import2: KoWildcardProvider = mockk {
            every { isWildcard } returns false
        }
        val imports = listOf(import1, import2)

        // when
        val sut = imports.withoutWildcard()

        // then
        sut.toList() shouldBeEqualTo listOf(import2)
    }
}
