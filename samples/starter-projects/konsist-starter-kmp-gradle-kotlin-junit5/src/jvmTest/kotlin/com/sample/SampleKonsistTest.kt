package com.sample

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assert
import org.junit.jupiter.api.Test

class SampleKonsistTest {
    @Test
    fun `classes with 'UseCase' suffix should reside in 'domain' and 'usecase' package`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .assert { it.resideInPackage("..domain..") }
    }
}
