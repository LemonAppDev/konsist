package com.lemonappdev.konsist.architecture.assertarchitecture.architecture10.project.presentation

import com.lemonappdev.konsist.architecture.assertarchitecture.architecture10.project.data.DataClass
import com.lemonappdev.konsist.architecture.assertarchitecture.architecture10.project.domain.DomainClass
import java.io.File

class PresentationClass(
    val dataClass: DataClass,
    val domainClass: DomainClass,
    val file: File,
)
