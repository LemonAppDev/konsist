package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.core.util.KotlinFileParser
import com.lemonappdev.konsist.core.util.KotlinFileParser.KOTLIN_FILE_EXTENSION
import com.lemonappdev.konsist.core.util.KotlinFileParser.KOTLIN_SNIPPET_FILE_EXTENSION
import java.io.File

internal val File.isKotlinFile: Boolean get() = isFile && name.endsWith(KOTLIN_FILE_EXTENSION)

internal val File.isKotlinSnippetFile: Boolean get() = isFile && name.endsWith(KOTLIN_SNIPPET_FILE_EXTENSION)

internal fun File.toKoFile(): KoFile = KotlinFileParser.getKoFile(this)
