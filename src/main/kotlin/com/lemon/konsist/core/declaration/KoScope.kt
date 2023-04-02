package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.const.KoModifier

class KoScope(
    private val koFiles: List<KoFile>,
) {
    constructor(koFile: KoFile) : this(listOf(koFile))

    fun files() = koFiles

    fun classes(modifiers: List<KoModifier> = emptyList(), includeNested: Boolean = false) =
        koFiles.flatMap { it.classes(modifiers, includeNested) }

    fun interfaces(modifiers: List<KoModifier> = emptyList(), includeNested: Boolean = false) =
        koFiles.flatMap { it.interfaces(modifiers, includeNested) }

    fun objects(modifiers: List<KoModifier> = emptyList(), includeNested: Boolean = false) =
        koFiles.flatMap { it.objects(modifiers, includeNested) }

    fun functions(modifiers: List<KoModifier> = emptyList(), includeNested: Boolean = false) =
        koFiles.flatMap { it.functions(modifiers, includeNested) }

    fun declarations(modifiers: List<KoModifier> = emptyList(), includeNested: Boolean = false) =
        koFiles.flatMap { it.declarations(modifiers, includeNested) }

    fun properties(modifiers: List<KoModifier> = emptyList(), includeNested: Boolean = false) =
        koFiles.flatMap { it.properties(modifiers, includeNested) }

    fun imports() = koFiles.flatMap { it.imports }

    fun packages() = koFiles.map { it.packageDirective }
}
