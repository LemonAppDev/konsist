package com.lemonappdev.konsist.api.provider

interface KoParentProvider : KoBaseProvider {
    val parent: KoParentProvider?
}
