package com.lemonappdev.konsist.core

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.sequence.print
import com.lemonappdev.konsist.api.ext.sequence.withNamePrefix
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class APrintDeclaration {
    @Test
    fun `return value after printDeclaration() is equal to original value`() {
        // given
        val scope = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.scope.koscope")
        val sut = scope
            .classes()
            .withNamePrefix("KoScopeForC")

        // then
        sut.print() shouldBeEqualTo sut
    }
}
