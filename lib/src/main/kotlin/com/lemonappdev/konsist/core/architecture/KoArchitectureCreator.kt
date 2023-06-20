package com.lemonappdev.konsist.core.architecture

open class KoArchitectureCreator {
    fun architecture(vararg layers: Layer) : KoArchitectureImpl = KoArchitectureImpl(*layers)
}
