package com.lemonappdev.konsist.core.verify.assert

import com.lemonappdev.konsist.core.declaration.KoScope
import com.lemonappdev.konsist.core.ext.withNamePrefix
import com.lemonappdev.konsist.core.verify.printDeclarations
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class PrintDeclaration {
    @Test
    fun `return value after printDeclaration() is equal to original value`() {
        // given
        val scope = KoScope.fromPackage("com.lemonappdev.konsist.core.declaration.koscope")
        val sut = scope
            .classes()
            .withNamePrefix("KoScopeForC")

        // then
        sut.printDeclarations() shouldBeEqualTo sut
    }
}
