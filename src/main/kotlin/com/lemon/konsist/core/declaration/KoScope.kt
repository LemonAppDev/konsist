package com.lemon.konsist.core.declaration

class KoScope(
    private val koFiles: List<KoFile>,
) {
    constructor(koFile: KoFile) : this(listOf(koFile))

    fun files() = koFiles

    fun classes(includeNested: Boolean = false) = koFiles.flatMap { it.classes(includeNested) }

    fun interfaces(includeNested: Boolean = false) = koFiles.flatMap { it.interfaces(includeNested) }

    fun objects(includeNested: Boolean = false) = koFiles.flatMap { it.objects(includeNested) }

    fun functions(includeNested: Boolean = false) = koFiles.flatMap { it.functions(includeNested) }

    fun declarations(includeNested: Boolean = false) = koFiles.flatMap { it.declarations(includeNested) }

    fun properties(includeNested: Boolean = false) = koFiles.flatMap { it.properties(includeNested) }

    fun imports() = koFiles.flatMap { it.imports }

    fun packages() = koFiles.map { it.packageDirective }
}
