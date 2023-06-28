package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.KoArchitecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator

open class KoArchitectureCreatorImpl : KoArchitectureCreator {
    override fun architecture(): KoArchitecture = KoArchitectureImpl()
}
