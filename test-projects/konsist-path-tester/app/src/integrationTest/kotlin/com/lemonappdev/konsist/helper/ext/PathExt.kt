package com.lemonappdev.konsist.helper.ext

import java.io.File
import java.nio.file.Paths

val sep = File.separator

fun String.toCanonicalPaths(): String = Paths.get(this).toString()

fun List<String>.toCanonicalPaths(): List<String> = map { Paths.get(it).toString() }
