package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.verify.assert


fun main() {
    val service = Layer("Service", "..service..")                       // name is necessary now?
    val controller = Layer("Controller", "..controller..")
    val persistence = Layer("Persistence", "..persistence..")

    val koArchitecture = Konsist
        .architecture(service, controller, persistence)
        .addDependencies {
            service.dependsOn(controller, persistence)
            controller.dependsOnAllLayers()
            persistence.notDependOnAnyLayer()

        }

    println(koArchitecture.dependencies)

    Konsist.scopeFromProduction()
        .assert(koArchitecture)
}
