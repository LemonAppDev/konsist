package com.lemonappdev.konsist

import androidx.lifecycle.ViewModel
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withParentClassOf
import com.lemonappdev.konsist.core.verify.assert

class AndroidSnippets {
    fun `classes extending 'ViewModel' should have 'ViewModel' suffix`() {
        Konsist.scopeFromProject()
            .classes()
//            .withParentClassOf<ViewModel>()
            .withParentClassOf(ViewModel::class)
            .assert { it.resideInPackage("..controller..") }
    }
}
