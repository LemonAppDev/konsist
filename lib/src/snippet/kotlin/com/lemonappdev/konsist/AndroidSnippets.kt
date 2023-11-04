package com.lemonappdev.konsist

import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.withAnnotationOf
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withParentClassOf
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

class AndroidSnippets {
    @Test
    fun `classes extending 'ViewModel' should have 'ViewModel' suffix`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withParentClassOf(ViewModel::class)
            .assertTrue { it.name.endsWith("ViewModel") }
    }

    @Test
    fun `Every 'ViewModel' public property has 'Flow' type`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withParentClassOf(ViewModel::class)
            .properties()
            .assertTrue {
                it.hasPublicOrDefaultModifier && it.hasType { type -> type.name == "kotlinx.coroutines.flow.Flow" }
            }
    }

    @Test
    fun `'Repository' classes should reside in 'repository' package`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("Repository")
            .assertTrue { it.resideInPackage("..repository..") }
    }

    @Test
    fun `no class should use Android util logging`() {
        Konsist
            .scopeFromProject()
            .files
            .assertFalse { it.hasImport { import -> import.name == "android.util.Log" } }
    }

    @Test
    fun `All JetPack Compose previews contain 'Preview' in method name`() {
        Konsist
            .scopeFromProject()
            .functions()
            .withAnnotationOf(Preview::class)
            .assertTrue {
                it.name.contains("Preview")
            }
    }
}
