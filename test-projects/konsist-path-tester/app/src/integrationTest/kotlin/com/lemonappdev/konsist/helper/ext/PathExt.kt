package com.lemonappdev.konsist.helper.ext

import java.io.File
import java.nio.file.Paths

val fileSeparator: String = File.separator

fun String.toCanonicalPath(): String = replace("/", File.separator)

fun List<String>.toCanonicalPath(): List<String> = map { it.replace("/", File.separator) }
