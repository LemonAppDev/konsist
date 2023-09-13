package com.lemonappdev.konsist

import androidx.lifecycle.ViewModel
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.withParentOf
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assert
import com.lemonappdev.konsist.api.verify.assertNot

class AndroidSnippets {
    fun `classes extending 'ViewModel' should have 'ViewModel' suffix`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withParentOf(ViewModel::class)
            .assert { it.name.endsWith("ViewModel") }
    }

    fun `Every 'ViewModel' public property has 'Flow' type`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withParentOf(ViewModel::class)
            .properties()
            .assert {
                it.hasPublicOrDefaultModifier && it.hasType("kotlinx.coroutines.flow.Flow")
            }
    }

    fun `'Repository' classes should reside in 'repository' package`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("Repository")
            .assert { it.resideInPackage("..repository..") }
    }

    fun `no class should use Android util logging`() {
        Konsist
            .scopeFromProject()
            .files
            .assertNot { it.hasImport { import -> import.name == "android.util.Log" } }
    }
}
