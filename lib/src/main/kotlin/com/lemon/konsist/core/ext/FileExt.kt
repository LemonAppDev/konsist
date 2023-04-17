package com.lemon.konsist.core.ext

import com.lemon.konsist.util.KotlinFileParser
import com.lemon.konsist.util.KotlinFileParser.KOTLIN_FILE_EXTENSION
import com.lemon.konsist.util.KotlinFileParser.KOTLIN_SNIPPET_FILE_EXTENSION
import java.io.File

internal val File.isKotlinFile get() = isFile && name.endsWith(KOTLIN_FILE_EXTENSION)

internal val File.isKotlinSnippetFile get() = isFile && name.endsWith(KOTLIN_SNIPPET_FILE_EXTENSION)

internal fun File.toKoFile() = KotlinFileParser.getKonsistFile(this)
