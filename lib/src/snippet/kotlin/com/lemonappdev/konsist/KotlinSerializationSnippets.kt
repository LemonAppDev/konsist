package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.enumConstants
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withEnumModifier
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.withAnnotationOf
import com.lemonappdev.konsist.api.verify.assertTrue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class KotlinSerializationSnippets {
    fun `classes annotated with 'Serializable' have all properties annotated with 'SerialName'`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAnnotationOf(Serializable::class)
            .properties()
            .assertTrue {
                it.hasAnnotationOf(SerialName::class)
            }
    }

    fun `enum classes annotated with 'Serializable' have all enum constants annotated with 'SerialName'`() {
        Konsist.scopeFromProject()
            .classes()
            .withEnumModifier()
            .withAnnotationOf(Serializable::class)
            .enumConstants
            .assertTrue { it.hasAnnotationOf(SerialName::class) }
    }

    fun `all models are serializable`() {
        Konsist
            .scopeFromPackage("story.model")
            .classes()
            .assertTrue {
                it.hasAnnotationOf(Serializable::class)
            }
    }
}
