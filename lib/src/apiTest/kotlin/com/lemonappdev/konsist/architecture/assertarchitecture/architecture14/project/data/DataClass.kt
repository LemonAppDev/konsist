@file:Suppress("detekt.WildcardImport", "ktlint:standard:no-wildcard-imports")

package com.lemonappdev.konsist.architecture.assertarchitecture.architecture14.project.data

import com.lemonappdev.konsist.architecture.assertarchitecture.architecture14.project.domain.*
import com.lemonappdev.konsist.architecture.assertarchitecture.architecture14.project.domain.DomainClass.Nested
import com.lemonappdev.konsist.architecture.assertarchitecture.architecture14.project.domain.domainFunction

class DataClass(
    val domainClass: DomainClass,
    val nested: Nested,
) {
    fun call() = domainFunction()
}
