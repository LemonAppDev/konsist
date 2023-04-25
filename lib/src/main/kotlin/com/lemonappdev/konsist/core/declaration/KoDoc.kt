package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.kdoc.psi.api.KDocElement

class KoDoc(private val kDocElement: KDocElement) {
    val text: String by lazy { kDocElement.text }
}
