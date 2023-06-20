package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.verify.assert


fun main() {
    val api = Layer("Api", "..api..")
    val core = Layer("Core", "..core..")

    val koArchitecture = Konsist
        .architecture(api, core)
        .addDependencies {
            api.dependsOn(core)
            core.dependsOn(api)
        }

    println(koArchitecture.dependencies)

    println(
        Konsist.scopeFromProduction()
            .assert(koArchitecture)
    )
}
