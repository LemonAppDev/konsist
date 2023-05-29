package com.lemonappdev.konsist.helper.ext

import java.io.File

val sep = File.separator

fun String.toNormalizedPath(): String = replace("/", File.separator)

fun List<String>.toNormalizedPaths(): List<String> = map { it.replace("/", File.separator) }
