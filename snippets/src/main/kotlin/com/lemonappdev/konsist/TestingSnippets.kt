package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.sequence.withoutModifiers
import com.lemonappdev.konsist.core.verify.assert

class TestingSnippets {
    fun `every class has test`(){
        Konsist.scopeFromProject()
            .classes()
            .assert { it.hasTest() }
    }

    fun `every class - except data and value class - has test`(){
        Konsist.scopeFromProject()
            .classes()
            .withoutModifiers(KoModifier.DATA, KoModifier.VALUE)
            .assert { it.hasTest() }
    }
}
