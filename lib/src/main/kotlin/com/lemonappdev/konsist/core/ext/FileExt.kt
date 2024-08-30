package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.util.FileExtension
import com.lemonappdev.konsist.core.util.KotlinFileParser
import java.io.File

internal val File.isKotlinFile: Boolean get() = isFile && name.endsWith(FileExtension.KOTLIN)

internal val File.isKotlinSnippetFile: Boolean get() = isFile && name.endsWith(FileExtension.KOTLIN_SNIPPET)

internal fun File.toKoFile(): KoFileDeclaration = KotlinFileParser.getKoFile(this)
