package com.lemonappdev.konsist

import androidx.lifecycle.ViewModel
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withAllParentsOf
import com.lemonappdev.konsist.api.verify.assert
import com.lemonappdev.konsist.api.verify.assertNot

class AndroidSnippets {
    fun `classes extending 'ViewModel' should have 'ViewModel' suffix`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAllParentsOf(ViewModel::class)
            .assert { it.name.endsWith("ViewModel") }
    }

    fun `no class should use Android util logging`() {
        Konsist
            .scopeFromProject()
            .files
            .assertNot { it.hasImports("android.util.Log") }
    }
}
