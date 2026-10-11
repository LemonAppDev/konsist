package com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.presentation

/**
 * KDoc reference [com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.data.DataClass] is not a dependency.
 */
class PresentationClass(
    val domainClass: com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.domain.DomainClass,
) {
    val dataClass =
        com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.data
            .DataClass()

    fun call() =
        com.lemonappdev.konsist.architecture.assertarchitecture.architecture11.project.data
            .dataFunction()

    fun count(data: List<String>) = data.size
}
