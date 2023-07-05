package com.lemonappdev.konsist.declaration

import androidx.lifecycle.ViewModel
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.sequence.withParentClassOf
import com.lemonappdev.konsist.core.verify.assert

class AndroidSnippets {
    fun `classes extending 'ViewModel' should have 'ViewModel' suffix`() {
        Konsist.scopeFromProject()
            .classes()
            .withParentClassOf<ViewModel>()
            .assert { it.resideInPackage("..controller..") }
    }
}
