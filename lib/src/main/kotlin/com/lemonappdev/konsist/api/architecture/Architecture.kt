package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.architecture.KoArchitectureCreatorImpl
import com.lemonappdev.konsist.core.architecture.KoArchitectureImpl

object Architecture : KoArchitectureCreator by KoArchitectureCreatorImpl() {
    fun architecture(dependencies: KoArchitecture.() -> Unit): KoArchitecture {
        val koArchitecture = KoArchitectureImpl()
        dependencies(koArchitecture)
        return koArchitecture
    }
}
