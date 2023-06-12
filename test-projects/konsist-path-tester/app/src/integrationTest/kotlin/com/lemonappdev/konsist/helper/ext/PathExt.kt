package com.lemonappdev.konsist.helper.ext

import java.io.File
import java.nio.file.Paths

val sep = File.separator

fun String.toCanonicalPaths(): String = replace("/", File.separator)

fun List<String>.toCanonicalPaths(): List<String> = map { it.replace("/", File.separator) }
