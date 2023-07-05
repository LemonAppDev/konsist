package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer

class ArchitectureSnippets {
    fun `2 layer architecture has correct dependencies`() {
        Konsist
            .scopeFromProject()
            .assertArchitecture {
                val presentation = Layer("Presentation", "com.myapp.presentation..")
                val data = Layer("Data", "com.myapp.data..")

                presentation.dependsOn(data)
                data.dependsOnNothing()
            }
    }
}
