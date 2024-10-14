package com.lemonappdev.konsist.core

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.isSortedByName
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withAllModifiers
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

class EnumKonsistTest {
    @Test
    fun `enums consts are defined interface alphabetical order `() {
        Konsist
            .scopeFromProduction()
            .classes()
            .withAllModifiers(KoModifier.ENUM)
            .assertTrue {
                it.enumConstants.isSortedByName()
            }
    }
}
