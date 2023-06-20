package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.verify.assert


fun main() {
    val main = Layer("Main", "..main..")                       // name is necessary now?
    val api = Layer("Api", "..api..")
    val core = Layer("Core", "..core..")

    val koArchitecture = Konsist
        .architecture(main, api, core)
        .addDependencies {
            main.dependsOn(api, core)
            api.notDependOnAnyLayer()
            core.notDependOnAnyLayer()
        }

    println(koArchitecture.dependencies)

    println(
        Konsist.scopeFromProduction()
            .assert(koArchitecture)
    )
}
