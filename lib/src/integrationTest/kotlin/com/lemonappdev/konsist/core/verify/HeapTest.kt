package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.sequence.print
import com.lemonappdev.konsist.api.ext.sequence.withNamePrefix
import org.junit.jupiter.api.Test

class HeapTest {
    @Test
    fun `return value after printDeclaration() is equal to original value`() {
        List(100) {
            Konsist
                .scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
                .classes()
                .withNamePrefix("KoScopeForC")
                .print()
        }

//        // given
//        val scope = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
//        val sut = scope
//            .classes()
//            .withNamePrefix("KoScopeForC")
//
//        // then
//        sut.print() shouldBeEqualTo sut
    }
}
