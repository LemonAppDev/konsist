package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.withSomeAnnotationsOf
import com.lemonappdev.konsist.api.verify.assert
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class KotlinSerializationSnippets {
    fun `classes annotated with Serializable have all properties annotated with SerialName`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withSomeAnnotationsOf(Serializable::class)
            .properties()
            .assert {
                it.hasAnnotationsOf(SerialName::class)
            }
    }
}
